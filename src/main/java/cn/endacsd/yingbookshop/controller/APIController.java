package cn.endacsd.yingbookshop.controller;

import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.service.BookService;
import cn.endacsd.yingbookshop.service.impl.BookServiceImpl;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
