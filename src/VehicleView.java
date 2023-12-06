package src;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your vehicle simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class VehicleView extends JFrame{
    final DrawPanel drawPanel;

    private final JButton startButton = new JButton("Start all vehicles");
    private final JButton stopButton = new JButton("Stop all vehicles");

    private final int x;
    private final int y;

    // Constructor
    public VehicleView(String framename, VehicleController vehicleController, int x, int y){
        this.x = x;
        this.y = y;
        drawPanel = new DrawPanel(x, x-240);
        initComponents(framename);
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(x, y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        gasPanel.initComponents();
        this.add(gasPanel);

        vehicleController.initComponents();
        //this.add(vehicleController);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(x / 5 - 15, 200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(x / 5 - 15, 200));
        this.add(stopButton);

        // This actionListener is for the gas button only

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

    public int getWidth(){
        return this.drawPanel.getWidth();
    }

    public int getHeight(){
        return this.drawPanel.getHeight();
    }

    public RenderObject addRenderObject(String imageName, Point position){
        return this.drawPanel.addRenderObject(imageName, position);
    }

    public void repaint(){
        this.drawPanel.repaint();
    }
}
