// IUserDao.java
package org.didnelpsun.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.didnelpsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface IUserDao {
    // 查询所有用户
    @Select("select * from user")
    List<User> selectAllUsers();
    // 插入用户
    @Insert("insert into user(name,sex,birthday,address) values (#{name},#{sex},#{birthday},#{address})")
    void insertUser(User user);
    // 更新用户
    @Update("update user set name=#{name},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);
    // 删除用户
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);
    // 查询一个用户
    @Select("select * from user where id=#{id}")
    User selectUser(Integer id);
    // 根据用户名模糊查询用户
    @Select("select * from user where name like #{name}")
    List<User> selectUsersByName(String name);
    // 获取用户总数
    @Select("select count(id) from user")
    Integer getUsersSum();
}
