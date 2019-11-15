package c3p0Demo;

import com.Utils.JDBCUtils2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 姜曙亮 on 2019/11/15.
 */
public class C3P0Demo3 {
    @Test
    /**
     * 使用新的工具类的测试
     */
    public void demo(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            conn= JDBCUtils2.getConnection();
            String sql="select * from test_table";
            pstmt=conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("username")+" "+rs.getString("math")+" "+rs.getString("english"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JDBCUtils2.release(rs,pstmt,conn);
        }
    }
}
