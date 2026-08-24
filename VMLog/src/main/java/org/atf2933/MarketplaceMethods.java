package org.atf2933;

import java.util.ArrayList;

public interface MarketplaceMethods {
    public void addCar(Vehicle car) throws AlreadyRegisteredVehicleException;
    public void saveVehicles();
    public void loadVehicle();
    public ArrayList<Vehicle> getVehicles();
    public void removeVehicle(Vehicle car);
}
