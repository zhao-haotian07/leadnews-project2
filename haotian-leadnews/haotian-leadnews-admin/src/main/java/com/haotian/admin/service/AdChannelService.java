package com.haotian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haotian.model.admin.dtos.ChannelDto;
import com.haotian.model.admin.pojos.AdChannel;
import com.haotian.model.common.dtos.ResponseResult;

public interface AdChannelService extends IService<AdChannel> {

    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    public ResponseResult findByNameAndPage(ChannelDto dto);


    /**
     * 新增频道
     * @param channel
     * @return
     */
    public ResponseResult insert(AdChannel channel);

    /**
     * 修改频道
     * @param adChannel
     * @return
     */
    public ResponseResult update(AdChannel adChannel);

    /**
     * 删除频道
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);

}
