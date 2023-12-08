package src.view;

import src.controller.GasController;
import src.controller.VehicleController;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the window that contains the controllers and the game DrawPanel.
 */
public class VehicleView extends JFrame {

    private final DrawPanel drawPanel;
    private final VehicleController vehicleController;

    /**
     * Creates a new vehicle view.
     * @param vehicleController Controller to add to this view.
     * @param width Width of the window.
     * @param height Height of the window.
     * @param worldWidth Width of the world
     * @param worldHeight Height of the world.
     */
    public VehicleView(VehicleController vehicleController, int width, int height, int worldWidth, int worldHeight){
        this.vehicleController = vehicleController;
        this.drawPanel = new DrawPanel(worldWidth, worldHeight);

        setTitle("VehicleSim v1.0");
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    /**
     * Initialises the components used in this view.
     */
    public void initComponents(){
        this.add(drawPanel);

        GasController gasController = vehicleController.getGasController();
        this.add(gasController);
        gasController.initComponents();

        this.add(vehicleController);
        vehicleController.initComponents();

        initWindow();
    }


    /**
     * Initialises the window, sets size and sets it visible.
     */
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


    /**
     * Loads image by file name.
     * @param name File name to load as image.
     */
    public void loadImage(String name){
        this.drawPanel.loadImage(name);
    }

    /**
     * Creates a new render object by image name and position.
     * @param imageName Image name (must be loaded with loadImage)
     * @param position Position to add render object at.
     * @return Created render object.
     */
    public RenderObject addRenderObject(String imageName, Point position){
        return this.drawPanel.addRenderObject(imageName, position);
    }

    /**
     * Removes a specific render object and repaints the frame.
     * @param object Render object to remove.
     */
    public void removeRenderObject(RenderObject object){
        this.drawPanel.removeRenderObject(object);
        this.repaint();
    }

}
