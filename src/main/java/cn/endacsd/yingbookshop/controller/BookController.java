package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.service.impl.UserServiceImpl;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {


    private UserService userService;
    @Autowired
    void setUserService(UserServiceImpl userService){
        this.userService=userService;
    }


    @RequestMapping(value = "/buyBook", method = RequestMethod.POST)
    public R buyBook(@RequestBody List<Item> items){
        return userService.buyAll(items,false);
    }


    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public R addBook(@RequestBody List<Item> itemList){
        return userService.addBook(itemList);
    }
}
