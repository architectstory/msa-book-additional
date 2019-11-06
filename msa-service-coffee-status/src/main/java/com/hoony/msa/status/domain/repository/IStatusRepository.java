package com.hoony.msa.status.domain.repository;

import com.hoony.msa.status.domain.entity.StatusEntity;

import java.util.List;

public interface IStatusRepository {
	long getAllStatusCount();
	List<StatusEntity> getAllStatus(String page, String size, String sort, String sortKey);
	StatusEntity addStatus(StatusEntity statusEntity);
	StatusEntity modifyStatus(StatusEntity statusEntity);
	void removeStatus(List<StatusEntity> statusEntity);
}
