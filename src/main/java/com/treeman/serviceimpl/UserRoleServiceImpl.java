package com.treeman.serviceimpl;

import com.treeman.mapper.UserRoleMapper;
import com.treeman.pojo.UserRole;
import com.treeman.service.IUseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements IUseRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getAll() {
        return userRoleMapper.getAll();
    }

    @Override
    public List<UserRole> getPage(int pageNum, int pageSize) {
        return userRoleMapper.getPage((pageNum-1)*pageSize,pageSize);
    }

    @Override
    public boolean insert(UserRole userRole) {
        return userRoleMapper.insert(userRole)>0;
    }

    @Override
    public UserRole getOneById(long id) {
        return userRoleMapper.getOneById(id);
    }

    @Override
    public UserRole getOneByUsername(String name) {
        return userRoleMapper.getOneByUsername(name);
    }

    @Override
    public boolean updateOne(UserRole userRole) {
        return userRoleMapper.updateOne(userRole)>0;
    }

    @Override
    public boolean deleteById(long id) {
        return userRoleMapper.deleteById(id)>0;
    }
}
