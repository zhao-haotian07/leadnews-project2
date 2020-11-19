package com.haotian.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.model.user.pojos.ApUserRealname;

public interface ApUserRealnameMapper extends BaseMapper<ApUserRealname> {

    /**
     * 按照状态分页查询用户列表
     * @param dto
     * @return
     */
    public ResponseResult loadListByStatus(AuthDto dto);


}
