package cn.endacsd.yingbookshop.security;


import cn.endacsd.yingbookshop.utils.R;
import cn.endacsd.yingbookshop.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.out.println("notPass");
        ResponseUtil.out(httpServletResponse, R.error(e.getMessage()));
    }
}
