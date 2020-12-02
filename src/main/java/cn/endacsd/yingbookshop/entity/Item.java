package cn.endacsd.yingbookshop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    @JsonProperty(value = "bookId")
    int bookId;
    @JsonProperty(value = "userId")
    int userId;
    @JsonProperty(value = "num")
    int num;

    @Override
    public String toString() {
        return "Item{" +
                "bookId=" + bookId +
                ", userId=" + userId +
                ", num=" + num +
                '}';
    }

    public Item(int bookId, int userId, int num) {
        this.bookId = bookId;
        this.userId = userId;
        this.num = num;
    }

}
