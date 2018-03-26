package com.xymn.basic.expressinfo.controller;
import java.io.IOException;
import java.util.ArrayList;
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
import com.xymn.basic.expressinfo.entity.ExpressInfoEntity;
import com.xymn.basic.expressinfo.service.ExpressInfoServiceI;
import com.xymn.connect.pinduoduo.service.PinduoduoOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 快递档案
 * @author onlineGenerator
 * @date 2018-03-01 15:52:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/expressInfoController")
@Api(value="ExpressInfo",description="快递档案",tags="expressInfoController")
public class ExpressInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ExpressInfoController.class);

	@Autowired
	private ExpressInfoServiceI expressInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private PinduoduoOrderService pinduoduoOrderService;


	/**
	 * 快递档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/xymn/basic/expressinfo/expressInfoList");
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
	public void datagrid(ExpressInfoEntity expressInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ExpressInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, expressInfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.expressInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除快递档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ExpressInfoEntity expressInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		expressInfo = systemService.getEntity(ExpressInfoEntity.class, expressInfo.getId());
		message = "快递档案删除成功";
		try{
			expressInfoService.delete(expressInfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快递档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除快递档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "快递档案删除成功";
		try{
			for(String id:ids.split(",")){
				ExpressInfoEntity expressInfo = systemService.getEntity(ExpressInfoEntity.class, 
				id
				);
				expressInfoService.delete(expressInfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "快递档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 


	/**
	 * 添加快递档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ExpressInfoEntity expressInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "快递档案添加成功";
		try{
			expressInfoService.save(expressInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快递档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新快递档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ExpressInfoEntity expressInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "快递档案更新成功";
		ExpressInfoEntity t = expressInfoService.get(ExpressInfoEntity.class, expressInfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(expressInfo, t);
			expressInfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "快递档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 快递档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ExpressInfoEntity expressInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(expressInfo.getId())) {
			expressInfo = expressInfoService.getEntity(ExpressInfoEntity.class, expressInfo.getId());
			req.setAttribute("expressInfoPage", expressInfo);
		}
		return new ModelAndView("com/xymn/basic/expressinfo/expressInfo-add");
	}
	/**
	 * 快递档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ExpressInfoEntity expressInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(expressInfo.getId())) {
			expressInfo = expressInfoService.getEntity(ExpressInfoEntity.class, expressInfo.getId());
			req.setAttribute("expressInfoPage", expressInfo);
		}
		return new ModelAndView("com/xymn/basic/expressinfo/expressInfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","expressInfoController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ExpressInfoEntity expressInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ExpressInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, expressInfo, request.getParameterMap());
		List<ExpressInfoEntity> expressInfos = this.expressInfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"快递档案");
		modelMap.put(NormalExcelConstants.CLASS,ExpressInfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("快递档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,expressInfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ExpressInfoEntity expressInfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"快递档案");
    	modelMap.put(NormalExcelConstants.CLASS,ExpressInfoEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("快递档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ExpressInfoEntity> listExpressInfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ExpressInfoEntity.class,params);
				for (ExpressInfoEntity expressInfo : listExpressInfoEntitys) {
					expressInfoService.save(expressInfo);
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
	@ApiOperation(value="快递档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ExpressInfoEntity>> list() {
		List<ExpressInfoEntity> listExpressInfos=expressInfoService.getList(ExpressInfoEntity.class);
		return Result.success(listExpressInfos);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取快递档案信息",notes="根据ID获取快递档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ExpressInfoEntity task = expressInfoService.get(ExpressInfoEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取快递档案信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建快递档案")
	public ResponseMessage<?> create(@ApiParam(name="快递档案对象")@RequestBody ExpressInfoEntity expressInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ExpressInfoEntity>> failures = validator.validate(expressInfo);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			expressInfoService.save(expressInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("快递档案信息保存失败");
		}
		return Result.success(expressInfo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新快递档案",notes="更新快递档案")
	public ResponseMessage<?> update(@ApiParam(name="快递档案对象")@RequestBody ExpressInfoEntity expressInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ExpressInfoEntity>> failures = validator.validate(expressInfo);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			expressInfoService.saveOrUpdate(expressInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新快递档案信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新快递档案信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除快递档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			expressInfoService.deleteEntityById(ExpressInfoEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("快递档案删除失败");
		}

		return Result.success();
	}
	
	
	/**
	  * 同步快递
	  * 
	  * @return
	  */
	 @RequestMapping(params = "doSyncExpress")
	 @ResponseBody
	 public AjaxJson doSyncExpress(HttpServletRequest request){
		 String message = null;
		 AjaxJson j = new AjaxJson();
		 message = "同步快递档案成功";
		 try{
			 
			pinduoduoOrderService.doSyncExpress();
		 }catch(Exception e){
			 e.printStackTrace();
			 message = "同步快递档案失败";
			 throw new BusinessException(e.getMessage());
		 }
		 j.setMsg(message);
		 return j;
	 }
	
	
}
