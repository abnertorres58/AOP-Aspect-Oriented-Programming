package com.freudromero.aopdemo.dao;

import com.freudromero.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    // Add a new method: findAccounts()
    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();

        // Create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");


        // Add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING ACCOUNT");

    }

    public boolean doWork() {
        System.out.println(getClass() + ": in doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
