package cn.mldn.test;

import cn.mldn.Service.INewsService;
import cn.mldn.vo.News;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@ContextConfiguration(locations = { "classpath:spring-transaction.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // 设置要使用的测试工具
public class TestNewsService extends TestCase {
	@Resource
	private INewsService newsService;
	@Test
	public void testAdd() { 
		News vo = new News();
		vo.setTitle("中国万岁，中国腾飞 - ");
		vo.setNote("中国梦 - ");
		vo.setPubdate(new Date());
		vo.setPrice(999.00);
		vo.setReadcount(900);
		TestCase.assertTrue(this.newsService.add(vo));
	} 
	
	@Test
	public void testGet() {
		News vo = this.newsService.get((long) 5) ;
		System.err.println(vo);
		TestCase.assertNotNull(vo);
	}
}
