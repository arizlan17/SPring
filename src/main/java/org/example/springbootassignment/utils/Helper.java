package org.example.springbootassignment.utils;

import org.example.springbootassignment.enums.AccountType;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.example.springbootassignment.constant.constant.*;

public class Helper {
    private static Scanner scanner = new Scanner(System.in);


    public static String validateStringInput(String prompt, String ReenterPrompt){
        System.out.println(prompt);
        String variableValue = scanner.nextLine().trim();
        return variableValue;

    }


    public static String validateEmailInput(String prompt, String ReenterPrompt){

        System.out.println(prompt);
        while(true){
            String validEmail = scanner.nextLine().trim();
            if (Pattern.matches(EMAIL_REGEX,validEmail)){
                return validEmail;
            }else {
                System.out.println(ReenterPrompt);
            }}
    }


    public static String validatePhoneNumberInput(String prompt, String ReenterPrompt){

        System.out.println(prompt);

        while(true){
            String PhoneNumber = scanner.nextLine().trim();
            if (PhoneNumber.matches(PNUM_REGEX)){
                return PhoneNumber;
            }else {
                System.out.println(ReenterPrompt);
            }}
    }


    public static String validateIdNumberInput(String prompt, String ReenterPrompt){
        System.out.println(prompt);
        while(true){
            String IdNum = scanner.nextLine().trim();

            if (IdNum.length() == IDNumberLength && IdNum.matches(IDNUM_REGEX)){
                return IdNum;
            }else {
                System.out.println(ReenterPrompt);
            }
        }

    }


    public static int validateIntInput(String prompt, String ReenterPrompt){
        int validNumber;
        System.out.println(prompt);

        while(true){
            try{
                validNumber = Integer.parseInt(scanner.nextLine().trim());

                return validNumber;
            }catch (InputMismatchException e){
                System.out.println(ReenterPrompt);
            }
        }
    }

    public static double validateDoubleInput(String prompt, String ReenterPrompt){
        double validNumber;
        System.out.println(prompt);

        while(true){
            try{
                validNumber = Double.parseDouble(scanner.nextLine().trim());
                return validNumber;
            }catch (InputMismatchException e){
                System.out.println(ReenterPrompt);
            }
        }
    }

    public static AccountType validateAccountType(){
        while (true){
            System.out.println("Select the Number fot Relevant Product - ");
            AccountType[] types = AccountType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.println(types[i].toString() + (" -> ") + (i + 1));
            }
            String selectedProductNumber = scanner.nextLine().trim();
            try {
                int choice =  Integer.parseInt(selectedProductNumber);
                if (choice > 0 && choice<= types.length){
                    return types[choice-1];
                }else {
                    System.out.println("Invalid Selection. Please Choose a valid Number .");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Selection. Please Choose a valid Number .");
            }
        }
    }


    public static int validateAccountNumberInput(String prompt, String ReenterPrompt){
        int validNumber;
        System.out.println(prompt);

        while(true){
            try{
                validNumber = scanner.nextInt();
                return validNumber;
            }catch (InputMismatchException e){
                System.out.println(ReenterPrompt);
                scanner.nextLine();
            }
        }
    }


}
