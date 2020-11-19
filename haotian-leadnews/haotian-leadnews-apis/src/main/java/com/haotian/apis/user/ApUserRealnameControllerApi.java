package com.haotian.apis.user;


import com.haotian.model.common.dtos.PageResponseResult;
import com.haotian.model.user.dtos.AuthDto;

public interface ApUserRealnameControllerApi {

    /**
     *按照状态查询用户认证列表
     * @param dto
     * @return
     */
    public PageResponseResult loadListByStatus(AuthDto dto);

}