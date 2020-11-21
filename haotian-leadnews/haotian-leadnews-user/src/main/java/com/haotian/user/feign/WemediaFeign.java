package com.haotian.user.feign;

import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.wemedia.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("leadnews-wemedia")
public interface WemediaFeign {

    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    @PostMapping("/api/v1/user/save")
    public ResponseResult save(@RequestBody WmUser wmUser);

    /**
     * 按照名称查询用户
     * @param name
     * @return
     */
    @GetMapping("/api/v1/user/findByName/{name}")
    public WmUser findByName(@PathVariable("name") String name);
}
