package com.hoony.msa.status.springboot.service;

import com.hoony.msa.status.domain.repository.IStatusRepository;
import com.hoony.msa.status.domain.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl extends StatusService {

    public StatusServiceImpl(IStatusRepository iStatusRepository) {

        super(iStatusRepository);
    }
}