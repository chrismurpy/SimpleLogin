package com.murphy.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author murphy
 * 数据库连接池 + 工具类
 */
public class DbUtils {
    /**
     * 1. 定义变量
     */
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    /**
     * 定义受影响的行数
     */
    private int count;

    private static String driver;
    private static String url;
    private static String userName;
    private static String userPass;
    private static DruidDataSource druid = new DruidDataSource();

    static {
        /**
         * 2. 加载驱动信息
         */
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        userName = bundle.getString("username");
        userPass = bundle.getString("password");

        // 使用Druid连接池
        druid.setDriverClassName(driver);
        druid.setUrl(url);
        druid.setUsername(userName);
        druid.setPassword(userPass);

        try {
            druid.setFilters(bundle.getString("filters"));
            druid.setInitialSize(Integer.parseInt(bundle.getString("initialSize")));
            druid.setMaxActive(Integer.parseInt(bundle.getString("maxActive")));
            druid.setMaxWait(Integer.parseInt(bundle.getString("maxWait")));
            druid.setTimeBetweenEvictionRunsMillis(Long.parseLong(bundle.getString("timeBetweenEvictionRunsMillis")));
            druid.setMinEvictableIdleTimeMillis(Long.parseLong(bundle.getString("minEvictableIdleTimeMillis")));
            druid.setValidationQuery(bundle.getString("validationQuery"));
            druid.setTestWhileIdle(Boolean.parseBoolean(bundle.getString("testWhileIdle")));
            druid.setTestOnBorrow(Boolean.parseBoolean(bundle.getString("testOnBorrow")));
            druid.setTestOnReturn(Boolean.parseBoolean(bundle.getString("testOnReturn")));
            druid.setPoolPreparedStatements(Boolean.parseBoolean(bundle.getString("poolPreparedStatements")));
            druid.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(bundle.getString("maxPoolPreparedStatementPerConnectionSize")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 3. 建立连接
     */
    protected Connection getConn(){
        try {
            // Druid
            conn = druid.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 4. 获得预处理通道
     */
    protected PreparedStatement getPs(String sql){
        try {
            ps = getConn().prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ps;
    }

    /**
     * 5. 绑定参数
     */
    protected void param(List list){
        if (list != null && list.size() > 0){
            for (int i=0; i<list.size();i++){
                try {
                    ps.setObject(i+1,list.get(i));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * 6. 执行操作查询
     */
    protected ResultSet query(String sql,List list){
        getPs(sql);
        param(list);
        try {
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    /**
     * 7. 关闭所有连接
     */
    protected void closeAll(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
