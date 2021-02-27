package ge.edu.btu.server.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomSalary implements Serializable {
    private String name;
    private String formula;

    private CustomSalary(){}

    public CustomSalary(String name, String formula) {
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
