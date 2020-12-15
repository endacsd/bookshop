package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Item;
import cn.endacsd.yingbookshop.entity.Record;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.CartMapper;
import cn.endacsd.yingbookshop.service.CartService;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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

        if(!checkBookNum(bookId,id,num)) return false;
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
        System.out.println(prenum+" "+num);
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
        return num<=Optional.ofNullable(cartMapper.getQTYbyDoubleId(userId,bookId)).orElse(-1)&&num<=1000;
    }


    @Override
    public boolean updateBook(Integer bookId, Integer userId, int num) {
        return cartMapper.updateOneBook(userId,bookId,num)!=null;
    }


    @Override
    public boolean checkBookNum(int bookId,int userId, int num) {
        Book book=bookMapper.findBookById(bookId);
        int bookQTY= book==null ? -1 : book.getQTY();
        int aftNum = Optional.ofNullable(cartMapper.getQTYbyDoubleId(userId,bookId)).orElse(-1);
        return bookQTY >= ((long) aftNum + num)&&(aftNum+num)<=1000;
    }
}
