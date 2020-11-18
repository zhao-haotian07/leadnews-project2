package com.haotian.admin.controller.v1;

import com.haotian.admin.service.AdChannelService;
import com.haotian.apis.admin.AdChannelControllerApi;
import com.haotian.model.admin.dtos.ChannelDto;
import com.haotian.model.admin.pojos.AdChannel;
import com.haotian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/channel")
public class AdChannelController implements AdChannelControllerApi {

    @Autowired
    private AdChannelService adChannelService;


    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto) {
        return adChannelService.findByNameAndPage(dto);
    }


    /**
     * 新增频道
     * @param channel
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody AdChannel channel) {
        return adChannelService.insert(channel);
    }

    /**
     * 修改频道
     * @param adChannel
     * @return
     */
    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody AdChannel adChannel) {
        return adChannelService.update(adChannel);
    }

    /**
     * 删除频道
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return adChannelService.deleteById(id);
    }


}
