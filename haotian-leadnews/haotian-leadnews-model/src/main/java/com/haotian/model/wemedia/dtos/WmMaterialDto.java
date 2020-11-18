package com.haotian.model.wemedia.dtos;


import com.haotian.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class WmMaterialDto extends PageRequestDto {
    /**
     * 是否收藏
     *  1：收藏
     */
    private Short isCollection;
}
