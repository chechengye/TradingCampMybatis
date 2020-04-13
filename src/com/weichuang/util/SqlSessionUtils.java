package com.weichuang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {

    /**
     * 获取sqlSession方法
     */
    public static SqlSession getSqlSession(){
        //1、获取主配置文件的位置
        String resource = "com/weichuang/mybatis/sqlMapConfig.xml";
        //2、根据文件获取输入流
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //3、根据输入流获取sql会话工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //4、根据sql会话工厂获取/打开一个sql会话。true:开启事务，可以执行数据库的增删改操作。否则false：只能进行查询操作
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
