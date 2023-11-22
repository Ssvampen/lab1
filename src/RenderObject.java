package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderObject {

    private Point position;
    private final BufferedImage image;

    public RenderObject(Point point, BufferedImage image){
        this.position = point;
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

}
