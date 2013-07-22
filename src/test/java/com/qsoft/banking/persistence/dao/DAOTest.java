package com.qsoft.banking.persistence.dao;

import com.qsoft.banking.persistence.model.BankAccountDTO;
import com.qsoft.banking.persistence.model.TransactionDTO;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 7/8/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DAOTest {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    BankAccountDAO bankAccountDAO;
    @Autowired
    TransactionDAO transactionDAO;
    @Qualifier("dataSourceTest")
    @Autowired
    DataSource dataSourcetest;
    IDatabaseTester iDatabaseTester;
    final String accountNumber="0123456789";
    final double balance = 100;
    @Before
    public void setup() throws Exception
    {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception
    {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception
    {
        iDatabaseTester = new DataSourceDatabaseTester(dataSourcetest);
        iDatabaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        iDatabaseTester.setDataSet(dataSet);
        iDatabaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
        iDatabaseTester.onTearDown();
    }
    @Test
    public void testGetAccount() throws Exception {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        assertEquals(accountNumber, bankAccountDTO.getAccountNumber());
        assertEquals(100.0, bankAccountDTO.getBalance());
        assertEquals(12345678, bankAccountDTO.getTimeStamp());
    }
    @Test
    public void testGetAccountNonAxist() throws Exception {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount("1234");
        assertTrue(bankAccountDTO==null);
    }
    @Test
    public void testSaveAccountNotAxist()
    {
        BankAccountDTO accountDTO = new BankAccountDTO("1234569");
        bankAccountDAO.save(accountDTO);

        BankAccountDTO accountDTO1 = bankAccountDAO.getAccount("1234569");
        assertTrue(accountDTO1!=null);
    }
    @Test
    public void testSaveAccountAxist()
    {
        BankAccountDTO accountDTO = bankAccountDAO.getAccount("0123456789");
        accountDTO.setBalance(130l);
        bankAccountDAO.save(accountDTO);
        BankAccountDTO accountDTORe = bankAccountDAO.getAccount("0123456789");
        assertEquals(230.0, accountDTORe.getBalance());
    }
    @Test
    public void testSaveTransaction()
    {
        TransactionDTO transactionDTO = new TransactionDTO("0123456789",100.0, "Them 100k", true, 12345l);
        transactionDAO.save(transactionDTO);
        List<TransactionDTO> list = transactionDAO.getAllTransacion("0123456789");
        assertEquals("0123456789" , list.get(0).getAccountNumber());
        assertEquals(100.0, list.get(0).getAmount());
        assertEquals("Them 100k", list.get(0).getDes());
        assertEquals(123456l, list.get(0).getTimeStamp());
        assertEquals(true, list.get(0).getState());
    }
    @Test
    public void testGetAllTransaction()
    {
        List<TransactionDTO> list = transactionDAO.getAllTransacion("0123456789");
        assertEquals("0123456789" , list.get(0).getAccountNumber());
        assertEquals(100.0, list.get(0).getAmount());
        assertEquals("Them 100k", list.get(0).getDes());
        assertEquals(123456l, list.get(0).getTimeStamp());
        assertEquals(true, list.get(0).getState());

        assertEquals("0123456789" , list.get(1).getAccountNumber());
        assertEquals(50.0, list.get(1).getAmount());
        assertEquals("Rut 50k", list.get(1).getDes());
        assertEquals(1234567l, list.get(1).getTimeStamp());
        assertEquals(false, list.get(1).getState());
    }
    @Test
    public void getAllTransactionFilterTime()
    {
        List<TransactionDTO> list = transactionDAO.getAllTransacion("0123456789", 123457l, 1234568l);
        assertEquals("0123456789" , list.get(0).getAccountNumber());
        assertEquals(50.0, list.get(0).getAmount());
        assertEquals("Rut 50k", list.get(0).getDes());
        assertEquals(1234567l, list.get(0).getTimeStamp());
        assertEquals(false, list.get(0).getState());
    }
    @Test
    public void getAllTransactionFilterCount()
    {
        List<TransactionDTO> list = transactionDAO.getAllTransacion("0123456789", 1);
        assertTrue(list.size()==1);
    }
}
