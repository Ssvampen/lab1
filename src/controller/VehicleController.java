package src.controller;

import src.model.VehicleGroup;
import src.model.vehicle.Volvo240;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main controller in our MVC.
 * Consists of a Panel of different action buttons.
 */
public class VehicleController extends JPanel {
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Scania Lower Bed");

    private final JButton turnLeftButton = new JButton("Turn Left");
    private final JButton turnRightButton = new JButton("Turn Right");

    private final JButton startButton = new JButton("Start all vehicles");
    private final JButton stopButton = new JButton("Stop all vehicles");

    private final JButton addVehicleButton = new JButton("Add vehicle");
    private final JButton removeVehicleButton = new JButton("Remove vehicle");

    private final VehicleGroup group;
    private final GasController gasController;
    private final int width;

    /**
     * Creates a new vehicle controller.
     * @param group Group that this controller manipulates.
     * @param gasController Controller for gas.
     * @param width Width of the world.
     */
    public VehicleController(VehicleGroup group, GasController gasController, int width){
        this.group = group;
        this.gasController = gasController;
        this.width = width;
    }

    /**
     * Initialises the components of the controller, adds action listeners.
     */
    public void initComponents(){
        this.setLayout(new GridLayout(2,4));
        this.add(gasButton, 0);
        this.add(turboOnButton, 1);
        this.add(liftBedButton, 2);
        this.add(brakeButton, 3);
        this.add(turboOffButton, 4);
        this.add(lowerBedButton, 5);
        this.add(turnLeftButton, 6);
        this.add(turnRightButton, 7);
        this.setPreferredSize(new Dimension((width/2)+300, 200));
        this.setBackground(Color.CYAN);

        // Vehicle add/remove buttons
        this.add(addVehicleButton);
        this.add(removeVehicleButton);

        addVehicleButton.addActionListener(e -> group.addVehicle(new Volvo240()));
        removeVehicleButton.addActionListener(e -> group.removeVehicle());

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(width/5-15,200));
        startButton.addActionListener(e -> group.start());
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(width/5-15,200));
        stopButton.addActionListener(e -> group.stop());
        this.add(stopButton);

        gasButton.addActionListener(e -> group.gas(gasController.getGasAmount()));
        brakeButton.addActionListener(e -> group.brake(gasController.getGasAmount()));
        turboOnButton.addActionListener(e -> group.turboOn());
        turboOffButton.addActionListener(e -> group.turboOff());
        liftBedButton.addActionListener(e -> group.liftBed(gasController.getGasAmount()));
        lowerBedButton.addActionListener(e -> group.lowerBed(gasController.getGasAmount()));
        turnLeftButton.addActionListener(e -> group.turnLeft());
        turnRightButton.addActionListener(e -> group.turnRight());

        gasController.initComponents();
    }

    public GasController getGasController() {
        return gasController;
    }

}
