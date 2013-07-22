package com.qsoft.banking.business;

import com.qsoft.banking.persistence.dao.BankAccountDAO;
import com.qsoft.banking.persistence.model.BankAccountDTO;
import com.qsoft.banking.persistence.model.TransactionDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BankAccountService {
    public  void setBankAccountDAO(BankAccountDAO mockDao);

    public  BankAccountDTO openAccount(String accountNumber);

    public BankAccountDTO getAccount(String accountNumber) throws SQLException;

    public void doDeposit(String accountNumber, double amount, String des) throws SQLException;

    public void doWithDraw(String accountNumber, double amount, String des) throws SQLException;

    public List<TransactionDTO> getAllTransaction(String accountNumber);

    public List<TransactionDTO> getAllTransaction(String accountNumber, long start, long end);

    public List<TransactionDTO> getAllTransaction(String accountNumber, int count);
}
