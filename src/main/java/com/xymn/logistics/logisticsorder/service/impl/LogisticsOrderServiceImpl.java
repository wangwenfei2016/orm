package com.xymn.logistics.logisticsorder.service.impl;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.xymn.connect.kuaidiniao.entity.SendBackEntity;
import com.xymn.connect.kuaidiniao.entity.TracesJson;
import com.xymn.connect.kuaidiniao.service.KuaidiniaoService;
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.entityorderincrement.OrderIncrement;
import com.xymn.connect.pinduoduo.entityorderincrement.OrderIncrementGoods;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderEntity;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderProductEntity;
import com.xymn.logistics.logisticsorder.entity.MyRowMapper;
import com.xymn.logistics.logisticsorder.service.LogisticsOrderService;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;

@Service("logisticsOrderService")
@Transactional
public class LogisticsOrderServiceImpl extends CommonServiceImpl implements LogisticsOrderService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private KuaidiniaoService kuaidiniaoService;
	
 	public void delete(LogisticsOrderEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LogisticsOrderEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LogisticsOrderEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LogisticsOrderEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LogisticsOrderEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LogisticsOrderEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(LogisticsOrderEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("order_code", t.getOrderCode());
		map.put("express_company", t.getExpressCompany());
		map.put("express_number", t.getExpressNumber());
		map.put("order_time", t.getOrderTime());
		map.put("payment_time", t.getPaymentTime());
		map.put("delivery_time", t.getDeliveryTime());
		map.put("promise_delivery_time", t.getPromiseDeliveryTime());
		map.put("consignee", t.getConsignee());
		map.put("phone", t.getPhone());
		map.put("province", t.getProvince());
		map.put("city", t.getCity());
		map.put("region", t.getRegion());
		map.put("street", t.getStreet());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LogisticsOrderEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{order_code}",String.valueOf(t.getOrderCode()));
 		sql  = sql.replace("#{express_company}",String.valueOf(t.getExpressCompany()));
 		sql  = sql.replace("#{express_number}",String.valueOf(t.getExpressNumber()));
 		sql  = sql.replace("#{order_time}",String.valueOf(t.getOrderTime()));
 		sql  = sql.replace("#{payment_time}",String.valueOf(t.getPaymentTime()));
 		sql  = sql.replace("#{delivery_time}",String.valueOf(t.getDeliveryTime()));
 		sql  = sql.replace("#{promise_delivery_time}",String.valueOf(t.getPromiseDeliveryTime()));
 		sql  = sql.replace("#{consignee}",String.valueOf(t.getConsignee()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{province}",String.valueOf(t.getProvince()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{region}",String.valueOf(t.getRegion()));
 		sql  = sql.replace("#{street}",String.valueOf(t.getStreet()));
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("wl_logistics_order",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public Boolean convertBatchData(List<String[]> rows, Integer[] needNumber, String shopName,
			List<LogisticsOrderEntity> list,List<LogisticsOrderProductEntity> products, AjaxJson j) {
			
			List<String> orderCodes = new ArrayList<>();
			Boolean flag = new Boolean(true);
			for (int i = 1; i < rows.size(); i++) {
				//必须列:0-"订单编号",1-"快递公司",2-"快递单号",3-"订单时间",4-"付款时间",5-"发货时间",6-"交易金额",7-"商品编号",8-"商品名称",9-"商品单价",10-"商品数量"
				//非必须:11-"承诺发货时间",12-"收货人",13-"手机",14-"省",15-"市",16-"区",17-"街道",18-"SKU编号",19-"外部编号",20-"商品规格"
				String orderCode = rows.get(i)[needNumber[0]] == null?null:rows.get(i)[needNumber[0]].replaceAll("\"", "");
				
				if(orderCode!=null){
					
					LogisticsOrderEntity entity = new LogisticsOrderEntity();
					//设置店铺名称
					entity.setStore(shopName);
					entity.setOrderCode(orderCode);//订单编号--订单
					entity.setExpressCompany(rows.get(i)[needNumber[1]]!=null?rows.get(i)[needNumber[1]].replaceAll("\"", ""):null);//快递公司
					entity.setExpressNumber(rows.get(i)[needNumber[2]]!=null?rows.get(i)[needNumber[2]].replaceAll("\"", ""):null);//快递编号
					try {
						Date orderTime = this.convertDate(rows.get(i)[needNumber[3]].replaceAll("\"", ""));
						if(orderTime==null){
							flag = false;
							j.setSuccess(false);
							j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的订单时间不能为空,请检查!!!");
							throw new Exception();
						}
						entity.setOrderTime(orderTime);//订单时间
					} catch (Exception e1) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的订单时间转换失败,请检查!!!");
						e1.printStackTrace();
					}
					try {
						Date paymentTime = this.convertDate(rows.get(i)[needNumber[4]].replaceAll("\"", ""));
						if(paymentTime==null){
							flag = false;
							j.setSuccess(false);
							j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的付款时间不能为空,请检查!!!");
							throw new Exception();
						}
						entity.setPaymentTime(paymentTime);//付款时间
					} catch (Exception e1) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的付款时间转换失败,请检查!!!");
						e1.printStackTrace();
					}
					try {
						Date deliveryTime = this.convertDate(rows.get(i)[needNumber[5]].replaceAll("\"", ""));
						if(deliveryTime==null){
							flag = false;
							j.setSuccess(false);
							j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的发货时间不能为空,请检查!!!");
							throw new Exception();
						}
						entity.setDeliveryTime(deliveryTime);//发货时间
					} catch (Exception e1) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的发货时间转换失败,请检查!!!");
						e1.printStackTrace();
					}
					try {
						entity.setAmount(Double.parseDouble(rows.get(i)[needNumber[6]].replaceAll("\"", "")));//交易金额
					} catch (NumberFormatException e1) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的交易金额转换失败,请检查!!!");
						e1.printStackTrace();
					}
					
					try {
						if(needNumber[11]!=null){
							entity.setPaymentTime(this.convertDate(rows.get(i)[needNumber[11]].replaceAll("\"", "")));//承诺发货时间
						}
					} catch (Exception e1) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的承诺发货时间转换失败,请检查!!!");
						e1.printStackTrace();
					}
					
					if(needNumber[12]!=null){
						entity.setConsignee(rows.get(i)[needNumber[12]]!=null?rows.get(i)[needNumber[12]].replaceAll("\"", ""):null);//收货人
					}
					if(needNumber[13]!=null){
						entity.setPhone(rows.get(i)[needNumber[13]]!=null?rows.get(i)[needNumber[13]].replaceAll("\"", ""):null);//电话
					}
					if(needNumber[14]!=null){
						entity.setProvince(rows.get(i)[needNumber[14]]!=null?rows.get(i)[needNumber[14]].replaceAll("\"", ""):null);//省					
					}
					if(needNumber[15]!=null){
						entity.setCity(rows.get(i)[needNumber[15]]!=null?rows.get(i)[needNumber[15]].replaceAll("\"", ""):null);//市
					}
					if(needNumber[16]!=null){
						entity.setRegion(rows.get(i)[needNumber[16]]!=null?rows.get(i)[needNumber[16]].replaceAll("\"", ""):null);//区
					}
					if(needNumber[17]!=null){
						entity.setStreet(rows.get(i)[needNumber[17]]!=null?rows.get(i)[needNumber[17]].replaceAll("\"", ""):null);//街道
					}
					
					
					
					
					LogisticsOrderProductEntity product = new LogisticsOrderProductEntity();
					
					product.setOrderCode(orderCode);//订单编号--产品
					product.setProductCode(rows.get(i)[needNumber[7]]!=null?rows.get(i)[needNumber[7]].replaceAll("\"", ""):null);//商品编号
					product.setProductName(rows.get(i)[needNumber[8]]!=null?rows.get(i)[needNumber[8]].replaceAll("\"", ""):null);//商品名称
					try {
						product.setProductUnitPrice(Double.parseDouble(rows.get(i)[needNumber[9]].replaceAll("\"", "")));//商品单价
					} catch (Exception e) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的商品单价转换失败,请检查!!!");
						e.printStackTrace();
					}
					try {
						product.setProductNum(Integer.parseInt(rows.get(i)[needNumber[10]].replaceAll("\"", "")));//商品数量
					} catch (Exception e) {
						flag = false;
						j.setSuccess(false);
						j.setMsg("订单编号为:"+rows.get(i)[needNumber[0]]+"商品编号为:"+rows.get(i)[needNumber[7]]+"的商品单价转换失败,请检查!!!");
						e.printStackTrace();
					}
					
					if(needNumber[18]!=null){
						product.setSkuCode(rows.get(i)[needNumber[18]]!=null?rows.get(i)[needNumber[18]].replaceAll("\"", ""):null);//sku编码
					}
					if(needNumber[19]!=null){
						product.setOutsideCode(rows.get(i)[needNumber[19]]!=null?rows.get(i)[needNumber[19]].replaceAll("\"", ""):null);//外部平台编码
					}
					if(needNumber[20]!=null){
						product.setProductSpecification(rows.get(i)[needNumber[20]]!=null?rows.get(i)[needNumber[20]].replaceAll("\"", ""):null);//商品规格
					}
					
					if(!orderCodes.contains(entity.getOrderCode())){
						list.add(entity);
					}
					products.add(product);
					orderCodes.add(orderCode);
				}else{
					flag = false;
					j.setSuccess(false);
					j.setMsg("订单编号不能为空!!!!");
				}
				
			}
				
		return flag;
	}

	private Date convertDate(String date) throws ParseException {
		
		if(StringUtil.isEmpty(date)){
			return null;
		}
		
		if(date.length() == 10){
			date+=" 00:00:00";
		}
		
		if(date.contains("/")){
			date.replaceAll("/", "-");
		}
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		
	}

	@Override
	public void saveBatchData(List<LogisticsOrderEntity> list, List<LogisticsOrderProductEntity> products,TSUser user) throws Exception {
		
		String orderTableName = "wl_exceldataorder_"+user.getUserName();
		String productTableName = "wl_exceldataproduct_"+user.getUserName();
		
		int pointsDataLimit = 2000;// 限制条数
		Integer size = list.size();
		int part = size / pointsDataLimit;// 分批数
		System.out.println("共有:"+size+"条,"+"分为:"+part+"批");
		for(int i = 0; i < part; i++) {
			System.out.println("正在执行第"+i+"批");
			//2000条
			List<LogisticsOrderEntity> listPage = list.subList(0, pointsDataLimit);
			for (int j = 0; j < listPage.size(); j++) {
				this.saveExcelDataOrder(listPage, orderTableName);
			}
			// 剔除
			list.subList(0, pointsDataLimit).clear();
		}
		//保存剩余订单
		this.saveExcelDataOrder(list, orderTableName);
		
		
		size = products.size();
		part = size / pointsDataLimit;
		
		System.out.println("共有:"+size+"条,"+"分为:"+part+"批");
		for(int i = 0; i < part; i++) {
			System.out.println("正在执行第"+i+"批");
			// 2000条
			List<LogisticsOrderProductEntity> listPage = products.subList(0, pointsDataLimit);
			for (int j = 0; j < listPage.size(); j++) {
				this.saveExcelDataProduct(listPage, productTableName);
			}
			// 剔除
			products.subList(0, pointsDataLimit).clear();
		}
		//保存剩余产品
		this.saveExcelDataProduct(products, productTableName);
		String orderZhu = "wl_logistics_order_"+user.getUserName();
//		String productZhu = "wl_logistics_order_product_"+user.getUserName();
		
		//去重复关联设置值
		this.doExcelDataToTable(orderTableName,productTableName,orderZhu);
		
	}
	
	
	private void doExcelDataToTable(String orderTableName, String productTableName, String orderZhu) {
		
		String deleteOrderSql = "DELETE   "
								+"FROM         "
								+"	"+orderTableName+" "
								+"WHERE   "
								+"	order_code IN (   "
								+"		SELECT    "
								+"			order_code  "
								+"		FROM    "
								+"			(       "
								+"				SELECT    "
								+"					w1.order_code    "
								+"				FROM    "
								+"					"+orderZhu+" w2,   "
								+"					"+orderTableName+" w1  "
								+"				WHERE   "
								+"					w1.order_code = w2.order_code    "
								+"			) aa );";
		
		
		String deleteProductSql = "DELETE       "
									+"FROM     "
									+"	"+productTableName+"  "
									+"WHERE         "
									+"	order_code IN ( "
									+"		SELECT  "
									+"			order_code  "
									+"		FROM     "
									+"			(    "
									+"				SELECT    "
									+"					order_code    "
									+"				FROM        "
									+"					"+productTableName+"   "
									+"				WHERE            "
									+"					order_code NOT IN (    "
									+"						SELECT      "
									+"							wl.order_code    "
									+"						FROM       "
									+"							"+orderTableName+" wl  "
									+"					)    "
									+"			) aa  );";
		
		String updateProductOrderIdSql = "UPDATE "+productTableName+" we       "
										+"SET order_id = (                        "
										+"	SELECT                                "
										+"		id                                "
										+"	FROM                                  "
										+"		"+orderTableName+" wa        "
										+"	WHERE                                 "
										+"		we.order_code = wa.order_code );";
		
//		String copyToOrderTableSql = "INSERT INTO wl_logistics_order SELECT  "
//									+"	*  "
//									+"FROM  "
//									+"	"+orderTableName+";";
		
		
//		String copyToProductTableSql = "INSERT INTO wl_logistics_order_product SELECT "
//										+"	*  "
//										+"FROM  "
//										+"	"+productTableName+";";
		//关联删除订单中间表
		jdbcTemplate.execute(deleteOrderSql);
		//关联删除产品中间表
		jdbcTemplate.execute(deleteProductSql);
		//修改产品表,关联修改增加订单id
		jdbcTemplate.execute(updateProductOrderIdSql);
		//拷贝订单到数据表
//		jdbcTemplate.execute(copyToOrderTableSql);
		//拷贝产品到数据表
//		jdbcTemplate.execute(copyToProductTableSql);
		
	}

	//储存订单数据
	private void saveExcelDataOrder(List<LogisticsOrderEntity> list,String orderTableName) {
		   
		   final List<LogisticsOrderEntity> tempBpplist = list;
		   String sql="INSERT INTO "+orderTableName+"(id,order_code,express_company,express_number,order_time,payment_time,delivery_time,promise_delivery_time,amount,consignee,phone,province,city,region,street,istail,store,reason) "
		   		+ "VALUES(REPLACE(uuid(), '-', ''),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   this.jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			   
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				ps.setString(1, tempBpplist.get(i).getOrderCode());
				ps.setString(2, tempBpplist.get(i).getExpressCompany());
				ps.setString(3, tempBpplist.get(i).getExpressNumber());
				ps.setString(4,tempBpplist.get(i).getOrderTime()==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tempBpplist.get(i).getOrderTime()));
				ps.setString(5, tempBpplist.get(i).getPaymentTime()==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tempBpplist.get(i).getPaymentTime()));
				ps.setString(6, tempBpplist.get(i).getDeliveryTime()==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tempBpplist.get(i).getDeliveryTime()));
				ps.setString(7, tempBpplist.get(i).getPromiseDeliveryTime()==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tempBpplist.get(i).getPromiseDeliveryTime()));
				ps.setDouble(8, tempBpplist.get(i).getAmount());
				ps.setString(9, tempBpplist.get(i).getConsignee());
				ps.setString(10, tempBpplist.get(i).getPhone());
				ps.setString(11, tempBpplist.get(i).getProvince());
				ps.setString(12, tempBpplist.get(i).getCity());
				ps.setString(13, tempBpplist.get(i).getRegion());
				ps.setString(14, tempBpplist.get(i).getStreet());
				ps.setString(15, tempBpplist.get(i).getIstail());
				ps.setString(16, tempBpplist.get(i).getStore());
				ps.setString(17, tempBpplist.get(i).getReason());
				
			}
	
			@Override
			public int getBatchSize() {
				
				return tempBpplist.size();
			}   
		       
		  });
	}
	
	
	//储存产品数据
	private void saveExcelDataProduct(List<LogisticsOrderProductEntity> list,String orderTableName) {
		
		final List<LogisticsOrderProductEntity> tempBpplist = list;
		String sql="INSERT INTO "+orderTableName+""
				+ "(id,order_code,product_code,sku_code,outside_code,product_name,product_specification,product_unit_price,product_num) "
				+ "VALUES(REPLACE(uuid(), '-', ''),?,?,?,?,?,?,?,?);";
		this.jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				ps.setString(1, tempBpplist.get(i).getOrderCode());
				ps.setString(2, tempBpplist.get(i).getProductCode());
				ps.setString(3, tempBpplist.get(i).getSkuCode());
				ps.setString(4, tempBpplist.get(i).getOutsideCode());
				ps.setString(5, tempBpplist.get(i).getProductName());
				ps.setString(6, tempBpplist.get(i).getProductSpecification());
				ps.setDouble(7, tempBpplist.get(i).getProductUnitPrice());
				ps.setInt(8, tempBpplist.get(i).getProductNum());
				
			}
			
			@Override
			public int getBatchSize() {
				
				return tempBpplist.size();
			}   
			
		});
	}

	@Override
	public Boolean createMiddleTable(TSUser user) {
		
		boolean isCreate = true; 
		
		
		String orderTableName = "wl_exceldataorder_"+user.getUserName();
		String orderDropSql = "DROP TABLE IF EXISTS `"+orderTableName+"`;";
		String orderCreateSql = "CREATE TABLE `"+orderTableName+"` (                                   "
						 +"`id` varchar(36) NOT NULL,                                             "
						 +"`create_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',           "
						 +"`create_by` varchar(50) DEFAULT NULL COMMENT '创建人登录名称',         "
						 +"`create_date` datetime DEFAULT NULL COMMENT '创建日期',                "
						 +"`update_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',           "
						 +"`update_by` varchar(50) DEFAULT NULL COMMENT '更新人登录名称',         "
						 +"`update_date` datetime DEFAULT NULL COMMENT '更新日期',                "
						 +"`sys_org_code` varchar(50) DEFAULT NULL COMMENT '所属部门',            "
						 +"`sys_company_code` varchar(50) DEFAULT NULL COMMENT '所属公司',        "
						 +"`bpm_status` varchar(32) DEFAULT '1' COMMENT '流程状态',               "
						 +"`order_code` varchar(50) DEFAULT NULL COMMENT '订单编号',              "
						 +"`express_company` varchar(100) DEFAULT NULL COMMENT '快递公司',        "
						 +"`express_number` varchar(50) DEFAULT NULL COMMENT '快递单号',          "
						 +"`order_time` datetime DEFAULT NULL COMMENT '订单时间',                 "
						 +"`payment_time` datetime DEFAULT NULL COMMENT '付款时间',               "
						 +"`delivery_time` datetime DEFAULT NULL COMMENT '发货时间',              "
						 +"`promise_delivery_time` datetime DEFAULT NULL COMMENT '承诺发货时间',  "
						 +"`amount` double(10,2) DEFAULT NULL COMMENT '交易金额',                 "
						 +"`consignee` varchar(100) DEFAULT NULL COMMENT '收件人',                "
						 +"`phone` varchar(32) DEFAULT NULL COMMENT '手机',                       "
						 +"`province` varchar(50) DEFAULT NULL COMMENT '省级',                    "
						 +"`city` varchar(50) DEFAULT NULL COMMENT '市级',                        "
						 +"`region` varchar(50) DEFAULT NULL COMMENT '区级',                      "
						 +"`street` varchar(255) DEFAULT NULL COMMENT '街道',                     "
						 +"`silence_hour` int(3) DEFAULT NULL COMMENT '沉默时间',                 "
						 +"`logistics_tail_time` datetime DEFAULT NULL,                           "
						 +"`logistics_tail_info` varchar(255) DEFAULT NULL COMMENT '物流跟踪信息',  "
						 +" `istail` varchar(100) DEFAULT NULL COMMENT '是否监控',               "
						 +"`logistics_tail_status` varchar(10) DEFAULT NULL COMMENT '跟踪状态',   "
						 +"`store` varchar(100) DEFAULT NULL COMMENT '店铺',           "
						 +"PRIMARY KEY (`id`)  "
						 +") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		
		String productTableName = "wl_exceldataproduct_"+user.getUserName();
		String productDropSql = "DROP TABLE IF EXISTS `"+productTableName+"`;";
		String productCreateSql = "CREATE TABLE `"+productTableName+"` (                              "
							+"  `id` varchar(36) NOT NULL,                                            "
							+"  `create_name` varchar(50) DEFAULT NULL COMMENT '创建人名称',          "
							+"  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人登录名称',        "
							+"  `create_date` datetime DEFAULT NULL COMMENT '创建日期',               "
							+"  `update_name` varchar(50) DEFAULT NULL COMMENT '更新人名称',          "
							+"  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人登录名称',        "
							+"  `update_date` datetime DEFAULT NULL COMMENT '更新日期',               "
							+"  `sys_org_code` varchar(50) DEFAULT NULL COMMENT '所属部门',           "
							+"  `sys_company_code` varchar(50) DEFAULT NULL COMMENT '所属公司',       "
							+"  `bpm_status` varchar(32) DEFAULT '1' COMMENT '流程状态',              "
							+"  `order_id` varchar(36) DEFAULT NULL COMMENT '主表id',                 "
							+"  `order_code` varchar(50) DEFAULT NULL COMMENT '订单编号',             "
							+"  `product_code` varchar(50) DEFAULT NULL COMMENT '商品编号',           "
							+"  `sku_code` varchar(50) DEFAULT NULL COMMENT 'SKU编号',                "
							+"  `outside_code` varchar(50) DEFAULT NULL COMMENT '外部编号',           "
							+"  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',          "
							+"  `product_specification` varchar(50) DEFAULT NULL COMMENT '商品规格',  "
							+"  `product_unit_price` double DEFAULT NULL COMMENT '商品单价',          "
							+"  `product_num` int(11) DEFAULT NULL COMMENT '商品数量',                "
							+"  PRIMARY KEY (`id`)                                                    "
							+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		
		try {
			//删除订单中间表
			jdbcTemplate.execute(orderDropSql);
			//删除产品中间表
			jdbcTemplate.execute(productDropSql);
			//创建订单中间表
			jdbcTemplate.update(orderCreateSql);
			//创建产品中间表
			jdbcTemplate.update(productCreateSql);
		} catch (Exception e) {
			isCreate = false;
			e.printStackTrace();
		}
		
		
		return isCreate;
	}
	
	
	/**
	 * 拼多多订单转换监控到系统
	 */
	@Override
	public void conversionToOurSystem(List<OrderIncrement> list,ShopTokens shopToken) {
		
		if(list.size()>0){
			//需要保存的订单
			List<LogisticsOrderEntity> orders = new ArrayList<>();
			//需要保存的产品
			List<LogisticsOrderProductEntity> products = new ArrayList<>();
			//获取店铺信息
			ShopDeployEntity findUniqueByProperty = this.findUniqueByProperty(ShopDeployEntity.class, "state", shopToken.getState());
			//获取要保存的表名
			TSUser user = this.get(TSUser.class, shopToken.getUserId());
			String orderTableName = "wl_logistics_order_"+user.getUserName();
			String productTableName = "wl_logistics_order_product_"+user.getUserName();
			
			
			List<String> expressNums = this.jdbcTemplate.queryForList("SELECT  express_number FROM "+orderTableName, String.class);
			for (int i = 0; i < list.size(); i++) {
				
				OrderIncrement orderIncrement = list.get(i);
				if(!expressNums.contains(orderIncrement.getTracking_number())){
					//创建保存的订单实体
					LogisticsOrderEntity orderEntity = new LogisticsOrderEntity();
					String uuid = this.getUUID();
					orderEntity.setId(uuid);
					orderEntity.setStore(findUniqueByProperty.getShopName());//店铺名称
					orderEntity.setOrderCode(orderIncrement.getOrder_sn());//订单编号
					orderEntity.setExpressCompany(orderIncrement.getLogistics_id());//快递公司id
					orderEntity.setExpressNumber(orderIncrement.getTracking_number());//快递单号
					//订单创建时间
					if(StringUtil.isNotEmpty(orderIncrement.getCreated_time())){
						try {
							orderEntity.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderIncrement.getCreated_time()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					//付款时间无
//					orderEntity.setPaymentTime(paymentTime);
					//发货时间
					if(StringUtil.isNotEmpty(orderIncrement.getShipping_time())){
						try {
							orderEntity.setDeliveryTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderIncrement.getShipping_time()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					//承诺发货时间
					if(StringUtil.isNotEmpty(orderIncrement.getLast_ship_time())){
						try {
							orderEntity.setPromiseDeliveryTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderIncrement.getLast_ship_time()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					//支付金额
					if(StringUtil.isNotEmpty(orderIncrement.getPay_amount())){
						orderEntity.setAmount(Double.parseDouble(orderIncrement.getPay_amount())); 
					}
					//收件人
					orderEntity.setConsignee(orderIncrement.getReceiver_name());
					//电话
					orderEntity.setPhone(orderIncrement.getReceiver_phone());
					//省
					orderEntity.setProvince(orderIncrement.getProvince());
					//市
					orderEntity.setCity(orderIncrement.getCity());
					//区
					orderEntity.setRegion(orderIncrement.getTown());
					//街道
					orderEntity.setStreet(orderIncrement.getAddress());
					//订单商品集合
					OrderIncrementGoods[] goods = orderIncrement.getItem_list();
					for (OrderIncrementGoods good : goods) {
						LogisticsOrderProductEntity productEntity = new LogisticsOrderProductEntity();
						productEntity.setOrderId(uuid);
						productEntity.setOrderCode(orderIncrement.getOrder_sn());
						productEntity.setProductCode(good.getGoods_id());//商品编号
						productEntity.setProductName(good.getGoods_name());//商品名称
						if(StringUtil.isNotEmpty(good.getGoods_count())){//商品数量
							productEntity.setProductNum(Integer.parseInt(good.getGoods_count()));
						}
						productEntity.setSkuCode(good.getSku_id());//sku编号
						productEntity.setOutsideCode(good.getOuter_id());//外部编号
						productEntity.setProductSpecification(good.getGoods_spec());//规格
						if(StringUtil.isNotEmpty(good.getGoods_price())){//商品单价
							productEntity.setProductUnitPrice(Double.parseDouble(good.getGoods_price()));
						}
						products.add(productEntity);
					}
					
					
					orders.add(orderEntity);
				}
				
			}
			
			//保存订单信息
			if(orders.size()>0){
				//发送订阅
				this.kuaidiniaoService.publishSubscribeBatch(orders,user,shopToken);
				this.saveExcelDataOrder(orders, orderTableName);
				//保存关联产品信息
				if(products.size()>0){
					this.saveExcelDataProduct(products, productTableName);
				}
			}
			
			
			
		}
		
		
	}
	

	//获取uuid
	public String getUUID(){
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public Integer doActionMonitoring(TSUser user,String shopName) {
		
		String orderTableName = "wl_exceldataorder_"+user.getUserName();
		
		
		String sql = "SELECT id , order_code , express_company  ,express_number ,order_time , payment_time , delivery_time , promise_delivery_time , amount , "+
					 " consignee , phone , province ,city , region ,street  ,store FROM "+orderTableName;
		List<LogisticsOrderEntity> orders = new ArrayList<>();
		orders = this.jdbcTemplate.query(sql, new RowMapper<LogisticsOrderEntity>(){
            public LogisticsOrderEntity mapRow(ResultSet rs, int rowNum)throws SQLException {
            	LogisticsOrderEntity orderEntity = new LogisticsOrderEntity();
            	orderEntity.setId(rs.getString("id"));
            	orderEntity.setOrderCode(rs.getString("order_code"));
            	orderEntity.setExpressCompany(rs.getString("express_company"));
            	orderEntity.setExpressNumber(rs.getString("express_number"));
            	orderEntity.setOrderTime(rs.getTimestamp("order_time"));
            	orderEntity.setPaymentTime(rs.getTimestamp("payment_time"));
            	orderEntity.setDeliveryTime(rs.getTimestamp("delivery_time"));
            	orderEntity.setPromiseDeliveryTime(rs.getTimestamp("promise_delivery_time"));
            	orderEntity.setAmount(rs.getDouble("amount"));
            	orderEntity.setConsignee(rs.getString("consignee"));
            	orderEntity.setPhone(rs.getString("phone"));
            	orderEntity.setProvince(rs.getString("province"));
            	orderEntity.setCity(rs.getString("city"));
            	orderEntity.setRegion(rs.getString("region"));
            	orderEntity.setStreet(rs.getString("street"));
            	orderEntity.setStore(rs.getString("store"));
                return orderEntity;
            }
		});
		
		//保存订单信息
		if(orders.size()>0){
			//发送订阅
			orderTableName = "wl_logistics_order_"+user.getUserName();
			this.kuaidiniaoService.publishSubscribeBatchExcel(orders,user,shopName);
			this.saveExcelDataOrder(orders, orderTableName);
			//保存关联产品信息
			String productTableName = "wl_exceldataproduct_"+user.getUserName();
			String productzhu= "wl_logistics_order_product_"+user.getUserName();
			String copyToProductTableSql = "INSERT INTO "+productzhu+" SELECT * FROM  "+productTableName +";";
			//拷贝产品到数据表
			jdbcTemplate.execute(copyToProductTableSql);
		}
		
		return (Integer)orders.size();
		
	}
	
	
	/**
	 * 组装用户的查询数据
	 * @param logisticsOrder 封装条件
	 * @param dataGrid 装载条件
	 */
	@Override
	public void queryOrderListByCondition(LogisticsOrderEntity logisticsOrder, DataGrid dataGrid) {
		
		List<LogisticsOrderEntity> list = new ArrayList<>();
		TSUser user = ResourceUtil.getSessionUser();
		String orderTableName = "wl_logistics_order_"+user.getUserName();
		String countSql = "SELECT COUNT(1) FROM "+orderTableName+"  WHERE 1=1 ";
		String dataSql = "SELECT                         "
							+" 	id,                      "
							+" 	create_name,             "
							+" 	create_by,               "
							+" 	create_date,             "
							+" 	update_name,             "
							+" 	update_by,               "
							+" 	update_date,             "
							+" 	sys_org_code,            "
							+" 	sys_company_code,        "
							+" 	bpm_status,              "
							+" 	order_code,              "
							+" 	express_company,         "
							+" 	express_number,          "
							+" 	order_time,              "
							+" 	payment_time,            "
							+" 	delivery_time,           "
							+" 	promise_delivery_time,   "
							+" 	amount,                  "
							+" 	consignee,               "
							+" 	phone,                   "
							+" 	province,                "
							+" 	city,                    "
							+" 	region,                  "
							+" 	street,                  "
							+" 	silence_hour,            "
							+" 	logistics_tail_time,     "
							+" 	logistics_tail_info,     "
							+" 	istail,                  "
							+" 	logistics_tail_status,   "
							+" 	store ,                   "
							+" 	reason                    "
							+" FROM                      "
							+" 	wl_logistics_order_admin "
							+" WHERE                     "
							+"	1 = 1 ";
		//快递公司查询
		if(StringUtil.isNotEmpty(logisticsOrder.getExpressCompany())){
			countSql += " AND express_company = '"+logisticsOrder.getExpressCompany()+"' ";
			dataSql += " AND express_company = '"+logisticsOrder.getExpressCompany()+"' ";
		}
		
		//快递单号查询
		if(StringUtil.isNotEmpty(logisticsOrder.getExpressNumber())){
			countSql += " AND express_number = '"+logisticsOrder.getExpressNumber()+"' ";
			dataSql += " AND express_number = '"+logisticsOrder.getExpressNumber()+"' ";
		}
		
		//订单编号查询
		if(StringUtil.isNotEmpty(logisticsOrder.getOrderCode())){
			countSql += " AND order_code = '"+logisticsOrder.getOrderCode()+"' ";
			dataSql += " AND order_code = '"+logisticsOrder.getOrderCode()+"' ";
		}
		
		//店铺名称查询
		if(StringUtil.isNotEmpty(logisticsOrder.getStore())){
			countSql += " AND store = '"+logisticsOrder.getStore()+"'  ";
			dataSql += " AND store = '"+logisticsOrder.getStore()+"'  ";
		}
		
		
		int allCounts = jdbcTemplate.queryForInt(countSql );
		dataGrid.setTotal(allCounts);
		
		int pageSize = dataGrid.getRows();// 每页显示数
		int page = dataGrid.getPage();
		int offset = (page-1)*pageSize;
		dataSql += " limit  "+ offset +" , " +pageSize;
		
		list = jdbcTemplate.query(dataSql, new RowMapper<LogisticsOrderEntity>(){
            public LogisticsOrderEntity mapRow(ResultSet rs, int rowNum)throws SQLException {
            	LogisticsOrderEntity orderEntity = new LogisticsOrderEntity();
            	orderEntity.setId(rs.getString(1));
            	orderEntity.setCreateName(rs.getString(2));
            	orderEntity.setCreateBy(rs.getString(3));
            	orderEntity.setCreateDate(rs.getTimestamp(4));
            	orderEntity.setUpdateName(rs.getString(5));
            	orderEntity.setUpdateBy(rs.getString(6));
            	orderEntity.setUpdateDate(rs.getTimestamp(7));
            	orderEntity.setSysOrgCode(rs.getString(8));
            	orderEntity.setSysCompanyCode(rs.getString(9));
            	orderEntity.setBpmStatus(rs.getString(10));
            	orderEntity.setOrderCode(rs.getString(11));
            	orderEntity.setExpressCompany(rs.getString(12));
            	orderEntity.setExpressNumber(rs.getString(13));
            	orderEntity.setOrderTime(rs.getTimestamp(14));
            	orderEntity.setPaymentTime(rs.getTimestamp(15));
            	orderEntity.setDeliveryTime(rs.getTimestamp(16));
            	orderEntity.setPromiseDeliveryTime(rs.getTimestamp(17));
            	orderEntity.setAmount(rs.getDouble(18));
            	orderEntity.setConsignee(rs.getString(19));
            	orderEntity.setPhone(rs.getString(20));
            	orderEntity.setProvince(rs.getString(21));
            	orderEntity.setCity(rs.getString(22));
            	orderEntity.setRegion(rs.getString(23));
            	orderEntity.setStreet(rs.getString(24));
            	orderEntity.setSilenceHour(rs.getInt(25));
            	orderEntity.setLogisticsTailTime(rs.getTimestamp(26));
            	orderEntity.setLogisticsTailInfo(rs.getString(27));
            	orderEntity.setIstail(rs.getString(28));
            	orderEntity.setLogisticsTailStatus(rs.getString(29));
            	orderEntity.setStore(rs.getString(30));
            	orderEntity.setReason(rs.getString(31));
            	
                return orderEntity;
            }
		});
		
		dataGrid.setResults(list);
		
	}
	
	/**
	 * 根据当前用户获取用户的所有店铺
	 */
	@Override
	public List<ShopDeployEntity> getUserShopBySessionUser(TSUser user) {
		
		List<ShopDeployEntity> list = this.findByProperty(ShopDeployEntity.class, "userId", user.getId());
		
		return list;
	}

	@Override
	public LogisticsOrderEntity getOrderById(String id, TSUser user) {
		
		String orderTableName = "wl_logistics_order_"+user.getUserName();
		String sql = "SELECT id ,order_code AS orderCode, order_time AS orderTime , payment_time AS paymentTime,store ,express_number AS expressNumber ,express_company AS expressCompany ,delivery_time AS  "
					 +"	deliveryTime ,promise_delivery_time AS promiseDeliveryTime ,consignee ,phone ,amount ,province ,city ,reason ,street FROM "+orderTableName+" wl WHERE wl.id = ?";
		LogisticsOrderEntity queryForObject = jdbcTemplate.queryForObject( sql, new Object[] {id}, new MyRowMapper());
		
		return queryForObject;
	}

	@Override
	public List<LogisticsOrderProductEntity> getOrderProductDetail(String id, TSUser user) {
		
		List<LogisticsOrderProductEntity> list = new ArrayList<>();
		String orderTableName = "wl_logistics_order_product_"+user.getUserName();
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList("SELECT * FROM "+orderTableName+" WHERE order_id = '"+id+"' ");
		
		for (int i = 0; i < queryForList.size(); i++) {
			Map<String,Object> map = queryForList.get(i);
			LogisticsOrderProductEntity entity = new LogisticsOrderProductEntity();
			entity.setId(map.get("id").toString());
			entity.setProductCode(map.get("product_code")==null?null:map.get("product_code").toString());
			entity.setProductName(map.get("product_name")==null?null:map.get("product_name").toString());
			entity.setProductNum(Integer.parseInt(map.get("product_num")==null?"0":map.get("product_num").toString()));
			entity.setProductSpecification(map.get("product_specification")==null?null:map.get("product_specification").toString());
			entity.setProductUnitPrice(Double.parseDouble(map.get("product_unit_price")==null?"0":map.get("product_unit_price").toString()));
			entity.setOutsideCode(map.get("outside_code")==null?null:map.get("outside_code").toString());
			entity.setSkuCode(map.get("sku_code")==null?null:map.get("sku_code").toString());
			
			list.add(entity);
		}
		
		return list;
	}

	@Override
	public List<TracesJson> getOrderTracesByExpressCode(String expressNumber, TSUser user) {
		
		List<TracesJson> list = new ArrayList<>();
		Gson gson = new Gson();
		String orderTableName = "wl_kuaidiniao_call_back_"+user.getUserName();
		String sql = "SELECT traces FROM "+orderTableName+" WHERE logistic_code = ? ";
		String queryForObject = null;
		try {
			queryForObject = jdbcTemplate.queryForObject( sql, new Object[] {expressNumber}, String.class);
		} catch (DataAccessException e) {
		}
		if(queryForObject!=null){
			TracesJson[] traces =  gson.fromJson(queryForObject, TracesJson[].class);
			list.addAll(Arrays.asList(traces));
		}
		return list;
	}
	
	
	
}