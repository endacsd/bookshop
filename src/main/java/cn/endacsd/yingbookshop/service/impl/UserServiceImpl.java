package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.*;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.service.BookService;
import cn.endacsd.yingbookshop.service.CartService;
import cn.endacsd.yingbookshop.service.OrderService;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private CartService cartService;
    private BookService bookService;
    private OrderService orderService;

    @Resource
    YUserMapper yUserMapper;

    @Autowired
    void setCartService(CartServiceImpl cartService){
        this.cartService=cartService;
    }

    @Autowired
    void setOrderService(OrderServiceImpl orderService){
        this.orderService=orderService;
    }
    @Autowired
    void setBookService(BookServiceImpl bookService){this.bookService=bookService;}

    private String getUsername(){

        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    private YUser getUser(){
        return yUserMapper.findByUsername(getUsername());
    }
    private Integer getUserId(){
        return getUser().getId();
    }
    private R getR(boolean isSuccess){
        return isSuccess ? R.ok() : R.error();
    }
    private R getR(boolean isSuccess,String successMessage,String errorMessage){
        return isSuccess ? R.ok(successMessage) : R.error(errorMessage);
    }



    @Override
    public R register(YUser yUser){

        // 对yUser进行数据校验
        String res1= UserUtil.checkUsername(yUser.getUsername());
        if(res1!=null) return R.error(415,res1);
        String res2= UserUtil.checkMail(yUser.getMail());
        if(res2!=null) return R.error(415,res2);
        String res3= UserUtil.checkPassword(yUser.getPassword());
        if(res3!=null) return R.error(415,res3);
        YUser user1=yUserMapper.findByUsername(yUser.getUsername());
        YUser user2=yUserMapper.findByMail(yUser.getMail());
        if(user1!=null) return R.error(400,"username exits");
        if(user2!=null) return R.error(400,"mail     exits");
        //初始化数据
        yUser.setAuthority("ROLE_USER");
        yUser.setAvatar("https://www.endacsd.cn/images/avatar.png");
        yUser.setSurplus(100*100);
        // add salt
        yUser.setSalt(StringUtil.getSalt());
        yUser.setPassword(MD5.encrypt(yUser.getPassword()+yUser.getSalt()));
        //保存
        try{
            yUserMapper.registerUser(yUser);
        }catch (Exception e){
            e.printStackTrace();
            return R.error(500,e.getMessage());
        }


        return R.ok().put("msg","success");


    }


    @Override
    public R addBook(Integer bookId, Integer num) {
        return getR(cartService.addBook(bookId,getUserId(),num));

    }


    @Override
    public R removeBook(Integer bookId, Integer num) {
        return getR(cartService.subBook(bookId,getUserId(),num));
    }

    @Override
    public R removeCart() {

        return getR(cartService.clear(getUserId()));
    }

    @Override
    public R buyAll(List<Item> list,boolean isCartBooks) {
        //1.1 检查 格式
        String result= ListUtil.checkDuplicate(list);
        if(result!=null){
            //格式有问题
            return R.error("购买失败:"+result);
        }



        //1.2 检查数目
        if(!list.stream().reduce(true,(res,cur)-> res&&cur.getNum()>0 ,(res,cur)->null)){
            return R.error("购买失败:数目错误");
        }



        //2. QTY
        if(!bookService.checkQTY(list)) return R.error("购买失败").put("reason","库存不足");

        //3. cost


        int cost=bookService.getCost(list);
        if(cost>getUser().getSurplus()){
            return R.error("购买失败").put("reason","余额不足!");
        }

        //无问题进行购买

        if(buyItems(list,isCartBooks)) return R.ok("购买成功");
        else               return R.error("服务器错误");
    }

    private boolean buyItems(List<Item> list,boolean isCartBooks){
        //

        int cost = bookService.getCost(list);
        int surplus = getUser().getSurplus();
        //1. 扣钱
        yUserMapper.updateSurplusById(surplus-cost,getUserId());
        //2. 扣库存
        bookService.subBooks(list);
        //3. 消除对应的购物车
        if(isCartBooks){
           removeBook(list);
        }
        //直接购买的情况下,已经加入购物车了

        //4. 增加订单
        orderService.addOrders(list,getUserId());

        return true;
    }
    /**
     * 直接购买
     **/
    public R buyOneBook(Integer bookId){
        //
        return buyManyBook(bookId,1);
    }

    public R buyManyBook(Integer bookId,Integer num){
        return buyAll(new ArrayList<>(Arrays.asList(new Item(bookId, getUserId(), num))),false);
    }


    @Override
    public R getCartInfo() {
        //获取购物车信息
        List<Record> recordList=cartService.getCartInfo(getUserId());
        return R.ok().put("itemList",recordList);
    }


    @Override
    public R addBook(List<Item> itemList) {
        //验证数据是否合法
        //1.1 检查 格式

        for(Item item : itemList){
            if(!bookService.checkBook(item.getBookId())) return R.error(item.getBookId()+"不存在");
            if(item.getNum()<=0)                        return R.error(item.getBookId()+"数量错误");
        }

        for(Item item : itemList){
            //
            //验证bookId存在
            addBook(item.getBookId(),item.getNum());
        }


        return R.ok("添加成功");
    }


    @Override
    public R removeBook(List<Item> itemList) {
        //
        System.out.println("=======");
        for(Item item : itemList){
            System.out.println(item);
            if(!bookService.checkBook(item.getBookId())) return R.error(item.getBookId()+"不存在");
            System.out.println(item);
            if(item.getNum()<=0)                        return R.error(item.getBookId()+"数量错误");
            System.out.println(item);
            if(!cartService.checkBook(getUserId(),item.getBookId(),item.getNum())) {
                return R.error(item.getBookId()+"超出数量限制");
            }
        }
        System.out.println("=======");
        //开始删除
        for(Item item : itemList){
            //
            //验证bookId存在
            removeBook(item.getBookId(),item.getNum());
        }
        return R.ok("删除成功");

    }

    @Override
    public R setBook(Item item) {

        int bookId=item.getBookId();
        int num =item.getNum();
        //
        if(num<0) return R.error("设置失败:不能设置为负数!!");

        else if(num==0){
            //删除
            return getR(cartService.removeBook(bookId,getUserId()),"设置成功","设置失败");
        }else{
            return getR(cartService.updateBook(bookId,getUserId(),num),"设置成功","设置失败");
        }
    }

    @Override
    public R getOrders() {
        return orderService.getOrdersByUserId(getUserId());
    }


    @Override
    public R getOrderByPage(Page page) {
        return PageUtil.getPage(orderService.getOrdersListByUserId(getUserId()),page,"order");
    }
}
