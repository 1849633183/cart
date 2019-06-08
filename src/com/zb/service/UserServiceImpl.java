package com.zb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zb.dao.OrderDao;
import com.zb.mapper.RoleMapper;
import com.zb.mapper.UserMapper;
import com.zb.pojo.Order;
import com.zb.pojo.Role;
import com.zb.pojo.Userinfo;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void addRoleToUser(String userId, String[] roleIds) {

		for (String roleId : roleIds) {
			userDao.addRoleToUser(userId, roleId);
		}
	}

	@Override
	public List<Role> findOtherRoles(String userId) {
		return userDao.findOtherRoles(userId);
	}

	@Override
	public Userinfo findById(String id) throws Exception {

		return userDao.findById(id);
	}

	@Override
	public String save(Userinfo userInfo) throws Exception {
		// 对密码进行加密处理
		if(checkUser(userInfo)){
		userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
		userInfo.setUstatus(1);
		userInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		userDao.save(userInfo);
		roleMapper.saveRole(userInfo.getId(),"1");
		new OrderDao().addOrder(new Order(userInfo,"-1",1,0));
		return "注册成功";
		}
		else
		{
			return "账号已存在";
		}
	}

	@Override
	public List<Userinfo> findAll() throws Exception {
		return userDao.findAll();
	}

	// spring security的登录service
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Userinfo userInfo = null;
		try {
			userInfo = userDao.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 处理自己的用户对象封装成UserDetails
		// User user=new
		// User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
		User user = new User(userInfo.getEmail(), userInfo.getPassword(), userInfo.getUstatus() == 0 ? false : true,
				true, true, true, getAuthority(userInfo.getRoles()));
		return user;
	}

	// 作用就是返回一个List集合，集合中装入的是角色描述
	public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

		List<SimpleGrantedAuthority> list = new ArrayList<>();
		for (Role role : roles) {
			list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		}
		return list;
	}

	@Override
	public boolean checkUser(Userinfo userinfo) throws Exception {

		if (null == userDao.findByEmail(userinfo.getEmail()))
			return true;
		else
			return false;
	}
}
