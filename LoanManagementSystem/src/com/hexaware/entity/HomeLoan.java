package com.hexaware.entity;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;

    public HomeLoan() {
        super();
    }

    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate,
                    int loanTerm, String loanType, String loanStatus,
                    String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public double getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(double propertyValue) {
        this.propertyValue = (int) propertyValue;
    }

    @Override
    public String toString() {
        return super.toString() + "\nProperty Address: " + propertyAddress +
               "\nProperty Value: " + propertyValue;
    }
}


