package src.controller;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the controller for the gas amount in our MVC.
 * Listens for changes to the gas spinner and updates the gas amount.
 */
public class GasController extends JPanel {
    private final JLabel gasLabel = new JLabel("Amount of gas");
    private JSpinner gasSpinner;
    int gasAmount = 0;

    public void initComponents(){
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());

        this.setLayout(new BorderLayout());
        this.add(gasLabel, BorderLayout.PAGE_START);
        this.add(gasSpinner, BorderLayout.PAGE_END);
    }

    public int getGasAmount(){
        return gasAmount;
    }
}
