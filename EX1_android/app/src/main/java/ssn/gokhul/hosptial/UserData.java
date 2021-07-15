package ssn.gokhul.hosptial;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    private String name;
    private String address;
    private int age;
    private String dob;
    private String gender;
    private String martial;
    private String contact;
    private String reg;
    private ArrayList<String> addiction;

    public UserData(String name, String address, int age, String dob, String gender, String martial, String contact, String reg, ArrayList<String> addiction) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.martial = martial;
        this.contact = contact;
        this.reg = reg;
        this.addiction = addiction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartial() {
        return martial;
    }

    public void setMartial(String martial) {
        this.martial = martial;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public ArrayList<String> getAddiction() {
        return addiction;
    }

    public void setAddiction(ArrayList<String> addiction) {
        this.addiction = addiction;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", martial='" + martial + '\'' +
                ", contact='" + contact + '\'' +
                ", reg='" + reg + '\'' +
                ", addiction=" + addiction +
                '}';
    }
}
