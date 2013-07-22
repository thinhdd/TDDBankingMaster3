package com.qsoft.banking.business.impl;

import com.qsoft.banking.business.BankAccountService;
import com.qsoft.banking.persistence.dao.BankAccountDAO;
import com.qsoft.banking.persistence.dao.impl.BankAccountDAOImpl;
import com.qsoft.banking.persistence.model.BankAccountDTO;
import com.qsoft.banking.persistence.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    BankAccountDAO bankAccountDAO;
    @Override
    public void setBankAccountDAO(BankAccountDAO mockDao) {
        this.bankAccountDAO=mockDao;
    }

    @Override
    public BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BankAccountDTO getAccount(String accountNumber) throws SQLException {
        return bankAccountDAO.getAccount(accountNumber);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doDeposit(String accountNumber, double amount, String des) throws SQLException {
        BankAccountDTO account = bankAccountDAO.getAccount(accountNumber);
        TransactionServiceImpl transactionImpl = new TransactionServiceImpl();
        transactionImpl.createTransaction(accountNumber, amount, des, true);
        account.setBalance(amount);
        bankAccountDAO.save(account);
    }

    @Override
    public void doWithDraw(String accountNumber, double amount, String des) throws SQLException {
        BankAccountDTO account = bankAccountDAO.getAccount(accountNumber);
        TransactionServiceImpl transactionImpl = new TransactionServiceImpl();
        transactionImpl.createTransaction(accountNumber, amount, des, false);
        account.setBalance(-amount);
        bankAccountDAO.save(account);    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber) {
        TransactionServiceImpl transactionImpl = new TransactionServiceImpl();
        return transactionImpl.getAllTransaction(accountNumber);    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber, long start, long end) {
        TransactionServiceImpl transactionImpl = new TransactionServiceImpl();
        return transactionImpl.getAllTransaction(accountNumber, start, end);    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber, int count) {
        TransactionServiceImpl transactionImpl = new TransactionServiceImpl();
        return transactionImpl.getAllTransaction(accountNumber, count);    }
}
