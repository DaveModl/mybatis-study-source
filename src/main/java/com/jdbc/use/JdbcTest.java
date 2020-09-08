package com.jdbc.use;

import com.db.util.JdbcUtil;

public class JdbcTest {
    public static void main(String[] args) {
//        String dbName = JdbcUtil.createDB("jdbc_test");
//        String createTab = "create table person_info("
//                +"id bigint not null auto_increment primary key,"
//                +"name varchar(30),"
//                +"age int)";
//        JdbcUtil.createTable(dbName,createTab);


//        String insert = "INSERT INTO person_info "
//                       +"VALUES (null,'Jerry',12)";
//        JdbcUtil.insertData("jdbc_test",insert);
        String update = "update person_info set name = ?,age = ? where id = ?";
        JdbcUtil.update("jdbc_test",update);
        String select = "select id,name,age from person_info";
        JdbcUtil.select("jdbc_test",select);
    }
}
