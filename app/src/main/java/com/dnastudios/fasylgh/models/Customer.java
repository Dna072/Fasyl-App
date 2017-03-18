package com.dnastudios.fasylgh.models;

/**
 * Created by Tillytet13 on 3/18/2017.
 */

public class Customer {
    private String mFullName;
    private String mCustomerBranch;
    private String mCustomerId;
    private String mAccountNumber;

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getCustomerBranch() {
        return mCustomerBranch;
    }

    public void setCustomerBranch(String customerBranch) {
        mCustomerBranch = customerBranch;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        mCustomerId = customerId;
    }
}
