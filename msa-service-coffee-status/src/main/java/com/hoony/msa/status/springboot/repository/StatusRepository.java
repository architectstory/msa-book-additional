package com.hoony.msa.status.springboot.repository;

import com.hoony.msa.status.domain.entity.StatusEntity;
import com.hoony.msa.status.domain.repository.IStatusRepository;
import com.hoony.msa.status.domain.vo.StatusVO;
import com.hoony.msa.status.springboot.jvo.StatusJVO;
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
public class StatusRepository implements IStatusRepository {

    private Logger logger = LoggerFactory.getLogger(StatusRepository.class);

    @Autowired
    private IStatusJPARepository iStatusJPARepository;


    @Override
    public long getAllStatusCount() {

        return iStatusJPARepository.count();
    }

    @Override
    public List<StatusEntity> getAllStatus(String page, String size, String sort, String sortKey) {
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
        Page<StatusJVO> statusEntityPages = iStatusJPARepository.findAll(pageable);
        List<StatusJVO> statusJVOList = statusEntityPages.getContent();

        List<StatusEntity> statusEntityList = new ArrayList<StatusEntity>();
        for (StatusJVO statusJVO : statusJVOList){

            //JVO->VO
            StatusVO statusVO = new StatusVO();
            statusVO.set_id(statusJVO.get_id());
            statusVO.setStatusName(statusJVO.getStatusName());
            statusVO.setOrderId(statusJVO.getOrderId());
            statusVO.setOrderName(statusJVO.getOrderName());
            statusVO.setUserId(statusJVO.getUserId());
            statusVO.setUserName(statusJVO.getUserName());

            //VO->Entity
            StatusEntity statusEntity = new StatusEntity();
            statusEntity.setStatusVO(statusVO);
            statusEntityList.add(statusEntity);
        }

        return statusEntityList;
    }

    @Override
    public StatusEntity addStatus(StatusEntity statusEntity) {

        StatusJVO statusJVO = new StatusJVO();
        statusJVO.set_id(statusEntity.getStatusVO().get_id());
        statusJVO.setStatusName(statusEntity.getStatusVO().getStatusName());
        statusJVO.setOrderId(statusEntity.getStatusVO().getOrderId());
        statusJVO.setOrderName(statusEntity.getStatusVO().getOrderName());
        statusJVO.setUserId(statusEntity.getStatusVO().getUserId());
        statusJVO.setUserName(statusEntity.getStatusVO().getUserName());
        iStatusJPARepository.save(statusJVO);
        return statusEntity;
    }

    @Override
    public StatusEntity modifyStatus(StatusEntity statusEntity) {
        StatusJVO statusJVO = new StatusJVO();
        statusJVO.set_id(statusEntity.getStatusVO().get_id());
        statusJVO.setStatusName(statusEntity.getStatusVO().getStatusName());
        statusJVO.setOrderId(statusEntity.getStatusVO().getOrderId());
        statusJVO.setOrderName(statusEntity.getStatusVO().getOrderName());
        statusJVO.setUserId(statusEntity.getStatusVO().getUserId());
        statusJVO.setUserName(statusEntity.getStatusVO().getUserName());
        iStatusJPARepository.save(statusJVO);
        return statusEntity;
    }

    @Override
    public void removeStatus(List<StatusEntity> statusEntityList) {
        List<StatusJVO> statusJVOList = new ArrayList<>();
        int cnt = statusEntityList.size();
        for (int i=0; i<cnt; i++){
            StatusJVO statusJVO = new StatusJVO();
            statusJVO.set_id(statusEntityList.get(i).getStatusVO().get_id());
            statusJVOList.add(statusJVO);
        }
        iStatusJPARepository.deleteAll(statusJVOList);
    }
}
