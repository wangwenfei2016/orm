package com.xymn.connect.pinduoduo.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xymn.connect.pinduoduo.entity.ShopTokens;
import com.xymn.connect.pinduoduo.service.PinduoduoOrderService;
import com.xymn.shop.shopdeploy.entity.ShopDeployEntity;

@RequestMapping("/pinduoduoOrder")
@Controller
public class PinduduoController {
	

	@Autowired 
	private PinduoduoOrderService pinduoduoOrderService;
	@Autowired 
	private SystemService systemService;
	
	/**
	 * 批量授权店铺配置
	 * @return
	 */
	@RequestMapping(value="/callBack",method = RequestMethod.GET)
	public String impowerShop(String state, String code,HttpServletRequest request){
		if(state!=null&&code!=null){
			ShopTokens shopToken = new ShopTokens();
			shopToken.setCode(code);
			shopToken.setState(state);
			shopToken = pinduoduoOrderService.initAccessToken(shopToken);
			if("true".equals(shopToken.getSuccess())){
				//通过店铺id获取唯一token
				ShopTokens oldTokens = this.systemService.findUniqueByProperty(ShopTokens.class, "ownerId", shopToken.getOwnerId());
				//如果唯一token是null,代表第一次初始化关联拼多多店铺 , 不等于null 是重新授权或者重复授权    一个店铺只能产生一个token 不可重复 通过state 关联
				if(oldTokens!=null){
					ShopDeployEntity shopEntity = this.systemService.findUniqueByProperty(ShopDeployEntity.class, "state", oldTokens.getState());
					if(state.equals(shopEntity.getState())){//相同代表刷新 不相同代表重复授权
						oldTokens.setAccessToken(shopToken.getAccessToken());
						oldTokens.setRefreshToken(shopToken.getRefreshToken());
						oldTokens.setScope(shopToken.getScope());
						oldTokens.setReason(null);
						oldTokens.setSuccess("true");
						this.systemService.saveOrUpdate(shopToken);
						request.setAttribute("success", "成功");
						request.setAttribute("reason", "成功刷新授权");
					}else{
						request.setAttribute("success", "失败");
						request.setAttribute("reason", "同一店铺不能重复授权,请重新授权原店铺");
					}
				}else{
					ShopDeployEntity shopEntity = this.systemService.findUniqueByProperty(ShopDeployEntity.class, "state", shopToken.getState());
					shopToken.setUserId(shopEntity.getUserId());
					shopToken.setState(shopEntity.getState());
					this.systemService.save(shopToken);
					request.setAttribute("success", "成功");
					request.setAttribute("reason", "成功授权");
				}
			}else{
				request.setAttribute("success", "失败");
				request.setAttribute("reason", shopToken.getReason()+" , 请重试");
			}
		}else{
			request.setAttribute("success", "失败");
			request.setAttribute("reason", "拼多多返回异常!!!请重试");
		}
		return "com/xymn/shop/shopdeploy/shoptoken";
	}

}
