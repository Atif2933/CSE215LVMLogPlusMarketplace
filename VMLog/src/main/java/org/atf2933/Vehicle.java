package org.atf2933;

import java.util.ArrayList;

public class Vehicle {

    private String brandName;
    private String modelName;
    private String ownerName;
    private String ownerPhone;
    private int mileage;
    private String engineModel;
    public boolean forSale;


    public Vehicle(String brandName, String modelName,String ownerName, String ownerPhone, int mileage,String engineModel,boolean forSale){

        this.brandName = brandName;
        this.modelName = modelName;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.mileage = mileage;
        this.engineModel = engineModel;
        this.forSale = forSale;

    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public void setForSale(boolean isForSale){
        this.forSale = forSale;
    }

    public boolean isForSale() {
        return forSale;
    }



    public String getBrandName(){
        return brandName;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public int getMileage() {
        return mileage;
    }

    public String getModelName() {
        return modelName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    ArrayList<Vehicle> cars = new ArrayList<>();

}
