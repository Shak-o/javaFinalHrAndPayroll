package ge.edu.btu.server.model;

public class CustomSalary {
    private String emp_id;
    private double component1;
    private double component2;
    private double component3;
    private double component4;
    private double component5;
    private double component6;
    private String formula;

    private CustomSalary(){}

    public CustomSalary(String emp_id, double component1, double component2, double component3, double component4, double component5, double component6, String formula) {
        this.emp_id = emp_id;
        this.component1 = component1;
        this.component2 = component2;
        this.component3 = component3;
        this.component4 = component4;
        this.component5 = component5;
        this.component6 = component6;
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public double getComponent1() {
        return component1;
    }

    public void setComponent1(double component1) {
        this.component1 = component1;
    }

    public double getComponent2() {
        return component2;
    }

    public void setComponent2(double component2) {
        this.component2 = component2;
    }

    public double getComponent3() {
        return component3;
    }

    public void setComponent3(double component3) {
        this.component3 = component3;
    }

    public double getComponent4() {
        return component4;
    }

    public void setComponent4(double component4) {
        this.component4 = component4;
    }

    public double getComponent5() {
        return component5;
    }

    public void setComponent5(double component5) {
        this.component5 = component5;
    }

    public double getComponent6() {
        return component6;
    }

    public void setComponent6(double component6) {
        this.component6 = component6;
    }
}
