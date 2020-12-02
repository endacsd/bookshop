package cn.endacsd.yingbookshop.mapper;


import cn.endacsd.yingbookshop.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper {


    @Select("select * from book")
    public List<Book> findAllBooks();
    @Insert("insert into book(name,author,publisher,price,QTY,cover,hide)" +
            "values(#{name},#{author},#{publisher},#{price},#{QTY},#{cover},#{hide})")
    public boolean saveBook(Book book);

    @Select("select * from book where name=#{0} AND hide=false")
    List<Book> findBooksByName(String name);


    @Update("update book set QTY=#{QTY} where id=#{id}")
    public Integer updateBookNum(Integer id,Integer QTY);


    @Select("select * from book where id=#{0} and hide=false")
    public Book findBookById(Integer bookId);

    @Select("select * from book where id=#{0} ")
    public Book findBookByIdFromAll(Integer bookId);

    @Update("update book set QTY=#{QTY},cover=#{cover}," +
            "name=#{name},author=#{author}," +
            "publisher=#{publisher},price=#{price}," +
            "hide=#{hide} " +
            "where id=#{id}")
    Integer updateBook(Book book);




}
