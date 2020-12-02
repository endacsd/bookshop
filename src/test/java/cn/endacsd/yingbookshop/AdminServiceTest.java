package cn.endacsd.yingbookshop;

import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.service.AdminService;
import cn.endacsd.yingbookshop.service.impl.AdminServiceImpl;
import cn.endacsd.yingbookshop.utils.DataMaker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AdminServiceTest {



    private AdminService adminService;
    @Autowired
    void setAdminService(AdminServiceImpl adminService){
        this.adminService=adminService;
    }



    @Test
        void testAddBook(){
        Book book= DataMaker.getTestBook();
        System.out.println(book);
        System.out.println(adminService);
        adminService.addBook(Arrays.asList(book));
    }

    @Test
    void testAddUser(){
        YUser yUser=DataMaker.getTestUser();
        List<YUser> yUserList=new ArrayList<>();
        yUserList.add(yUser);
        System.out.println(yUserList);
        adminService.addUser(yUserList);

    }



}
