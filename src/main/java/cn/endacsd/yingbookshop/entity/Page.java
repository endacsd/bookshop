package cn.endacsd.yingbookshop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Page {
    @JsonProperty(value = "pageSize")
    int pageSize;
    @JsonProperty(value = "index")
    int index;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", index=" + index +
                '}';
    }
}
