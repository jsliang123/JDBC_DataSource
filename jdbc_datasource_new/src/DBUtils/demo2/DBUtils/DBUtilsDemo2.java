package DBUtils.demo2.DBUtils;

import DBUtils.domain.Account;
import com.Utils.JDBCUtils;
import com.Utils.JDBCUtils2;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 姜曙亮 on 2019/11/15.
 * DBUtils的查询操作
 */
public class DBUtilsDemo2 {
    @Test
    /**
     * 查询一条记录的操作
     */
    public void demo1() throws SQLException {
        //创建核心类
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        //执行查询
        Account account=queryRunner.query("select * from test_table where id=?", new ResultSetHandler<Account>() {
            //这里实现接口的方法
            @Override
            public Account handle(ResultSet rs) throws SQLException {
                Account account=new Account();
                while(rs.next()){
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setSex(rs.getString("sex"));
                    account.setMath(rs.getString("math"));
                    account.setEnglish(rs.getString("english"));
                }
                return account;
            }
        }, 1);
        System.out.println(account);
    }

    @Test
    /**
     * 查询多条记录
     */
    public void demo2() throws SQLException {
        //创建核心类
        QueryRunner queryRunner=new QueryRunner(JDBCUtils2.getDataSource());
        List<Account> list=queryRunner.query("select * from test_table", new ResultSetHandler<List<Account>>() {

            @Override
            public List<Account> handle(ResultSet rs) throws SQLException {
                //创建集合
                List<Account> list=new ArrayList<Account>();
                while(rs.next()){
                    //将结果集中数据装入对象中
                    Account account=new Account();
                    account.setId(rs.getInt("id"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setSex(rs.getString("sex"));
                    account.setMath(rs.getString("math"));
                    account.setEnglish(rs.getString("english"));
                    //将对象装入集合
                    list.add(account);

                }
                return list;
            }
        });
        //使用集合
        for(Account account:list){
            System.out.println(account);
        }
    }

}
