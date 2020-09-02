package cn.mldn.test;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestMessageService {
	private static ApplicationContext ctx = null ;
	static {	// 静态代码块优先于所有的代码执行，目的是为了静态属性初始化
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void testDeptConstructor() {
	}
}
