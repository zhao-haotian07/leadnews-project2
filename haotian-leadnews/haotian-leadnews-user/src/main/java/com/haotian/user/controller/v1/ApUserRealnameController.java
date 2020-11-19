package com.haotian.user.controller.v1;

import com.haotian.apis.admin.ApUserRealnameControllerApi;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class ApUserRealnameController implements ApUserRealnameControllerApi {

    @Autowired
    private ApUserRealnameService apUserRealnameService;

    /**
     * 按照状态查询用户认证列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult loadListByStatus(@RequestBody AuthDto dto) {
        return apUserRealnameService.loadListByStatus(dto);
    }
}
