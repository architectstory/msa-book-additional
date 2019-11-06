package com.hoony.msa.member.springboot.rest;

import com.hoony.msa.member.domain.vo.UserVO;
import com.hoony.msa.member.springboot.service.UserServiceImpl;
import com.hoony.msa.member.system.common.ResponseMetaVO;
import com.hoony.msa.member.system.common.ResponseVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/v1/member")
@RestController
@Api(value = "UserController")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserServiceImpl userServiceImpl;

    @ApiOperation(value="", notes="전체조회", response = ResponseVO.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully retrieved list"),
            @ApiResponse(code=401, message = "You ar not authorized to view the resource"),
            @ApiResponse(code=403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code=404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<ResponseVO> getAllUser(){
        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //전체 count 조회
        responseMetaVO.setAllRecordCount(Long.bitCount(userServiceImpl.getAllUserCount()));

        //조회
        List<UserVO> userVOList =
                userServiceImpl.getAllUser("1", "10", "desc", "_id");

        //결과객체 리턴
        responseVO.setResultVO(userVOList);
        responseVO.setResponseMetaVO(responseMetaVO);
        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

    @ApiOperation(value="", notes="단건 저장<br>_id 값은 자동생성됨", response = ResponseVO.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully retrieved list"),
            @ApiResponse(code=401, message = "You ar not authorized to view the resource"),
            @ApiResponse(code=403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code=404, message = "The resource you were trying to reach is not found"),
    })

    @GetMapping("/{_id}")
    public String getUseById(@PathVariable String _id){
        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //입력
        UserVO userVO = new UserVO();
        userVO.set_id(_id);
        UserVO resultUserVO = userServiceImpl.getUserById(userVO);

        //건수
        responseMetaVO.setAllRecordCount(1);

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO(resultUserVO);
        return resultUserVO.getUserName();
    }

    @PostMapping
    public ResponseEntity<ResponseVO> addUser(@RequestBody UserVO userVO) throws Exception {

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //입력
        userVO.set_id(UUID.randomUUID().toString());
        UserVO resultUserVO = userServiceImpl.addUser(userVO);

        //건수
        responseMetaVO.setAllRecordCount(1);

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO(resultUserVO);
        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

    @ApiOperation(value="", notes="단건 수정<br>_id 값은 필수입력<br>_id 값 기준으로 업데이트함"
            , response = ResponseVO.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully retrieved list"),
            @ApiResponse(code=401, message = "You ar not authorized to view the resource"),
            @ApiResponse(code=403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code=404, message = "The resource you were trying to reach is not found"),
    })
    @PutMapping
    public ResponseEntity<ResponseVO> modifyUser(@RequestBody UserVO userVO){

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //수정
        UserVO resultUserVO = userServiceImpl.modifyUser(userVO);

        //건수
        responseMetaVO.setAllRecordCount(1);

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO(resultUserVO);

        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO> removeUser(@RequestBody List<String> _idList) throws Exception {

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //삭제
        int cnt =_idList.size();
        List<UserVO> userVOList = new ArrayList<>();
        for (int i=0; i< cnt; i++){
            UserVO userVO = new UserVO();
            userVO.set_id(_idList.get(i));
            userVOList.add(userVO);
        }

        userServiceImpl.removeUser(userVOList);

        //건수
        responseMetaVO.setAllRecordCount(_idList.size());

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO("");
        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }
}
