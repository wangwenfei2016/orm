package com.xymn.sa.service;
import com.xymn.sa.entity.SalesanalysisInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface SalesanalysisInfoEntityServiceI extends CommonService{
	
 	public void delete(SalesanalysisInfoEntity entity) throws Exception;
 	
 	public Serializable save(SalesanalysisInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(SalesanalysisInfoEntity entity) throws Exception;
 	
}
