package cn.endacsd.yingbookshop;


import cn.endacsd.yingbookshop.service.CartService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CartServiceTest {


    @Autowired
    private CartService cartService;



    @Test
    void checkGetInfoTest(){
        System.out.println(cartService.getCartInfo(7));
    }

    @Test
    void checkBookTest(){
        cartService.checkBook(7,3,0);
    }


    @Test
    void checkBookNumTest(){
        boolean res= cartService.checkBookNum(21,8,3);
        System.out.println(res);

    }
    @Test
    void OptionalTest(){
        Integer x=null;
        boolean res= 2 > Optional.ofNullable(x).orElse(-1);
    }
}
