package org.example.springbootassignment.enums;

public enum AccountType {
    SAVINGSACCOUNT("Savings Account"),
    CURRENTACCOUNT("Current Accounts"),
    FIXEDDEPOSITE("Fixed Deposits"),
    INVESETMENT("Investment");




    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
