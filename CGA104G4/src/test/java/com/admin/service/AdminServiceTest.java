package com.admin.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.admin.entity.Admin;

import core.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@EnableTransactionManagement
public class AdminServiceTest {
	@Autowired
	private AdminService service;

	@Test
	public void register() {
		Admin admin = new Admin();
		admin.setAdmAcc("vera1211");
		admin.setAdmPwd("1qaz@WSX");
		admin.setAdmName("Vera");
		admin = service.register(admin);
		assertThat(admin.getAdmId(), notNullValue());
	}

	@Test
	public void login() {
		Admin admin = new Admin();
		admin.setAdmAcc("admin01");
		admin.setAdmPwd("password1");
		admin = service.login(admin);
		assertThat(admin.getAdmName(), equalTo("eric"));
	}

	@Test
	public void edit() {
		Admin admin = new Admin();
		admin.setAdmAcc("admin01");
		admin.setAdmName("password1");
		admin.setAdmAccStat(0);
		admin = service.edit(admin);
		assertThat(admin.isSuccessful(), equalTo(true));
	}

	@Test
	public void findAll() {
		List<Admin> admin = service.findAll();
		assertThat(admin.size(), equalTo(3));
	}

	@Test
	public void save() {
		Admin admin = new Admin();
		admin.setAdmAcc("admin01");
		admin.setAdmPwd("password1");
		admin.setAdmName("eric");
		admin.setAdmAccStat(0);
		assertThat(service.save(admin), equalTo(true));
	}
}
