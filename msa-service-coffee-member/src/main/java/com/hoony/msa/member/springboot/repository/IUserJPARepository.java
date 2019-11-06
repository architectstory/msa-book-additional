package com.hoony.msa.member.springboot.repository;

import com.hoony.msa.member.springboot.jvo.UserJVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserJPARepository extends JpaRepository<UserJVO, String> {
}
