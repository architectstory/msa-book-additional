package com.hoony.msa.order.springboot.repository;

import com.hoony.msa.order.springboot.jvo.OrderJVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderJPARepository extends JpaRepository<OrderJVO, String> {
}
