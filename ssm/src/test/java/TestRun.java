import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.didnelpsun.dao.IUserDao;
import org.didnelpsun.entity.User;
import org.didnelpsun.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestRun {
    @Test
    public void testSpring(){
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        // 获取对象
        UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userService");
        // 调用方法
        userService.selectAllUsers();
    }
    @Test
    public void testMyBatis() throws IOException {
        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqSession对象
        SqlSession session = sqlSessionFactory.openSession();
        // 获取代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        // 查询所有数据
        List<User> users = userDao.selectAllUsers();
        for(User user : users){
            System.out.println(user.toString());
        }
        // 关闭资源
        session.close();
        in.close();
    }
}
