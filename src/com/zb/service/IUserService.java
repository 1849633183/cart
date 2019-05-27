package com.zb.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.zb.pojo.Role;
import com.zb.pojo.Userinfo;

public interface IUserService extends UserDetailsService {
    List<Userinfo> findAll() throws Exception;

    void save(Userinfo userInfo) throws Exception;

    Userinfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds);
}
