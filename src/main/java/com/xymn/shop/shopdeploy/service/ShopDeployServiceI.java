package com.xymn.shop.shopdeploy.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;

public interface ShopDeployServiceI extends CommonService{
	
 	public void delete(ShopDeployEntity entity) throws Exception;
 	
 	public Serializable save(ShopDeployEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ShopDeployEntity entity) throws Exception;
 	
 	/**
 	 * 关联删除店铺和token
 	 * @param shopDeploy
 	 * @param findUniqueByProperty
 	 */
	public void deleteShopAndToken(ShopDeployEntity shopDeploy, ShopTokens findUniqueByProperty)throws Exception;
 	
}
