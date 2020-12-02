package cn.endacsd.yingbookshop.service;


import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    R getAllUserInfo(Page page);
    R setUserInfo(YUser yUser);
    R setBookInfo(Book book);
    R addBook(List<Book> book);
    R uploadFIle();

    R addUser(List<YUser> yUserList);

    R getALLBookInfo(Page page);
}
