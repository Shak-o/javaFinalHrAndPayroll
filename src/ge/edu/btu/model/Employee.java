package ge.edu.btu.model;

import java.util.Map;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String age;
    private String position;
    private String p_id;
    private Map<String, Integer> salary;

    public Employee(String name, String surname, String nickname, String age, String position, String p_id, Map<String, Integer> salary) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.age = age;
        this.position = position;
        this.p_id = p_id;
        this.salary = salary;
    }

    public Map<String, Integer> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, Integer> salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    void salaryToString(){

    }
}
