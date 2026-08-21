package org.atf2933;

import java.util.ArrayList;

public abstract class User {
    private int ID;
   private String password;
   private String name;
   private String phonenum;


   public User(int ID, String password, String name,String phonenum){
       this.ID = ID;
       this.password = password;
       this.name = name;
       this.phonenum = phonenum;
   }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName(){
       return name;
    }
    public String getPassword(){
       return password;
    }
    public int getID(){
       return ID;
    }

    public String getPhonenum() {
        return phonenum;
    }
    private ArrayList<User> userArray = new ArrayList<>();

    public ArrayList<User> getUserArray() {
        return userArray;
    }
}
