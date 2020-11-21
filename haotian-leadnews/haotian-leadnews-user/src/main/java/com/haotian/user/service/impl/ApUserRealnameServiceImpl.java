package com.haotian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haotian.common.constants.user.UserConstants;
import com.haotian.model.article.pojos.ApAuthor;
import com.haotian.model.common.dtos.PageResponseResult;
import com.haotian.model.common.dtos.ResponseResult;
import com.haotian.model.common.enums.AppHttpCodeEnum;
import com.haotian.model.user.dtos.AuthDto;
import com.haotian.model.user.pojos.ApUser;
import com.haotian.model.user.pojos.ApUserRealname;
import com.haotian.model.wemedia.pojos.WmUser;
import com.haotian.user.feign.ArticleFeign;
import com.haotian.user.feign.WemediaFeign;
import com.haotian.user.mapper.ApUserMapper;
import com.haotian.user.mapper.ApUserRealnameMapper;
import com.haotian.user.service.ApUserRealnameService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    /**
     * 按照状态分页查询用户列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {

        //1.检查参数
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);//无效参数
        }

        //分页检查
        dto.checkParam();

        //2.根据状态分页查询
        LambdaQueryWrapper<ApUserRealname> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (dto.getStatus() != null) {
            lambdaQueryWrapper.eq(ApUserRealname::getStatus, dto.getStatus());
        }
        //分页条件构建
        IPage pageParam = new Page(dto.getPage(), dto.getSize());
        IPage page = page(pageParam, lambdaQueryWrapper);

        //3.返回结果
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;

    }

    /**
     * 根据状态进行审核
     *
     * @param dto
     * @param status  0 创建中,1 待审核,2 审核失败,9 审核通过
     * @return
     */
    @GlobalTransactional
    @Override
    public ResponseResult updateStatusById(AuthDto dto, Short status) {
        //1.检查参数
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //检查状态     0 创建中
        //            1 待审核
        //            2 审核失败
        //            9 审核通过
        if (checkStatus(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.修改状态
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        if (dto.getMsg() != null) {
            //设置拒绝消息,如果页面有传拒绝消息就设置,没有则不设置.
            //dto.msg是页面传过来的消息,setReason是表中的字段
            apUserRealname.setReason(dto.getMsg());
        }
        updateById(apUserRealname);

        //3.如果审核通过,那么创建自媒体账户和作者信息
        if (status.equals(UserConstants.PASS_AUTH)) {//再次判断审核状态,9:为审核通过
            //创建自媒体账户,创作作者信息
            ResponseResult result = createWmUserAndAuthor(dto);
            if(result != null){
                //创建成功,返回信息
                return result;
            }
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
    private ApUserMapper apUserMapper;

    @Autowired
    private WemediaFeign wemediaFeign;

    /**
     * 创建自媒体账户和作者信息
     * @param dto
     * @return
     */
    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        //获取ap_user信息
        Integer apRealnameId = dto.getId();
        ApUserRealname apUserRealname = getById(apRealnameId);
        ApUser apUser = apUserMapper.selectById(apUserRealname.getUserId());
        if(apUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //查询用户
        WmUser wmUser = wemediaFeign.findByName(apUser.getName());
        //创建自媒体账户
        if(wmUser == null){
            wmUser = new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());//创建时间
            wmUser.setName(apUser.getName());//用户名
            wmUser.setPassword(apUser.getPassword());//密码
            wmUser.setSalt(apUser.getSalt());//加密盐
            wmUser.setPhone(apUser.getPhone());//手机号
            wmUser.setStatus(9);////状态,审核通过
            //保存
            wemediaFeign.save(wmUser);//分布式事务处理保存
        }

        //创建作者
        createAuthor(wmUser);

        apUser.setFlag((short) 1);//0 普通用户 ,1 自媒体人, 2 大V
        //修改信息
        apUserMapper.updateById(apUser);
        return null;

    }

    @Autowired
    private ArticleFeign articleFeign;

    /**
     * 创建作者
     * @param wmUser
     */
    private void createAuthor(WmUser wmUser) {
        //查询作者信息
        Integer apAuthorId = wmUser.getApUserId();
        ApAuthor apAuthor = articleFeign.findByUserId(apAuthorId);
        if(apAuthor ==null){//没有作者的信息,创建作者
            apAuthor = new ApAuthor();
            apAuthor.setName(wmUser.getName());//作者名称
            apAuthor.setCreatedTime(new Date());//创建时间
            apAuthor.setUserId(apAuthorId);
            apAuthor.setType(UserConstants.AUTH_TYPE);//1 签约合作商 ,2 平台自媒体人
            articleFeign.save(apAuthor);//分布式事务处理保存
        }
    }


    /**
     * 检查状态 0:创建中 1:待审核 2:审核失败 3:审核通过
     * @param status
     * @return
     */
    private boolean checkStatus(Short status) {
        if (status == null ||
                (!status.equals(UserConstants.FAIL_AUTH)//审核失败取反
                        && !status.equals(UserConstants.PASS_AUTH)//审核通过取反
                )) {
            return true;//状态未通过
        }
        return false;//状态通过
    }
}































