package com.codegym;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class LoanAccount implements FinalSettlement {
    private static final double[] LOAN_INTEREST_RATE = {7.5, 8.4, 8.8, 12};
    private double loanAmount;
    private int loanTerm;
    private double loanInterestRate;
    private Date dateCreated;
    String timeStamp = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(Calendar.getInstance().getTime());


    public LoanAccount(){}

    public LoanAccount(int loanTerm, double loanAmount, double loanInterestRate){
      this.loanTerm = loanTerm;
      this.loanAmount = loanAmount;
      this.loanInterestRate = loanInterestRate;
      this.dateCreated = Bank.parseDate(timeStamp);
    }

    public static double getLoanInterestRate(int month){
        return LOAN_INTEREST_RATE[month /3-1];
    }

    public double getMonthlyPayment(){
        double monthlyInterestRate = getLoanInterestRate(loanTerm) / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate /
                (1-Math.pow(1/(1 + monthlyInterestRate),getLoanTerm()));
        return monthlyPayment;
    }

    //tien lai tai thang loan term
    public double getMonthlyPayment(int loanTerm){
        double monthlyInterestRate = getLoanInterestRate(loanTerm) / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), loanTerm)));
        return monthlyPayment;
    }

    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * getLoanTerm();
        return totalPayment;
    }
    //lai tai thang : month
    public double getTotalPayment(int month) {
        double totalPayment = getMonthlyPayment() * month;
        return totalPayment;
    }
    public boolean isMatchLoanTerm() {
        Date now = new Date();
        LocalDate nowTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate pastTime = getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        boolean isMatchDay = nowTime.getDayOfMonth() == pastTime.getDayOfMonth();
        boolean isMatchMonth = (getLoanTerm() == Bank.getTimeElapsed(getDateCreated(), now)[1]);
        return isMatchDay && isMatchMonth;
    }

    //in thong tin khoan vay
    public void printLoanAccount() {
        System.out.printf("%-2s%-15s%20s%6s\n", "|", "Ngày bắt đầu:", Bank.convertDate(getDateCreated()), "|");
        System.out.printf("%-2s%-15s%18.2f%6s\n", "|", "Số tiền vay:", getLoanAmount(), "|");
        System.out.printf("%-2s%-15s%18.2f%2s%6s\n", "|", "Lãi suất:", getLoanInterestRate(getLoanTerm()), "%", "|");
        System.out.printf("%-2s%-15s%12d%8s%6s\n", "|", "Kỳ hạn vay:", getLoanTerm(), "Tháng", "|");
        System.out.println("-------------------------------------------");
    }

    //tinh tien lai khoan vay
    public void LoanCalculator() {
        System.out.println("                 Bảng lãi tiền vay                ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%5s%20s%16s\n", "Kỳ hạn vay  ", "Tiền trả mỗi tháng", "Tổng tiền phải trả");
        System.out.printf("#%-5d%20.2f%20.2f\n\n", getLoanTerm(), getMonthlyPayment(), getTotalPayment());
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public double finalSettlement() {
        Date now = new Date();
        double finalAmount = 0;
        if (isMatchLoanTerm()) {
            System.out.printf("Tất toán khoản vay đúng hạn. Lãi suất áp dụng là: %5.2f%2s\n",
                    getLoanInterestRate(getLoanTerm()), "%");
            finalAmount = getTotalPayment();
            System.out.printf("Số tiền cần thanh toán: %16.2f VND\n", finalAmount);
        } else {
            System.out.println("Tất toán khoản vay trước hạn!");
            System.out.printf("Thời gian tính lãi: " +
                    Bank.getTimeElapsed(getDateCreated(), now)[1] + " tháng | ");
            System.out.printf("Lãi suất áp dụng là: %5.2f%2s",
                    getLoanInterestRate(getLoanTerm()), "%\n");
            double payedMount = (getLoanAmount() / getLoanTerm()) *
                    Bank.getTimeElapsed(getDateCreated(), now)[1] +
                    getLoanAmount() * getLoanInterestRate(getLoanTerm()) / (getLoanTerm() * 100);
            double remainPayment = getLoanAmount() - payedMount;
            finalAmount = payedMount + remainPayment;
            System.out.println("                    Bảng lãi tiền vay                  ");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%8s%20s%12s%12s\n", "Kỳ hạn", "Đã thanh toán", "Còn nợ", "Tổng");
            System.out.printf("%8d%18.2f%16.2f%14.2f\n", getLoanTerm(), payedMount, remainPayment, finalAmount);
        }
        System.out.println("Tiếp tục tất toán tài khoản vay? ");
        Bank.pressEnter();
        System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
        Bank.pressEnter();
        return finalAmount;
    }
}
