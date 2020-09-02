package cn.mldn.util.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * 完成的是一个个具体的验证规则的判断操作
 */
public class ValidateRuleUtil {
    /**
     * 验证传入的mime类型是否复合于当前的开发要求
     * @param mimeRules 整体验证规则
     * @param mime 每一个上传文件的类型
     * @return
     */
    public static boolean isMime(String mimeRules [],String mime){
        if(isString(mime)) {
            for (int i = 0; i < mimeRules.length; i++) {
                if (mime.equals(mimeRules[i])) {
                    return true;
                }
            }
        }
        return true;
    }
    
    /**
     * 验证码的检测，验证码的属性名称固定为rand
     * @param request
     * @param str
     * @return
     */
    public static boolean isRand(HttpServletRequest request,String str){
        if (isString(str)){
           String rand = (String) request.getSession().getAttribute("rand");
           if(!isString(rand)){
               return rand.equalsIgnoreCase(str);
           }
        }
        return false;
    }
    
    /**str
     * 判断是否是整数
     * @param str
     * @return
     */
    public static boolean isInt(String str){
        if (isString(str)){
        return str.matches("\\d+");
        }
        return false;
    }
    /**
     * 验证是否是日期,格式为"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    
    public static boolean isDatetime(String str){
        if (isString(str)){
            return str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        }
        return false;
    }
    
    
    /**
     * 验证是否是日期,格式为"yyyy-MM-dd"
     * @return
     */
    
    public static boolean isDate(String str){
        if (isString(str)){
            return str.matches("\\d{4}-\\d{2}-\\d{2}");
        }
        return false;
    }
    
    /**
     * 判断是否是小数
     * @param str
     * @return
     */
    public static boolean isDouble(String str){
        if(isString(str)){
            return str.matches("\\d+(\\.\\d+)?");
        }
        return false;
    }
    
    
    /**
     *  如果传入的内容为null或者是空字符串，则表示错误，返回false
     * @param str
     * @return
     */
    public static boolean isString(String str){
        if (str == null || "".equals(str)){
            return false;
        }
        return true;
    }
}
