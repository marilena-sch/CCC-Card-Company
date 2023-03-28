/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

import mainClasses.User;

/**
 *
 * @author Eva Apple
 */
public class Customer extends User {

    private String expiration_date;
    private int credit_limit;
    private int current_debt;
    private int available_balance;

    public String getExpDate() {
        return this.expiration_date;
    }

    public void setExpDate(String date) {
        this.expiration_date = date;
    }

    public int getCredLim() {
        return this.credit_limit;
    }

    public void setCredLim(int limit) {
        this.credit_limit = limit;
    }

    public int getCurrDebt() {
        return this.current_debt;
    }

    public void setCurrDebt(int debt) {
        this.current_debt = debt;
    }

    public int getAvBal() {
        return this.available_balance;
    }

    public void setAvBal(int balance) {
        this.available_balance = balance;
    }

}
