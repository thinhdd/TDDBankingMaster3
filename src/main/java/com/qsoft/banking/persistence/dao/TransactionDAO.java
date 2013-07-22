package com.qsoft.banking.persistence.dao;

import com.qsoft.banking.persistence.model.TransactionDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionDAO {
    public void save(TransactionDTO capture);

    public List<TransactionDTO> getAllTransacion(String accountNumber);

    public List<TransactionDTO> getAllTransacion(String accountNumber, long start, long end);

    public List<TransactionDTO> getAllTransacion(String accountNumber, int count);
}
