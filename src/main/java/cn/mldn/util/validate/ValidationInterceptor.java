package cn.mldn.util.validate;

import cn.mldn.util.resource.ResourceReadUtil;
import cn.mldn.util.validator.ValidatorUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ValidationInterceptor implements HandlerInterceptor {

    Logger log = Logger.getLogger(ValidationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        //需要取得HandlerMethod对象，这样可以取得相关的Action信息
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Map<String, String> errors = ValidatorUtils.validate(request,handlerMethod);
        if (errors.size() >0){
            request.setAttribute("errors",errors);
            flag = false;
            request.getRequestDispatcher(ResourceReadUtil.getErrorPageValue(handlerMethod)).forward(request,response);
        }else {
            return flag;
        }
        return flag;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("****postHandle****"+handler.getClass()+"、modelAndView = "+ modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("****afterCompletion****"+handler.getClass());
    
    }
}
