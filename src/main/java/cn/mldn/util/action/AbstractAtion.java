package cn.mldn.util.action;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class AbstractAtion {
    @Resource
    private MessageSource msgSoure;
    
    /**
     * 根据指定的KEY的信息进行资源数据的读取控制
     * @param msgKey 表示要读取的资源文件的key的内容
     * @return
     */
    public String Value(String msgKey,Object ...args){
        return  this.msgSoure.getMessage(msgKey,args, Locale.getDefault());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
        //本方法的处理指的时追加有一个自定义的转换编辑器，如果遇见的操作目标类型为java.util.Date类
        //则使用定义好的SimpleDateFormat类来进行格式化处理，并且允许此参数的内容为空
        binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(sdf,true));
    }
}
