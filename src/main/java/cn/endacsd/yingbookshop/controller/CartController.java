package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.service.impl.UserServiceImpl;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private UserService userService;
    @Autowired
    void setUserService(UserServiceImpl userService){
        this.userService=userService;
    }


    @RequestMapping("getCartInfo")
    R getCartInfo(){
        return userService.getCartInfo();
    }

    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public R addBook(@RequestBody List<Item> itemList){
        return userService.addBook(itemList);
    }

    @RequestMapping(value = "removeBook",method = RequestMethod.POST)
    public R removeBook(@RequestBody List<Item> itemList){
        return userService.removeBook(itemList);
    }


    @RequestMapping(value = "buyBook",method = RequestMethod.POST)
    public R buyBook(@RequestBody List<Item> itemList){
        return userService.buyAll(itemList,true);
    }


    @RequestMapping(value = "clearCart")
    public R clearCart(){
        return userService.removeCart();
    }


    @RequestMapping(value = "setBook",method = RequestMethod.POST)
    public R setBook(@RequestBody Item item){
        return userService.setBook(item);
    }
}
