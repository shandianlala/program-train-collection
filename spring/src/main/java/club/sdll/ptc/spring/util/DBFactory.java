package club.sdll.ptc.spring.util;

import java.sql.Connection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 数据工厂
 * @author chengxiwang
 * @createDate 2017年10月26日
 */
public final class DBFactory {
	
	private static ApplicationContext ac = null;
	
	private DBFactory() {}
	
	public static void getConnection() {
		if(ac == null) {
			ac = new ClassPathXmlApplicationContext("classpath:conf/applicationContext-spring.xml");
		}
	}
	
	public static Object getBean(String beanName) {
		getConnection();
		Object bean = ac.getBean(beanName);
		return bean;
	} 

}
