package com.hoony.msa.order.springboot.service;

import com.hoony.msa.order.domain.repository.IOrderRepository;
import com.hoony.msa.order.domain.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderServiceImpl extends OrderService {

    public OrderServiceImpl(IOrderRepository iOrderRepository) {

        super(iOrderRepository);
    }
}