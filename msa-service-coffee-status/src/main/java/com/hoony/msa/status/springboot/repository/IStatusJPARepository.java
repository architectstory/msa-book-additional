package com.hoony.msa.status.springboot.repository;

import com.hoony.msa.status.springboot.jvo.StatusJVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusJPARepository extends JpaRepository<StatusJVO, String> {
}
