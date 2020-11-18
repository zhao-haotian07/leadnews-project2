package com.haotian.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotian.admin.mapper.AdSensitiveMapper;
import com.haotian.admin.service.AdSensitiveService;
import com.haotian.model.admin.dtos.SensitiveDto;
import com.haotian.model.admin.pojos.AdSensitive;
import com.haotian.model.common.dtos.PageResponseResult;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdSensitiveServiceImpl extends ServiceImpl<AdSensitiveMapper, AdSensitive> implements AdSensitiveService {
    /**
     * 根据分页查找敏感词
     * @param dto
     * @return
     */
    @Override
    public ResponseResult list(SensitiveDto dto) {
        //1.判断参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //检验分页 check核查
        dto.checkParam();

        //根据名称模糊查询
        Page page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<AdSensitive> lambdaQueryWrapper = new LambdaQueryWrapper();
        //判断敏感词是否为空
        if(StringUtils.isNotBlank(dto.getName())){
            lambdaQueryWrapper.like(AdSensitive::getSensitives,dto.getName());
        }
        IPage result = page(page, lambdaQueryWrapper);
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(), (int) result.getTotal());
        responseResult.setData(result.getRecords());
        return responseResult;


    }

    /**
     * 新增敏感词
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult insert(AdSensitive adSensitive) {
        if(adSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //设置新增的时间
        adSensitive.setCreatedTime(new Date());
        save(adSensitive);//保存
        //返回成功的状态码
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 修改敏感词
     * @param adSensitive
     * @return
     */
    @Override
    public ResponseResult update(AdSensitive adSensitive) {
        //1判断参数
        if(adSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        updateById(adSensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Integer id) {
        //判断参数
        if(id == null ) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //判断该敏感词是否存在
        AdSensitive sensitive = getById(id);
        if(sensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //删除操作
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
