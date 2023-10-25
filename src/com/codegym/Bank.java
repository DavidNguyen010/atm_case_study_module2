package com.codegym;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bank {
    /**
     * Hàm tính ngày, tháng giữ 2 khoảng thời dateCreated và now
     * trả về mảng int[2] elapsedTime trong đó elapsedTime[0] = ngày, elapsedTime[1] = tháng
     * Sử dụng để tính tiền khi tất toán tài khoản ngân hàng
     */

    public static int[] getTimeElapsed(Date dateCreated, Date now){
        int[] elapsedTime = new int[2];
        long timeElapsed = now.getTime() - dateCreated.getTime();
        long totalHours = timeElapsed / 3600000;
        int monthElapsed = (int) totalHours/ (30*24);
        int dayElapsed = (int) (totalHours - (monthElapsed * 30 *24)) / 24;
        elapsedTime[0] = dayElapsed;
        elapsedTime[1] = monthElapsed;
        return elapsedTime;
    }

    public static String generateAccountNumber(){
        StringBuilder string = new StringBuilder("BankID-");
        for (int i = 0; i<6;i++){
            string.append((char) ('0' + Math.random() * ('9' - '0' ))) ;

        }
        return string.toString();
    }

    public static void  printTransactionActivity(){
        System.out.printf("%65s", "------------------------\n");
        System.out.printf("%58s", "Lịch sử giao dịch\n");
        System.out.printf("%65s", "------------------------\n");
        System.out.printf("%20s%15s%15s%15s%35s\n",
                "Ngày        ", "Loại  ", "Biến động ",
                "Số dư    ", "Nội dung           ");
    }

    public static void printSavingAccountStatement(){
        System.out.println("-------------------------------------------");
        System.out.println("        Thông Tin Tài Khoản Tiết Kiệm      ");
        System.out.println("-------------------------------------------");
    }

    public static void printMenu(){
        System.out.println("\n");
        System.out.println("                                          MAIN MENU                                      ");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%28s%38s%33s", "Tài Khoản Giao Dịch", "Tài Khoản Tiết Kiệm", "Tài Khoản Vay\n");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-25s%-23s%-25s%25s", "11: Tạo Tài Khoản", "14: Xem thông tin", "21: Gửi Tiết Kiệm", "31: Vay Tiền\n");
        System.out.printf("%-25s%-23s%-25s%25s", "12: Rút tiền", "15: Lịch sử giao dịch", "22: Tính lãi", "32: Xem thông tin\n");
        System.out.printf("%-25s%-23s%-25s%25s", "13: Nạp tiền", "", "23: Xem thông tin", "33: Tất toán khoản vay\n");
        System.out.printf("%-25s%-23s%-25s%25s", "0: Thoát", "", "24: Tất toán tiền gửi", "34: Tính lãi vay\n");
        System.out.println();
        System.out.print("Chọn chức năng: ");
    }

    // convert date to dd-MM-yyyy
    public static String convertDate(Date date){
        String format = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }
    // HH:mm:ss dd-MM-yyyy
    public static String convertDetailDate(Date date){
        String format = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date){
        try {
            return new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").parse(date);
        }catch (ParseException e){
            return null;
        }
    }

    public static void pressEnter(){
        System.out.print("Press \"ENTER\" to continue...");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }
}
