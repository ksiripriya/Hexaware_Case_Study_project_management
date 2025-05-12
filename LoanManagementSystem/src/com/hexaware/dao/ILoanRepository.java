package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Loan;
import com.hexaware.entity.Customer;
import com.hexaware.exception.InvalidCustomerException;
import com.hexaware.exception.InvalidLoanException;

public interface ILoanRepository {

    boolean applyLoan(Loan loan) throws InvalidLoanException, InvalidCustomerException;

    double calculateInterest(int loanId) throws InvalidLoanException;

    double calculateInterest(double principalAmount, double interestRate, int loanTerm);

    String loanStatus(int loanId) throws InvalidLoanException, InvalidCustomerException;

    double calculateEMI(int loanId) throws InvalidLoanException;

    double calculateEMI(double principalAmount, double interestRate, int loanTerm);

    boolean loanRepayment(int loanId, double amount) throws InvalidLoanException;

    List<Loan> getAllLoans() throws Exception;  // Corrected method name

    Loan getLoanById(int loanId) throws InvalidLoanException, InvalidCustomerException, Exception;

    Customer getCustomerById(int customerId) throws InvalidCustomerException;
}


