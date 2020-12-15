package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.service.BookService;
import cn.endacsd.yingbookshop.service.impl.BookServiceImpl;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/public")
public class APIController {


    @Resource
    BookMapper bookMapper;
    private BookService bookService;
    @Autowired
    void setBookService(BookServiceImpl bookService){this.bookService=bookService;}


    @RequestMapping("getBookList")
    public R getBookList(){
        return R.ok().put("bookList",bookMapper.findAllBooks().stream().filter(book -> !book.isHide()));
    }


    @RequestMapping("getBookShow")
    public R getBookShow(){
        List<Book> list=bookMapper.findAllBooks();
        List<Book> bookList=new ArrayList<>();
        int siz=5;
        Random r=new Random();
        for(Book book : list){
            if(book.isHide()) continue;
            if(r.nextInt()%2==0){
                siz--;
                bookList.add(book);
            }
            if(siz==0) break;
        }
        return R.ok().put("bookList",bookList);
    }
    @RequestMapping("getBookInfo/{bookId}")
    public R getBookInfo(@PathVariable Integer bookId){
        System.out.println(bookId);
        return R.ok().put("bookInfo",bookMapper.findBookById(bookId));
    }
    @RequestMapping(value = "getBookListByPage",method = RequestMethod.POST)
    public R getBookListByPage(@RequestBody Page page){
        System.out.println(page.getPageSize()+" "+page.getIndex());
        return bookService.findBooksByPage(page);
    }


    @RequestMapping(value = "/images/{path}",produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@PathVariable String path, HttpServletResponse response) throws IOException {
        File file = new File("src/Files/"+path);
        response.setContentType("image/png");
        OutputStream os =response.getOutputStream();
        ImageIO.write(ImageIO.read(new FileInputStream(file)),"png",os);
    }
}
