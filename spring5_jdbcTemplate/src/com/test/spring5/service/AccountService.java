package com.test.spring5.service;


import com.test.spring5.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "accountService")
@Transactional(timeout = 10,readOnly = false, propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void accountMoney(){
        try{
            //开启事务

            //事务执行
            accountDao.reduceMoney();
            int i = 1/0;
            accountDao.addMoney();

            //事务提交
        } catch(Exception e){
            //出现异常回滚
        }
    }

    public void accountMoneyByTx(){
        //PlatformTransactionManager manager = new DataSourceTransactionManager();
        accountDao.reduceMoney();
        accountDao.addMoney();
        //此处不能trycatch，不然manger无法捕捉到异常做出回滚
    }


}
