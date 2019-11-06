package com.hoony.msa.member.domain.service;

import com.hoony.msa.member.domain.entity.UserEntity;
import com.hoony.msa.member.domain.repository.IUserRepository;
import com.hoony.msa.member.domain.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	private IUserRepository iUserRepository;

	public UserService(IUserRepository iUserRepository) {

		this.iUserRepository = iUserRepository;
	}

	@Override
	public long getAllUserCount() {
		return iUserRepository.getAllUserCount(); }

	@Override
	public List<UserVO> getAllUser(String page, String size, String sort, String sortKey) {

		List<UserEntity> userEntities = iUserRepository.getAllUser(page, size, sort, sortKey);

		List<UserVO> userVOList = new ArrayList();

		if(userEntities==null) return userVOList;

		for (UserEntity userEntity : userEntities){
			userVOList.add(userEntity.getUserVO());
		}
		return userVOList;
	}

	@Override
	public UserVO getUserById(UserVO userVO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserVO(userVO);

		userEntity = iUserRepository.getUserById(userEntity);

		return userEntity.getUserVO();
	}

	@Override
	public UserVO addUser(UserVO userVO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserVO(userVO);
		iUserRepository.addUser(userEntity);
		return userVO;
	}

	@Override
	public UserVO modifyUser(UserVO userVO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserVO(userVO);
		iUserRepository.modifyUser(userEntity);
		return userVO;
	}

	@Override
	public void removeUser(List<UserVO> userVOList) {
		List<UserEntity> userEntityList = new ArrayList<>();

		int pCnt = userVOList.size();
		for(int i=0; i<pCnt; i++){
			UserVO userVO = userVOList.get(i);
			UserEntity userEntity = new UserEntity();
			userEntity.setUserVO(userVO);
			userEntityList.add(userEntity);
		}

		iUserRepository.removeUser(userEntityList);
	}

}