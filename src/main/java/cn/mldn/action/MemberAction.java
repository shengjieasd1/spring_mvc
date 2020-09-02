package cn.mldn.action;

import cn.mldn.util.SplitPageUtil;
import cn.mldn.vo.Member;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class MemberAction {
    private Logger log = Logger.getLogger(MemberAction.class);
    
    @RequestMapping(value = "/aaa/{msgParam}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String aaa(@PathVariable("msgParam") String msg){
        return "ECHO : "+msg;
    }
    
    @RequestMapping(value = "/echo/{msgParam}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String echo(@PathVariable("msgParam") String msg){
        return "ECHO : "+msg;
    }
    
    //定义该方法的访问路径，而后表示该方法返回的数据类型为普通的文本类型（MIME）
    @RequestMapping(value = "/info",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String info(){//该方法的返回值即回应的主题消息
        return "www.mldnjava.cn";
    }
    
    @RequestMapping(value = "/member",method = RequestMethod.PUT,produces = "application/json;text/plain;charset=UTF-8")
    public @ResponseBody Object edit(Member vo1){
        log.info("【***** 修改用户信息 ******】"+vo1);
        JSONObject obj = new JSONObject();
        obj.put("flag",true);
        return obj;
    }
    
    @RequestMapping(value = "/member/{condition}",method = RequestMethod.GET,produces = "application/json;text/plain;charset=UTF-8")
    public @ResponseBody Object list(@PathVariable("condition") String condition, SplitPageUtil su){
        log.info("【当前查询】condition = "+condition);
        List<Member> all = new ArrayList<>();
        for(int i = (su.getCurrentPage()-1)*su.getLineSize();i<su.getCurrentPage()*su.getLineSize();i++){
            Member vo = new Member();
            vo.setMid(i);
            vo.setName("姓名-"+i);
            vo.setSalary(10000.0+i);
            vo.setHiredate(new Date());
            all.add(vo);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("memberCount",892349);
        result.put("allMembers",all);
        result.put("condition",condition);
        return result;
        
    }
    
    @RequestMapping(value = "/member/{mid:\\d+}",method = RequestMethod.GET,produces = "application/json;text/plain;charset=UTF-8")
    public @ResponseBody Object get(@PathVariable("mid") int mid){
        Member vo = new Member();
        vo.setMid(mid);
        vo.setName("aaa");
        vo.setSalary(1999.99);
        vo.setHiredate(new Date());
        return vo;
    }
    
    @RequestMapping(value = "/member/{mid:\\d+}",method = RequestMethod.DELETE,produces = "application/json;text/plain;charset=UTF-8")
    public @ResponseBody Object delete(@PathVariable("mid") Member mid){
        log.info("【***** 删除用户信息 ******】"+mid);
        JSONObject obj = new JSONObject();
        obj.put("flag",true);
        return obj;
    }
    
    @RequestMapping(value = "/member",method = RequestMethod.POST,produces = "application/json;text/plain;charset=UTF-8")
    public @ResponseBody Object add(Member vo){
        log.info("【***** 增加用户信息 ******】"+vo);
        JSONObject obj = new JSONObject();
        obj.put("flag",true);
        return obj;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
        //本方法的处理指的时追加有一个自定义的转换编辑器，如果遇见的操作目标类型为java.util.Date类
        //则使用定义好的SimpleDateFormat类来进行格式化处理，并且允许此参数的内容为空
        binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(sdf,true));
    }
}
