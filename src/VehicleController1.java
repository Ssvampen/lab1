package src;

import src.hingeable.Platformable;
import src.vehicle.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleController1 {

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    // A list of vehicles, modify if needed
    private final java.util.List<Vehicle> vehicles = new ArrayList<>();
    // A map of vehicles mapped to their renderObjects
    private final Map<Vehicle, RenderObject> renderObjects = new HashMap<>();

    private String[] supportedModels;


    public VehicleController1(String[] supportedModels){
        this.supportedModels = supportedModels;
    }

    public void loadImages(){
        // Load images here
        for(String model : supportedModels) {
            frame.loadImage(model); // TODO: law of demeter!!!!!!!!!!!!!!
        }
    }

    //methods:
    public void addVehicle(Vehicle vehicle){
        this.vehicles.add(vehicle);
        RenderObject object = this.frame.addRenderObject(vehicle.getModelName(),
                new Point((int) Math.round(vehicle.getPosition().getX()),
                        (int) Math.round(vehicle.getPosition().getY())));

        this.renderObjects.put(vehicle, object);
    }


    /*

                RenderObject renderObject = renderObjects.get(vehicle);
                if(renderObject == null)
                    continue;

                bounceVehicleIfOutOfBounds(vehicle);

                // Update render object position
                Point point = new Point((int) Math.round(vehicle.getPosition().getX()),
                        (int) Math.round(vehicle.getPosition().getY()));
                renderObject.setPosition(point);

                vehicle.move();
            }
            frame.repaint();
    */

}
