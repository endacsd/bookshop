package cn.endacsd.yingbookshop;


import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Resource
    private YUserMapper yUserMapper;
    @Test
    void getUserInfoTest(){
        System.out.println(
        yUserMapper.findByUsername("endacsd"));
    }
}
