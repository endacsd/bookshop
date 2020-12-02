package cn.endacsd.yingbookshop.controller;


import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

    private UserService userService;
    @Autowired
    void setUserService(UserService userService){
        this.userService=userService;
    }

    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    public R register(@RequestBody YUser user){
        System.out.println(user);

        return userService.register(user);
    }

}
