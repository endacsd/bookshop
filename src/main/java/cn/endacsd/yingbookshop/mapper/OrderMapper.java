package cn.endacsd.yingbookshop.mapper;

import cn.endacsd.yingbookshop.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * from `order` where userId=#{0} order by date DESC")
    List<Order> findOrdersByUserId(Integer userId);

    @Select("SELECT * from `order` where bookId=#{0}")
    List<Order> findOrdersByBookId(Integer bookId);

    @Select("SELECT * from `order`")
    List<Order> findAllOrders();


    @Insert("insert into `order`(userId,bookId,num,date) " +
            "values(#{userId},#{bookId},#{num},#{date})")
    Integer addOrder(Order order);
}
