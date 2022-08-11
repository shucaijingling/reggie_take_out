package com.itheima.reggie_take_out.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie_take_out.common.BaseContext;
import com.itheima.reggie_take_out.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取本次请求路径uri：格式：/backend/page/login/login.html
        String uri = request.getRequestURI();

        //定义拦截路径
        String[] uris = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        //判断是否为拦截路径 true为拦截，false不拦截
        boolean check = check(uris, uri);

        //判断请求uri是否拦截 false
        //判断用户是否已经登录，登录了也放行 判断请求的session是否为空，不为空就放行
        if (check){
            log.info("拦截到的请求1：{}",uri);
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute("employee") != null){
            log.info("拦截到的请求2：{}",uri);
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        //如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        log.info("拦截到的请求3：{}",uri);
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 判断是否匹配到拦截路径，匹配到拦截路径为true，否则为false
     * @param uris
     * @param requestUri
     * @return
     */
    public boolean check(String[] uris, String requestUri) {
        for (String uri : uris) {
            boolean match = PATH_MATCHER.match(uri, requestUri);
            if (match){
                return true;
            }
        }
        return false;
    }
}
