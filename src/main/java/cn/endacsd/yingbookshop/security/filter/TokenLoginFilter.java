package cn.endacsd.yingbookshop.security.filter;


import cn.endacsd.yingbookshop.entity.YUser;
import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.security.SecurityUser;
import cn.endacsd.yingbookshop.security.TokenManager;
import cn.endacsd.yingbookshop.utils.R;
import cn.endacsd.yingbookshop.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private AuthenticationManager authenticationManager;
    private YUserMapper yUserMapper;



    public TokenLoginFilter(AuthenticationManager authenticationManager,
                            TokenManager tokenManager,
                            RedisTemplate redisTemplate,
                            YUserMapper yUserMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.yUserMapper=yUserMapper;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
    }

    //1 获取表单提交用户名和密码
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        //获取表单提交数据
        //System.out.println("233333333");
        try {
            YUser user = new ObjectMapper().readValue(request.getInputStream(), YUser.class);
            //System.out.println(user);
            //System.out.println(yUserMapper);
            user.setPassword(user.getPassword()+yUserMapper.findSaltByUsername(user.getUsername()));
            //System.out.println(user);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //2 认证成功调用的方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        //认证成功，得到认证成功之后用户信息
        System.out.println("okokokokokokok");
        SecurityUser user = (SecurityUser)authResult.getPrincipal();
        //根据用户名生成token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        //把用户名称和用户权限列表放到redis
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(),user.getPermissionValueList());
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername()+"Token",token);
        //返回token
        ResponseUtil.out(response, R.ok().put("token",token));
    }

    //3 认证失败调用的方法
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        ResponseUtil.out(response, R.error("用户名或密码错误"));
    }
}
