package currencyConverter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		TestBean bean = context.getBean("testBean", TestBean.class);
		
		System.out.println(bean.getName());
	}
}
