package src;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final JButton gasButton = new JButton("Gas");
    private final JButton brakeButton = new JButton("Brake");
    private final JButton turboOnButton = new JButton("Saab Turbo on");
    private final JButton turboOffButton = new JButton("Saab Turbo off");
    private final JButton liftBedButton = new JButton("Scania Lift Bed");
    private final JButton lowerBedButton = new JButton("Lower Lift Bed");

    private final JButton turnLeftButton = new JButton("Turn Left");
    private final JButton turnRightButton = new JButton("Turn Right");

    public void initComponents(VehicleController vehicleC, int x, int gasAmount){
        this.setLayout(new GridLayout(2,4));
        this.add(gasButton, 0);
        this.add(turboOnButton, 1);
        this.add(liftBedButton, 2);
        this.add(brakeButton, 3);
        this.add(turboOffButton, 4);
        this.add(lowerBedButton, 5);
        this.add(turnLeftButton, 6);
        this.add(turnRightButton, 7);
        this.setPreferredSize(new Dimension((x/2)+4, 200));
        this.setBackground(Color.CYAN);

        gasButton.addActionListener(e -> vehicleC.gas(gasAmount));
        brakeButton.addActionListener(e -> vehicleC.brake(gasAmount));
        turboOnButton.addActionListener(e -> vehicleC.turboOn());
        turboOffButton.addActionListener(e -> vehicleC.turboOff());
        liftBedButton.addActionListener(e -> vehicleC.liftBed(gasAmount));
        lowerBedButton.addActionListener(e -> vehicleC.lowerBed(gasAmount));
        turnLeftButton.addActionListener(e -> vehicleC.turnLeft());
        turnRightButton.addActionListener(e -> vehicleC.turnRight());
    }
}
