// UserDAO.java
package org.didnelpsun.dao;

import org.didnelpsun.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAO {
    // 模拟DAO操作
    // 设置一个Map容器，保存一个Integer的数据索引即User的id和User的数据
    private static final Map<Integer, User> users = new HashMap<>();
    // 注入模拟数据
    static {
        users.put(1,new User(1,"黄","男","2000-04-22", "湖北省武汉市"));
        users.put(2,new User(2,"金","男","2003-07-05", "湖北省仙桃市"));
        users.put(3,new User(3,"蓝","女","2001-11-17", "湖北省荆州市"));
        users.put(4,new User(4,"绿","男","2002-10-21", "福建省福州市"));
        users.put(5,new User(5,"紫","女","2001-05-06", "浙江省宁波市"));
    }
    // 数据插入指针，即user的id
    private static Integer pointer=6;
    // 保存用户，包括插入操作和更新操作
    public void saveUser(User user){
        // 如果传入user不带有id，就给一个id给user，即插入一个新用户
        if(user.getId()==null){
            // 判断pointer是否在users存在，如果在就自增，获取一个最新的id
            while (users.containsKey(pointer))
                pointer++;
            user.setId(pointer);
        }
        // 注意更新user时不能更新user的id，否则会覆盖其他数据
        users.put(user.getId(), user);
    }
    // 获取所有值的集合
    public Collection<User> selectAllUsers(){
        return users.values();
    }
    // 获取单个用户
    public User selectUser(Integer id){
        return users.get(id);
    }
    // 删除单个用户
    public User deleteUser(Integer id){
        return users.remove(id);
    }
}
