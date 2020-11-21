package com.haotian.article.controller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.haotian.apis.article.AuthorControllerApi;
import com.haotian.article.service.AuthorService;
import com.haotian.model.article.pojos.ApAuthor;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import com.haotian.model.user.dtos.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController implements AuthorControllerApi {

    @Autowired
    private AuthorService authorService;

    /**
     *根据用户id查询作者信息
     * @param id
     * @return
     */
    @GetMapping("/findByUserId/{id}")
    @Override
    public ApAuthor findByUserId(Integer id) {
        List<ApAuthor> list = authorService.list(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getUserId, id));
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存作者
     * @param apAuthor
     * @return
     */
    @PostMapping("/save")
    @Override
    public ResponseResult save(ApAuthor apAuthor) {
        //保存前先把时间存进去
        apAuthor.setCreatedTime(new Date());
        authorService.save(apAuthor);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult authPass(AuthDto dto) {
        return null;
    }

    @Override
    public ResponseResult authFail(AuthDto dto) {
        return null;
    }
}
