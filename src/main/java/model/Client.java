package model;

public class Client {
    private Integer ID;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

    public Client(){

    }

    public Client(Integer ID, String firstName, String lastName, Integer age, String email, String password, String phoneNumber, String address) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "('" + this.ID + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.age + "', '" + this.email + "', '" + this.password + "', '" + this.phoneNumber + "', '" + this.address + "')";
    }
}
