package com.qsoft.banking.persistence.dao;

import com.qsoft.banking.persistence.model.BankAccountDTO;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BankAccountDAO {
    public void save(BankAccountDTO account) ;

    public BankAccountDTO getAccount(String accountNumber);
}
