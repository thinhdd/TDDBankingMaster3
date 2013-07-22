package com.qsoft.banking.business.impl;

import com.qsoft.banking.business.TransactionService;
import com.qsoft.banking.persistence.dao.TransactionDAO;
import com.qsoft.banking.persistence.dao.impl.TransactionDAOImpl;
import com.qsoft.banking.persistence.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    static TransactionDAO transactionDAO;
    @Override
    public void setTransactionDAO(TransactionDAO mockTDao) {
        transactionDAO=mockTDao;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createTransaction(String accountNumber, double amount, String des, boolean state) {
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber, amount, des, state);
        transactionDAO.save(transactionDTO);    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber) {
        return transactionDAO.getAllTransacion(accountNumber);    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber, long start, long end) {
        return transactionDAO.getAllTransacion(accountNumber, start, end);  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getAllTransaction(String accountNumber, int count) {
        return transactionDAO.getAllTransacion(accountNumber, count);
    }
}
