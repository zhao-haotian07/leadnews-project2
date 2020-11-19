package com.haotian.apis.admin;

import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.user.dtos.AuthDto;

public interface ApUserRealnameControllerApi {

    /**
     *按照状态查询用户认证列表
     * @param dto
     * @return
     */
    public ResponseResult loadListByStatus(AuthDto dto);
}
