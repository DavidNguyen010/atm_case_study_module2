package com.codegym;

import java.util.ArrayList;
import java.util.Scanner;

public class CurrentAccount extends Account {
    private final double MINIMUM_BALANCE = 50000;
    private final double FEE = 0;
    private ArrayList<Transaction> transactions;
    private ArrayList<SavingAccount> savingAccounts;
    private ArrayList<LoanAccount> loanAccounts;
    private double balance;
    private double annualInterestRate; // ti le phan tram hang nam

    public CurrentAccount(){
        super();
        this.balance = MINIMUM_BALANCE;
        this.transactions = new ArrayList<>();
        this.savingAccounts = new ArrayList<>();
    }

    public CurrentAccount(String name, String id){
        super(name,id);
        this.balance = MINIMUM_BALANCE;
        this.transactions = new ArrayList<>();
        this.savingAccounts = new ArrayList<>();
        this.loanAccounts = new ArrayList<>();
    }

    // generate new account

    public static CurrentAccount CreateAccount(){
        Scanner inp = new Scanner(System.in);
        System.out.print("CMND: ");
        String id = inp.nextLine();
        System.out.print("Họ và tên: ");
        String name = inp.nextLine();

        return new CurrentAccount(name,id);
    }

    //generate saving account
    public SavingAccount createSavingAccount(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Số tiền muốn gửi tiết kiệm: ");
        double startBalance = inp.nextDouble();
        System.out.print("Chọn kỳ hạn gửi (3, 6, 9, 12 tháng) |"
                +
                " Gửi không kỳ hạn (nhập vào 0): ");
        int savingTerm = inp.nextInt();
        double annualInterestRate = SavingAccount.getSavingInterestRate(savingTerm);
        System.out.print("Số tiền gửi vào định kỳ hàng tháng (từ tháng thứ 2): ");
        double regularSavingAmount = inp.nextDouble();
        return new SavingAccount(startBalance, regularSavingAmount, savingTerm, annualInterestRate);
    }

    //generate loan Account
    public LoanAccount createLoanAccount(){
        LoanAccount loanAccount;
        Scanner inp = new Scanner(System.in);
        System.out.printf("Số tiền tối đa có thể vay: %10.2f%4s\n",
                getMaxLoanAmount(), Account.CURRENCY);
        System.out.print("Số tiền muốn vay: ");
        double loanAmount = inp.nextDouble();
        System.out.print("Chọn kỳ hạn vay (3, 6, 9, 12 tháng): ");
        int loanTerm = inp.nextInt();
        double loanInterestRate = LoanAccount.getLoanInterestRate(loanTerm);
        return new LoanAccount(loanTerm, loanAmount, loanInterestRate);
    }

    public double getMaxLoanAmount(){
        return getBalance() *2;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getFEE(){
        return FEE;
    }

    public ArrayList<LoanAccount> getLoanAccounts(){
        return loanAccounts;
    }

    public void setLoanAccounts(ArrayList<LoanAccount> loanAccounts) {
        this.loanAccounts = loanAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }
    public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts){
        this.savingAccounts = savingAccounts;
    }

    //check loan Account
    public String isActiveLoanAccount(){
        if (loanAccounts.isEmpty()){
            return "NO";
        }else return "YES";
    }

    // check saving Account
    public String isActiveSavingAccount(){
        if (savingAccounts.isEmpty()){
            return "NO";
        }else return "YES";
    }

    public void printAccountCard(){
        System.out.println("---------------------------------------");
        System.out.println("          Thông Tin Tài Khoản          ");
        System.out.println("---------------------------------------");
        System.out.printf("%-2s%-15s%20s%3s", "|", "Tên:", getCustomerName(), "|\n");
        System.out.printf("%-2s%-15s%20s%3s", "|", "Số tài khoản:", getAccountNumber(), "|\n");
        System.out.printf("%-2s%-15s%20s%3s", "|", "Ngày tạo:", Bank.convertDate(getDateCreated()), "|\n");
        System.out.printf("%-2s%-15s%20.2f%3s", "|", "Số dư:", getBalance(), "|\n");
        System.out.printf("%-2s%-15s%20s%3s", "|", "Tiền gửi:", isActiveSavingAccount(), "|\n");
        System.out.printf("%-2s%-15s%20s%3s", "|", "Khoản nợ:", isActiveSavingAccount(), "|\n");
        System.out.print("---------------------------------------\n");
    }

    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public void deposit(){
        Scanner inp = new Scanner(System.in);
        System.out.print("Nhập vào số tiền cần nạp: ");
        double amount = inp.nextDouble();
        setBalance(getBalance() + amount);
        transactions.add(new Transaction("Nạp tiền", amount, getBalance(),
                "Nạp tiền vào tài khoản"));
    }

    public void deposit(double amount){
        setBalance(getBalance() + amount);
        transactions.add(new Transaction("Tất toán",amount,getBalance(),"Tất toán tài khoản tiết kiệm" ));
    }
    // rut tien khi tat toan khoan vay
    public void withdraw(double amount){
        if (getBalance() - amount - FEE > MINIMUM_BALANCE) {
            setBalance(getBalance() - amount - FEE);
            transactions.add(new Transaction("Tất toán", amount, getBalance(),
                    "Tất toán tài khoản vay"));
        } else {
            throw new IllegalArgumentException("Không đủ tiền!");
        }
    }

    public void withdraw() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập vào số tiền cần rút: ");
        double amount = input.nextDouble();
        if (getBalance() - amount - FEE > MINIMUM_BALANCE) {
            setBalance(getBalance() - amount - FEE);
            transactions.add(new Transaction("Rút tiền", amount, getBalance(),
                    "Rút tiền từ tài khoản"));
        } else {
            throw new IllegalArgumentException("Không đủ tiền!");
        }
    }
}
