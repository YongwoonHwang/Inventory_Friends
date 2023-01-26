import javax.swing.*;
import net.sourceforge.jdatepicker.impl.*;

class Test4 extends JFrame{
    JDatePickerImpl datePicker;
    JDatePanelImpl datePanel;
    public Test4(){
        UtilDateModel model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        add(datePicker);
        setSize(500, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Test4();
    }
}//class ends