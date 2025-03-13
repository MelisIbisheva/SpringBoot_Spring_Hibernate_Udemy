package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    // add find account method
    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWar);

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

}
