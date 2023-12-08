package src.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private final Map<String, BufferedImage> loadedImages = new HashMap<>();
    private final List<RenderObject> renderObjects = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    // Does not return the true panel width
    public int getWidth(){
        return this.getSize().width;

    }

    // Does not return the true panel height
    public int getHeight(){
        return this.getSize().height;
    }

    /**
     * Loads an image by file name.
     * @param name File name to load.
     */
    public void loadImage(String name){
        try {
            InputStream stream = DrawPanel.class.getResourceAsStream("../../pics/" + name + ".jpg");
            if(stream == null)
                return;

            this.loadedImages.put(name, ImageIO.read(stream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a render object to the draw panel.
     * @param imageName Image of the render object (must be loaded with loadImage)
     * @param position Position to add render object at.
     * @return Created render object.
     */
    public RenderObject addRenderObject(String imageName, Point position){
        RenderObject newRenderObject = new RenderObject(position, loadedImages.get(imageName));
        renderObjects.add(newRenderObject);
        return newRenderObject;
    }

    /**
     * Removes a specific render object from the panel.
     * @param object Render object to remove.
     */
    public void removeRenderObject(RenderObject object){
        renderObjects.remove(object);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(RenderObject object : this.renderObjects){
            g.drawImage(object.getImage(), object.getPosition().x, object.getPosition().y, null);
        }
    }
}
