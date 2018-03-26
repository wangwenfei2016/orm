
package com.xymn.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;  

public class XLSX2CSV {
	
    enum xssfDataType {  
    	
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,  
    }  
    
    
    public XLSX2CSV(){}
  
   
    class XSSFSheetHandler extends DefaultHandler {  
  
         
        private StylesTable stylesTable;  
  
        
        private ReadOnlySharedStringsTable sharedStringsTable;  
  
         
        private final PrintStream output;  
  
         
        private final int minColumnCount;  
  
        
        private boolean vIsOpen;  
  
        
        private xssfDataType nextDataType;  
  
         
        private short formatIndex;  
        
        private String formatString;  
        
        private final DataFormatter formatter;  
  
        private int thisColumn = -1;  
         
        private int lastColumnNumber = -1;  
   
        private StringBuffer value; 
        
        private String[] record;  
        
        private List<String[]> rows = new ArrayList<String[]>();  
        
        private boolean isCellNull = false;  
  
         
        public XSSFSheetHandler(StylesTable styles,  
                ReadOnlySharedStringsTable strings, int cols, PrintStream target) {  
            this.stylesTable = styles;  
            this.sharedStringsTable = strings;  
            this.minColumnCount = cols;  
            this.output = target;  
            this.value = new StringBuffer();  
            this.nextDataType = xssfDataType.NUMBER;  
            this.formatter = new DataFormatter();  
            record = new String[this.minColumnCount];  
            rows.clear();// 每次读取都清空行集合  
        }  
  
       
        public void startElement(String uri, String localName, String name,  
                Attributes attributes) throws SAXException {  
  
            if ("inlineStr".equals(name) || "v".equals(name)) {  
                vIsOpen = true;  
                value.setLength(0);  
            }  
            else if ("c".equals(name)) {  
                String r = attributes.getValue("r");  
                int firstDigit = -1;  
                for (int c = 0; c < r.length(); ++c) {  
                    if (Character.isDigit(r.charAt(c))) {  
                        firstDigit = c;  
                        break;  
                    }  
                }  
                thisColumn = nameToColumn(r.substring(0, firstDigit));  
                this.nextDataType = xssfDataType.NUMBER;  
                this.formatIndex = -1;  
                this.formatString = null;  
                String cellType = attributes.getValue("t");  
                String cellStyleStr = attributes.getValue("s");  
                if ("b".equals(cellType))  
                    nextDataType = xssfDataType.BOOL;  
                else if ("e".equals(cellType))  
                    nextDataType = xssfDataType.ERROR;  
                else if ("inlineStr".equals(cellType))  
                    nextDataType = xssfDataType.INLINESTR;  
                else if ("s".equals(cellType))  
                    nextDataType = xssfDataType.SSTINDEX;  
                else if ("str".equals(cellType))  
                    nextDataType = xssfDataType.FORMULA;  
                else if (cellStyleStr != null) {  
                    int styleIndex = Integer.parseInt(cellStyleStr);  
                    XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);  
                    this.formatIndex = style.getDataFormat();  
                    this.formatString = style.getDataFormatString();  
                    if (this.formatString == null)  
                        this.formatString = BuiltinFormats  
                                .getBuiltinFormat(this.formatIndex);  
                }  
            }  
  
        }  
  
        public void endElement(String uri, String localName, String name)  
                throws SAXException {  
  
            String thisStr = null;  
            if ("v".equals(name)) {  
                switch (nextDataType) {  
  
                case BOOL:  
                    char first = value.charAt(0);  
                    thisStr = first == '0' ? "FALSE" : "TRUE";  
                    break;  
  
                case ERROR:  
                    thisStr = "\"ERROR:" + value.toString() + '"';  
                    break;  
  
                case FORMULA:  
                    thisStr = '"' + value.toString() + '"';  
                    break;  
  
                case INLINESTR:  
                    XSSFRichTextString rtsi = new XSSFRichTextString(  
                            value.toString());  
                    thisStr = '"' + rtsi.toString() + '"';  
                    break;  
  
                case SSTINDEX:  
                    String sstIndex = value.toString();  
                    try {  
                        int idx = Integer.parseInt(sstIndex);  
                        XSSFRichTextString rtss = new XSSFRichTextString(  
                                sharedStringsTable.getEntryAt(idx));  
                        thisStr = '"' + rtss.toString() + '"';  
                    } catch (NumberFormatException ex) {  
                        output.println("Failed to parse SST index '" + sstIndex  
                                + "': " + ex.toString());  
                    }  
                    break;  
  
                case NUMBER:  
                    String n = value.toString();  
                    // 判断是否是日期格式  
                    if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {  
                        Double d = Double.parseDouble(n);  
                        Date date=HSSFDateUtil.getJavaDate(d);  
                        thisStr=formateDateToString(date);  
                    } else if (this.formatString != null)  
                        thisStr = formatter.formatRawCellContents(  
                                Double.parseDouble(n), this.formatIndex,  
                                this.formatString);  
                    else  
                        thisStr = n;  
                    break;  
  
                default:  
                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";  
                    break;  
                }  
  
                
                if (lastColumnNumber == -1) {  
                    lastColumnNumber = 0;  
                }  
                //判断单元格的值是否为空  
                if (thisStr == null || "".equals(isCellNull)) {  
                    isCellNull = true;// 设置单元格是否为空值  
                }  
                record[thisColumn] = thisStr;  
                if (thisColumn > -1)  
                    lastColumnNumber = thisColumn;  
  
            } else if ("row".equals(name)) {  
  
                if (minColumns > 0) {  
                    // Columns are 0 based  
                    if (lastColumnNumber == -1) {  
                        lastColumnNumber = 0;  
                    }  
                    if (isCellNull == false && record[0] != null && record[1] != null)// 判断是否空行  
                    {  
                        rows.add(record.clone());  
                        isCellNull = false;  
                    }
                    //滞空读取到当前行的值
                    for (int i = 0; i < record.length; i++) {  
                        record[i] = null;  
                    }  
                }  
                lastColumnNumber = -1;  
            }  
  
        }  
  
        public List<String[]> getRows() {  
            return rows;  
        }  
  
        public void setRows(List<String[]> rows) {  
            this.rows = rows;  
        }  
  
        
        public void characters(char[] ch, int start, int length)  
                throws SAXException {  
            if (vIsOpen)  
                value.append(ch, start, length);  
        }  
  
       
        private int nameToColumn(String name) {  
            int column = -1;  
            for (int i = 0; i < name.length(); ++i) {  
                int c = name.charAt(i);  
                column = (column + 1) * 26 + c - 'A';  
            }  
            return column;  
        }  
  
        private String formateDateToString(Date date) {  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化日期  
            return sdf.format(date);  
  
        }  
  
    }  
   
  
    private OPCPackage xlsxPackage;  
    private int minColumns;  
    private PrintStream output;  
    private String[] sheetNames;  
  
    
    public XLSX2CSV(OPCPackage pkg, PrintStream output,  
            String[] sheetNames, int minColumns) {  
        this.xlsxPackage = pkg;  
        this.output = output;  
        this.minColumns = minColumns;  
        this.sheetNames = sheetNames;  
    }  
  
   
    public List<String[]> processSheet(StylesTable styles,  
            ReadOnlySharedStringsTable strings, InputStream sheetInputStream)  
            throws IOException, ParserConfigurationException, SAXException {  
  
        InputSource sheetSource = new InputSource(sheetInputStream);  
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();  
        SAXParser saxParser = saxFactory.newSAXParser();  
        XMLReader sheetParser = saxParser.getXMLReader();  
        XSSFSheetHandler handler = new XSSFSheetHandler(styles, strings,  
                this.minColumns, this.output);  
        sheetParser.setContentHandler(handler);  
        sheetParser.parse(sheetSource);
        return handler.getRows();  
    }  
  
    
    public List<String[]> process() throws IOException, OpenXML4JException,  
            ParserConfigurationException, SAXException {  
  
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(  
                this.xlsxPackage);  
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);  
        List<String[]> list = new ArrayList<String[]>();  
        List<String[]> list1 = new ArrayList<String[]>();  
        StylesTable styles = xssfReader.getStylesTable();  
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader  
                .getSheetsData();  
        int index = 0;  
        while (iter.hasNext()) {  
            InputStream stream = iter.next();  
            String sheetNameTemp = iter.getSheetName();
            //导入所有sheet
            if(sheetNames[0].equals("ALL")){
            	list1 = processSheet(styles, strings, stream);  
    			for(String[] str:list1){
    				list.add(str);
    			}
    			stream.close();
    			++index;  
    		//导入选中sheet
            }else{
            	for(int m=0;m<sheetNames.length;m++){
            		if (sheetNames[m].equals(sheetNameTemp)) {  
            			list1 = processSheet(styles, strings, stream);  
            			for(String[] str:list1){
            				list.add(str);
            			}
            			stream.close();
            			++index;  
            		}
            	}
            	
            }
        }  
        return list;  
    }  
  
    /** 
     * 读取Excel 
     *  
     * @param path 
     *            文件路径 
     * @param sheetNames 
     *            sheet名称 
     * @param minColumns 
     *            列总数 
     * @return 
     * @throws SAXException 
     * @throws ParserConfigurationException 
     * @throws OpenXML4JException 
     * @throws IOException 
     */  
    public static List<String[]> readerExcel(InputStream in, String[] sheetNames,  
            int minColumns) throws IOException, OpenXML4JException,  
            ParserConfigurationException, SAXException {  
        OPCPackage p = OPCPackage.open(in);
        XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out,  
                sheetNames, minColumns);  
        List<String[]> list = xlsx2csv.process();  
        p.close();  
        return list;
    } 
    
    public static List<String> getSheets(InputStream in) throws IOException, OpenXML4JException,  
            ParserConfigurationException, SAXException{
    	 OPCPackage p = OPCPackage.open(in);
    	 XSSFReader xssfReader = new XSSFReader(p);
         XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader  
                 .getSheetsData();
         List<String> list = new ArrayList<>();
         while (iter.hasNext()) {
        	 InputStream stream = iter.next();
             String sheetNameTemp = iter.getSheetName();
             list.add(sheetNameTemp);
		}
         p.close();
         return list;
    }
    
    
    public static String[] getRow(InputStream in, String[] sheetNames,  
            int minColumns) throws IOException, OpenXML4JException,  
            ParserConfigurationException, SAXException{
    	
    	 OPCPackage p = OPCPackage.open(in);  
         XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out,sheetNames, minColumns);  
         String[] list = xlsx2csv.process1(sheetNames);
         
    	return list;
    } 
    
    public String[] process1(String[] sheetNames) throws IOException, OpenXML4JException,  
    ParserConfigurationException, SAXException {  

		ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(  
		        this.xlsxPackage);  
		XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);  
		List<String[]> list = new ArrayList<String[]>();  
		String[] list1 = new String[50];  
		StylesTable styles = xssfReader.getStylesTable();  
		XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader  
		        .getSheetsData();  
		while (iter.hasNext()) {
		    InputStream stream = iter.next();  
		    String sheetNameTemp = iter.getSheetName();
		    //导入所有sheet
		    if(sheetNames[0].equals(sheetNameTemp)){
		    	list1 = processSheet1(styles, strings, stream);
				list.add(list1);
				break;
			//导入选中sheet
		    }
		}  
		return list1;  
	} 
    
    public String[] processSheet1(StylesTable styles,  
            ReadOnlySharedStringsTable strings, InputStream sheetInputStream)  
            throws IOException, ParserConfigurationException, SAXException {  
  
        InputSource sheetSource = new InputSource(sheetInputStream);  
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();  
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader(); 
        XSSFSheetHandler handler = new XSSFSheetHandler(styles, strings,  
                this.minColumns, this.output);  
        sheetParser.setContentHandler(handler);  
        sheetParser.parse(sheetSource);
        return handler.getRows().get(0);  
    }  
    
}
