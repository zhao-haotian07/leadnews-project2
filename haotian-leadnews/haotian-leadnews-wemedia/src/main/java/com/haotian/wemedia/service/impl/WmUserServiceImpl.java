package com.haotian.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotian.model.wemedia.pojos.WmUser;
import com.haotian.wemedia.mapper.WmUserMapper;
import com.haotian.wemedia.service.WmUserService;
import org.springframework.stereotype.Service;

@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
}
