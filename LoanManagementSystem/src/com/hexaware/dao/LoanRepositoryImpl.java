package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.util.DBConnUtil;
import com.hexaware.entity.Loan;
import com.hexaware.entity.Customer;
import com.hexaware.exception.InvalidCustomerException;
import com.hexaware.exception.InvalidLoanException;

public class LoanRepositoryImpl implements ILoanRepository {

    private final String dbFile = "resources/dp.properties"; // Update this path to your database configuration file

    // Apply Loan - Assume some business logic to add a loan application
    @Override
    public boolean applyLoan(Loan loan) throws InvalidLoanException, InvalidCustomerException {
        // Insert loan application logic here
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "INSERT INTO Loan (loanId, principalAmount, interestRate, loanTerm, loanType, loanStatus, customerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, loan.getLoanId());
            ps.setDouble(2, loan.getPrincipalAmount());
            ps.setDouble(3, loan.getInterestRate());
            ps.setInt(4, loan.getLoanTerm());
            ps.setString(5, loan.getLoanType());
            ps.setString(6, loan.getLoanStatus());
            ps.setInt(7, loan.getCustomer().getCustomerId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new InvalidLoanException("Error applying loan: " + e.getMessage());
        }
    }

    // Calculate interest using loanId
    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "SELECT principalAmount, interestRate, loanTerm FROM Loan WHERE loanId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double principalAmount = rs.getDouble("principalAmount");
                double interestRate = rs.getDouble("interestRate");
                int loanTerm = rs.getInt("loanTerm");
                return calculateInterest(principalAmount, interestRate, loanTerm);
            } else {
                throw new InvalidLoanException("Loan not found with ID: " + loanId);
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest: " + e.getMessage());
        }
    }

    // Calculate interest using principal, interest rate, and loan term
    @Override
    public double calculateInterest(double principalAmount, double interestRate, int loanTerm) {
        return (principalAmount * interestRate * loanTerm) / 1200;
    }

    // Loan status - Ensure return type is String (matches ILoanRepository)
    @Override
    public String loanStatus(int loanId) throws InvalidLoanException {
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "SELECT loanStatus FROM Loan WHERE loanId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("loanStatus");
            } else {
                throw new InvalidLoanException("Loan not found with ID: " + loanId);
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error fetching loan status: " + e.getMessage());
        }
    }

    // Calculate EMI using loanId
    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "SELECT principalAmount, interestRate, loanTerm FROM Loan WHERE loanId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, loanId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double principalAmount = rs.getDouble("principalAmount");
                double interestRate = rs.getDouble("interestRate");
                int loanTerm = rs.getInt("loanTerm");
                return calculateEMI(principalAmount, interestRate, loanTerm);
            } else {
                throw new InvalidLoanException("Loan not found with ID: " + loanId);
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating EMI: " + e.getMessage());
        }
    }

    // Calculate EMI using principal, interest rate, and loan term
    @Override
    public double calculateEMI(double principalAmount, double interestRate, int loanTerm) {
        return (principalAmount * interestRate * Math.pow(1 + interestRate, loanTerm)) / (Math.pow(1 + interestRate, loanTerm) - 1);
    }

    // Loan repayment logic
    @Override
    public boolean loanRepayment(int loanId, double amount) throws InvalidLoanException {
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "UPDATE Loan SET remainingAmount = remainingAmount - ? WHERE loanId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, amount);
            ps.setInt(2, loanId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new InvalidLoanException("Error during loan repayment: " + e.getMessage());
        }
    }

    // Fetch all loans
    @Override
    public List<Loan> getAllLoans() throws Exception {
        List<Loan> loans = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "SELECT * FROM Loan";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Loan loan = new Loan();  
                loan.setLoanId(rs.getInt("loanId"));
                loan.setPrincipalAmount(rs.getDouble("principalAmount"));
                loan.setInterestRate(rs.getDouble("interestRate"));
                loan.setLoanTerm(rs.getInt("loanTerm"));
                loan.setLoanType(rs.getString("loanType"));
                loan.setLoanStatus(rs.getString("loanStatus"));
                loan.setCustomer(getCustomerById(rs.getInt("customerId")));  // Assuming getCustomerById works fine
                loans.add(loan);
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching all loans: " + e.getMessage(), e);
        }
        return loans;
    }

    // Fetch customer by customerId
    @Override
    public Customer getCustomerById(int customerId) throws InvalidCustomerException {
        try (Connection conn = DBConnUtil.getConnection(dbFile)) {
            String query = "SELECT * FROM Customer WHERE customerId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("customerId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getInt("creditScore")
                );
            } else {
                throw new InvalidCustomerException("Customer not found with ID: " + customerId);
            }
        } catch (SQLException e) {
            throw new InvalidCustomerException("Error fetching customer: " + e.getMessage());
        }
    }

	@Override
	public Loan getLoanById(int loanId) throws InvalidLoanException, InvalidCustomerException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
   

	