package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.service.BookService;
import cn.endacsd.yingbookshop.utils.PageUtil;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    //
    @Resource
    BookMapper bookMapper;

    @Override
    public int getCost(List<Item> items) {

        return items.stream()
                .map(item -> bookMapper.findBookById(item.getBookId()).getPrice()*item.getNum())
                .reduce(0, Integer::sum
        );
    }

    @Override
    public boolean checkQTY(List<Item> items) {
        return items.stream()
                .map(item -> {
                    Book book=bookMapper.findBookById(item.getBookId());
                    return book != null && book.getQTY() >= item.getNum();
                }).reduce(true,(res,cur)->res&cur);
    }

    @Override
    public boolean subBooks(List<Item> items) {
        //假定已经经过检查
        //只修改book表
        for (Item item : items){
            //
            Book book=bookMapper.findBookById(item.getBookId());
            if(book == null) return  false;
            if(book.getQTY()-item.getNum()<0) return false;
            bookMapper.updateBookNum(book.getId(),book.getQTY()-item.getNum());
        }
        return true;
    }


    @Override
    public boolean checkBook(Integer bookId) {
        System.out.println(bookId);
        return bookMapper.findBookByIdFromAll(bookId) != null;
    }


    @Override
    public R findBooksByPage(Page page) {
        List<Book> list=bookMapper.findAllBooks().stream().filter(book -> !book.isHide()).collect(Collectors.toList());
        return PageUtil.getPage(list,page,"book");

    }
}