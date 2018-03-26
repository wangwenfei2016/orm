package com.xymn.user.userparameter.service;
import com.xymn.user.userparameter.entity.WlUserParameterEntity;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;

import java.io.Serializable;

public interface WlUserParameterServiceI extends CommonService{
	
 	public void delete(WlUserParameterEntity entity) throws Exception;
 	
 	public Serializable save(WlUserParameterEntity entity) throws Exception;
 	
 	public void saveOrUpdate(WlUserParameterEntity entity) throws Exception;
 	
 	/**
 	 * 保存用户限制时间
 	 * @param wlUserParameter
 	 * @param sessionUser
 	 */
	public void doAddUserTime(WlUserParameterEntity wlUserParameter, TSUser sessionUser)throws Exception;
 	
}
