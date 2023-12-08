package src;

import src.controller.GasController;
import src.controller.VehicleController;
import src.model.hingeable.LoadingPlatform;
import src.model.observers.VehicleAddRemoveObserver;
import src.model.VehicleGroup;
import src.model.observers.VehicleMovementObserver;
import src.model.util.Vector;
import src.model.vehicle.*;
import src.view.RenderObject;
import src.view.VehicleView;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The entry point of our application.
 * Responsible for setting up all components in our architecture and also maps Vehicle -> Render object.
 */
public class Application implements VehicleMovementObserver, VehicleAddRemoveObserver {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    private static final String[] SUPPORTED_MODELS = new String[]{"Saab95", "Scania_1337", "Volvo240"};

    private final Map<Vehicle, RenderObject> renderObjects = new HashMap<>();
    private final VehicleGroup group;
    private final VehicleView view;

    private Application(){
        int worldHeight = WINDOW_HEIGHT - 240;
        group = new VehicleGroup(WINDOW_WIDTH, worldHeight);

        GasController gasController = new GasController();
        VehicleController vehicleController = new VehicleController(group, gasController, WINDOW_WIDTH);

        view = new VehicleView(vehicleController, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH, worldHeight);
    }

    public static void main(String[] args) {
        new Application().run();
    }

    public void run(){
        view.initComponents();
        Arrays.stream(SUPPORTED_MODELS)
                .forEach(view::loadImage);

        // Setup this application as an observer.
        group.addVehicleAddRemoveListener(this);
        group.addVehicleMoveListener(this);

        addVehicles();

        // Start the timer
        group.startAnimation();
    }

    private void addVehicles(){
        Vehicle volvo = VehicleFactory.createNewVolvo240();
        volvo.setPosition(new Vector(0, 0));
        group.addVehicle(volvo);

        Vehicle saab = VehicleFactory.createNewSaab95();
        saab.setPosition(new Vector(0, 100));
        group.addVehicle(saab);

        Vehicle scania = VehicleFactory.createNewScania(new LoadingPlatform());
        scania.setPosition(new Vector(0, 200));
        group.addVehicle(scania);
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
