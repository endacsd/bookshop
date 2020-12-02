package cn.endacsd.yingbookshop.service.impl;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.mapper.BookMapper;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.service.AdminService;
import cn.endacsd.yingbookshop.service.UserService;
import cn.endacsd.yingbookshop.utils.PageUtil;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    YUserMapper yUserMapper;

    @Resource
    BookMapper bookMapper;
    private UserService userService;
    @Autowired
    void setUserService(UserServiceImpl userService){
        this.userService=userService;
    }


    @Override
    public R getAllUserInfo(Page page) {
        System.out.println(yUserMapper.findAllYUser());
        //return R.ok("获取消息成功").put("userList",yUserMapper.findAllYUser());
        return PageUtil.getPage(yUserMapper.findAllYUser(),page,"user");
    }

    @Override
    public R setUserInfo(YUser yUser) {
        System.out.println(yUser.isHide());
        int userId=yUser.getId();

        String username=yUser.getUsername();
        YUser user=yUserMapper.findByUsername(username);
        //
        if(user==null) return R.error("查无此人");
        if(userId!=user.getId()||!yUser.getMail().equals(user.getMail())) return R.error("用户无法匹配");
        Integer result=yUserMapper.setUserInfo(yUser);

        if(result==null || result ==0) return R.error("修改失败");
        return R.ok("修改成功");
    }

    @Override
    public R setBookInfo(Book book) {
        //查询
        int bookId=book.getId();
        Book preBook=bookMapper.findBookByIdFromAll(bookId  );
        //
        if(preBook==null) return R.error("此书不存在");

        //检查
        Integer result=bookMapper.updateBook(book);
        if(result==null || result ==0) return R.error("修改失败");
        return R.ok("修改成功");
    }

    @Override
    public R addBook(List<Book> bookList) {
        //
        if(bookList.size()==0) return R.error("请输入超过一本书籍的信息");
        List<String> failBookList=new ArrayList<>();
        for(Book book : bookList){
            try{
                bookMapper.saveBook(book);
            }catch (Exception e){
                failBookList.add(book.getName()+":"+e.getMessage());
            }
        }
        if(failBookList.size()==bookList.size()){
            return R.error("全部添加失败");
        }else if(failBookList.size()!=0){
            return R.error("部分添加成功").put("failList",failBookList);
        }else{
            return R.ok("添加成功");
        }
    }

    @Override
    public R addUser(List<YUser> yUserList) {
        if(yUserList.size()==0) return R.error("请输入超过一个用户的信息");
        List<String> failUserList=new ArrayList<>();
        for(YUser user : yUserList){
            try{
                yUserMapper.registerUser(user);
            }catch (Exception e){
                failUserList.add(user.getUsername()+":"+e.getMessage());
            }
        }
        if(failUserList.size()==yUserList.size()){
            return R.error("全部添加失败");
        }else if(failUserList.size()!=0){
            return R.error("部分添加成功").put("failList",failUserList);
        }else{
            return R.ok("添加成功");
        }
    }

    @Override
    public R uploadFIle() {
        return R.error();
    }


    @Override
    public R getALLBookInfo(Page page) {
        //return R.ok("获取成功").put("bookList",bookMapper.findAllBooks());
        return PageUtil.getPage(bookMapper.findAllBooks(),page,"book");
    }
}
