package com.codegym;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account {
    public static final String CURRENCY = "VND";
    private String id;
    private String customerName;
    private String accountNumber;
    private Date dateCreated;
    String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());


    public Account(){
        this.id = "";
        this.customerName = "";
        this.accountNumber = "";
        this.dateCreated = Bank.parseDate(timeStamp);
    }
    public Account(String customerName, String id) {
        this.id = id;
        this.customerName = customerName.toUpperCase();
        this.accountNumber = Bank.generateAccountNumber();
        this.dateCreated = Bank.parseDate(timeStamp);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
