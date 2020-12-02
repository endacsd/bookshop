package cn.endacsd.yingbookshop.utils;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.YUser;

public class DataMaker {
    public static Book getTestBook(){
        Book book=new Book();
        book.setQTY(1);
        book.setPrice(100);
        book.setPublisher("test");
        book.setName("test");
        book.setCover(null);
        book.setAuthor("test");
        return book;
    }

    public static YUser getTestUser(){
        YUser yUser=new YUser();
        yUser.setUsername("test"+StringUtil.getSalt());
        String salt=StringUtil.getSalt();
        String password=MD5.encrypt(salt+salt);
        yUser.setPassword(password);
        yUser.setSalt(salt);
        yUser.setAvatar("");
        yUser.setAuthority("ROLE_TEST");
        yUser.setMail(StringUtil.getSalt()+"@"+StringUtil.getSalt()+"."+StringUtil.getSalt());
        yUser.setSurplus(1000*100);
        return yUser;
    }
}
