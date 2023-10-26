package com.codegym;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class SavingAccount {
    private static final double[] SAVING_INTEREST_RATE = {3.5, 4.8, 5.3, 5.5, 6.4}; // Mảng lưu lãi suất tiền gửi tiết kiệm tương ứng kỳ hạn 0 kỳ hạn,3,6,9,12 tháng
    private final double MINIMUM_START_BALANCE = 5_000_000; // Số tiền nhỏ nhất khi bắt đầu tiết kiệm
    private Date dateCreated;
    private double startBalance;
    private double balance;
    private int savingTerm;
    private double regularSavingAmount;
    private double annualInterestRate;
    private double monthlyInterestRate;
    String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());


    public SavingAccount() {

    }
    public SavingAccount(double startBalance, double regularSavingAmount, int savingTerm, double annualInterestRate) {
        this.dateCreated = Bank.parseDate(timeStamp); // Định ngay gửi tiết kiệm tài khoản
        this.startBalance = startBalance;
        this.savingTerm = savingTerm;
        this.regularSavingAmount = regularSavingAmount;
        this.annualInterestRate = annualInterestRate;
    }

    public double getMINIMUM_START_BALANCE() {
        return MINIMUM_START_BALANCE;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getSavingTerm() {
        return savingTerm;
    }

    public void setSavingTerm(int savingTerm) {
        this.savingTerm = savingTerm;
    }

    public double getRegularSavingAmount() {
        return regularSavingAmount;
    }

    public void setRegularSavingAmount(double regularSavingAmount) {
        this.regularSavingAmount = regularSavingAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(double monthlyInterestRate) {
        this.monthlyInterestRate = annualInterestRate/12;
    }

    public static double getSavingInterestRate(int month) {
        return SAVING_INTEREST_RATE[month / 3];
    }
    public static double[] getSavingInterestRate(){
        return SAVING_INTEREST_RATE;
    }

    public void SavingCalculator() {
        Scanner inp = new Scanner(System.in);
        int month;
        System.out.print("Nhập vào số tháng cần tính : ");
        month = inp.nextInt();
        System.out.println("                 Bảng lãi thẻ tiết kiệm                ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%5s%20s%22s", "Thời gian", "Tiền lãi", "Tổng tiền");
        System.out.println();
        for (int i = 1; i <= month; i++) {
            System.out.printf("#%-5d%20.2f%20.2f\n", i, getMonthInterest(i), getTotalDeposit(i));
        }
    }

    public void printSavingAccount() {
        System.out.println();
        System.out.printf("%-2s%-15s%20s%6s\n", "|", "Ngày bắt đầu:", Bank.convertDate(getDateCreated()), "|");
        System.out.printf("%-2s%-15s%18.2f%6s\n", "|", "Tiền gửi ban đầu:", getStartBalance(), "|");
        System.out.printf("%-2s%-15s%18.2f%2s%6s\n", "|", "Lãi suất:", getAnnualInterestRate(), "%", "|");
        System.out.printf("%-2s%-15s%12d%8s%6s\n", "|", "Kỳ hạn gửi:", getSavingTerm(), "Tháng", "|");
        System.out.println("-------------------------------------------");

    }

    public double getMonthInterest(int month) {
        double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) { // Gửi tiết kiệm tích lũy nên tháng thứ 1 chưa có tiền góp vào hàng tháng
                monthInterest = getStartBalance() * getAnnualInterestRate() / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount(); // Công vào tiền góp mỗi tháng từ tháng thứ 2
                monthInterest = totalDeposits * getAnnualInterestRate() / 1200;
            }

        }
        return totalDeposits;
    }

    //tong tien tai thang can tinh
    public double getTotalDeposit(int month) {
        double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) {
                monthInterest = getStartBalance() * getAnnualInterestRate() / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount();
                monthInterest = totalDeposits * getAnnualInterestRate() / 1200;
                totalDeposits += monthInterest;
            }
        }
        return totalDeposits;
    }

    //tong tien nhan duoc khi tat toan truoc han --> lai truoc ki han
    public double getTotalDeposit(int month, double interestRate) {
        double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) {
                monthInterest = getStartBalance() * interestRate / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount();
                monthInterest = totalDeposits * interestRate / 1200;
                totalDeposits += monthInterest;
            }
        }
        return totalDeposits;
    }

    //rest day in month
    public double getRemainDeposit(int day) {
        return day * getTotalDeposit
                (Bank.getTimeElapsed(getDateCreated(), new Date())[1] + 1) / 30;
    }

    //kiem tra dung han truoc han
    public boolean isMatchSavingTerm() {
        Date now = new Date();
        LocalDate nowTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate pastTime = getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        boolean isMatchDay = nowTime.getDayOfMonth() == pastTime.getDayOfMonth();
        boolean isMatchMonth = (getSavingTerm() == Bank.getTimeElapsed(getDateCreated(), now)[1]);
        return isMatchDay && isMatchMonth;
    }

    public double finalSettlement() {
        double finalAmount = 0;
        printSavingAccount();
        Date now = new Date();
        if (isMatchSavingTerm()) {
            System.out.printf("Tất toán tiền gửi đúng hạn. Lãi suất áp dụng là: %5.2f%2s\n", getAnnualInterestRate(), "%");
            System.out.println("Tiếp tục tất toán tài khoản tiết kiệm? ");
            Bank.pressEnter();
            finalAmount = getTotalDeposit(Bank.getTimeElapsed(getDateCreated(), now)[1]);
            System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
            Bank.pressEnter();
        } else if ((getSavingTerm() != Bank.getTimeElapsed(getDateCreated(), now)[1])) {
            double interestRate = SavingAccount.getSavingInterestRate(0);
            System.out.println("Tất toán tiền gửi trước hạn!");
            System.out.printf("Thời gian tính lãi: " +
                    Bank.getTimeElapsed(getDateCreated(), now)[1] + " tháng " +
                    Bank.getTimeElapsed(getDateCreated(), now)[0] + " ngày.\n");
            System.out.printf("Lãi suất áp dụng là: %5.2f%2s\n", interestRate, "%");
            System.out.println("\nTiếp tục tất toán? ");
            Bank.pressEnter();
            if (Bank.getTimeElapsed(getDateCreated(), now)[0] != 0) {
                finalAmount = getTotalDeposit(Bank.getTimeElapsed(getDateCreated(), now)[1], interestRate);
                finalAmount += getRemainDeposit(Bank.getTimeElapsed(getDateCreated(), now)[0]);
                System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
                Bank.pressEnter();
            } else {
                finalAmount = getTotalDeposit(Bank.getTimeElapsed(getDateCreated(), now)[1], interestRate);
                System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
                Bank.pressEnter();
            }
        }
        return finalAmount;
    }
}
