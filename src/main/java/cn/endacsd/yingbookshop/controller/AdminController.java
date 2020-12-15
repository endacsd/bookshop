package cn.endacsd.yingbookshop.controller;


import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.Page;
import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.service.AdminService;
import cn.endacsd.yingbookshop.service.impl.AdminServiceImpl;
import cn.endacsd.yingbookshop.utils.FileUtil;
import cn.endacsd.yingbookshop.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1")
public class AdminController {

    //info
    private AdminService adminService;
    @Autowired
    void setAdminService(AdminServiceImpl adminService){
        this.adminService=adminService;
    }



    @RequestMapping(value = "getAllBookInfoByPage",method = RequestMethod.POST)
    public R getAllBookInfoByPage(@RequestBody Page page){
        return adminService.getALLBookInfo(page);
    }
    @RequestMapping(value = "getAllUserInfoByPage",method = RequestMethod.POST)
    public R getAllUserInfoByPage(@RequestBody Page page){

        System.out.println("DEB:" + "getUserInfo"+ page);
        return adminService.getAllUserInfo(page);
    }

    @RequestMapping(value = "setUserInfo",method = RequestMethod.POST)
    public R setUserInfo(@RequestBody YUser user){
        return adminService.setUserInfo(user);
    }

    @RequestMapping(value ="setBookInfo",method = RequestMethod.POST)
    public R setBookInfo(@RequestBody Book book){
        return adminService.setBookInfo(book);
    }

    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    public R addBook(@RequestBody List<Book> bookList){
        return adminService.addBook(bookList);
    }


    @RequestMapping(value = "uploadFile" ,method = RequestMethod.POST)
    public R uploadFile(){
        return R.error();
    }


    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public R addUser(@RequestBody List<YUser> yUserList){
        return adminService.addUser(yUserList);
    }


    @RequestMapping(value = "uploadCover", method = RequestMethod.POST)
    public R fileUploadPic(@RequestParam("file") MultipartFile file){
        System.out.println(file);
        String res= FileUtil.fileUpload(file,"/api/public/images/");
        return R.ok(res);
    }



}
