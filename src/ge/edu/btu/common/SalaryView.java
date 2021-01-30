package ge.edu.btu.common;

import ge.edu.btu.server.model.Employee;

import java.io.Serializable;

public class SalaryView implements Serializable {
    private String emp_id;
    private double deduction;
    private double accurancy;
    private double bonuses;


    public SalaryView(String emp_id, double deduction, double accurancy, double bonuses) {
        this.emp_id = emp_id;
        this.deduction = deduction;
        this.accurancy = accurancy;
        this.bonuses = bonuses;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(double accurancy) {
        this.accurancy = accurancy;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }
}
