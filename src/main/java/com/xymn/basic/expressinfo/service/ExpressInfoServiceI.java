package com.xymn.basic.expressinfo.service;
import com.xymn.basic.expressinfo.entity.ExpressInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ExpressInfoServiceI extends CommonService{
	
 	public void delete(ExpressInfoEntity entity) throws Exception;
 	
 	public Serializable save(ExpressInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ExpressInfoEntity entity) throws Exception;
 	
}
