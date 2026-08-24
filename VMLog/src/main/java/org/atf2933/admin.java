package org.atf2933;
import java.io.*;

public class admin extends User {

    public admin(int ID,String password, String name,String phonenum){
        super(ID,password,name,phonenum);
    }



    public static admin readAdmin(){
        File adminData = new File("admindata.txt");

        try{
            FileReader fr = new FileReader(adminData);
            BufferedReader br = new BufferedReader(fr);
            //System.out.println("Looking!");
            //System.out.println(adminData.getAbsolutePath());
            int ID = Integer.parseInt(br.readLine());
            String password = br.readLine();
            String name = br.readLine();
            String phonenum = br.readLine();
            br.close();
            return new admin(ID,password,name, phonenum);
        } catch (IOException e) {
            System.out.println("File not found!");
            return null;
        }
    }

}
