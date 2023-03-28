/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author Eleni-Maria
 */
public class Employee extends User{
    private int employee_id;
    private String emploee_firstname;
    private String employee_lastname;
    
    public int getEmployeeID() {
        return this.employee_id;
    }

    public void setEmployeeID(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployeeFirstName() {
        return this.emploee_firstname;
    }

    public void setEmployeeFirstName(String emploee_firstname) {
        this.emploee_firstname = emploee_firstname;
    }

    public String getEmployeeLastName() {
        return this.employee_lastname;
    }

    public void setEmployeeLastName(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }
}
