package cn.endacsd.yingbookshop.mapper;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Record;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {
    @Insert("insert into cart_book(userId,bookId,num)" +
            "values(#{userId},#{bookId},#{num})")
    boolean addBook(int userId,int bookId,int num);

    @Update("UPDATE cart_book SET num=(num-1) where userId=#{userId} AND bookId=#{bookId}")
    boolean removeOneBook(Integer userId ,Integer bookId);

    @Update("UPDATE cart_book SET num=#{num} where userId=#{userId} AND bookId=#{bookId}")
    Boolean updateOneBook(Integer userId,Integer bookId,Integer num);

    @Delete("DELETE FROM cart_book WHERE userId=#{userId} AND bookId=#{bookId}")
    boolean clearOneBook(Integer userId,Integer bookId);

    @Delete("DELETE FROM cart_book where userId=#{0}")
    Integer     clearOneCart(Integer userId);
    @Delete("DELETE FROM cart_book where num=0")
    Integer formant();
    @Select("SELECT * FROM cart_book where userId=#{userId} AND bookId=#{bookId} ")
    Integer check(Integer userId, Integer bookId);


    @Select("SELECT * FROM cart_book where userId=#{userId}")
    List<Item> getCartInfo(Integer userId);


    @Select("SELECT num FROM cart_book where userId=#{userId} AND bookId=#{bookId}")
    Integer getQTYbyDoubleId(Integer userId,Integer bookId);
}
