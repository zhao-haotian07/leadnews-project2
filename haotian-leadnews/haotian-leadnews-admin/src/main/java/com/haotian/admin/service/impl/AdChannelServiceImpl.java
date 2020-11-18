package com.haotian.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotian.admin.mapper.AdChannelMapper;
import com.haotian.admin.service.AdChannelService;
import com.haotian.model.admin.dtos.ChannelDto;
import com.haotian.model.admin.pojos.AdChannel;
import com.haotian.model.common.dtos.PageResponseResult;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {


    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        //1.参数检测
        if(dto == null){
            //返回无效参数代码 PARAM_INVALID(501,"无效参数"),
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //分页参数检查
        dto.checkParam();

        //2.名称模糊分页查询
        Page page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<AdChannel> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isBlank(dto.getName())){
            lambdaQueryWrapper.like(AdChannel::getName,dto.getName());
        }
        IPage result = page(page, lambdaQueryWrapper);

        //3.结果封装
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) result.getTotal());
        responseResult.setData(result.getRecords());
        return responseResult;

    }

    /**
     * 新增
     * @param channel
     * @return
     */
    @Override
    public ResponseResult insert(AdChannel channel) {
        //1.校验参数
        if(channel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //保存
        channel.setCreatedTime(new Date());
        save(channel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);



    }

    /**
     * 修改频道
     * @param adChannel
     * @return
     */
    @Override
    public ResponseResult update(AdChannel adChannel) {
        //1.验证参数
        if(null == adChannel){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.修改频道
        updateById(adChannel);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 删除频道
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Integer id) {
        //1.验证参数
        if(null == id){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //判断频道是否存在
        AdChannel adChannel = getById(id);
        if(adChannel == null){
            ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //验证该频道是否有效
        if(adChannel.getStatus()){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"该频道有效,不可以删除");
        }

        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);


    }


}
