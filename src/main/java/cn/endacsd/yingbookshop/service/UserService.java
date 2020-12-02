package cn.endacsd.yingbookshop.service;


import cn.endacsd.yingbookshop.entity.*;
import cn.endacsd.yingbookshop.utils.R;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    R register(YUser yUser) ;

    R addBook(Integer bookId,Integer num);

    R removeBook(Integer bookId,Integer num);

    R removeCart();

    R buyAll(List<Item> list,boolean isCartBooks);


    R getCartInfo();

    R addBook(List<Item> itemList);

    R removeBook(List<Item> itemList);

    R setBook(Item item);

    R getOrders();

    R getOrderByPage(Page page);
}
