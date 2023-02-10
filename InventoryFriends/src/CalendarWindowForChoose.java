import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
/*
jdatepicker
출처 https://mvnrepository.com/artifact/net.sourceforge.jdatepicker/jdatepicker/1.3.2
*/
import net.sourceforge.jdatepicker.impl.*;

public class CalendarWindowForChoose extends JWindow {
    JDatePanelImpl datePanel;
    //    JDatePickerImpl datePicker;
    static Object selectDate;
    JTextField textField;


    public CalendarWindowForChoose() {
        setSize(200, 180);
        UtilDateModel model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
//        datePicker = new JDatePickerImpl(datePanel);
        add(datePanel);
        datePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDate = datePanel.getModel().getValue();
                if (selectDate == null){
                    setVisible(false);
                    textField.setText(null);
                }
                else {
                    setVisible(false);
                    textField.setText(dateToString(selectDate));
                }
            }
        });
    }

    public Object getDate(){
        return datePanel.getModel().getValue();
    }

    public String dateToString(Object date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    public void setTextField(JTextField jTextField){
        textField = jTextField;
    }

    public void main(String[] args) {
        new CalendarWindowForChoose();
    }
}