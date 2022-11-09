package core.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.admin.dao.AdminDao;
import com.admin.entity.Admin;

public class TestApp {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDao adminDao = applicationContext.getBean(AdminDao.class);
		Admin admin = adminDao.selectById(1);
		System.out.println(admin.getAdmName());
		((ConfigurableApplicationContext) applicationContext).close();
	}
}
