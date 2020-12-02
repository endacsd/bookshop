package cn.endacsd.yingbookshop.utils;

import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Order;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class OrderUtil {
    public static Order makeOrder(Item item){

        Order order=new Order();
        order.setBookId(item.getBookId());
        order.setNum(item.getNum());
        order.setUserId(item.getUserId());
        order.setDate(getSystemDate());
        return order;
    }

    public static String getSystemDate() {
        //
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

    }
    public static void main(String[] args) {
        //System.out.println(formatter.format(getSystemDate()));
        System.out.println(makeOrder(new Item(1,1,1)));
    }
}
