package com.haotian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.model.user.pojos.ApUserRealname;

public interface ApUserRealnameService extends IService<ApUserRealname> {

    /**
     * 按照状态分页查询用户列表
     * @param dto
     * @return
     */
    public ResponseResult loadListByStatus(AuthDto dto);

}
