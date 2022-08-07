package com.treeman.service;

import com.treeman.pojo.UserRole;

import java.util.List;

public interface IUseRoleService {
    public List<UserRole> getAll();
    public List<UserRole> getPage(int pageNum,int pageSize);
    public boolean insert(UserRole userRole);
    public UserRole getOneById(long id);
    public UserRole getOneByUsername(String name);
    public boolean updateOne(UserRole userRole);
    public boolean deleteById(long id);
}
