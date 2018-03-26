package com.xymn.shop.shopdeploy.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;
import com.xymn.shop.shopdeploy.service.ShopDeployServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 店铺配置
 * @author onlineGenerator
 * @date 2018-03-01 15:12:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/shopDeployController")
@Api(value="ShopDeploy",description="店铺配置",tags="shopDeployController")
public class ShopDeployController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ShopDeployController.class);

	@Autowired
	private ShopDeployServiceI shopDeployService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 店铺配置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/xymn/shop/shopdeploy/shopDeployList");
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
	public void datagrid(ShopDeployEntity shopDeploy,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ShopDeployEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, shopDeploy, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.shopDeployService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除店铺配置
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ShopDeployEntity shopDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		shopDeploy = systemService.getEntity(ShopDeployEntity.class, shopDeploy.getId());
		message = "店铺配置删除成功";
		try{
			shopDeployService.delete(shopDeploy);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除店铺配置
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "店铺配置删除成功";
		try{
			for(String id:ids.split(",")){
				ShopDeployEntity shopDeploy = systemService.getEntity(ShopDeployEntity.class,id);
				ShopTokens findUniqueByProperty = this.systemService.findUniqueByProperty(ShopTokens.class, "state", shopDeploy.getState());
				
				shopDeployService.deleteShopAndToken(shopDeploy,findUniqueByProperty);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 /**
	  * 店铺开启监控
	  * @return
	  */
	 @RequestMapping(params = "doMonitoring")
	 @ResponseBody
	 public AjaxJson doMonitoring(String id,HttpServletRequest request){
		 String message = null;
		 AjaxJson j = new AjaxJson();
		 message = "店铺配置删除成功";
		 try{
			 ShopDeployEntity shopDeploy = systemService.getEntity(ShopDeployEntity.class,id);
			 shopDeployService.delete(shopDeploy);
			 systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		 }catch(Exception e){
			 e.printStackTrace();
			 message = "店铺配置删除失败";
			 throw new BusinessException(e.getMessage());
		 }
		 j.setMsg(message);
		 return j;
	 }


	/**
	 * 添加店铺配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ShopDeployEntity shopDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "店铺配置添加成功";
		try{
			shopDeployService.save(shopDeploy);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "店铺配置添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新店铺配置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ShopDeployEntity shopDeploy, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "店铺配置更新成功";
		ShopDeployEntity t = shopDeployService.get(ShopDeployEntity.class, shopDeploy.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(shopDeploy, t);
			shopDeployService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "店铺配置更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 店铺配置新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ShopDeployEntity shopDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(shopDeploy.getId())) {
			shopDeploy = shopDeployService.getEntity(ShopDeployEntity.class, shopDeploy.getId());
			req.setAttribute("shopDeployPage", shopDeploy);
		}
		return new ModelAndView("com/xymn/shop/shopdeploy/shopDeploy-add");
	}
	/**
	 * 店铺配置编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ShopDeployEntity shopDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(shopDeploy.getId())) {
			shopDeploy = shopDeployService.getEntity(ShopDeployEntity.class, shopDeploy.getId());
			req.setAttribute("shopDeployPage", shopDeploy);
		}
		return new ModelAndView("com/xymn/shop/shopdeploy/shopDeploy-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","shopDeployController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ShopDeployEntity shopDeploy,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ShopDeployEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, shopDeploy, request.getParameterMap());
		List<ShopDeployEntity> shopDeploys = this.shopDeployService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"店铺配置");
		modelMap.put(NormalExcelConstants.CLASS,ShopDeployEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("店铺配置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,shopDeploys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ShopDeployEntity shopDeploy,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"店铺配置");
    	modelMap.put(NormalExcelConstants.CLASS,ShopDeployEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("店铺配置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ShopDeployEntity> listShopDeployEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ShopDeployEntity.class,params);
				for (ShopDeployEntity shopDeploy : listShopDeployEntitys) {
					shopDeployService.save(shopDeploy);
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
	@ApiOperation(value="店铺配置列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ShopDeployEntity>> list() {
		List<ShopDeployEntity> listShopDeploys=shopDeployService.getList(ShopDeployEntity.class);
		return Result.success(listShopDeploys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取店铺配置信息",notes="根据ID获取店铺配置信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ShopDeployEntity task = shopDeployService.get(ShopDeployEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取店铺配置信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建店铺配置")
	public ResponseMessage<?> create(@ApiParam(name="店铺配置对象")@RequestBody ShopDeployEntity shopDeploy, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ShopDeployEntity>> failures = validator.validate(shopDeploy);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			shopDeployService.save(shopDeploy);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("店铺配置信息保存失败");
		}
		return Result.success(shopDeploy);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新店铺配置",notes="更新店铺配置")
	public ResponseMessage<?> update(@ApiParam(name="店铺配置对象")@RequestBody ShopDeployEntity shopDeploy) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ShopDeployEntity>> failures = validator.validate(shopDeploy);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			shopDeployService.saveOrUpdate(shopDeploy);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新店铺配置信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新店铺配置信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除店铺配置")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			shopDeployService.deleteEntityById(ShopDeployEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("店铺配置删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 批量授权店铺配置
	 * @return
	 */
	 @RequestMapping(params = "impowerShop")
	@ResponseBody
	public AjaxJson impowerShop(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "授权成功";
		try{
			for(String id:ids.split(",")){
				ShopDeployEntity shopDeploy = systemService.getEntity(ShopDeployEntity.class, id);
				shopDeploy.setImpowerStatus("0");
				shopDeploy.setImpowerTimeLimit(new Date());
				shopDeployService.updateEntitie(shopDeploy);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "授权失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 
}
