package com.hoony.msa.status.springboot.rest;

import com.hoony.msa.status.domain.vo.StatusVO;
import com.hoony.msa.status.springboot.service.StatusServiceImpl;
import com.hoony.msa.status.system.common.ResponseMetaVO;
import com.hoony.msa.status.system.common.ResponseVO;
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
@RequestMapping("/api/v1/status")
@RestController
@Api(value = "StatusController")
public class StatusController {

    private Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    MessageSource messageSource;

    @Autowired
    StatusServiceImpl statusServiceImpl;

    @ApiOperation(value="", notes="전체조회", response = ResponseVO.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Successfully retrieved list"),
            @ApiResponse(code=401, message = "You ar not authorized to view the resource"),
            @ApiResponse(code=403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code=404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<ResponseVO> getAllStatus(){
        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //전체 count 조회
        responseMetaVO.setAllRecordCount(Long.bitCount(statusServiceImpl.getAllStatusCount()));

        //조회
        List<StatusVO> statusVOList =
                statusServiceImpl.getAllStatus("1", "10", "desc", "_id");

        //결과객체 리턴
        responseVO.setResultVO(statusVOList);
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

    @PostMapping
    public ResponseEntity<ResponseVO> addStatus(@RequestBody StatusVO statusVO) throws Exception {

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //입력
        statusVO.set_id(UUID.randomUUID().toString());
        StatusVO resultStatusVO = statusServiceImpl.addStatus(statusVO);

        //건수
        responseMetaVO.setAllRecordCount(1);

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO(resultStatusVO);
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
    public ResponseEntity<ResponseVO> modifyStatus(@RequestBody StatusVO statusVO){

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //수정
        StatusVO resultStatusVO = statusServiceImpl.modifyStatus(statusVO);

        //건수
        responseMetaVO.setAllRecordCount(1);

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO(resultStatusVO);

        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseVO> removeStatus(@RequestBody List<String> _idList) throws Exception {

        //결과 객체선언
        ResponseVO responseVO = new ResponseVO();
        ResponseMetaVO responseMetaVO = new ResponseMetaVO();

        //삭제
        int cnt =_idList.size();
        List<StatusVO> statusVOList = new ArrayList<>();
        for (int i=0; i< cnt; i++){
            StatusVO statusVO = new StatusVO();
            statusVO.set_id(_idList.get(i));
            statusVOList.add(statusVO);
        }

        statusServiceImpl.removeStatus(statusVOList);

        //건수
        responseMetaVO.setAllRecordCount(_idList.size());

        //결과객체 리턴
        responseVO.setResponseMetaVO(responseMetaVO);
        responseVO.setResultVO("");
        return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.OK);
    }
}
