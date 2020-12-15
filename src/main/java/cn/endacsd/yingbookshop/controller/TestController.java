package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.CartMapper;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.utils.FileUtil;
import cn.endacsd.yingbookshop.utils.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin
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






    @RequestMapping(value = "/test/images/{path}",produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@PathVariable String path, HttpServletResponse response) throws IOException {
        File file = new File("src/Files/"+path);
        response.setContentType("image/png");
        OutputStream os =response.getOutputStream();
        ImageIO.write(ImageIO.read(new FileInputStream(file)),"png",os);
    }
}
