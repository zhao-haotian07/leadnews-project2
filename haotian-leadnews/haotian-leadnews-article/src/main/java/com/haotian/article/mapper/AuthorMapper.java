package com.haotian.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haotian.model.article.pojos.ApAuthor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<ApAuthor> {

}
