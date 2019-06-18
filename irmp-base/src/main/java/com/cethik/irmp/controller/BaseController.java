/*
 * Copyright 2012-2018 CETHIK CETITI All Rights Reserved.
 */

package com.cethik.irmp.controller;

import com.cethik.irmp.dto.BaseResponse;
import com.cethik.irmp.util.DateUtils;
/*import com.cethik.irmp.util.ShiroUtils;
import com.cethik.irmp.vmodel.CurrentUser;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.beans.PropertyEditorSupport;
import java.sql.SQLSyntaxErrorException;
import java.util.Date;
import java.util.List;

/**
 * the description of class or interface
 *
 * @author suzhaoyang@cetiti.com
 * @date 2018-08-31
 */
public abstract class BaseController {
    private final static Logger log = LoggerFactory.getLogger(BaseController.class);
    private final static int UNKOWN_ERROR = 201;
    private final static int PARAM_ERROR = 202;
    private final static int SQL_ERROR = 203;
    private int iDisplayLength;
    private int iDisplayStart;
    protected int getiDisplayLength(HttpServletRequest request){
        if(this.iDisplayLength==0){
            this.iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength")); // 每页显示的行数
        }
        return this.iDisplayLength;
    }

    protected int getiDisplayStart(HttpServletRequest request){
        if(this.iDisplayStart==0){
            this.iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart")); // 每页显示的行数
        }
        return this.iDisplayStart;
    }
    /**
     * 初始化数据绑定
     * 将字段中String类型转化为Date类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    boolean beanValidate(BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            StringBuilder errors = new StringBuilder();
            for (ObjectError error : errorList) {
                errors.append(",").append(error.getDefaultMessage());
            }
            throw new ValidationException(errors.substring(1));
        }
        return true;
    }

    /**
     * 异常处理
     */

    @ExceptionHandler({Throwable.class})
    public BaseResponse<?> bindException(Throwable throwable) {
        log.error("系统错误：", throwable);
        return BaseResponse.buildFailResponse(UNKOWN_ERROR, "系统错误，请重试!");
    }

    @ExceptionHandler({ConversionFailedException.class, IllegalArgumentException.class})
    public BaseResponse<?> convertParamException(ConversionFailedException e1, IllegalArgumentException e2) {
        log.error("数据参数格式不正确：", e1, e2);
        return BaseResponse.buildFailResponse(PARAM_ERROR, "数据参数格式不正确!");
    }

    @ExceptionHandler({SQLSyntaxErrorException.class})
    public BaseResponse<?> convertException(SQLSyntaxErrorException e) {
        log.error("SQL异常：", e);
        return BaseResponse.buildFailResponse(SQL_ERROR, "SQL异常!");
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public BaseResponse<?> convertException(DuplicateKeyException e) {
        log.error("主键冲突异常：", e);
        return BaseResponse.buildFailResponse(SQL_ERROR, "唯一键冲突");
    }


/*    public CurrentUser getUser() {
        return ShiroUtils.getUser();
    }

    public Integer getUserId() {
        return getUser().getId();
    }

    public String getUsername() {
        return getUser().getName();
    }*/


    protected int getPageNum(HttpServletRequest request){
        Integer iDisplayStart =getiDisplayStart(request);
        Integer iDisplayLength =getiDisplayLength(request);
        System.out.println("iDisplayStart:" + iDisplayStart);
        System.out.println("iDisplayLength:" + iDisplayLength);
        Integer pagenum = 1 + iDisplayStart / iDisplayLength;
        return pagenum;
    }
}
