package com.hoony.msa.member.domain.repository;

import com.hoony.msa.member.domain.entity.UserEntity;

import java.util.List;

public interface IUserRepository {
	long getAllUserCount();
	List<UserEntity> getAllUser(String page, String size, String sort, String sortKey);
	UserEntity getUserById(UserEntity userEntity);
	UserEntity addUser(UserEntity userEntity);
	UserEntity modifyUser(UserEntity userEntity);
	void removeUser(List<UserEntity> userEntity);
}
