package com.haotian.user.controller.v1;

import com.haotian.apis.user.ApUserRealnameControllerApi;
import com.haotian.common.constants.user.UserConstants;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class ApUserRealnameController implements ApUserRealnameControllerApi {

    @Autowired
    private ApUserRealnameService apUserRealnameService;

    /**
     * 按照状态查询用户认证列表
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Override
    public ResponseResult loadListByStatus(@RequestBody AuthDto dto) {
        return apUserRealnameService.loadListByStatus(dto);
    }


    /**
     * 审核通过 按钮
     * @param dto
     * @return
     */
    @PostMapping("/authPass")
    @Override
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        //UserConstants.PASS_AUTH : 9  ,状态是审核通过
        return apUserRealnameService.updateStatusById(dto, UserConstants.PASS_AUTH);
    }

    /**
     * 审核失败 按钮
     * @param dto
     * @return
     */
    @Override
    public ResponseResult authFail(AuthDto dto) {
        //UserConstants.FAIL_AUT : 2  ,状态是审核失败
        return apUserRealnameService.updateStatusById(dto,UserConstants.FAIL_AUTH);
    }


}
