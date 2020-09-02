package cn.mldn.util.resource;

import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

public class ResourceReadUtil {
    
    /**
     * 读取错误页的信息
     * @param handlerMethod
     * @return
     */
    public static String getErrorPageValue(HandlerMethod handlerMethod){
        String pageKey = handlerMethod.getBean().getClass().getSimpleName() + "." + handlerMethod.getMethod().getName() + ".rules";
        String pagUrl = getValue(handlerMethod,pageKey);
        if(pagUrl == null){
            pagUrl = getValue(handlerMethod,"error.page");
        }
        return pagUrl;
    }
    
    /**
     * 实现手工配置读取
     * @param handlerMethod
     * @param msgKey
     * @return
     */
    public static String getValue(HandlerMethod handlerMethod,String msgKey){
        try {
            //现在取得了验证规则的key的信息之后实际上并无法知道key对应的具体的内容是声明，而内容需要依靠AbstractAction.getValue
            Method getValueMethod = handlerMethod.getBean().getClass().getMethod("getValue", String.class, Object.class);
            return getValueMethod.invoke(handlerMethod.getBean(), msgKey, null).toString();
        }catch (Exception e){
            return "";
        }
    }

}
