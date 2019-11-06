package com.hoony.msa.member.domain.entity;

import com.hoony.msa.member.domain.vo.UserVO;

public class UserEntity {
    private UserVO userVO;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}
