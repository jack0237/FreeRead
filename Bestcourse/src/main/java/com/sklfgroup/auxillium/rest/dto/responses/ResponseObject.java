package com.sklfgroup.auxillium.rest.dto.responses;

public class ResponseObject {
    private String RespCode;
    private String RespMsg;
    private Object RespData;

    public ResponseObject() {
    }

    public ResponseObject(String RespCode, String RespMsg, Object RespData) {
        this.RespCode = RespCode;
        this.RespMsg = RespMsg;
        this.RespData = RespData;
    }

    public String getRespCode() {
        return RespCode;
    }

    public void setRespCode(String RespCode) {
        RespCode = RespCode;
    }

    public String getRespMsg() {
        return RespMsg;
    }

    public void setRespMsg(String RespMsg) {
        RespMsg = RespMsg;
    }

    public Object getRespData() {
        return RespData;
    }

    public void setRespData(Object RespData) {
        RespData = RespData;
    }
}