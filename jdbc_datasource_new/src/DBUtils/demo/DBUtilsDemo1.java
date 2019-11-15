package DBUtils.demo;

import com.Utils.JDBCUtils2;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by 姜曙亮 on 2019/11/15.
 */
public class DBUtilsDemo1 {
    /**
     * DBUtils的增删改操作
     */
    @Test
    /**
     * 添加操作
     */
    public  void demo1() throws SQLException {
        //创建核心类
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("insert into test_table values (?,?,?,?,?,?)","7","yaya","123","女","79","78");
    }
    @Test
    /**
     * 修改操作
     */
    public void demo2() throws SQLException {
        //创建核心类
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("update test_table set username=? where id=?","yayi",4);
    }
    @Test
    /**
     * 删除操作
     */
    public void demo3() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        queryRunner.update("delete from test_table where id=?",3);
    }
}
