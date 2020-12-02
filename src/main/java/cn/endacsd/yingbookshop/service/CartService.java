package cn.endacsd.yingbookshop.service;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Record;

import java.util.List;

public interface CartService {

    boolean addBook(Integer bookId,Integer id,Integer num);
    boolean removeBook(Integer bookId,Integer id);
    boolean clear(Integer userId);
    boolean addOneBook(Integer bookId,Integer userId);
    boolean subOneBook(Integer bookId,Integer userId);
    List<Record> getCartInfo(Integer userId);
    boolean subBook(Integer bookId,Integer id,Integer num);



    boolean checkBook(int userId, int bookId, int num);

    boolean updateBook(Integer bookId,Integer userId,int num);
}
