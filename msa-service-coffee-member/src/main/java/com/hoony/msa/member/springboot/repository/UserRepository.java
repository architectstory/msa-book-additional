package com.hoony.msa.member.springboot.repository;

import com.hoony.msa.member.domain.entity.UserEntity;
import com.hoony.msa.member.domain.repository.IUserRepository;
import com.hoony.msa.member.domain.vo.UserVO;
import com.hoony.msa.member.springboot.jvo.UserJVO;
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
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private IUserJPARepository iUserJPARepository;


    @Override
    public long getAllUserCount() {

        return iUserJPARepository.count();
    }

    @Override
    public List<UserEntity> getAllUser(String page, String size, String sort, String sortKey) {
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
        Page<UserJVO> userEntityPages = iUserJPARepository.findAll(pageable);
        List<UserJVO> userJVOList = userEntityPages.getContent();

        List<UserEntity> userEntityList = new ArrayList<UserEntity>();
        for (UserJVO userJVO : userJVOList){

            //JVO->VO
            UserVO userVO = new UserVO();
            userVO.set_id(userJVO.get_id());
            userVO.setUserName(userJVO.getUserName());

            //VO->Entity
            UserEntity userEntity = new UserEntity();
            userEntity.setUserVO(userVO);
            userEntityList.add(userEntity);
        }

        return userEntityList;
    }

    @Override
    public UserEntity getUserById(UserEntity userEntity) {
        UserJVO userJVO = new UserJVO();
        userJVO.set_id(userEntity.getUserVO().get_id());
        Optional<UserJVO> optionalUserJVO = iUserJPARepository.findById(userJVO.get_id());

        UserVO userVO = new UserVO();
        userVO.set_id(optionalUserJVO.get().get_id());
        userVO.setUserName(optionalUserJVO.get().getUserName());

        UserEntity resultUserEntity = new UserEntity();
        resultUserEntity.setUserVO(userVO);

        return resultUserEntity;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {

        UserJVO userJVO = new UserJVO();
        userJVO.set_id(userEntity.getUserVO().get_id());
        userJVO.setUserName(userEntity.getUserVO().getUserName());
        iUserJPARepository.save(userJVO);
        return userEntity;
    }

    @Override
    public UserEntity modifyUser(UserEntity userEntity) {
        UserJVO userJVO = new UserJVO();
        userJVO.set_id(userEntity.getUserVO().get_id());
        userJVO.setUserName(userEntity.getUserVO().getUserName());
        iUserJPARepository.save(userJVO);
        return userEntity;
    }

    @Override
    public void removeUser(List<UserEntity> userEntityList) {
        List<UserJVO> userJVOList = new ArrayList<>();
        int cnt = userEntityList.size();
        for (int i=0; i<cnt; i++){
            UserJVO userJVO = new UserJVO();
            userJVO.set_id(userEntityList.get(i).getUserVO().get_id());
            userJVOList.add(userJVO);
        }
        iUserJPARepository.deleteAll(userJVOList);
    }
}
