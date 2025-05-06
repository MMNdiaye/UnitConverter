package sn.ndiaye.uconverter.views;

import sn.ndiaye.uconverter.logic.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ConverterGUI extends JFrame {
    private Manager manager;
    private JComboBox<String> measureSelect;
    // variable components
    private JTextField toConvertInput;
    private JComboBox<String> unit1Select;
    private JComboBox<String> unit2Select;
    private JTextField convertedOutput;

    //label components
    private JLabel measureLabel;
    private JLabel toConvertLabel;
    private JLabel unit1Label;
    private JLabel unit2Label;
    private JLabel convertedLabel;

    private JButton convertButton;

    public ConverterGUI(Manager manager) {
        this.manager = manager;
        setTitle("Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        activateListeners();
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        mainPanel.add(new TopPanel());
        mainPanel.add(new BottomPanel());
        mainPanel.add(convertButton);
        add(mainPanel);
        pack();
        setVisible(true);
    }

    private void initComponents() {
        // variable components
        measureSelect = new JComboBox<>();
        for (String measure : manager.getMeasures())
            measureSelect.addItem(measure);
        toConvertInput = new JTextField();
        unit1Select = new JComboBox<>();
        unit2Select = new JComboBox<>();
        fillVariableComponents();
        convertedOutput = new JTextField();

        //label components
        measureLabel = new JLabel("Measure:");
        toConvertLabel = new JLabel("Value:");
        unit1Label = new JLabel("Unit:");
        unit2Label = new JLabel("Unit:");
        convertedLabel = new JLabel("Result:");

        convertButton = new JButton("Convert");
    }

    private void fillVariableComponents() {
        for (String unit : manager.getConverterUnits()) {
            unit1Select.addItem(unit);
            unit2Select.addItem(unit);
        }
        unit1Select.setSelectedIndex(0);
        unit2Select.setSelectedIndex(1);
    }

    private void resetVariableComponents() {
        unit1Select.removeAllItems();
        unit2Select.removeAllItems();
        fillVariableComponents();
    }

    private void activateListeners() {
        measureSelect.addActionListener((ActionEvent e) -> {
            manager.setConverter(measureSelect.getItemAt(
                    measureSelect.getSelectedIndex()));
            resetVariableComponents();
        });

        convertButton.addActionListener((ActionEvent e) -> {
            String measure = measureSelect.getItemAt(measureSelect.getSelectedIndex());
            String unit1 = unit1Select.getItemAt(unit1Select.getSelectedIndex());
            String unit2 = unit2Select.getItemAt(unit2Select.getSelectedIndex());
            String value = (toConvertInput != null) ? toConvertInput.getText() : "0";
            convertedOutput.setText(manager.process(measure, unit1, unit2, value));
        });
    }


    class TopPanel extends JPanel {
        TopPanel() {
            setLayout(new GridLayout(1, 2));
            add(measureLabel);
            add(measureSelect);
        }
    }

    class BottomPanel extends JPanel {
        BottomPanel() {
            setLayout(new GridLayout(2, 6));
            // grid management
            JComponent[][] components = new JComponent[2][6];
            // to convert part:
            components[1][0] = toConvertLabel;
            components[1][1] = toConvertInput;
            components[0][2] = unit1Label;
            components[1][2] = unit1Select;
           // converted part:
            components[1][3] = convertedLabel;
            components[1][4] = convertedOutput;
            components[0][5] = unit2Label;
            components[1][5] = unit2Select;

            // adding
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 6 ; j++) {
                    if (components[i][j] == null)
                        add(new JPanel());
                    else
                        add(components[i][j]);
                }
        }
    }


}
