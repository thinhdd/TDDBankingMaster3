package com.qsoft.banking.persistence.model;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/24/13
 * Time: 10:14 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name ="transaction")
@SequenceGenerator(name = "seq_id1", sequenceName = "seq_id1", initialValue = 1, allocationSize = 1)
public class TransactionDTO {

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id1")
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "open_time_stamp")
    long timeStamp;
    @Column(name = "amount")
    private double amount;
    @Column(name = "description")
    private String des;
    @Column(name = "state")
    private boolean state;
    static Calendar calendar =Calendar.getInstance();
    public TransactionDTO(String accountNumber, double amount, String des, boolean state) {
        this.accountNumber=accountNumber;
        this.amount=amount;
        this.des=des;
        this.timeStamp=calendar.getTimeInMillis();
        this.state=state;
        //To change body of created methods use File | Settings | File Templates.
    }
    public TransactionDTO(String accountNumber, double amount, String des, boolean state, long timeStamp) {
        this.accountNumber=accountNumber;
        this.amount=amount;
        this.des=des;
        this.timeStamp=timeStamp;
        this.state=state;
        //To change body of created methods use File | Settings | File Templates.
    }

    public TransactionDTO() {
    }

    public String getAccountNumber() {
        return this.accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public double getAmount() {
        return this.amount;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getDes() {
        return this.des;  //To change body of created methods use File | Settings | File Templates.
    }

    public long getTimeStamp() {
        return this.timeStamp;  //To change body of created methods use File | Settings | File Templates.
    }

    public static void setCalendar(Calendar calendars) {
        calendar = calendars;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
