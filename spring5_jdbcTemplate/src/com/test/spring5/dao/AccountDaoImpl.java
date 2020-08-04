package com.test.spring5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "accountDaoImpl")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addMoney() {
        String sql = "update t_account set `money`=`money` + ? where `id`=1";
        jdbcTemplate.update(sql, 1000);
    }

    @Override
    public void reduceMoney() {
        String sql = "update t_account set `money`=`money` - ? where `id`=2";
        jdbcTemplate.update(sql, 1000);
    }

}
