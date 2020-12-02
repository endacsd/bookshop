package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Record;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.CartMapper;
import cn.endacsd.yingbookshop.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {



    @Resource
    CartMapper cartMapper;


    @Resource
    BookMapper bookMapper;

    @Override
    public boolean addBook(Integer bookId, Integer id,Integer num) {
        //检查是否存在
        Integer prenum = cartMapper.getQTYbyDoubleId(id,bookId);
        if(prenum==null){
            cartMapper.addBook(id,bookId,num);
        }else{
            //已经存在
            cartMapper.updateOneBook(id,bookId,num+prenum);
        }
        return true;

    }

    @Override
    public boolean removeBook(Integer bookId, Integer id) {
        return cartMapper.clearOneBook(id,bookId);
    }

    @Override
    public boolean clear(Integer userId) {
        return cartMapper.clearOneCart(userId)>0;
    }

    @Override
    public boolean addOneBook(Integer bookId, Integer userId){
        return addBook(bookId,userId,1);
    }

    @Override
    public boolean subOneBook(Integer bookId, Integer userId) {
        return subBook(bookId,userId,1);
    }


    @Override
    public List<Record> getCartInfo(Integer userId) {
        List<Item> itemList=cartMapper.getCartInfo(userId);
        List<Record> resList=new ArrayList<>();
        for(Item item : itemList){
            //
            //
            resList.add(new Record(bookMapper.findBookByIdFromAll(item.getBookId()),item.getNum()));
        }
        return resList;
    }


    @Override
    public boolean subBook(Integer bookId, Integer id, Integer num) {
        Integer prenum = cartMapper.getQTYbyDoubleId(id,bookId);
        if(prenum==null||prenum<num){
            return false;
        }else if(prenum.equals(num)){
            removeBook(bookId,id);
        }else{
            cartMapper.updateOneBook(id,bookId,prenum-num);
        }
        return true;
    }







    @Override
    public boolean checkBook(int userId, int bookId, int num) {
        System.out.println(userId+" "+bookId);
        System.out.println(cartMapper.getQTYbyDoubleId(userId,bookId));
        return num<=cartMapper.getQTYbyDoubleId(userId,bookId);
    }


    @Override
    public boolean updateBook(Integer bookId, Integer userId, int num) {
        return cartMapper.updateOneBook(userId,bookId,num)!=null;
    }
}
