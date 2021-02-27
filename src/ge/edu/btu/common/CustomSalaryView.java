package ge.edu.btu.common;

import java.io.Serializable;

public class CustomSalaryView implements Serializable {
    private String name;
    private String formula;

    private CustomSalaryView(){}

    public CustomSalaryView(String name, String formula) {
        this.name = name;
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
