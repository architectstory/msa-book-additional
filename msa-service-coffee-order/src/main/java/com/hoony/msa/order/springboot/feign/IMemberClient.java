package com.hoony.msa.order.springboot.feign;

import com.hoony.msa.order.system.common.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "msa-service-coffee-member"
        ,fallback = MemberClientFallback.class
)
public interface IMemberClient {

    @GetMapping(value = "/api/v1/member")
    public ResponseEntity<ResponseVO> getAllUser();

    @GetMapping(value = "/api/v1/member/{_id}")
    public String getUseById(@PathVariable(value = "_id")  String _id);

    @PostMapping(value = "/api/v1/member")
    public ResponseEntity<ResponseVO> addUser(@RequestBody UserVO userVO);

    @PutMapping(value = "/api/v1/member")
    public ResponseEntity<ResponseVO> modifyUser(@RequestBody UserVO userVO);

    @DeleteMapping(value = "/api/v1/user")
    public ResponseEntity<ResponseVO> removeUser(@RequestBody List<String> _idList);
}
