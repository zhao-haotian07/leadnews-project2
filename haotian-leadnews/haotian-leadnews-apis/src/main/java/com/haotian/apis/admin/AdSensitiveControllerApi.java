package com.haotian.apis.admin;


import com.haotian.model.admin.dtos.SensitiveDto;
import com.haotian.model.admin.pojos.AdSensitive;
import com.haotian.model.common.dtos.ResponseResult;

public interface AdSensitiveControllerApi {

    /**
     * 根据名称分页查询敏感词
     * @param dto
     * @return
     */
    public ResponseResult list(SensitiveDto dto);

    /**
     * 新增
     * @param adSensitive
     * @return
     */
    public ResponseResult save(AdSensitive adSensitive);

    /**
     * 修改
     * @param adSensitive
     * @return
     */
    public ResponseResult update(AdSensitive adSensitive);

    /**
     * 删除敏感词
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);
}