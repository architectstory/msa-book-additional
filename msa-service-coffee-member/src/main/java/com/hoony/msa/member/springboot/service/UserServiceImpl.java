package com.hoony.msa.member.springboot.service;

import com.hoony.msa.member.domain.repository.IUserRepository;
import com.hoony.msa.member.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends UserService {

    public UserServiceImpl(IUserRepository iUserRepository) {

        super(iUserRepository);
    }
}