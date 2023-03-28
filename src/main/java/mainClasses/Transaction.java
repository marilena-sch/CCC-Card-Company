/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Eva Apple
 */
public class Transaction {

    private int transaction_id;
    private int customer_id;
    private int seller_id;
    private int transaction_day;
    private int transaction_month;
    private int transaction_year;
    private int transaction_amount;
    private String transaction_type;
    private int employee_id;

    public int getTransactionID() {
        return this.transaction_id;
    }

    public void setTransactionID(int ID) {
        this.transaction_id = ID;
    }

    public int getCustomerID() {
        return this.customer_id;
    }

    public void setCustomerID(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getSellerID() {
        return this.seller_id;
    }

    public void setSellerID(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getTransAmount() {
        return this.transaction_amount;
    }

    public void setTransAmount(int amount) {
        this.transaction_amount = amount;
    }
    
    public int getTransDay() {
        return this.transaction_day;
    }

    public void setTransDay(int transaction_day) {
        this.transaction_day = transaction_day;
    }

    public int getTransMonth() {
        return this.transaction_month;
    }

    public void setTransMonth(int transaction_month) {
        this.transaction_month = transaction_month;
    }
    
    public int getTransYear() {
        return this.transaction_year;
    }

    public void setTransYear(int transaction_year) {
        this.transaction_year = transaction_year;
    }
    
    public String getTransType() {
        return this.transaction_type;
    }

    public void setTransType(String type) {
        this.transaction_type = type;
    }
    
    public int getEmployeID() {
        return this.employee_id;
    }

    public void setEmployeeID(int employee_id) {
        this.employee_id = employee_id;
    }
}
