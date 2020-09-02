package cn.mldn.util.validator;

import cn.mldn.util.resource.ResourceReadUtil;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ValidatorUtils {
    private static Logger log = Logger.getLogger(ValidatorUtils.class);
    /**
     * 实现提交参数的验证，使用指定Action的指定验证规则处理
     * @param request
     * @param handlerMethod
     * @return 所有的验证错误信息保存在Map集合中返回，如果没有错误，则Map集合的长度为0
     */
    public static Map<String,String> validate(HttpServletRequest request, HandlerMethod handlerMethod){
        //通过给定的Action名称以及要调用的业务方法"rules"一起拼凑出要取出的验证规则，在Validations.properties中定义
        String validationKey = handlerMethod.getBean().getClass().getSimpleName() + "." + handlerMethod.getMethod().getName() + ".rules";
        Map<String,String> errors = new HashMap<>();
        try {
        ////现在取得了验证规则的key的信息之后实际上并无法知道key对应的具体的内容是声明，而内容需要依靠AbstractAction.getValue
        Method getValueMethod = handlerMethod.getBean().getClass().getMethod("getValue", String.class, Object.class);
        try {//如果现在没有指定的key有可能产生异常，就认为现在没有具体的验证规则出现
            //通过getValue（）方法的Method对象取得对应的验证信息
            String validationValue = (String) getValueMethod.invoke(handlerMethod.getBean(), validationKey,null);
            if (validationValue != null) {
                log.info("****preHandle****  validationValue  ="+validationValue );
                //取得全部的提交参数，需要针对于给定的规则进行拆分控制
                String result[] = validationValue.split("\\|");
                for(int x = 0;x<result.length;x++){
                    String temp[] = result[x].split(":");
                    String paramName = temp[0];
                    String paramRule = temp [1];//验证规则
                    String paramValue = request.getParameter(paramName);
                    log.info("提交参数paramName"+paramName+"paramValue = "+request.getParameter(paramName));
                    switch (paramRule){
                        case "string":{
                            if(!ValidateRuleUtil.isString(paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.string.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                        case "int":{
                            if(!ValidateRuleUtil.isInt(paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.int.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                        case "double":{
                            if(!ValidateRuleUtil.isDouble(paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.double.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                        case "date":{
                            if(!ValidateRuleUtil.isDate(paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.date.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                        case "datetime":{
                            if(!ValidateRuleUtil.isDatetime(paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.datetime.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                        case "rand":{
                            if(!ValidateRuleUtil.isRand(request,paramValue)){//该验证没有通过
                                String msg = (String) getValueMethod.invoke(handlerMethod.getBean(), "validation.rand.msg",null);
                                errors.put(paramName,msg);
                            }
                            break;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }}catch (Exception e){
            e.printStackTrace();
        }
        if(errors.size() == 0){//如果之前有错就不用验证上传类型了
            //判断是否有上传文件
            MultipartResolver mr = new CommonsMultipartResolver(); //上传操作
            if(mr.isMultipart(request)){ //当前有上传文件
                //需要拼凑验证规则使用的key的信息
                String mimeKey = handlerMethod.getBean().getClass().getSimpleName() + "." + handlerMethod.getMethod().getName() + ".rules";
                String mimeValue = ResourceReadUtil.getValue(handlerMethod,mimeKey);
                if(mimeValue == null){ //没有消息读到
                    mimeValue = ResourceReadUtil.getValue(handlerMethod,"mime.rules");
                }
                String mimeResult [] = mimeValue.split("\\|");//因为是一组规则，所以需要拆分
                MultipartRequest mreq = (MultipartRequest) request;  //处理上传时的request
                Map<String, MultipartFile> fileMap = mreq.getFileMap();//获得全部的上传文件
                if(fileMap.size() > 0){
                    //需要判断每一个文件类型
                    Iterator<Map.Entry<String,MultipartFile>> iter = fileMap.entrySet().iterator();
                    while (iter.hasNext()){ //判断每个文件的类型
                        Map.Entry<String,MultipartFile> me = iter.next();
                        if(me.getValue().getSize() > 0){ //当前这个上传文件的长度大于0
                            if(ValidateRuleUtil.isMime(mimeResult,me.getValue().getContentType())){
                                errors.put("",ResourceReadUtil.getValue(handlerMethod,"validation.mime.msg"));
                            }
                        }
                    }
                }
            }
        }
        return errors;
    }
}
