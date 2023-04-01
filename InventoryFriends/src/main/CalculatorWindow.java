package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorWindow extends JFrame {

    HintTextField textField;
    CalculatorWindow(){
        Calculator calc = new Calculator();
        setUndecorated(true);
        add(calc.getView());
        setSize(250, 300);
        calc.setBtnSubmitAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(calc.getResult());
                textField.setForeground(Color.BLACK);
                textField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            }
        });
        setAlwaysOnTop(true);
//        setVisible(true);
    }

    protected void setTextField(HintTextField htf){
        textField = htf;
    }

}
