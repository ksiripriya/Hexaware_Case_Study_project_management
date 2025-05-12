package com.hexaware.main;

import com.hexaware.dao.ILoanRepository;
import com.hexaware.dao.LoanRepositoryImpl;
import com.hexaware.entity.*;
import com.hexaware.exception.InvalidCustomerException;
import com.hexaware.exception.InvalidLoanException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LoanManagementMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository loanRepo = new LoanRepositoryImpl();

        while (true) {
            System.out.println("\n===== Loan Management System =====");
            System.out.println("1. Apply for Loan");
            System.out.println("2. Calculate Interest");
            System.out.println("3. Calculate EMI");
            System.out.println("4. Make Loan Repayment");
            System.out.println("5. Check Loan Status");
            System.out.println("6. Get All Loans");
            System.out.println("7. Get Loan By ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            try {
                switch (choice) {
                    case 1:

                        System.out.println("Enter loan details:");

                        System.out.print("Loan ID: ");

                        int loanId = scanner.nextInt();

                        System.out.print("Customer ID: ");

                        int customerId = scanner.nextInt();

                        scanner.nextLine(); // consume newline

                        System.out.print("Customer Name: ");

                        String name = scanner.nextLine();

                        System.out.print("Email: ");

                        String email = scanner.nextLine();

                        System.out.print("Phone: ");

                        String phone = scanner.nextLine();

                        System.out.print("Address: ");

                        String address = scanner.nextLine();

                        System.out.print("Credit Score: ");

                        int creditScore = scanner.nextInt();



                        Customer customer = new Customer(customerId, name, email, phone, address, creditScore);



                        System.out.print("Principal Amount: ");

                        double principal = scanner.nextDouble();

                        System.out.print("Interest Rate (%): ");

                        double rate = scanner.nextDouble();

                        System.out.print("Loan Term (months): ");

                        int term = scanner.nextInt();

                        scanner.nextLine(); // consume newline



                        System.out.print("Loan Type (Home/Car): ");

                        String type = scanner.nextLine();



                        Loan loan;

                        if (type.equalsIgnoreCase("Home")) {

                            System.out.print("Property Address: ");

                            String propAddress = scanner.nextLine();

                            System.out.print("Property Value: ");

                            int propValue = scanner.nextInt();

                            loan = new HomeLoan(loanId, customer, principal, rate, term, "Home", "Pending", propAddress, propValue);

                        } else if (type.equalsIgnoreCase("Car")) {

                            scanner.nextLine(); // consume newline before reading string

                            System.out.print("Car Model: ");

                            String model = scanner.nextLine();

                            System.out.print("Car Value: ");

                            int carValue = scanner.nextInt();

                            loan = new CarLoan(loanId, customer, principal, rate, term, "Car", "Pending", model, carValue);

                        } else {

                            System.out.println("Invalid loan type.");

                            break;

                        }



                        loanRepo.applyLoan(loan);

                        System.out.println("Loan application submitted successfully.");

                        break;

                    case 2:
                        System.out.print("Enter loan ID: ");
                        int id1 = scanner.nextInt();
                        double interest = loanRepo.calculateInterest(id1);
                        System.out.printf("Interest: %.2f%n", interest);
                        break;

                    case 3:
                        System.out.print("Enter loan ID: ");
                        int id2 = scanner.nextInt();
                        double emi = loanRepo.calculateEMI(id2);
                        System.out.printf("EMI: %.2f%n", emi);
                        break;

                    case 4:
                        System.out.print("Enter loan ID: ");
                        int id3 = scanner.nextInt();
                        System.out.print("Enter repayment amount: ");
                        double amount = scanner.nextDouble();
                        loanRepo.loanRepayment(id3, amount);
                        break;

                    case 5:
                        System.out.print("Enter loan ID: ");
                        int id4 = scanner.nextInt();
                        loanRepo.loanStatus(id4);
                        break;

                    case 6:
                        List<Loan> allLoans = loanRepo.getAllLoans();
                        for (Loan l : allLoans) {
                            System.out.println(l);
                        }
                        break;

                    case 7:
                        System.out.print("Enter loan ID: ");
                        int id5 = scanner.nextInt();
                        Loan loanDetails = loanRepo.getLoanById(id5);
                        if (loanDetails != null) {
                            System.out.println(loanDetails);
                        }
                        break;

                    case 8:
                        System.out.println("Exiting... Thank you!");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InvalidLoanException | InvalidCustomerException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
