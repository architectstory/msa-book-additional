package com.hoony.msa.order.domain.service;

import com.hoony.msa.order.domain.entity.OrderEntity;
import com.hoony.msa.order.domain.repository.IOrderRepository;
import com.hoony.msa.order.domain.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	private IOrderRepository iOrderRepository;

	public OrderService(IOrderRepository iOrderRepository) {

		this.iOrderRepository = iOrderRepository;
	}

	@Override
	public long getAllOrderCount() {
		return iOrderRepository.getAllOrderCount(); }

	@Override
	public List<OrderVO> getAllOrder(String page, String size, String sort, String sortKey) {

		List<OrderEntity> orderEntities = iOrderRepository.getAllOrder(page, size, sort, sortKey);

		List<OrderVO> orderVOList = new ArrayList();

		if(orderEntities==null) return orderVOList;

		for (OrderEntity orderEntity : orderEntities){
			orderVOList.add(orderEntity.getOrderVO());
		}
		return orderVOList;
	}

	@Override
	public OrderVO addOrder(OrderVO orderVO) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderVO(orderVO);
		iOrderRepository.addOrder(orderEntity);
		return orderVO;
	}

	@Override
	public OrderVO modifyOrder(OrderVO orderVO) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderVO(orderVO);
		iOrderRepository.modifyOrder(orderEntity);
		return orderVO;
	}

	@Override
	public void removeOrder(List<OrderVO> orderVOList) {
		List<OrderEntity> orderEntityList = new ArrayList<>();

		int pCnt = orderVOList.size();
		for(int i=0; i<pCnt; i++){
			OrderVO orderVO = orderVOList.get(i);
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setOrderVO(orderVO);
			orderEntityList.add(orderEntity);
		}

		iOrderRepository.removeOrder(orderEntityList);
	}

}