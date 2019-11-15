package com.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by jsliang on 2019/11/14.
 */
public class JDBCUtils2 {
    //创建一个连接池，只需要创建一次
    private  static final ComboPooledDataSource dataSource=new ComboPooledDataSource();
    //获得连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    //获得连接池
    public static DataSource getDataSource(){
        return dataSource;
    }
    public static void release(ResultSet rs, PreparedStatement pstmt,Connection conn ){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }

        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                pstmt=null;
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }

    }
}
