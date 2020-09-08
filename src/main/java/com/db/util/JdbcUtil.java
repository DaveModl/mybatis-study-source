package com.db.util;

import com.google.gson.Gson;
import com.jdbc.use.PersonIfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        return createConnection(DRIVER, URL, USERNAME, PASSWORD);
    }

    public static String createDB(String name) {
        //取得sql执行器
        Statement stat = null;
        Connection conn = null;
        try {
            conn = getConnection();
            log.info("create database start:");
            //创建sql执行器
            stat = conn.createStatement();
            String sql = "CREATE DATABASE " + name;
            //执行sql
            stat.executeUpdate(sql);
            log.info("Create database success.");
        } catch (SQLException e) {
            log.error("create database error:", e);
        } finally {
            closeConnection(conn, stat, null, null);
        }
        return name;
    }

    public static void createTable(String name,String sql){
        Connection connection = null;
        Statement statement = null;
        final String url = "jdbc:mysql://localhost:3306/" + name + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        try {
            connection = createConnection(DRIVER,url,USERNAME,PASSWORD);
            log.info("create table start:");
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            log.info("Create table success.");
        } catch (SQLException e) {
            log.error("Create table error:",e);
        }finally {
            closeConnection(connection,statement,null,null);
        }
    }

    public static int insertData(String name,String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        int rows = 0;
        final String url = "jdbc:mysql://localhost:3306/" + name + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        try {
            connection = createConnection(DRIVER,url,USERNAME,PASSWORD);
            log.info("insert table start:");
            //手动提交
            connection.setAutoCommit(false);
            //预编译
            ps = connection.prepareStatement(sql);
            rows = ps.executeUpdate();
            log.info("update rows are:{}",rows);
            connection.commit();
            log.info("insert table success.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.info("rollback table error.",ex);
            }
            log.error("insert table error:",e);
        }finally {
            closeConnection(connection,null,ps,null);
        }
        return rows;
    }
    
    public static List<PersonIfo> select(String name,String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        List<PersonIfo> personIfos = new ArrayList<>();
        ResultSet rs = null;
        final String url = "jdbc:mysql://localhost:3306/" + name + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        try {
            connection = createConnection(DRIVER,url,USERNAME,PASSWORD);
            log.info("select table start:");
            //预编译
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                PersonIfo pif = PersonIfo.builder()
                        //索引从0开始
                        .id(rs.getLong(1))
                        .name(rs.getString(2))
                        .age(rs.getInt(3))
                        .build();
                personIfos.add(pif);
            }
            log.info("select table success.");
            log.info("result;{}",new Gson().toJson(personIfos));
        } catch (SQLException e) {
            log.error("select table error:",e);
        }finally {
            closeConnection(connection,null,ps,rs);
        }
        return personIfos;
    }

    public static int update(String name,String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        int[] rows = new int[0];
        final String url = "jdbc:mysql://localhost:3306/" + name + "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
        try {
            connection = createConnection(DRIVER,url,USERNAME,PASSWORD);
            log.info("update table start:");
            //手动提交
            connection.setAutoCommit(false);
            //预编译
            ps = connection.prepareStatement(sql);
            ps.setString(1,"Xixi");
            ps.setInt(2,22);
            ps.setLong(3,1L);

            ps.addBatch();

            ps.setString(1,"Alice");
            ps.setInt(2,20);
            ps.setLong(3,2L);

            ps.addBatch();
            //批量更新
            rows = ps.executeBatch();
            log.info("update rows are:{}",rows.length);
            connection.commit();
            log.info("update table success.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.info("rollback table error.",ex);
            }
            log.error("update table error:",e);
        }finally {
            closeConnection(connection,null,ps,null);
        }
        return rows.length;
    }


    private static Connection createConnection(String driver, String url, String username, String password) {
        Connection conn = null;
        try {
            //注册驱动
            Class.forName(driver);
            //建立连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void closeConnection(Connection connection, Statement statement, PreparedStatement ps, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Closing rs error:",e);
            }
        }

        if (ps !=  null){
            try {
                ps.close();
            } catch (SQLException e) {
                log.error("Closing pstat error:",e);
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Closing stat error:",e);
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("closing conn error:",e);
            }
        }
    }
}
