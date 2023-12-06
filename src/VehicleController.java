package src;

import javax.swing.*;
import java.awt.*;

public class VehicleController extends JPanel {
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");

    private final JButton turnLeftButton = new JButton("Turn Left");
    private final JButton turnRightButton = new JButton("Turn Right");

    private final VehicleGroup group;
    private final GasController gasController;
    private final int width;

    public VehicleController(VehicleGroup group, GasController gasController, int width){
        this.group = group;
        this.gasController = gasController;
        this.width = width;
    }

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
        this.setPreferredSize(new Dimension((width/2)+4, 200));
        this.setBackground(Color.CYAN);

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
