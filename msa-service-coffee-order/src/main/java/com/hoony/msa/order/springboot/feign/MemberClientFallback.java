package com.hoony.msa.order.springboot.feign;

import com.hoony.msa.order.system.common.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberClientFallback implements IMemberClient {

    private Logger logger = LoggerFactory.getLogger(MemberClientFallback.class);

    @Override
    public ResponseEntity<ResponseVO> getAllUser() {
        logger.debug("MemberClientFallback");
        return new ResponseEntity<ResponseVO>(new ResponseVO(), HttpStatus.OK);
    }

    @Override
    public String getUseById(String _id) {
        logger.debug("MemberClientFallback");
        return new String("fallback");
    }

    @Override
    public ResponseEntity<ResponseVO> addUser(UserVO userVO) {
        logger.debug("MemberClientFallback");
        return new ResponseEntity<ResponseVO>(new ResponseVO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO> modifyUser(UserVO userVO) {
        logger.debug("MemberClientFallback");
        return new ResponseEntity<ResponseVO>(new ResponseVO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseVO> removeUser(List<String> _idList) {
        logger.debug("MemberClientFallback");
        return new ResponseEntity<ResponseVO>(new ResponseVO(), HttpStatus.OK);
    }
}
