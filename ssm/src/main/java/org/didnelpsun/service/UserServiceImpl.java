// UserServiceImpl.java
package org.didnelpsun.service;

import org.didnelpsun.dao.IUserDao;
import org.didnelpsun.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    // 私有的DAO
    private IUserDao userDao;
    // Spring注入
    @Autowired
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public List<User> selectAllUsers() {
        return userDao.selectAllUsers();
    }
}
