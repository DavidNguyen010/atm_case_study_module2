package com.codegym;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM extends Bank{
    public static void main(String[] args) {
        int choice;

        ArrayList<CurrentAccount> currentAccounts
                = new ArrayList<>();

        do {
            Bank.printMenu();
            Scanner inp = new Scanner(System.in);
            choice = inp.nextInt();
            switch (choice){
                case 11:{
                    if (currentAccounts.isEmpty()){
                        currentAccounts.add(CurrentAccount.CreateAccount());
                    }else {
                        System.out.println("Tài khoản đã được tạo.");
                    }
                    Bank.pressEnter();
                    break;
                }
                case 12:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước");
                    }else {
                        currentAccounts.get(0).withdraw();
                        System.out.println("RÚT TIỀN THÀNH CÔNG.");
                    }
                    Bank.pressEnter();
                    break;
                }
                case 13:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        currentAccounts.get(0).deposit();
                        System.out.println("NẠP TIỀN THÀNH CÔNG");
                    }
                    Bank.pressEnter();
                    break;
                }
                case 14:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        currentAccounts.get(0).printAccountCard();
                    }
                    Bank.pressEnter();
                    break;
                }
                case 15: {
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                        Bank.pressEnter();
                        break;
                    }else if(currentAccounts.get(0).getTransactions().isEmpty()){
                        System.out.println("Chưa có giao dịch nào.");
                        Bank.pressEnter();
                        break;
                    }else {
                        Bank.printTransactionActivity();
                        currentAccounts.get(0).getTransactions()
                                .forEach(Transaction::printTransaction);
                        System.out.println("\n");
                        Bank.pressEnter();
                    }
                    break;
                }

                case 21:{
                    if (currentAccounts.isEmpty()) {
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        currentAccounts.get(0).getSavingAccounts().
                                add(currentAccounts.get(0).createSavingAccount());
                    }
                    Bank.pressEnter();
                    break;
                }


                case 22:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        if (currentAccounts.get(0).getSavingAccounts().isEmpty()){
                            System.out.println("D co tk tiet kiem");
                        }else {
                            currentAccounts.get(0).getSavingAccounts().get(0).SavingCalculator();
                        }
                    }
                    Bank.pressEnter();
                    break;
                }

                //bug
                case 23:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        if (currentAccounts.get(0).getSavingAccounts().isEmpty()){
                            System.out.println("D co tk tiet kiem");
                        }else {
                            Bank.printSavingAccountStatement();
                            currentAccounts.get(0).getSavingAccounts().get(0).printSavingAccount();
                        }
                    }
                    Bank.pressEnter();
                    break;
                }
                case 24:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                        Bank.pressEnter();
                        break;
                    }else if (currentAccounts.get(0).getSavingAccounts().isEmpty()){
                        System.out.println("Chua co tk tiet kiem");
                        Bank.pressEnter();
                        break;
                    }else {
                        currentAccounts.get(0).deposit(currentAccounts.get(0)
                                .getSavingAccounts().get(0).finalSettlement());
                        currentAccounts.get(0).getSavingAccounts().clear();
                        break;
                    }
                }

                case 31:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }else {
                        currentAccounts.get(0).getLoanAccounts().add(currentAccounts.get(0).createLoanAccount());
                    }
                    Bank.pressEnter();
                    break;
                }
                //bug null index
                case 32:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }
                    else if(currentAccounts.get(0).getLoanAccounts().isEmpty()) {
                        System.out.println("Chưa có khoản vay nào.");
                    }else {
                        currentAccounts.get(0).getLoanAccounts().get(0).printLoanAccount();
                    }
                    Bank.pressEnter();
                    break;
                }
                //bug null index
                case 33:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }
                    else if (currentAccounts.get(0).getLoanAccounts().isEmpty()) {
                        System.out.println("Chưa có khoản vay nào.");
                    }else {
                            currentAccounts.get(0).withdraw(currentAccounts.get(0).
                                    getLoanAccounts().get(0).finalSettlement());
                            currentAccounts.get(0).getLoanAccounts().clear();
                    }
                    Bank.pressEnter();
                    break;
                }
                //bug null index
                case 34:{
                    if (currentAccounts.isEmpty()){
                        System.out.println("Hãy mở tài khoản trước.");
                    }
                    else if (currentAccounts.get(0).getLoanAccounts().isEmpty()) {
                        System.out.println("Chưa có khoản vay nào.");
                    }else {
                        currentAccounts.get(0).getLoanAccounts().get(0).LoanCalculator();
                    }
                    Bank.pressEnter();
                    break;
                }

                case 0:{
                    System.out.println("Cookkkkkkkk");
                    choice = 0;
                }
            }
        }while (choice != 0);
    }
}
