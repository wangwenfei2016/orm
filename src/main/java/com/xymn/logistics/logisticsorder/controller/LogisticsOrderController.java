package com.xymn.logistics.logisticsorder.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xpath.operations.Bool;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;
import com.xymn.connect.kuaidiniao.entity.CallBackJson;
import com.xymn.connect.kuaidiniao.entity.TracesJson;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderEntity;
import com.xymn.logistics.logisticsorder.entity.LogisticsOrderProductEntity;
import com.xymn.logistics.logisticsorder.service.LogisticsOrderService;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;
import com.xymn.user.userparameter.entity.WlUserParameterEntity;
import com.xymn.util.excel.XLSX2CSV;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 物流跟踪订单
 * @author onlineGenerator
 * @date 2018-03-01 15:40:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/logisticsOrderController")
@Api(value="LogisticsOrder",description="物流跟踪订单",tags="logisticsOrderController")
public class LogisticsOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LogisticsOrderController.class);

	@Autowired
	private LogisticsOrderService logisticsOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;


	/**
	 * 物流跟踪订单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		
		WlUserParameterEntity parameterEntity = this.systemService.findUniqueByProperty(WlUserParameterEntity.class, "userId", user.getId());
		request.setAttribute("remindHour", parameterEntity.getRemindHour());
		return new ModelAndView("com/xymn/logistics/logisticsorder/logisticsOrderList");
	}
	/**
	 * 物流导入 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "importExcel")
	public ModelAndView importExcel(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		List<ShopDeployEntity> list = this.logisticsOrderService.getUserShopBySessionUser(user);
		request.setAttribute("list", list);
		return new ModelAndView("com/xymn/logistics/logisticsorder/logisticsOrder-import");
	}
	/**
	 * 物流订单详情页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "toOrderDetail")
	public ModelAndView toOrderDetail(HttpServletRequest request,String id) {
		
		TSUser user = ResourceUtil.getSessionUser();
		LogisticsOrderEntity orderEntity = new LogisticsOrderEntity();
		List<LogisticsOrderProductEntity> productEntities = new ArrayList<>();
		List<TracesJson> traces = new ArrayList<>();
		if(user!=null){
			//订单信息
			orderEntity = this.logisticsOrderService.getOrderById(id,user);
			//商品信息
			productEntities = this.logisticsOrderService.getOrderProductDetail(id,user);
			//通过快递单号获取快递追踪信息
			traces = this.logisticsOrderService.getOrderTracesByExpressCode(orderEntity.getExpressNumber(),user);
		}
		request.setAttribute("orderEntity", orderEntity);
		request.setAttribute("productEntities", productEntities);
		request.setAttribute("traces", traces);
		
		return new ModelAndView("com/xymn/logistics/logisticsorder/toOrderDetail");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(LogisticsOrderEntity logisticsOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try{
			//自定义追加查询条件
			this.logisticsOrderService.queryOrderListByCondition(logisticsOrder,dataGrid);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除物流跟踪订单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LogisticsOrderEntity logisticsOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		logisticsOrder = systemService.getEntity(LogisticsOrderEntity.class, logisticsOrder.getId());
		message = "物流跟踪订单删除成功";
		try{
			logisticsOrderService.delete(logisticsOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物流跟踪订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除物流跟踪订单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物流跟踪订单删除成功";
		try{
			for(String id:ids.split(",")){
				LogisticsOrderEntity logisticsOrder = systemService.getEntity(LogisticsOrderEntity.class, 
				id
				);
				logisticsOrderService.delete(logisticsOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "物流跟踪订单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加物流跟踪订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LogisticsOrderEntity logisticsOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物流跟踪订单添加成功";
		try{
			logisticsOrderService.save(logisticsOrder);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物流跟踪订单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新物流跟踪订单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LogisticsOrderEntity logisticsOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物流跟踪订单更新成功";
		LogisticsOrderEntity t = logisticsOrderService.get(LogisticsOrderEntity.class, logisticsOrder.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(logisticsOrder, t);
			logisticsOrderService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物流跟踪订单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 物流跟踪订单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LogisticsOrderEntity logisticsOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(logisticsOrder.getId())) {
			logisticsOrder = logisticsOrderService.getEntity(LogisticsOrderEntity.class, logisticsOrder.getId());
			req.setAttribute("logisticsOrderPage", logisticsOrder);
		}
		return new ModelAndView("com/xymn/logistics/logisticsorder/logisticsOrder-add");
	}
	/**
	 * 物流跟踪订单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LogisticsOrderEntity logisticsOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(logisticsOrder.getId())) {
			logisticsOrder = logisticsOrderService.getEntity(LogisticsOrderEntity.class, logisticsOrder.getId());
			req.setAttribute("logisticsOrderPage", logisticsOrder);
		}
		return new ModelAndView("com/jeecg/logisticsorder/logisticsOrder-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","logisticsOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LogisticsOrderEntity logisticsOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LogisticsOrderEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, logisticsOrder, request.getParameterMap());
		List<LogisticsOrderEntity> logisticsOrders = this.logisticsOrderService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"物流跟踪订单");
		modelMap.put(NormalExcelConstants.CLASS,LogisticsOrderEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物流跟踪订单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,logisticsOrders);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LogisticsOrderEntity logisticsOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"物流跟踪订单");
    	modelMap.put(NormalExcelConstants.CLASS,LogisticsOrderEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物流跟踪订单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<LogisticsOrderEntity> listLogisticsOrderEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LogisticsOrderEntity.class,params);
				for (LogisticsOrderEntity logisticsOrder : listLogisticsOrderEntitys) {
					logisticsOrderService.save(logisticsOrder);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="物流跟踪订单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LogisticsOrderEntity>> list() {
		List<LogisticsOrderEntity> listLogisticsOrders=logisticsOrderService.getList(LogisticsOrderEntity.class);
		return Result.success(listLogisticsOrders);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取物流跟踪订单信息",notes="根据ID获取物流跟踪订单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LogisticsOrderEntity task = logisticsOrderService.get(LogisticsOrderEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取物流跟踪订单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建物流跟踪订单")
	public ResponseMessage<?> create(@ApiParam(name="物流跟踪订单对象")@RequestBody LogisticsOrderEntity logisticsOrder, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LogisticsOrderEntity>> failures = validator.validate(logisticsOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			logisticsOrderService.save(logisticsOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("物流跟踪订单信息保存失败");
		}
		return Result.success(logisticsOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新物流跟踪订单",notes="更新物流跟踪订单")
	public ResponseMessage<?> update(@ApiParam(name="物流跟踪订单对象")@RequestBody LogisticsOrderEntity logisticsOrder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LogisticsOrderEntity>> failures = validator.validate(logisticsOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			logisticsOrderService.saveOrUpdate(logisticsOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新物流跟踪订单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新物流跟踪订单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除物流跟踪订单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			logisticsOrderService.deleteEntityById(LogisticsOrderEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("物流跟踪订单删除失败");
		}

		return Result.success();
	}
	
	
	@RequestMapping(params = "resolvingExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson resolvingExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			try {
				InputStream in = file.getInputStream();
				List<String> names = XLSX2CSV.getSheets(in);
				j.setObj(names);
				j.setMsg("文件加载成功！");
			} catch (Exception e) {
				j.setMsg("文件加载失败！");
				j.setSuccess(false);
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(params = "saveExcelData", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveExcelData(HttpServletRequest request, HttpServletResponse response,String shopName,String sheetName,HttpSession session) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			try {
				InputStream in = file.getInputStream();
				String fileName = file.getOriginalFilename();
				String name = "";
				if(fileName!=null&&fileName.contains(".")){
					name = fileName.substring(fileName.indexOf(".") + 1, fileName.length());
				}
				
				// 对应列的excel下标
				Integer[] needNumber = new Integer[11];
				//判断列名是否存在
				Boolean flag = new Boolean(true);
				//获取主题第一行标题
				List<String> titles = new ArrayList<>();
				List<String[]> rows = null;
				// 判断后缀解析excel
				if ("xlsx".equals(name)) {
					rows = XLSX2CSV.readerExcel(in,new String[]{sheetName},30);
					for (int i = 0; i < rows.get(0).length; i++) {
						if(rows.get(0)[i]!=null){
							titles.add(rows.get(0)[i].trim().replaceAll("\"", ""));
						}
					}
					
					//必须列名集合
					List<String> neededvalue = new ArrayList<>(Arrays.asList("订单编号", "快递公司", "快递单号", "订单时间", "付款时间",
							"发货时间","交易金额","商品编号","商品名称","商品单价","商品数量"));
					
					//获取值的下标
					for (int i = 0; i < titles.size(); i++) {
						for (int k = 0; k < neededvalue.size(); k++) {
							if((titles.get(i)).equals((neededvalue.get(k)))){
								needNumber[k] = i;
								continue;
							}
						}
					}
					
					for (int k = 0; k < needNumber.length; k++) {
						if(needNumber[k]==null){
							flag = false;
							j.setMsg("缺失列名:"+neededvalue.get(k)+",请检查!!!");
						}
					}
					
				} else {
					flag = false;
					j.setMsg("文件名格式不对请检查!!!");
				}
				// 对应列的excel下标
				Integer[] allNumber = new Integer[21];
				//全列名集合
				List<String> needTitles = new ArrayList<>(Arrays.asList(
						"订单编号", "快递公司", "快递单号", "订单时间", "付款时间",
						"发货时间","交易金额","商品编号","商品名称","商品单价","商品数量","承诺发货时间",
						"收货人","手机","省","市","区","街道","SKU编号","外部编号","商品规格"));
				
				//主订单集合--订单
				List<LogisticsOrderEntity> list = new ArrayList<>();
				//子订单集合--产品
				List<LogisticsOrderProductEntity> products = new ArrayList<>();
				Boolean isConver = new Boolean(false);
				
				if(flag){
					//获取值的下标
					for (int i = 0; i < titles.size(); i++) {
						for (int k = 0; k < needTitles.size(); k++) {
							if((needTitles.get(k)).equals((titles.get(i)))){
								allNumber[k] = i;
								continue;
							}
						}
					}
					isConver = this.logisticsOrderService.convertBatchData(rows,allNumber,shopName,list,products,j);
				}
				
				TSUser user =  (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
				if(isConver&&user!=null){
					Boolean isCreate = this.logisticsOrderService.createMiddleTable(user);
					if(isCreate){
						this.logisticsOrderService.saveBatchData(list,products,user);
						//对数据尽心监控
						Integer count = this.logisticsOrderService.doActionMonitoring(user,shopName);
						j.setMsg("成功导入"+count+"条订单记录! , 大约需要整点时间进行物流跟踪信息匹配!请稍候查询");
					}else{
						j.setMsg("网络出现中断,请重试!!!");
						j.setSuccess(false);
					}
				}
				
			} catch (Exception e) {
				j.setMsg("文件解析失败！");
				j.setSuccess(false);
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
