package com.weichuang.test;

import com.weichuang.mapper.UserMapper;
import com.weichuang.pojo.User;
import com.weichuang.util.SqlSessionUtils;
import com.weichuang.vo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuickStartDemo {

    @Test
    public void testFn(){

        try {
            //1、获取主配置文件的位置
            String resource = "com/weichuang/mybatis/sqlMapConfig.xml";
            //2、根据文件获取输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //3、根据输入流获取sql会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //4、根据sql会话工厂获取/打开一个sql会话。true:开启事务，可以执行数据库的增删改操作。否则false：只能进行查询操作
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            //5、从会话对象中获取一个mapper映射
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getAllUser();
            System.out.println(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFn1(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void testFn2(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("zhangfei");
        user.setPassword("123456");
        mapper.addUser(user);
    }
    /**
     * 需求：根据用户名模糊查询用户信息，查询条件放到QueryVo的user属性中。
     */
    @Test
    public void testFn3(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("i");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> userList = mapper.getUsersByQueryVo(vo);
        System.out.println(userList);
    }
    /**
     * 需求:查询用户表数据条数
     */
    @Test
    public void testFn4(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.getUserCount();
        System.out.println(count);
    }
    /**
     *
     需求：根据性别和名字查询用户
     */
    @Test
    public void testFn5(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = "";
        String sex = "男";
        List<User> userList = mapper.getUsersByNameAndSex(username , sex);
        System.out.println(userList);
    }

    @Test
    public void testFn6(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<User> userList = mapper.getUsersByIds(ids);
        System.out.println(userList);
    }
    /**
     * Mybatis整合Spring测试
     */
    @Test
    public void testFn7(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        List<User> allUser = userMapper.getAllUser();
        System.out.println(allUser);
    }
}
