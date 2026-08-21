package org.atf2933;

import java.io.*;
import java.util.ArrayList;

public class Marketplace {

    public Marketplace(){
        loadVehicle();
    }
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
     File fileCars = new File("allcars.txt");

    public void addCar(Vehicle car){
        vehicles.add(car);
        saveVehicles();
    }
    public void saveVehicles(){
        try {
            FileWriter fw = new FileWriter(fileCars);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Vehicle car: vehicles){
                bw.write(
                        car.getBrandName() + "|" +
                                car.getModelName() + "|" +
                                car.getOwnerName() + "|" +
                                car.getOwnerPhone() + "|" +
                                car.getMileage() + "|" +
                                car.getEngineModel()+"|"+
                                car.isForSale());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Failed to find file!");
        }
    }
    public void loadVehicle(){
        try{

            FileReader fr = new FileReader(fileCars);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while((line=br.readLine())!=null){

                String[] dat = line.split("\\|");

                Vehicle car = new Vehicle(
                        dat[0],dat[1],dat[2],dat[3],Integer.parseInt(dat[4]),dat[5],Boolean.parseBoolean(dat[6])
                );

                vehicles.add(car);

            }
            br.close();

        } catch(FileNotFoundException e){
            System.out.println("FileNotFoundException");

        } catch(IOException e){
            System.out.println("IOException");

        }
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    public ArrayList<Vehicle> listedCars = new ArrayList<>();
    public void removeVehicle(Vehicle car){
        vehicles.remove(car);
        saveVehicles();
    }
}
