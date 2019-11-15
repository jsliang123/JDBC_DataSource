package DBUtils.Demo3;

import DBUtils.domain.Account;
import com.Utils.JDBCUtils2;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 姜曙亮 on 2019/11/15.
 * ResulutHandler的实现类
 */
public class DBUtilsDmeo3 {
    @Test
    /**
     * ArrayHandler:将一条记录封装到一个Object数组中
     */
    public void demo1() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        Object[] objs=queryRunner.query("select * from test_table where id=?",new ArrayHandler(),4);
        System.out.println(Arrays.toString(objs));
    }

    @Test
    /**
     * ArrayListHandler:将多条记录封装到装有Object数组的List集合中
     */
    public void demo2() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        List<Object[]> list=queryRunner.query("select * from test_table", new ArrayListHandler());
        for(Object[] objects:list){
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    /**
     * 使用BeanHandler:将一条记录封装到一个javaBean中
     */
    public void demo3() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        Account account=queryRunner.query("select * from test_table where id=?",new BeanHandler<Account>(Account.class),4);
        //注意数据库中列名和类中的属性值保持一致
        System.out.println(account);
    }

    @Test
    /**
     * 使用BeanListHandler：将多条记录封装到装有javaBean的List集合中
     */
    public void demo4() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        List<Account> list=queryRunner.query("select * from test_table",new BeanListHandler<Account>(Account.class));
        for(Account account:list){
            System.out.println(account);
        }
    }
    @Test
    /**
     * MapHandler:将一条记录封装到Map中
     */
    public void demo5() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        Map<String,Object> map=queryRunner.query("select * from test_table where id=?",new MapHandler(),4);
        System.out.println(map);
    }

    @Test
    /**
     * MapListHandler:将多条记录封装到装有Map的List中
     */
    public void demo6() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        List<Map<String ,Object>> list=queryRunner.query("select * from test_table",new MapListHandler());
        for(Map<String,Object> map:list){
            System.out.println(map);
        }
    }
    @Test
    /**
     * ColimnListHandler：将某一列的之封装到List集合中
     */
    public void demo7() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        List<Object> list=queryRunner.query("select username from test_table",new ColumnListHandler<Object>("username"));
        for(Object object:list){
            System.out.println(object);
        }
    }
    @Test
    /**
     * ScalarHandler:将某个值封装
     */
    public void demo8() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        Object object=queryRunner.query("select count(*) from test_table",new ScalarHandler<>());
        System.out.println(object);
    }
}
