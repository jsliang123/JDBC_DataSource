package c3p0Demo;

import com.Utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 姜曙亮 on 2019/11/15.
 */
public class C3P0Demo2 {
    @Test
    /**
     * 使用.xml配置文件
     */
    public void demo(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
//            dataSource.setUser("root");
//            dataSource.setPassword("root");
            conn=dataSource.getConnection();

            String sql="select * from test_table";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("username")+" "+rs.getString("math")+" "+rs.getString("english"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,conn);
        }
    }
}
