package cn.endacsd.yingbookshop.utils;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Page;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static <T> R getPage(List<T> list, Page page,String resName){

        //
        int pageSize= page.getPageSize();
        int index=page.getIndex();
        int length=list.size();


        int pageLength=(length+pageSize-1) / pageSize;
        if(index<=0) index=1;
        if(index>pageLength) index=pageLength;
        List<T> resList=new ArrayList<>();
        int pageNumber=pageLength;
        if(pageLength==0){
            pageNumber=1;
            index=1;
        }else{
            //0-(index-1)
            for(int i=(index-1)*pageSize;i<length&&i<index*pageSize;i++){
                resList.add(list.get(i));
            }
        }
        return R.ok().put("pageNumber",pageNumber).put(resName+"List",resList).put("index",index).put(resName+"Number",length);
    }
}
