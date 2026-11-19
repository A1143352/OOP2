import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat; 
import javax.swing.*;

public class Exercise2 {
    static JFrame frm = new JFrame("單位換算器");
    static JPanel centerPanel = new JPanel(new GridLayout(2, 2, 15, 15));

    static String[] types = {"長度", "重量", "溫度"};
    static String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    static String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    static String[] tempUnits = {"攝氏", "華氏", "克氏"};

    static JComboBox<String> typeBox = new JComboBox<>(types);
    static JTextField inputField = new JTextField();
    static JComboBox<String> fromBox = new JComboBox<>(lengthUnits);
    static JTextField outputField = new JTextField();
    static JComboBox<String> toBox = new JComboBox<>(lengthUnits);
    static JButton calcBtn = new JButton("換算");

    public static void main(String[] args) {
        frm.setSize(480, 280);
        frm.setLayout(new BorderLayout(10, 10));
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputField.setEditable(false);

        
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Font uiFont = new Font("Dialog", Font.PLAIN, 16);
        typeBox.setFont(uiFont);
        inputField.setFont(uiFont);
        fromBox.setFont(uiFont);
        outputField.setFont(uiFont);
        toBox.setFont(uiFont);
        calcBtn.setFont(uiFont);

        centerPanel.add(inputField);
        centerPanel.add(fromBox);
        centerPanel.add(outputField);
        centerPanel.add(toBox);

        frm.add(typeBox, BorderLayout.NORTH);
        frm.add(centerPanel, BorderLayout.CENTER);
        frm.add(calcBtn, BorderLayout.SOUTH);

        typeBox.addActionListener(new TypeListener());
        calcBtn.addActionListener(new CalcListener());

        frm.setVisible(true);
    }

    static class TypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String selectedType = (String) typeBox.getSelectedItem();
            String[] currentUnits;

            if (selectedType.equals("長度")) {
                currentUnits = lengthUnits;
            } else if (selectedType.equals("重量")) {
                currentUnits = weightUnits;
            } else {
                currentUnits = tempUnits;
            }

            fromBox.removeAllItems();
            toBox.removeAllItems();
            for (String u : currentUnits) {
                fromBox.addItem(u);
                toBox.addItem(u);
            }
            outputField.setText("");
        }
    }

    static class CalcListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double val = Double.parseDouble(inputField.getText());
                String type = (String) typeBox.getSelectedItem();
                String from = (String) fromBox.getSelectedItem();
                String to = (String) toBox.getSelectedItem();
                double result = 0;

                if (type.equals("長度")) {
                    if (val < 0) {
                        outputField.setText("長度不可為負數");
                        return;
                    }

                    double meters = val;
                    if (from.equals("公分")) meters = val * 0.01;
                    else if (from.equals("英吋")) meters = val * 0.0254;
                    else if (from.equals("英尺")) meters = val * 0.3048;

                    if (to.equals("公尺")) result = meters;
                    else if (to.equals("公分")) result = meters / 0.01;
                    else if (to.equals("英吋")) result = meters / 0.0254;
                    else if (to.equals("英尺")) result = meters / 0.3048;

                } else if (type.equals("重量")) {
                    
                    if (val < 0) {
                        outputField.setText("重量不可為負數");
                        return;
                    }

                    double grams = val;
                    if (from.equals("公斤")) grams = val * 1000;
                    else if (from.equals("磅")) grams = val * 453.59237;
                    else if (from.equals("盎司")) grams = val * 28.34952;

                    if (to.equals("公克")) result = grams;
                    else if (to.equals("公斤")) result = grams / 1000;
                    else if (to.equals("磅")) result = grams / 453.59237;
                    else if (to.equals("盎司")) result = grams / 28.34952;

                } else if (type.equals("溫度")) {
                    double c = val;
                    if (from.equals("華氏")) c = (val - 32) * 5.0 / 9.0;
                    else if (from.equals("克氏")) c = val - 273.15;

                   
                    if (c < -273.15 - 1e-6) {
                        outputField.setText("不可低於絕對零度");
                        return;
                    }

                    if (to.equals("攝氏")) result = c;
                    else if (to.equals("華氏")) result = c * 9.0 / 5.0 + 32;
                    else if (to.equals("克氏")) result = c + 273.15;
                }

                DecimalFormat df = new DecimalFormat("#.####");
                outputField.setText(df.format(result));

            } catch (NumberFormatException ex) {
                outputField.setText("請輸入正確數字");
            }
        }
    }
}