/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.dto;

import org.springframework.http.ResponseEntity;

/**
 * 数据统一返回格式
 *
 * @author suzhaoyang@cetiti.com
 * @date 2018-08-31
 */
public class BaseResponse<T> {
    private final static int SUCCESS = 200;

    public T data;

    public String msg;

    public int code;

    public static <T> BaseResponse<T> buildResponse(int code, String msg, T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setData(data);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static <T> BaseResponse<T> buildSuccessResponse() {
        return buildResponse(SUCCESS, "请求成功", null);

    }

    public static <T> BaseResponse<T> buildSuccessResponse(T data) {
        return buildResponse(SUCCESS, "请求成功", data);

    }

    public static <T> BaseResponse<T> buildFailResponse(int statusCode, String msg) {
        return buildResponse(statusCode, msg, null);
    }

    public ResponseEntity<?> toResponseEntity() {
        return ResponseEntity.status(code).body(this);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
