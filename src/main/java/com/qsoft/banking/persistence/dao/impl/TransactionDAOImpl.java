package com.qsoft.banking.persistence.dao.impl;

import com.qsoft.banking.persistence.dao.TransactionDAO;
import com.qsoft.banking.persistence.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/16/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
@Component
public class TransactionDAOImpl implements TransactionDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(TransactionDTO capture) {
        entityManager.persist(capture);
        entityManager.flush();
    }

    @Override
    public List<TransactionDTO> getAllTransacion(String accountNumber) {
        Query query = entityManager.createQuery("select o from TransactionDTO o where o.accountNumber=:qAccountNumber", TransactionDTO.class);
        query.setParameter("qAccountNumber",accountNumber);
        return query.getResultList();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getAllTransacion(String accountNumber, long start, long end) {
        Query query = entityManager.createQuery("select o from TransactionDTO o where o.accountNumber=:qAccountNumber and o.timeStamp>:qstart and o.timeStamp<:qend", TransactionDTO.class);
        query.setParameter("qAccountNumber",accountNumber);
        query.setParameter("qstart", start);
        query.setParameter("qend", end);
        return query.getResultList();
    }

    @Override
    public List<TransactionDTO> getAllTransacion(String accountNumber, int count) {
        Query query = entityManager.createQuery("select o from TransactionDTO o where o.accountNumber=:qAccountNumber", TransactionDTO.class);
        query.setParameter("qAccountNumber",accountNumber);
        query.setMaxResults(count);
        return query.getResultList();    }
}
