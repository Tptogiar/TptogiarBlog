package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CookiesUtils;
import utils.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author Tptogiar
 * @descripiton 对除“/visitor”外的所有Serlvet程序进行过滤，以及
 * 对目录pages的过滤
 * @create 2021/07/24-16:29
 */

@WebFilter({"/pages/*","/user","/essay"})
public class LoginFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            logger.info("LoginFilter拦截到"+req.getRequestURL());
            String servletPath = req.getServletPath();
            if ("/visitor".equals(servletPath)){
                chain.doFilter(request,response);
                return;
            }
            Cookie userIdCookie = CookiesUtils.getCookie("userId", req.getCookies());
            if(userIdCookie==null){
                req.getRequestDispatcher("/login.html").forward(request,response);
                logger.info("跳转至登录页面");
            }else{
                Integer userIdInt = WebUtils.parseToInt(userIdCookie.getValue().toString(), null);
                req.setAttribute("userId",userIdInt);
                chain.doFilter(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
