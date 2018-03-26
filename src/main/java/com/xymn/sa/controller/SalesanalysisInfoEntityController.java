package com.xymn.sa.controller;
import com.xymn.sa.entity.SalesanalysisInfoEntity;
import com.xymn.sa.service.SalesanalysisInfoEntityServiceI;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 销售信息表
 * @author onlineGenerator
 * @date 2018-03-02 15:06:23
 * @version V1.0   
 *
 */
@Api(value="SalesanalysisInfoEntity",description="销售信息表",tags="SalesanalysisInfoEntityController")
@Controller
@RequestMapping("/salesanalysisInfoEntityController")
public class SalesanalysisInfoEntityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SalesanalysisInfoEntityController.class);

	@Autowired
	private SalesanalysisInfoEntityServiceI saSalesanalysisInfoEntityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;



	/**
	 * 销售信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/xymn/sa/salesanalysisInfoEntityList");
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
	public void datagrid(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SalesanalysisInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, saSalesanalysisInfoEntity);
		//自定义追加查询条件
		String createDate_begin = request.getParameter("createDate_begin");
		String createDate_end = request.getParameter("createDate_end");
		String userName=request.getParameter("userName");
		try{
		//自定义追加查询条件
		if(StringUtil.isNotEmpty(createDate_begin)){
			cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(createDate_begin));
		}
		if(StringUtil.isNotEmpty(createDate_end)){
			cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(createDate_end));
		}
		if(StringUtil.isNotEmpty(userName)){
			cq.put("userName",userName);
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.saSalesanalysisInfoEntityService.getDataGridReturn(cq, true);
		String total_salary =
				String.valueOf(saSalesanalysisInfoEntityService.findOneForJdbc("select sum(trading_amount) as ssum from t_sa_salesAnalysis_info").get("ssum"));
		dataGrid.setFooter("tradingAmount:"+total_salary+",rechargeType:合计");
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除销售信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		saSalesanalysisInfoEntity = systemService.getEntity(SalesanalysisInfoEntity.class, saSalesanalysisInfoEntity.getId());
		message = "销售信息表删除成功";
		try{
			saSalesanalysisInfoEntityService.delete(saSalesanalysisInfoEntity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "销售信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除销售信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "销售信息表删除成功";
		try{
			for(String id:ids.split(",")){
				SalesanalysisInfoEntity saSalesanalysisInfoEntity = systemService.getEntity(SalesanalysisInfoEntity.class,
				id
				);
				saSalesanalysisInfoEntityService.delete(saSalesanalysisInfoEntity);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "销售信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加销售信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "销售信息表添加成功";
		try{
			saSalesanalysisInfoEntityService.save(saSalesanalysisInfoEntity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "销售信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新销售信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "销售信息表更新成功";
		SalesanalysisInfoEntity t = saSalesanalysisInfoEntityService.get(SalesanalysisInfoEntity.class, saSalesanalysisInfoEntity.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(saSalesanalysisInfoEntity, t);
			saSalesanalysisInfoEntityService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "销售信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 销售信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(saSalesanalysisInfoEntity.getId())) {
			saSalesanalysisInfoEntity = saSalesanalysisInfoEntityService.getEntity(SalesanalysisInfoEntity.class, saSalesanalysisInfoEntity.getId());
			req.setAttribute("saSalesanalysisInfoEntityPage", saSalesanalysisInfoEntity);
		}
		return new ModelAndView("com/xymn/sa/salesanalysisInfoEntity-add");
	}
	/**
	 * 销售信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(saSalesanalysisInfoEntity.getId())) {
			saSalesanalysisInfoEntity = saSalesanalysisInfoEntityService.getEntity(SalesanalysisInfoEntity.class, saSalesanalysisInfoEntity.getId());
			req.setAttribute("saSalesanalysisInfoEntityPage", saSalesanalysisInfoEntity);
		}
		return new ModelAndView("com/xymn/sa/salesanalysisInfoEntity-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","saSalesanalysisInfoEntityController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(SalesanalysisInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, saSalesanalysisInfoEntity, request.getParameterMap());
		List<SalesanalysisInfoEntity> saSalesanalysisInfoEntitys = this.saSalesanalysisInfoEntityService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"销售信息表");
		modelMap.put(NormalExcelConstants.CLASS,SalesanalysisInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("销售信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,saSalesanalysisInfoEntitys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(SalesanalysisInfoEntity saSalesanalysisInfoEntity, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid, ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"销售信息表");
    	modelMap.put(NormalExcelConstants.CLASS,SalesanalysisInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("销售信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<SalesanalysisInfoEntity> listSaSalesanalysisInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),SalesanalysisInfoEntity.class,params);
				for (SalesanalysisInfoEntity saSalesanalysisInfoEntity : listSaSalesanalysisInfoEntitys) {
					saSalesanalysisInfoEntityService.save(saSalesanalysisInfoEntity);
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
	@ApiOperation(value="销售信息表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<SalesanalysisInfoEntity>> list() {
		List<SalesanalysisInfoEntity> listSaSalesanalysisInfoEntitys=saSalesanalysisInfoEntityService.getList(SalesanalysisInfoEntity.class);
		return Result.success(listSaSalesanalysisInfoEntitys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取销售信息表信息",notes="根据ID获取销售信息表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		SalesanalysisInfoEntity task = saSalesanalysisInfoEntityService.get(SalesanalysisInfoEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取销售信息表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建销售信息表")
	public ResponseMessage<?> create(@ApiParam(name="销售信息表对象")@RequestBody SalesanalysisInfoEntity saSalesanalysisInfoEntity, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SalesanalysisInfoEntity>> failures = validator.validate(saSalesanalysisInfoEntity);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			saSalesanalysisInfoEntityService.save(saSalesanalysisInfoEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("销售信息表信息保存失败");
		}
		return Result.success(saSalesanalysisInfoEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新销售信息表",notes="更新销售信息表")
	public ResponseMessage<?> update(@ApiParam(name="销售信息表对象")@RequestBody SalesanalysisInfoEntity saSalesanalysisInfoEntity) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SalesanalysisInfoEntity>> failures = validator.validate(saSalesanalysisInfoEntity);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			saSalesanalysisInfoEntityService.saveOrUpdate(saSalesanalysisInfoEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新销售信息表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新销售信息表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除销售信息表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			saSalesanalysisInfoEntityService.deleteEntityById(SalesanalysisInfoEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("销售信息表删除失败");
		}

		return Result.success();
	}
}
