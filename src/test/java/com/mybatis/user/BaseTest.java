package com.mybatis.user;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;
@Slf4j
public class BaseTest {
    private static SqlSessionFactory sqlSessionFactory;
    public static final Gson gson = new Gson();
    @BeforeClass
    public static void init() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                log.error("init config error:",e);
            }
        }
    }

    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
