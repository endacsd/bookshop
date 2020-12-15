package cn.endacsd.yingbookshop.service;


import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    //

    int getCost(List<Item> items);
    boolean checkQTY(List<Item> items);
    boolean subBooks(List<Item> items);
    boolean checkBook(Integer bookId);

    R findBooksByPage(Page page);

    boolean checkBookNum(int bookId,int num);
}
