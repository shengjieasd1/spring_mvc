package cn.mldn.action;

import cn.mldn.util.action.AbstractAtion;
import cn.mldn.vo.Emp;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 如果要定义该Action映射路径，则可以在类上定义统一名称与方法中的路径组合
// 或者直接在某一个方法上进行具体的映射路径不在类上进行映射路径的配置
// 该名称绝对不能够重复（完全重复）
@Controller    // 当前的这个程序属于Spring MVC中的一个控制器
@RequestMapping("/pages/emp/*")
public class EmpAction extends AbstractAtion {	// 只要以“/pages/emp/”开头就表示访问此Action
	private Logger log = Logger.getLogger(EmpAction.class);
	
	@RequestMapping("add")
	public ModelAndView add(Emp emp){
		log.info(emp);
		
		return null;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
		//本方法的处理指的时追加有一个自定义的转换编辑器，如果遇见的操作目标类型为java.util.Date类
		//则使用定义好的SimpleDateFormat类来进行格式化处理，并且允许此参数的内容为空
		binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(sdf,true));
	}
	
	@RequestMapping("get")
	public ModelAndView get(int eid, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		log.info("***** contextPath =" + request.getContextPath());
		log.info("***** sessionId =" + session.getId());
		log.info("***** realPath =" + application.getRealPath("/"));
		try {
			response.getWriter().println("Hello World !");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@RequestMapping("remove")
	public ModelAndView remove(@RequestParam(value = "empno",defaultValue = "10") int eid){
		System.out.println(eid * 2);
		return null;
	}
	
	@RequestMapping("list")
	public ModelAndView list(
			@RequestParam(value = "cp", defaultValue = "1") int currentPage,
			@RequestParam(value = "ls", defaultValue = "5")int lineSize,
			@RequestParam(value = "col", defaultValue = "ename")String column,
			@RequestParam(value = "kw", defaultValue = "")String keyword){
		log.info("***** currentPage ="+currentPage);
		log.info("***** lineSize ="+lineSize);
		log.info("***** column ="+column);
		log.info("***** keyword ="+keyword);
		
		return null;
	}
	
	@RequestMapping("info")
	public ModelAndView info(){
		log.info(super.Value("vo.edit.msg","雇员"));
		log.info(super.Value("emp.add.page"));
		log.info(super.Value("emp.add.rules"));
		return null;
	}
	
	
	@RequestMapping("echo")	// 最终路径：/pages/emp/echo
	public ModelAndView echo(String msg) {	// 进行消息的接收，msg就表示参数
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allEmps", new ArrayList<String>()) ;
		map.put("empCount", 1000) ; 
		ModelAndView mav = new ModelAndView("/pages/show") ;
		mav.addAllObjects(map) ;	// 将Map集合交由ModelAndView处理
		mav.addObject("info", "ECHO : " + msg) ;	// 设置request属性范围
		Logger.getLogger(cn.mldn.action.EmpAction.class).info("ECHO : " + msg);
		return mav ;	// 表示现在不进行跳转，一般如果使用Ajax连接，才需要使用这样的返回方式
	}
	
	@RequestMapping("sss")	// 最终路径：/pages/emp/echo
	public ModelAndView sss(String msg) {	// 进行消息的接收，msg就表示参数
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allEmps", new ArrayList<String>()) ;
		map.put("empCount", 2000) ;
		ModelAndView mav = new ModelAndView("show") ;
		mav.addAllObjects(map) ;	// 将Map集合交由ModelAndView处理
		mav.addObject("info", "ECHO : " + msg) ;	// 设置request属性范围
		Logger.getLogger(cn.mldn.action.EmpAction.class).info("ECHO : " + msg);
		return mav ;	// 表示现在不进行跳转，一般如果使用Ajax连接，才需要使用这样的返回方式
	}
	
}  
