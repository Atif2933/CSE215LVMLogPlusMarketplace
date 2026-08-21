package org.atf2933;
import java.util.*;
import java.io.*;

public class UserManager extends User {


    public UserManager(int ID, String password, String name,String phonenum){
        super(ID,password,name,phonenum);

    }


    public void createUser(User userobj){
        getUserArray().add(userobj);
        saveUser();
    }
    public void removeUser(User userobj){
        getUserArray().remove(userobj);
        saveUser();
    }

    public void saveUser(){
        try {
            FileWriter fw = new FileWriter("userdata.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (User user : getUserArray()) {
                bw.write(
                        user.getID() + "|" +
                                user.getPassword() + "|" +
                                user.getName() + "|" +
                                user.getPhonenum()
                );
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    public void readUsers() {

        File userFile = new File("userdata.txt");

        try {

            FileReader fr = new FileReader(userFile);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                UserManager user = new UserManager(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3]
                );

                getUserArray().add(user);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error loading users!");
        }
    }
    public void displayUsers(){

        for(User u:getUserArray()){
            System.out.println(u);
        }
    }
}
