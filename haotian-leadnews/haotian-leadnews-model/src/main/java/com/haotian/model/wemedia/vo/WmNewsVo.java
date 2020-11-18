package com.haotian.model.wemedia.vo;


import com.haotian.model.wemedia.pojos.WmNews;
import lombok.Data;

@Data
public class WmNewsVo extends WmNews {

    /**
     * 文章作者名称
     */
    private String authorName;
}
