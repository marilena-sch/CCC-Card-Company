/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Eleni-Maria
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DB_Connection {

    
//    public static void main(String args[]) {
//        create_tables();
//        insert_tables();
//    }

   public static void create_tables() {

        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();

            String individual = "CREATE TABLE INDIVIDUALS"
                    + "(account_id INTEGER not NULL,"
                    + "firstname VARCHAR(40) not NULL,"
                    + "lastname VARCHAR(40) not NULL,"
                    + "expiration_date DATE not NULL,"
                    + "account_limit INTEGER not NULL,"
                    + "current_debt INTEGER not NULL,"
                    + "available_balance INTEGER not NULL,"
                    + "password VARCHAR(15) not NULL,"
                    + "PRIMARY KEY (account_id))";
            stmt.executeUpdate(individual);

            String companies = "CREATE TABLE COMPANIES"
                    + "(account_id INTEGER not NULL,"
                    + "firstname VARCHAR(40) not NULL,"                   
                    + "expiration_date DATE not NULL,"
                    + "account_limit INTEGER not NULL,"
                    + "current_debt INTEGER not NULL,"
                    + "available_balance INTEGER not NULL,"
                    + "password VARCHAR(15) not NULL,"
                    + "PRIMARY KEY (account_id))";
            stmt.executeUpdate(companies);
            
            String employees = "CREATE TABLE EMPLOYEES"
                    + "(employee_id INTEGER not NULL,"
                    + "account_id INTEGER not NULL,"
                    + "firstname VARCHAR(40) not NULL,"
                    + "lastname VARCHAR(40) not NULL,"
                    + "PRIMARY KEY (employee_id))";
            stmt.executeUpdate(employees);

            String sellers = "CREATE TABLE SELLERS"
                    + "(account_id INTEGER not NULL,"
                    + "firstname VARCHAR(40) not NULL,"
                    + "lastname VARCHAR(40) not NULL,"
                    + "seller_fee INTEGER not NULL,"
                    + "profit INTEGER not NULL,"
                    + "current_debt INTEGER not NULL,"
                    + "password VARCHAR(15) not NULL,"
                    + "PRIMARY KEY (account_id))";
            stmt.executeUpdate(sellers);

            String transactions = "CREATE TABLE TRANSACTIONS"
                    + "(transaction_id INTEGER not NULL,"
                    + "customer_id INTEGER not NULL,"
                    + "seller_id INTEGER not NULL,"
                    + "day INTEGER not NULL,"
                    + "month INTEGER not NULL,"
                    + "year INTEGER not NULL,"
                    + "transaction_amount INTEGER not NULL,"
                    + "transaction_type VARCHAR(40) not NULL,"
                    + "employee_id INTEGER not NULL,"
                    + "PRIMARY KEY (transaction_id))";
            stmt.executeUpdate(transactions);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        System.out.println("Database is connected!");
    }

    public static void insert_tables() {
        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ccc", "root", "");
            stmt = con.createStatement();

            String insert_individual;
            String insert_companies;
            String insert_employees;
            String insert_sellers;
            String insert_transaction;

            insert_individual
                    = "INSERT INTO individuals (account_id,firstname,lastname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('123','Eleni','Papadaki','2022-02-01','1000','100','900','EleniP')";
            stmt.executeUpdate(insert_individual);

            insert_individual
                    = "INSERT INTO individuals (account_id,firstname,lastname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('456','Maria','Daskalaki','2023-09-01','1500','50','1450','MariaD')";
            stmt.executeUpdate(insert_individual);

            insert_individual
                    = "INSERT INTO individuals (account_id,firstname,lastname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('321','Stella','Tzortzakaki','2027-03-01','3000','500','2500','StellaT')";
            stmt.executeUpdate(insert_individual);

            insert_individual
                    = "INSERT INTO individuals (account_id,firstname,lastname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('654','Michail','Stavrianakis','2030-05-01','10000','2000','8000','MichailS')";
            stmt.executeUpdate(insert_individual);

            insert_individual
                    = "INSERT INTO individuals (account_id,firstname,lastname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('789','Evaggelia','Psicharaki','2025-01-01','2000','300','1700','EvaggeliaP')";
            stmt.executeUpdate(insert_individual);

            insert_companies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('111','Apple','2022-06-01','100000','0','100000','AppleC')";
            stmt.executeUpdate(insert_companies);

            insert_companies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('222','Samsung','2026-03-01','50000','1000','49000','SamsungC')";
            stmt.executeUpdate(insert_companies);

            insert_companies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('333','Rouvas','2023-08-01','30000','5000','25000','RouvasC')";
            stmt.executeUpdate(insert_companies);

            insert_companies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('444','Zaros','2024-10-01','25500','5000','20500','ZarosC')";
            stmt.executeUpdate(insert_companies);

            insert_companies
                    = "INSERT INTO companies (account_id,firstname,expiration_date,account_limit,current_debt,available_balance,password)"
                    + "VALUES ('555','Planet','2031-11-01','40000','24000','26000','PlanetC')";
            stmt.executeUpdate(insert_companies);

            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('790','111','Gewrgios','Ksirouchakis')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('807','111','Stavros','Ntroumpogiannis')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('531','222','Emmanouil','Papadakis')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('645','222','Argiri','Petraki')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('598','333','Maria','Stroumpaki')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('777','333','Gewrgia','Stroumpaki')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('608','444','Stavros','Schoinarakis')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('506','444','Eleni-Maria','Schoinaraki')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('999','555','Varvara','Savoidaki')";
            stmt.executeUpdate(insert_employees);
            
            insert_employees
                    = "INSERT INTO employees (employee_id,account_id,firstname,lastname)"
                    + "VALUES ('406','555','Evaggelia','Milolidaki')";
            stmt.executeUpdate(insert_employees);
            
            insert_sellers
                    = "INSERT INTO sellers (account_id,firstname,lastname,seller_fee,profit,current_debt,password)"
                    + "VALUES ('231','Ioannis','Manolakis','20','300','50','IoannisM')";
            stmt.executeUpdate(insert_sellers);

            insert_sellers
                    = "INSERT INTO sellers (account_id,firstname,lastname,seller_fee,profit,current_debt,password)"
                    + "VALUES ('546','Mathaios','Stamatakis','50','500','100','MathaiosS')";
            stmt.executeUpdate(insert_sellers);

            insert_sellers
                    = "INSERT INTO sellers (account_id,firstname,lastname,seller_fee,profit,current_debt,password)"
                    + "VALUES ('879','Argyro','Vroulaki','100','800','500','ArgyroV')";
            stmt.executeUpdate(insert_sellers);

            insert_sellers
                    = "INSERT INTO sellers (account_id,firstname,lastname,seller_fee,profit,current_debt,password)"
                    + "VALUES ('011','Petros','Stamatiou','150','1000','700','PetrosS')";
            stmt.executeUpdate(insert_sellers);

            insert_sellers
                    = "INSERT INTO sellers (account_id,firstname,lastname,seller_fee,profit,current_debt,password)"
                    + "VALUES ('012','Marina','Mprilaki','50','300','150','MarinaM')";
            stmt.executeUpdate(insert_sellers);
            
            
            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0000','333','879','3','4','2022','400','Pistwsh','0')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0001','123','231','20','1','2022','500','Xrewsh','0')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0002','111','879','13','2','2022','100','Pistwsh','0')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0003','654','546','15','5','2022','900','Pistwsh','0')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0004','444','012','18','3','2022','300','Xrewsh','506')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0005','321','011','9','6','2022','150','Xrewsh','0')";
            stmt.executeUpdate(insert_transaction);

            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0006','555','546','3','4','2022','400','Pistwsh','0')";
            stmt.executeUpdate(insert_transaction);
            
            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0007','555','011','18','3','2022','300','Xrewsh','999')";
            stmt.executeUpdate(insert_transaction);
            
           insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0008','222','0','3','2','2022','500','Xrewsh','0')";
            stmt.executeUpdate(insert_transaction);
            
            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0009','456','0','2','2','2022','50','Xrewsh','0')";
            stmt.executeUpdate(insert_transaction);
            
            insert_transaction
                    = "INSERT INTO transactions (transaction_id,customer_id,seller_id,day,month,year,transaction_amount,transaction_type, employee_id)"
                    + "VALUES ('0010','011','0','1','2','2022','500','Xrewsh','0')";
            stmt.executeUpdate(insert_transaction);
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
        System.out.println("Database is connected!");

    }
}
