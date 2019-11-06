package com.hoony.msa.order.domain.repository;

import com.hoony.msa.order.domain.entity.OrderEntity;

import java.util.List;

public interface IOrderRepository {
	long getAllOrderCount();
	List<OrderEntity> getAllOrder(String page, String size, String sort, String sortKey);
	OrderEntity addOrder(OrderEntity orderEntity);
	OrderEntity modifyOrder(OrderEntity orderEntity);
	void removeOrder(List<OrderEntity> orderEntity);
}
