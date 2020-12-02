package cn.endacsd.yingbookshop.controller;


import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.utils.R;
import cn.endacsd.yingbookshop.utils.UserUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    YUserMapper yUserMapper;
    @RequestMapping("userInfo")
    public R getUserInfo(){
        String username=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserUtil.yUserInfo(yUserMapper.findByUsername(username));
    }


    @RequestMapping("setUserInfo")
    public R setUserInfo(YUser yUser){
        return R.ok();
    }
}
