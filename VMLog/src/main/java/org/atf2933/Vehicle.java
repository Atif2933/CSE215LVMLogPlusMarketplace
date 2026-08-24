package org.atf2933;

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



}
