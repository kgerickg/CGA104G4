package com.admin.service;

import java.util.List;

import com.admin.entity.Admin;

import core.service.CoreService;

public interface AdminService extends CoreService {
	Admin register(Admin admin);

	Admin login(Admin admin);

	Admin edit(Admin admin);

	List<Admin> findAll();

	
	boolean save(Admin admin);
}
