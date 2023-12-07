package src;

import javax.swing.*;
import java.awt.*;

public class VehicleView extends JFrame {

    private final DrawPanel drawPanel;
    private final VehicleController vehicleController;

    public VehicleView(VehicleController vehicleController, int width, int height){
        this.vehicleController = vehicleController;
        this.drawPanel = new DrawPanel(width, width-240);

        setTitle("VehicleSim v1.0");
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    public void initComponents(){
        this.add(drawPanel);

        GasController gasController = vehicleController.getGasController();
        this.add(gasController);
        gasController.initComponents();

        this.add(vehicleController);
        vehicleController.initComponents();

        initWindow();
    }


    private void initWindow() {
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void loadImage(String name){
        this.drawPanel.loadImage(name);
    }

    public RenderObject addRenderObject(String imageName, Point position){
        return this.drawPanel.addRenderObject(imageName, position);
    }

}
