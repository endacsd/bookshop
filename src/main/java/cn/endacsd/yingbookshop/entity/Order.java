package cn.endacsd.yingbookshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Order {


    @JsonProperty(value = "userId")
    int userId;
    @JsonProperty(value = "bookId")
    int bookId;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @JsonProperty(value = "book")
    Book book;
    @JsonProperty(value = "num")
    int num;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @JsonProperty(value = "date")
    String date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
