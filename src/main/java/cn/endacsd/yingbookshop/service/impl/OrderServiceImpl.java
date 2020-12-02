package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Order;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.OrderMapper;
import cn.endacsd.yingbookshop.service.OrderService;
import cn.endacsd.yingbookshop.utils.OrderUtil;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    OrderMapper orderMapper;

    @Resource
    BookMapper bookMapper;
    @Override
    public R getOrdersByUserId(Integer userId) {
        return R.ok().put("orderList",getOrdersListByUserId(userId));
    }

    @Override
    public List<Order> getOrdersListByUserId(Integer userId) {
        List<Order> list= orderMapper.findOrdersByUserId(userId);
        for(Order order : list){
            order.setBook(bookMapper.findBookByIdFromAll(order.getBookId()));
        }
        return list;
    }

    @Override
    public boolean addOrders(List<Item> list,Integer userId) {
        //
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        for(Item item : list){
            item.setUserId(userId);
            Order order=OrderUtil.makeOrder(item);
            System.out.println(order);
            orderMapper.addOrder(order);
        }
        return true;
    }
}
