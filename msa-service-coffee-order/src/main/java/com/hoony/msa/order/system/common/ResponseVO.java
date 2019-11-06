package com.hoony.msa.order.system.common;


public class ResponseVO {
    ResponseMetaVO responseMetaVO;
    Object resultVO;

    public ResponseMetaVO getResponseMetaVO() {
        return responseMetaVO;
    }

    public void setResponseMetaVO(ResponseMetaVO responseMetaVO) {
        this.responseMetaVO = responseMetaVO;
    }

    public Object getResultVO() {
        return resultVO;
    }

    public void setResultVO(Object resultVO) {
        this.resultVO = resultVO;
    }
}