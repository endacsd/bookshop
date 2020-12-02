package cn.endacsd.yingbookshop.utils;

import cn.endacsd.yingbookshop.entity.Item;

import java.util.HashSet;
import java.util.List;

public class ListUtil {


    public static String checkDuplicate(List<Item> items){
        HashSet<Integer> S=new HashSet<>();
        Integer userId=null;
        for(Item item : items){
            if(S.contains(item.getBookId())) {
                return "格式不对: 包含了相同的的book";
            }
            if(userId==null) userId=item.getUserId();
            else {
                if(userId!=item.getUserId()) return "格式不对: 包含了相同的userId";
            }
            S.add(item.getBookId());

        }
        return null;
    }

    public static String checkUsername(List<Item> items, Integer userId) {
        for(Item item : items){
            if(item.getUserId()!=userId) return "包含了错误的用户来源";
        }
        return null;
    }
}
