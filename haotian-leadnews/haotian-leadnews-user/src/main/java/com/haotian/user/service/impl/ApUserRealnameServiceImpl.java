package com.haotian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotian.model.common.dtos.PageResponseResult;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.model.user.pojos.ApUserRealname;
import com.haotian.user.mapper.ApUserRealnameMapper;
import com.haotian.user.service.ApUserRealnameService;
import org.springframework.stereotype.Service;

@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    /**
     * 按照状态分页查询用户列表
     * @param dto
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {

        //1.检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);//无效参数
        }

        //分页检查
        dto.checkParam();

        //2.根据状态分页查询
        LambdaQueryWrapper<ApUserRealname> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(dto.getStatus() != null){
            lambdaQueryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        //分页条件构建
        IPage pageParam = new Page(dto.getPage(), dto.getSize());
        IPage page = page(pageParam, lambdaQueryWrapper);

        //3.返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;

    }
}
