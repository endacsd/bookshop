package cn.endacsd.yingbookshop.security.impl;

import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Resource
    YUserMapper yUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        YUser yUser= yUserMapper.findByUsername(username);
        if(yUser==null){
            throw new UsernameNotFoundException("用戶名不存在！");
        }else if(yUser.isHide()){
            throw new UsernameNotFoundException("该用户已经被禁用，请联系管理员");
        }

        SecurityUser user=new SecurityUser(yUser);
        List<String > permissionValueList= new ArrayList<>();
        permissionValueList.add(yUser.getAuthority());
        user.setPermissionValueList(permissionValueList);

        return user;
    }

}
