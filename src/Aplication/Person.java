package Aplication;

public class Person {

    private String userName;
    private String pass;


    public Person(){

    }
    public Person(String userName,String pass){
        this.userName=userName;
        this.pass=pass;
    }

    public String getPass() {
        return pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
