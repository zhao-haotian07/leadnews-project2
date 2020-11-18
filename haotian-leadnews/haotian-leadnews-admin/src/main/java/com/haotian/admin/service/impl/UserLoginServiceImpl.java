package com.haotian.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.haotian.admin.mapper.AdUserMapper;
import com.haotian.admin.service.UserLoginService;
import com.haotian.model.admin.dtos.AdUserDto;
import com.haotian.model.admin.pojos.AdUser;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import com.haotian.gateway.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class UserLoginServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements UserLoginService {

    /**
     * admin管理员登录功能
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(AdUserDto dto) {
        //1.校验参数(账号密码)
        if (StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE,"账号或者密码错误,请重试.");
        }
        //查询账号信息
        QueryWrapper<AdUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name",dto.getName());
        //获取用户信息
        List<AdUser> list = list(wrapper);
        //判断是否有该账号
        if(list != null && list.size() == 1){
            //拿到该用户信息
            AdUser adUser = list.get(0);
            //加密
            String pswd = DigestUtils.md5DigestAsHex((dto.getPassword() + adUser.getSalt()).getBytes());
            //验证密码
            if(adUser.getPassword().equals(pswd)){
                HashMap<String, Object> map = Maps.newHashMap();
                adUser.setPassword("");
                adUser.setSalt("");
                map.put("token", AppJwtUtil.getToken(adUser.getId().longValue()));
                map.put("user",adUser);
                return ResponseResult.okResult(map);
            }else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
        }

    }
}















