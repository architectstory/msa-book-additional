package com.hoony.msa.order.domain.entity;

import com.hoony.msa.order.domain.vo.OrderVO;

public class OrderEntity {
    private OrderVO orderVO;

    public OrderVO getOrderVO() {
        return orderVO;
    }

    public void setOrderVO(OrderVO orderVO) {
        this.orderVO = orderVO;
    }
}
