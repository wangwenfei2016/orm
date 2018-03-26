package com.xymn.logistics.logisticsorder.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MyRowMapper implements RowMapper<LogisticsOrderEntity>{

	@Override
	public LogisticsOrderEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		LogisticsOrderEntity entity = new LogisticsOrderEntity();
		
		entity.setId(rs.getString("id"));
		entity.setOrderCode(rs.getString("orderCode"));
		entity.setOrderTime(rs.getTimestamp("orderTime"));
		entity.setPaymentTime(rs.getTimestamp("paymentTime"));
		entity.setStore(rs.getString("street"));
		entity.setExpressNumber(rs.getString("expressNumber"));
		entity.setExpressCompany(rs.getString("expressCompany"));
		entity.setDeliveryTime(rs.getTimestamp("deliveryTime"));
		entity.setPromiseDeliveryTime(rs.getTimestamp("promiseDeliveryTime"));
		entity.setConsignee(rs.getString("consignee"));
		entity.setPhone(rs.getString("phone"));
		entity.setAmount(rs.getDouble("amount"));
		entity.setProvince(rs.getString("province"));
		entity.setCity(rs.getString("city"));
		entity.setReason(rs.getString("reason"));
		entity.setStreet(rs.getString("street"));
		return entity;
	}

}
