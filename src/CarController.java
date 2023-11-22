package src;

import src.util.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    // A map of cars mapped to their renderObjects
    private final Map<Car, RenderObject> renderObjects = new HashMap<>();

    private static final String[] SUPPORTED_MODELS = new String[]{"Volvo240", "Saab95", "Scania_1337"};

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Load images here
        for(String model : SUPPORTED_MODELS) {
            cc.frame.drawPanel.loadImage(model);
        }

        Car volvo = new Volvo240();
        volvo.setPosition(new Vector(0, 0));
        cc.addCar(volvo);

        Car saab = new Saab95();
        saab.setPosition(new Vector(0, 100));
        cc.addCar(saab);

        Truck scania = new Scania(new LoadingPlatform());
        scania.setPosition(new Vector(0, 200));
        cc.addCar(scania);

        // Start the timer
        cc.timer.start();
    }

    public void addCar(Car car){
        this.cars.add(car);
        RenderObject object = this.frame.drawPanel.addRenderObject(car.getModelName(),
                new Point((int) Math.round(car.getPosition().getX()), (int) Math.round(car.getPosition().getY())));
        this.renderObjects.put(car, object);
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {

                // check car not outside bounds
                RenderObject renderObject = renderObjects.get(car);
                if(renderObject == null)
                    continue;


                double x = car.getPosition().getX();
                double y = car.getPosition().getY();
                int errorFixHeight = 55;
                int errorFixWidth = 100;
                boolean outsideX = x < 0 || x > frame.drawPanel.getWidth() - errorFixWidth;
                boolean outsideY =  y < 0 || y > frame.drawPanel.getHeight() - errorFixHeight;
                // Make car turn if it is outside bounds
                // The true panel size is not used, hence the errorFix integer. TODO: Search for better solution
                if(outsideX ||
                       outsideY) {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                    x = Math.min(Math.max(0,x),frame.drawPanel.getWidth()-errorFixWidth);
                    y = Math.min(Math.max(0,y),frame.drawPanel.getHeight()-errorFixHeight);


                }

                car.setPosition(new Vector(x, y));

                // Update render object position
                Point point = new Point((int) Math.round(car.getPosition().getX()),
                        (int) Math.round(car.getPosition().getY()));
                renderObject.setPosition(point);

                car.move();
            }

            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Car car: cars){
            car.brake(brake);
        }
    }

    void liftBed(int amount){
        for(Car car: cars){
            if(car instanceof Scania){
                ((Scania)car).decreaseLoadingPlatformAngle(amount);
            }
        }
    }

    void lowerBed(int amount){
        for(Car car: cars){
            if(car instanceof Scania){
                ((Scania)car).increaseLoadingPlatformAngle(amount);
            }
        }
    }

    void turboOn(){
        for (Car car: cars){
            if(car instanceof Saab95){
                ((Saab95)car).setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Car car: cars){
            if(car instanceof Saab95){
                ((Saab95)car).setTurboOff();
            }
        }
    }

    void stop(){
        for(Car car: cars){
            car.stopEngine();
        }
    }

    void start(){
        for(Car car: cars){
            car.startEngine();
        }
    }

    private Car getRandomCar(){
        return cars.get(new Random().nextInt(cars.size()));
    }

    void turnRight(){
        getRandomCar().turnRight();
    }

    void turnLeft(){
        getRandomCar().turnLeft();
    }

}
