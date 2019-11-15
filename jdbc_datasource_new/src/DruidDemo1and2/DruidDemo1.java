package DruidDemo1and2;

import com.Utils.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by 姜曙亮 on 2019/11/14.
 */
public class DruidDemo1 {
    @Test
    /**
     * Druid使用方式2
     * 配置的方式设置参数
     * Druid配置方式可以使用属性文件配置的
     * 文件名称没有规定的但是属性文件中的key是规定的
     */
    public void demo2(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        DataSource dataSource=null;
        try{
            //加载属性文件
            Properties properties=new Properties();
            properties.load(new FileInputStream("src/druid.properties"));
            //使用Druid产生DataSource
            dataSource=DruidDataSourceFactory.createDataSource(properties);
            //获取连接
            conn=dataSource.getConnection();
            //编写sql
            String sql="select * from test_table";
            //预编译SQL
            pstmt=conn.prepareStatement(sql);
            //设置参数
            //执行sql
            rs=pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("username")+" "+rs.getString("sex")+" "+rs.getString("math")+" "+rs.getString("english"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,conn);
            //此处Druid已经将conn的close()方法重写，实现了归还链接而非销毁链接
        }
    }
    /**
     * Druid的使用方式1手动配置
     */
    public void demo1(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            //使用连接池
            DruidDataSource dataSource=new DruidDataSource();
            //手动设置数据库链接参数
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/test");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            //获得链接,如此JDBCUtils中的getConnection()无用了
            //conn= JDBCUtils.getConnection();
            conn=dataSource.getConnection();
            //编写sql
            String sql="select * from test_table";
            //预编译SQL
            pstmt=conn.prepareStatement(sql);
            //设置参数
            //执行sql
            rs=pstmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("username")+" "+rs.getString("sex")+" "+rs.getString("math")+" "+rs.getString("english"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,conn);
            //此处Druid已经将conn的close()方法重写，实现了归还链接而非销毁链接
        }
    }
}
