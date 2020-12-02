package cn.endacsd.yingbookshop.mapper;
import cn.endacsd.yingbookshop.entity.Book;
import cn.endacsd.yingbookshop.entity.YUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface YUserMapper {


    @Select("select * from user")
    public List<YUser> findAllYUser();

    @Select("select * from user where username=#{0} ")
    public YUser findByUsername(String username);

    @Select("select * from user where mail=#{0} ")
    public YUser findByMail(String mail);

    @Insert("insert into user(username,password,mail,authority,surplus,avatar,salt,hide)" +
            "values(#{username},#{password},#{mail},#{authority},#{surplus},#{avatar},#{salt},#{hide})")
    boolean registerUser(YUser user);


    @Select("select salt from user where username=#{0}")
    String findSaltByUsername(String username);



    @Update("update user set surplus=#{surplus} where id=#{id}")
    void updateSurplusById(Integer surplus,Integer id);


    @Update("update user set " +
            "authority=#{authority},surplus=#{surplus},avatar=#{avatar},hide=#{hide} where id=#{id}")
    Integer setUserInfo(YUser user);


}
