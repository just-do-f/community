package com.example.mycommunity.interceptor;

import com.example.mycommunity.mapper.UserMapper;
import com.example.mycommunity.model.User;
import com.example.mycommunity.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create by czl on 2021/7/8 17:20
 */
@Service
public class sessionInterception implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies= request.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){

                String token=cookie.getValue();
                UserExample userExample=new UserExample();
                userExample.createCriteria().andTokenEqualTo(token);
                List<User> user=userMapper.selectByExample(userExample);
                if(user.size()!=0){
                    request.getSession().setAttribute("user",user.get(0));
                }
                break;
            }
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
