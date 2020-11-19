package com.haotian.apis.wemedia;

import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.wemedia.pojos.WmUser;

public interface WmUserControllerApi {

    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    public ResponseResult save(WmUser wmUser);

    /**
     * 按照名称查询用户
     * @param name
     * @return
     */
    public WmUser findByName(String name);
}