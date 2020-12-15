package cn.endacsd.yingbookshop;


import cn.endacsd.yingbookshop.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestBookMapper {

    @Resource
    BookMapper bookMapper;
    @Test
    void testCheckBook(){
        System.out.println(bookMapper.findBookById(3));
    }
    @Test
    void test(){
        System.out.println(10000000*1000000);
    }
}
