package com.haotian.admin.controller.v1;

import com.haotian.admin.service.UserLoginService;
import com.haotian.apis.admin.LoginControllerApi;
import com.haotian.model.admin.dtos.AdUserDto;
import com.haotian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {

    @Autowired
    private UserLoginService userLoginService;


    /**
     * admin管理员登录功能
     * @param dto
     * @return
     */
    @PostMapping("/in")
    @Override
    public ResponseResult login(AdUserDto dto) {
        return userLoginService.login(dto);
    }
}
