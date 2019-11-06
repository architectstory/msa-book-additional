package com.hoony.msa.status.domain.service;

import com.hoony.msa.status.domain.vo.StatusVO;

import java.util.List;

public interface IStatusService {

	long getAllStatusCount();
	List<StatusVO> getAllStatus(String page, String size, String sort, String sortKey);

	StatusVO addStatus(StatusVO statusVO);
	StatusVO modifyStatus(StatusVO statusVO);
	void removeStatus(List<StatusVO> statusVOList);
}