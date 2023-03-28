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
public class Seller extends User {

    private int profit;
    private int seller_fee;
    private int current_debt;

    public int getProfit() {
        return this.profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getSellerFee() {
        return this.seller_fee;
    }

    public void setSellerFee(int fee) {
        this.seller_fee = fee;
    }

    public int getCurrDebt() {
        return this.current_debt;
    }

    public void setCurrDebt(int debt) {
        this.current_debt = debt;
    }
}
