package src;

import src.hingeable.LoadingPlatform;
import src.util.Vector;
import src.vehicle.*;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Application implements VehicleMovementObserver, VehicleAddRemoveObserver {

    private final Map<Vehicle, RenderObject> renderObjects = new HashMap<>();
    private final VehicleGroup group;
    private final VehicleView view;

    private Application(){
        int width = 800;
        int height = 800;
        group = new VehicleGroup(width, height);
        view = new VehicleView(new VehicleController(group, new GasController(), width), height, height);
    }

    public void run(){
        view.initComponents();
        Arrays.asList("Saab95", "Scania_1337", "Volvo240").forEach(view::loadImage);

        group.addVehicleAddRemoveListener(this);
        group.addVehicleMoveListener(this);

        Vehicle volvo = VehicleFactory.createNewVolvo240();
        volvo.setPosition(new Vector(0, 0));
        group.addVehicle(volvo);

        Vehicle saab = VehicleFactory.createNewSaab95();
        saab.setPosition(new Vector(0, 100));
        group.addVehicle(saab);

        Vehicle scania = VehicleFactory.createNewScania(new LoadingPlatform());
        scania.setPosition(new Vector(0, 200));
        group.addVehicle(scania);


        // Start the timer
        group.startAnimation();
    }

    public static void main(String[] args) {
        new Application().run();
    }

    public void onVehicleMoved(Vehicle vehicle, Vector position){
        RenderObject renderObject = renderObjects.get(vehicle);
        if(renderObject == null)
            return;

        // Update render object position
        Point point = new Point((int) Math.round(vehicle.getPosition().getX()),
                (int) Math.round(vehicle.getPosition().getY()));
        renderObject.setPosition(point);
        this.view.repaint();
    }

    @Override
    public void onVehicleAdded(Vehicle vehicle) {
        RenderObject object = view.addRenderObject(vehicle.getModelName(),
                new Point((int) Math.round(vehicle.getPosition().getX()),
                        (int) Math.round(vehicle.getPosition().getY())));

        this.renderObjects.put(vehicle, object);
    }

    @Override
    public void onVehicleRemoved(Vehicle vehicle) {
        RenderObject object = this.renderObjects.get(vehicle);
        if(object == null)
            return;

        view.removeRenderObject(object);
    }

}
