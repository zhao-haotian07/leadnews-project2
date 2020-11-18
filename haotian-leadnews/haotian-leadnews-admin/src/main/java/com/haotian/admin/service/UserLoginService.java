package com.haotian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotian.model.admin.dtos.AdUserDto;
import com.haotian.model.admin.pojos.AdUser;
import com.haotian.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserLoginService extends IService<AdUser> {

    /**
     * admin管理员登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(@RequestBody AdUserDto dto);




}
