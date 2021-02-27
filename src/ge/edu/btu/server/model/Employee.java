package ge.edu.btu.server.model;

import java.io.Serializable;
import java.util.Map;

public class Employee implements Serializable {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String age;
    private String position;
    private String p_id;
    private String gender;
    private Double firstComponent;
    private Double secondComponent;
    private Double thirdComponent;
    private Double fourthComponent;
    private Double fifthComponent;
    private Double sixthComponent;
    private String formulaName;

    public Employee(String name, String surname, String nickname, String age, String position, String p_id, String gender, Double firstComponent, Double secondComponent, Double thirdComponent, Double fourthComponent, Double fifthComponent, Double sixthComponent, String formulaName) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.age = age;
        this.position = position;
        this.p_id = p_id;
        this.gender = gender;
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;
        this.thirdComponent = thirdComponent;
        this.fourthComponent = fourthComponent;
        this.fifthComponent = fifthComponent;
        this.sixthComponent = sixthComponent;
        this.formulaName = formulaName;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public Double getFirstComponent() {
        return firstComponent;
    }

    public void setFirstComponent(Double firstComponent) {
        this.firstComponent = firstComponent;
    }

    public Double getSecondComponent() {
        return secondComponent;
    }

    public void setSecondComponent(Double secondComponent) {
        this.secondComponent = secondComponent;
    }

    public Double getThirdComponent() {
        return thirdComponent;
    }

    public void setThirdComponent(Double thirdComponent) {
        this.thirdComponent = thirdComponent;
    }

    public Double getFourthComponent() {
        return fourthComponent;
    }

    public void setFourthComponent(Double fourthComponent) {
        this.fourthComponent = fourthComponent;
    }

    public Double getFifthComponent() {
        return fifthComponent;
    }

    public void setFifthComponent(Double fifthComponent) {
        this.fifthComponent = fifthComponent;
    }

    public Double getSixthComponent() {
        return sixthComponent;
    }

    public void setSixthComponent(Double sixthComponent) {
        this.sixthComponent = sixthComponent;
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
