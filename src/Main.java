package src;

import src.hingeable.LoadingPlatform;
import src.util.Vector;
import src.vehicle.*;

public class Main {
    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController(new String[]{"Saab95", "Scania", "Volvo240", "Scania_1337"});

        // Start a new view and send a reference of self
        cc.frame = new VehicleView("VehicleSim 1.0", cc, 800, 800);

        cc.loadImages();

        Vehicle volvo = new Volvo240();
        volvo.setPosition(new Vector(0, 0));
        cc.addVehicle(volvo);

        Vehicle saab = new Saab95();
        saab.setPosition(new Vector(0, 100));
        cc.addVehicle(saab);

        Truck scania = new Scania(new LoadingPlatform());
        scania.setPosition(new Vector(0, 200));
        cc.addVehicle(scania);

        // Start the timer
        cc.startTimer();
    }
}
