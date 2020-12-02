package cn.endacsd.yingbookshop.entity;

public class Record {
    public Record(Book book, Integer num) {
        this.book = book;
        this.num = num;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    private Book book;
    private Integer num;


}
