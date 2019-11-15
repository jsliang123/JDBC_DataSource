package c3p0Demo;

import com.Utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 姜曙亮 on 2019/11/15.
 * C3P0连接池测试
 */
public class C3P0Demo1 {
    
    @Test
    /**
     * 手动设置参数
     */
    public void demo1(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            //获得连接:从连接池中获取
            ComboPooledDataSource dataSource=new ComboPooledDataSource();
            //手动设置连接的参数
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql:///test");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            //获得连接
            conn=dataSource.getConnection();

            /*String sql1="UPDATE test_table SET sex=? WHERE username=?";
            String sql2="select * from test_table where username=?";
            pstmt=conn.prepareStatement(sql1);//每次都产生一个新的pstmt
            pstmt.setString(1,"女");
            pstmt.setString(2,"xiaogei");
            pstmt.addBatch(sql1);

            //对sql2怎么处理最后才能加入到batch批处理中
            */

            String sql1="select * from test_table where username=?";
            pstmt=conn.prepareStatement(sql1);
            pstmt.setString(1,"xiaoming");
            rs=pstmt.executeQuery();
            if(rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("sex")+" "+rs.getString("math")+" "+rs.getString("english"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,conn);//连接池的底层已经将Connection的close()方法修改不会销毁连接了而是归还连接池
        }
    }
}
