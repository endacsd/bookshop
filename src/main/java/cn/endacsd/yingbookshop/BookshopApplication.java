package cn.endacsd.yingbookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {})
@MapperScan(basePackages = {"cn.endacsd.yingbookshop.mapper"})
//  @ComponentScan(basePackageClasses= {cn.endacsd.yingbookshop.controller.GlobalExceptionHandler.class})
public class BookshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshopApplication.class, args);
    }

}
