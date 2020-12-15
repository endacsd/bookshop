package cn.endacsd.yingbookshop.controller;


import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.utils.FileUtil;
import cn.endacsd.yingbookshop.utils.R;
import cn.endacsd.yingbookshop.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    YUserMapper yUserMapper;

    @Autowired
    UserService userService;


    @RequestMapping("userInfo")
    public R getUserInfo(){
        String username=(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserUtil.yUserInfo(yUserMapper.findByUsername(username));
    }


    @RequestMapping("setUserInfo")
    public R setUserInfo(YUser yUser){
        return R.ok();
    }



    @RequestMapping(value = "commitKey",method = RequestMethod.POST)
    public R commitKey(@RequestBody String str){
        return R.error(str+" is not a right key!!");
    }







    @RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
    public R fileUploadAvatar(@RequestParam("file") MultipartFile file){

        String res=FileUtil.fileUpload(file,"/api/public/images/");
        System.out.println(res);
        if(res==null){return R.error();}
        return userService.setAvatar(res);
    }





}
