package com.xymn.user.userparameter.controller;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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
import com.xymn.user.userparameter.entity.WlUserParameterEntity;
import com.xymn.user.userparameter.service.WlUserParameterServiceI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 用户参数
 * @author onlineGenerator
 * @date 2018-03-22 18:48:54
 * @version V1.0   
 *
 */
@Api(value="WlUserParameter",description="用户参数",tags="wlUserParameterController")
@Controller
@RequestMapping("/wlUserParameterController")
public class WlUserParameterController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WlUserParameterController.class);

	@Autowired
	private WlUserParameterServiceI wlUserParameterService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 用户参数列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/xymn/user/userparameter/wlUserParameterList");
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
	public void datagrid(WlUserParameterEntity wlUserParameter,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WlUserParameterEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wlUserParameter, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.wlUserParameterService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除用户参数
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WlUserParameterEntity wlUserParameter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wlUserParameter = systemService.getEntity(WlUserParameterEntity.class, wlUserParameter.getId());
		message = "用户参数删除成功";
		try{
			wlUserParameterService.delete(wlUserParameter);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户参数删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除用户参数
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户参数删除成功";
		try{
			for(String id:ids.split(",")){
				WlUserParameterEntity wlUserParameter = systemService.getEntity(WlUserParameterEntity.class, 
				id
				);
				wlUserParameterService.delete(wlUserParameter);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户参数删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户参数
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WlUserParameterEntity wlUserParameter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户参数添加成功";
		try{
			wlUserParameterService.save(wlUserParameter);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户参数添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新用户参数
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WlUserParameterEntity wlUserParameter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户参数更新成功";
		WlUserParameterEntity t = wlUserParameterService.get(WlUserParameterEntity.class, wlUserParameter.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(wlUserParameter, t);
			wlUserParameterService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户参数更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 用户参数新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WlUserParameterEntity wlUserParameter, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wlUserParameter.getId())) {
			wlUserParameter = wlUserParameterService.getEntity(WlUserParameterEntity.class, wlUserParameter.getId());
			req.setAttribute("wlUserParameterPage", wlUserParameter);
		}
		return new ModelAndView("com/xymn/user/userparameter/wlUserParameter-add");
	}
	/**
	 * 用户参数编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WlUserParameterEntity wlUserParameter, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wlUserParameter.getId())) {
			wlUserParameter = wlUserParameterService.getEntity(WlUserParameterEntity.class, wlUserParameter.getId());
			req.setAttribute("wlUserParameterPage", wlUserParameter);
		}
		return new ModelAndView("com/xymn/user/userparameter/wlUserParameter-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","wlUserParameterController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WlUserParameterEntity wlUserParameter,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WlUserParameterEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wlUserParameter, request.getParameterMap());
		List<WlUserParameterEntity> wlUserParameters = this.wlUserParameterService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户参数");
		modelMap.put(NormalExcelConstants.CLASS,WlUserParameterEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户参数列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,wlUserParameters);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WlUserParameterEntity wlUserParameter,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"用户参数");
    	modelMap.put(NormalExcelConstants.CLASS,WlUserParameterEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户参数列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<WlUserParameterEntity> listWlUserParameterEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WlUserParameterEntity.class,params);
				for (WlUserParameterEntity wlUserParameter : listWlUserParameterEntitys) {
					wlUserParameterService.save(wlUserParameter);
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
	@ApiOperation(value="用户参数列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<WlUserParameterEntity>> list() {
		List<WlUserParameterEntity> listWlUserParameters=wlUserParameterService.getList(WlUserParameterEntity.class);
		return Result.success(listWlUserParameters);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取用户参数信息",notes="根据ID获取用户参数信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		WlUserParameterEntity task = wlUserParameterService.get(WlUserParameterEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取用户参数信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建用户参数")
	public ResponseMessage<?> create(@ApiParam(name="用户参数对象")@RequestBody WlUserParameterEntity wlUserParameter, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WlUserParameterEntity>> failures = validator.validate(wlUserParameter);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			wlUserParameterService.save(wlUserParameter);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("用户参数信息保存失败");
		}
		return Result.success(wlUserParameter);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新用户参数",notes="更新用户参数")
	public ResponseMessage<?> update(@ApiParam(name="用户参数对象")@RequestBody WlUserParameterEntity wlUserParameter) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<WlUserParameterEntity>> failures = validator.validate(wlUserParameter);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			wlUserParameterService.saveOrUpdate(wlUserParameter);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新用户参数信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新用户参数信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除用户参数")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			wlUserParameterService.deleteEntityById(WlUserParameterEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("用户参数删除失败");
		}

		return Result.success();
	}
	
	
	/**
	 * 添加用户参数
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddUserTime")
	@ResponseBody
	public AjaxJson doAddUserTime(WlUserParameterEntity wlUserParameter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户参数添加成功";
		try{
			TSUser sessionUser = ResourceUtil.getSessionUser();
			if(sessionUser!=null){
				wlUserParameterService.doAddUserTime(wlUserParameter,sessionUser);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}else{
				message = "保存失败,请重新登录";
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "用户参数添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
}
