package com.xymn.sa.service.impl;
import com.xymn.sa.service.SalesanalysisInfoEntityServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.xymn.sa.entity.SalesanalysisInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("salesanalysisInfoEntityService")
@Transactional
public class SalesanalysisInfoEntityServiceImpl extends CommonServiceImpl implements SalesanalysisInfoEntityServiceI {


	public void delete(SalesanalysisInfoEntity entity) throws Exception{
		super.delete(entity);
		//执行删除操作增强业务
		this.doDelBus(entity);
	}

	public Serializable save(SalesanalysisInfoEntity entity) throws Exception{
		Serializable t = super.save(entity);
		//执行新增操作增强业务
		this.doAddBus(entity);
		return t;
	}

	public void saveOrUpdate(SalesanalysisInfoEntity entity) throws Exception{
		super.saveOrUpdate(entity);
		//执行更新操作增强业务
		this.doUpdateBus(entity);
	}

	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(SalesanalysisInfoEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
		//-----------------sql增强 end------------------------------

		//-----------------java增强 start---------------------------
		//-----------------java增强 end-----------------------------
	}
	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(SalesanalysisInfoEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
		//-----------------sql增强 end------------------------------

		//-----------------java增强 start---------------------------
		//-----------------java增强 end-----------------------------
	}
	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(SalesanalysisInfoEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
		//-----------------sql增强 end------------------------------

		//-----------------java增强 start---------------------------
		//-----------------java增强 end-----------------------------
	}

	private Map<String,Object> populationMap(SalesanalysisInfoEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("recharge_type", t.getRechargeType());
		map.put("trading_amount", t.getTradingAmount());
		map.put("user_name", t.getUserName());
		map.put("real_name", t.getRealName());
		map.put("company", t.getCompany());
		map.put("mobile_phone", t.getMobilePhone());
		map.put("pay_unit_price", t.getPayUnitPrice());
		map.put("pay_number", t.getPayNumber());
		map.put("recharge_day", t.getRechargeDay());
		return map;
	}

	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
	public String replaceVal(String sql,SalesanalysisInfoEntity t){
		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
		sql  = sql.replace("#{recharge_type}",String.valueOf(t.getRechargeType()));
		sql  = sql.replace("#{trading_amount}",String.valueOf(t.getTradingAmount()));
		sql  = sql.replace("#{user_name}",String.valueOf(t.getUserName()));
		sql  = sql.replace("#{real_name}",String.valueOf(t.getRealName()));
		sql  = sql.replace("#{company}",String.valueOf(t.getCompany()));
		sql  = sql.replace("#{mobile_phone}",String.valueOf(t.getMobilePhone()));
		sql  = sql.replace("#{pay_unit_price}",String.valueOf(t.getPayUnitPrice()));
		sql  = sql.replace("#{pay_number}",String.valueOf(t.getPayNumber()));
		sql  = sql.replace("#{recharge_day}",String.valueOf(t.getRechargeDay()));
		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
		return sql;
	}

	/**
	 * 执行JAVA增强
	 */
	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("t_sa_salesanalysis_info",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			}
		}
	}
}