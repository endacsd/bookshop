package cn.endacsd.yingbookshop.service;


import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Order;
import cn.endacsd.yingbookshop.mapper.OrderMapper;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface OrderService {

    R getOrdersByUserId(Integer userId);

    boolean addOrders(List<Item> list,Integer userId);

    List<Order> getOrdersListByUserId(Integer userId);
}
