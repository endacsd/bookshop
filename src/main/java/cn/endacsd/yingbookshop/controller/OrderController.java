package cn.endacsd.yingbookshop.controller;


import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.service.OrderService;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.service.impl.UserServiceImpl;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    void setUserService(UserServiceImpl userService){
        this.userService=userService;
    }
    private UserService userService;



    @RequestMapping("getOrder")
    public R getOrder(){
        return userService.getOrders();
    }


    @RequestMapping(value = "getOrderByPage",method = RequestMethod.POST)
    public R getOrderByPage(@RequestBody Page page){
        return userService.getOrderByPage(page);
    }
}
