package com.test.spring5.dao;

import com.test.spring5.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository(value="userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user){
        String sql = "insert into t_user(`username`,`password`,`email`)" +
                "values(?,?,?)";
        int res = jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getEmail());
        System.out.println("添加结果" + res);
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "delete from t_user where `id` = ?";
        int res = jdbcTemplate.update(sql,id);
        System.out.println("删除结果" + res);
    }

    @Override
    public void updateUserById(int id, User user) {
        String sql = "update t_user set `username`=?, `password`=?, `email`=? where `id` = ?";
        int res = jdbcTemplate.update(sql, user.getUserName(),user.getPassword(), user.getEmail(), id);
        System.out.println("修改结果" + res);

    }

    @Override
    public int findCount() {
        String sql = "select count(*) from t_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("表中有" + count);
        return  count;
    }

    @Override
    public User findUserById(int id) {
        String sql = "select `id`, `username`, `password`,`email` from t_user where id = ?";
        //第一个参数：sql，
        //第二个参数：RowMapper，是接口，返回不同类型数据，使用这个接口中的实现类完成数据的封装
        //第三个参数：sql语句的值
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public List<User> findAll() {
        String sql = "select `id`, `username`, `password`,`email` from t_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }


    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        String sql = "insert into t_user(`username`,`password`,`email`)" +
                "values(?,?,?)";
        int[] arr = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(arr));
    }


    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        String sql = "update t_user set `username`=?, `password`=?, `email`=? where `id` = ?";
        int[] arr = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void batchDelete(List<Object[]> batchArgs) {
        String sql = "delete from t_user where `id` = ?";
        int[] arr = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(arr));
    }
}
