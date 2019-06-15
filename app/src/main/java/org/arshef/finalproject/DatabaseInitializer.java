package org.arshef.finalproject;

import org.arshef.finalproject.Models.BankAccount;
import org.arshef.finalproject.Models.Sim;

public class DatabaseInitializer {

    public static boolean dbexists = false;

    public static void init(){
        bankaccdb();
        simdb();
        dbexists = true;
    }

    public static void bankaccdb(){
        BankAccount acc1 = new BankAccount(10000000, "111111111111", "1111");
        BankAccount acc2 = new BankAccount(10000000, "222222222222", "2222");
        BankAccount acc3 = new BankAccount(10000000, "333333333333", "3333");
        BankAccount acc4 = new BankAccount(10000000, "444444444444", "4444");
        BankAccount acc5 = new BankAccount(10000000, "555555555555", "5555");
        BankAccount acc6 = new BankAccount(10000000, "666666666666", "6666");
        acc1.save();
        acc2.save();
        acc3.save();
        acc4.save();
        acc5.save();
        acc6.save();
    }

    public static void simdb(){
        Sim sim1 = new Sim("09121111111");
        Sim sim2 = new Sim("09122222222");
        Sim sim3 = new Sim("09123333333");
        Sim sim4 = new Sim("09124444444");
        Sim sim5 = new Sim("09125555555");
        Sim sim6 = new Sim("09126666666");
        sim1.save();
        sim2.save();
        sim3.save();
        sim4.save();
        sim5.save();
        sim6.save();
    }
}
