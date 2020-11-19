package com.haotian.wemedia.controller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.haotian.apis.wemedia.WmUserControllerApi;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import com.haotian.model.wemedia.pojos.WmUser;
import com.haotian.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class WmUserController implements WmUserControllerApi {

    @Autowired
    private WmUserService wmUserService;

    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(WmUser wmUser) {
        wmUserService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 按照名称查询用户
     * @param name
     * @return
     */
    @GetMapping("/findByName/{name}")
    @Override
    public WmUser findByName(@PathVariable("name") String name) {
        List<WmUser> list = wmUserService.list(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
