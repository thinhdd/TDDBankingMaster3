package com.qsoft.banking.business;

import com.qsoft.banking.persistence.dao.TransactionDAO;
import com.qsoft.banking.persistence.dao.impl.TransactionDAOImpl;
import com.qsoft.banking.persistence.model.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionService {
    public void setTransactionDAO(TransactionDAO mockTDao);

    public void createTransaction(String accountNumber, double amount, String des, boolean state);

    public List<TransactionDTO> getAllTransaction(String accountNumber);

    public List<TransactionDTO> getAllTransaction(String accountNumber, long start, long end);

    public List<TransactionDTO> getAllTransaction(String accountNumber, int count);
}
