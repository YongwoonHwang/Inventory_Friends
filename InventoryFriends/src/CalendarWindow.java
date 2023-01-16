import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
jdatepicker
출처 https://mvnrepository.com/artifact/net.sourceforge.jdatepicker/jdatepicker/1.3.2
*/
import net.sourceforge.jdatepicker.impl.*;

public class CalendarWindow extends JWindow {
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;
    static MemoWindow memoWindow;
    static Object selectDate;


    public CalendarWindow() {
        setSize(200, 180);
        UtilDateModel model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        add(datePanel);
        datePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDate = datePanel.getModel().getValue();
//                System.out.println(selectDate.getClass().getName());
                setVisible(false);
                memoWindow.jtaMemoWin.setText("");
                memoWindow.setDate(selectDate);
                memoWindow.btnDelete.setEnabled(false);
                memoWindow.btnSave.setText("저장");
                memoWindow.setVisible(true);
            }
        });
    }

    public void setMemoTab(MemoTab mt){
        memoWindow.setMemoTab(mt);
    }
    public void setMemoWindow(MemoWindow mw){ memoWindow = mw; }
    public Object getDate(){
        return datePanel.getModel().getValue();
    }
    public static void main(String[] args) {
        new CalendarWindow();
    }
}