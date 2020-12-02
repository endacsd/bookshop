package cn.endacsd.yingbookshop;

import cn.endacsd.yingbookshop.mapper.CartMapper;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BookshopApplicationTests {

    @Resource
    CartMapper cartMapper;
    @Test
    void contextLoads() {

        cartMapper.addBook(100,200,1);
    }

    @Test
    void testRemove(){
        cartMapper.removeOneBook(100,200);
    }

    @Test
    void testUpdate(){
        cartMapper.updateOneBook(100,200,3);
    }

    @Test
    void testRemoveOneCart(){
        cartMapper.clearOneCart(100);
    }

    @Test
    void testGetInfo(){
        System.out.println(cartMapper.getCartInfo(1));
    }

    @Test
    void testGetQTYbyDoubleId(){
        System.out.println(cartMapper.getQTYbyDoubleId(7,3));
    }

    @Test
    void check(){
        System.out.println(cartMapper.check(1,200));
    }



}
