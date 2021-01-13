package ge.edu.btu.common;

public class Employee {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String age;
    private String position;
    private String p_id;
    private String gender;
    private String salary;

    public Employee(String name, String surname, String nickname, String age,String gender, String position, String p_id, String salary) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.age = age;
        this.position = position;
        this.p_id = p_id;
        this.salary = salary;
        this.gender = gender;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    void salaryToString(){

    }
}