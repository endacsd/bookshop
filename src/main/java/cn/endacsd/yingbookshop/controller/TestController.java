package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.CartMapper;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.utils.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    BookMapper bookMapper;

    @Resource
    CartMapper cartMapper;

    @Resource
    YUserMapper yUserMapper;
    @RequestMapping("/api/getAllBooks")
    public R getAllBooks(){
        return R.ok().put("bookList",bookMapper.findAllBooks());
    }


    @RequestMapping("/testPost")
    public R testPost(@RequestParam Map<String,Object> r){
        return R.ok(r);
    }
    @RequestMapping("/addBook")
    public R addBook(@RequestBody Book book){

        return R.ok().put("saveInfo",bookMapper.saveBook(book));
    }

    @RequestMapping("/testGetAllUser")
    public R testGetAllUser(){
        return R.ok().put("usersList",yUserMapper.findAllYUser());
    }

    @RequestMapping("/api/testCart1")
    public R testCart1(){
        return R.ok().put("num",cartMapper.check(10,1));
    }

    @RequestMapping(value = "/test/addBooks",method = RequestMethod.POST)
    public R testCart(@RequestBody List<Item> itemList){
        System.out.println(itemList);
        return R.ok();
    }
    @RequestMapping("/admin/testRole")
    public R testRole(){
        return R.ok();
    }
}
