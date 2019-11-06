package com.hoony.msa.order.springboot.repository;

import com.hoony.msa.order.domain.entity.OrderEntity;
import com.hoony.msa.order.domain.repository.IOrderRepository;
import com.hoony.msa.order.domain.vo.OrderVO;
import com.hoony.msa.order.springboot.jvo.OrderJVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository {

    private Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @Autowired
    private IOrderJPARepository iOrderJPARepository;


    @Override
    public long getAllOrderCount() {

        return iOrderJPARepository.count();
    }

    @Override
    public List<OrderEntity> getAllOrder(String page, String size, String sort, String sortKey) {
        //Sort
        Sort sortObj = new Sort(Sort.Direction.DESC, sortKey);
        if(sort.equals("ASC"))
            sortObj = new Sort(Sort.Direction.ASC, sortKey);

        //Paging
        Pageable pageable =
                PageRequest.of(Integer.parseInt(page)-1
                        ,Integer.parseInt(size)
                        ,sortObj);

        //Result->JVOList
        Page<OrderJVO> orderEntityPages = iOrderJPARepository.findAll(pageable);
        List<OrderJVO> orderJVOList = orderEntityPages.getContent();

        List<OrderEntity> orderEntityList = new ArrayList<OrderEntity>();
        for (OrderJVO orderJVO : orderJVOList){

            //JVO->VO
            OrderVO orderVO = new OrderVO();
            orderVO.set_id(orderJVO.get_id());
            orderVO.setOrderName(orderJVO.getOrderName());
            orderVO.setUserId(orderJVO.getUserId());
            orderVO.setUserName(orderJVO.getUserName());

            //VO->Entity
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderVO(orderVO);
            orderEntityList.add(orderEntity);
        }

        return orderEntityList;
    }

    @Override
    public OrderEntity addOrder(OrderEntity orderEntity) {

        OrderJVO orderJVO = new OrderJVO();
        orderJVO.set_id(orderEntity.getOrderVO().get_id());
        orderJVO.setOrderName(orderEntity.getOrderVO().getOrderName());
        orderJVO.setUserId(orderEntity.getOrderVO().getUserId());
        orderJVO.setUserName(orderEntity.getOrderVO().getUserName());
        iOrderJPARepository.save(orderJVO);
        return orderEntity;
    }

    @Override
    public OrderEntity modifyOrder(OrderEntity orderEntity) {
        OrderJVO orderJVO = new OrderJVO();
        orderJVO.set_id(orderEntity.getOrderVO().get_id());
        orderJVO.setOrderName(orderEntity.getOrderVO().getOrderName());
        orderJVO.setUserId(orderEntity.getOrderVO().getUserId());
        orderJVO.setUserName(orderEntity.getOrderVO().getUserName());
        iOrderJPARepository.save(orderJVO);
        return orderEntity;
    }

    @Override
    public void removeOrder(List<OrderEntity> orderEntityList) {
        List<OrderJVO> orderJVOList = new ArrayList<>();
        int cnt = orderEntityList.size();
        for (int i=0; i<cnt; i++){
            OrderJVO orderJVO = new OrderJVO();
            orderJVO.set_id(orderEntityList.get(i).getOrderVO().get_id());
            orderJVOList.add(orderJVO);
        }
        iOrderJPARepository.deleteAll(orderJVOList);
    }
}
