package com.hoony.msa.status.domain.service;

import com.hoony.msa.status.domain.entity.StatusEntity;
import com.hoony.msa.status.domain.repository.IStatusRepository;
import com.hoony.msa.status.domain.vo.StatusVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class StatusService implements IStatusService {

	private Logger logger = LoggerFactory.getLogger(StatusService.class);

	private IStatusRepository iStatusRepository;

	public StatusService(IStatusRepository iStatusRepository) {

		this.iStatusRepository = iStatusRepository;
	}

	@Override
	public long getAllStatusCount() {
		return iStatusRepository.getAllStatusCount(); }

	@Override
	public List<StatusVO> getAllStatus(String page, String size, String sort, String sortKey) {

		List<StatusEntity> statusEntities = iStatusRepository.getAllStatus(page, size, sort, sortKey);

		List<StatusVO> statusVOList = new ArrayList();

		if(statusEntities==null) return statusVOList;

		for (StatusEntity statusEntity : statusEntities){
			statusVOList.add(statusEntity.getStatusVO());
		}
		return statusVOList;
	}

	@Override
	public StatusVO addStatus(StatusVO statusVO) {
		StatusEntity statusEntity = new StatusEntity();
		statusEntity.setStatusVO(statusVO);
		iStatusRepository.addStatus(statusEntity);
		return statusVO;
	}

	@Override
	public StatusVO modifyStatus(StatusVO statusVO) {
		StatusEntity statusEntity = new StatusEntity();
		statusEntity.setStatusVO(statusVO);
		iStatusRepository.modifyStatus(statusEntity);
		return statusVO;
	}

	@Override
	public void removeStatus(List<StatusVO> statusVOList) {
		List<StatusEntity> statusEntityList = new ArrayList<>();

		int pCnt = statusVOList.size();
		for(int i=0; i<pCnt; i++){
			StatusVO statusVO = statusVOList.get(i);
			StatusEntity statusEntity = new StatusEntity();
			statusEntity.setStatusVO(statusVO);
			statusEntityList.add(statusEntity);
		}

		iStatusRepository.removeStatus(statusEntityList);
	}

}