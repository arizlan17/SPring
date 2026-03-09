package org.example.springbootassignment.utils;

import org.example.springbootassignment.model.BankAccount;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class AccountFinder {

    static Scanner scanner = new Scanner(System.in);

    public static void deleteBankAccount(String AcconuntNumber, List<BankAccount> AccountsList) {
        Optional<BankAccount> searchedAccountNumber = AccountsList.stream().filter(account -> Objects.equals(account.getAccountNumber(), AcconuntNumber)).findFirst();

        searchedAccountNumber.ifPresentOrElse(
                account -> {
                    System.out.println("Account Found");

                    while (true) {
                        System.out.println("Confirm Deleting This Account " +
                                "If Yes - Y / No - n");
                        String Confirmation = scanner.nextLine().trim();
                        if (Confirmation.equalsIgnoreCase("Y")) {
                            AccountsList.removeIf(customer -> Objects.equals(account.getAccountNumber(), AcconuntNumber));
                            System.out.println( "Account Deleted from the Database");
                        }
                        if (Confirmation.equalsIgnoreCase("N")) {
                            System.out.println( "Account Not Deleted from the Database");
                        }
                    }
                }, () -> {
                    System.out.println("Account Not Found");
                }
        );
    }

    public static Optional<BankAccount> getAccount(int AccountNumber, List<BankAccount>accountList){
        Optional<BankAccount> searchedAccount = accountList.stream().filter(account -> Objects.equals(account.getAccountNumber(), AccountNumber)).findFirst();
        return searchedAccount;

    }
}
