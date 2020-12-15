package cn.endacsd.yingbookshop.security;

import cn.endacsd.yingbookshop.mapper.YUserMapper;
import cn.endacsd.yingbookshop.security.filter.TokenAuthFilter;
import cn.endacsd.yingbookshop.security.filter.TokenLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsUtils;
;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {


    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private UserDetailsService userDetailsService;
    private YingCorsConfiguration yingCorsConfiguration;
    @Resource
    private YUserMapper yUserMapper;
    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager,
                                  RedisTemplate redisTemplate,
                                  YingCorsConfiguration yingCorsConfiguration) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.yingCorsConfiguration = yingCorsConfiguration;
    }
    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    //设置退出的地址和token，redis操作地址
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        http.exceptionHandling().authenticationEntryPoint(new UnauthEntryPoint())//没有权限访问

                .and().csrf().disable().authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/api/register","/api/public/**").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/api/logout")//退出路径
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate,yUserMapper))
                .addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic()
        ;
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       //http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        // http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
        //http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }

    //调用userDetailsService和密码处理
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
    //不进行认证的路径，可以直接访问
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/test/**","/register","/api/public/**");

    }





}
