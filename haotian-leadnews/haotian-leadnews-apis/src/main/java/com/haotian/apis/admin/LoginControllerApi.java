package com.haotian.apis.admin;


import com.haotian.model.admin.dtos.AdUserDto;
import com.haotian.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginControllerApi {

    /**
     * admin登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(@RequestBody AdUserDto dto);
}