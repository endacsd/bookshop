package cn.endacsd.yingbookshop.order;

import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.service.OrderService;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.service.impl.OrderServiceImpl;
import cn.endacsd.yingbookshop.utils.OrderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
@SpringBootTest
public class OrderTest {

    @Autowired
    void setOrderService(OrderServiceImpl orderService){
        this.orderService=orderService;
    }

    private OrderService orderService;

    @Test
    void addOrder(){
        System.out.println(orderService);
        orderService.addOrders(Arrays.asList(
                new Item(1,1,1)
        ),1);
    }
}
