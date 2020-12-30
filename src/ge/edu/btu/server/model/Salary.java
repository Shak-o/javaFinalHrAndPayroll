package ge.edu.btu.server.model;

import java.sql.Date;

public class Salary {
    private String name;
    private Date active_date;

    public Salary(String name, Date active_date) {
        this.name = name;
        this.active_date = active_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getActive_date() {
        return active_date;
    }

    public void setActive_date(Date active_date) {
        this.active_date = active_date;
    }
}
