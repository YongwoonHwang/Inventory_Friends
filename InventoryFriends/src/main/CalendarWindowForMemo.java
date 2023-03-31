package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
jdatepicker
출처 https://mvnrepository.com/artifact/net.sourceforge.jdatepicker/jdatepicker/1.3.2
*/
import net.sourceforge.jdatepicker.impl.*;

public class CalendarWindowForMemo extends JWindow {
    JDatePanelImpl datePanel;
    //    JDatePickerImpl datePicker;
    static MemoWindow memoWindow;
    static Object selectDate;


    public CalendarWindowForMemo() {
        setSize(200, 180);
        UtilDateModel model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
//        datePicker = new JDatePickerImpl(datePanel);
        add(datePanel);
        setAlwaysOnTop(true);
        datePanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDate = datePanel.getModel().getValue();
                if (selectDate == null){
                    setVisible(false);
                }
                else {
                    setVisible(false);
                    memoWindow.jtaMemoWin.setText("");
                    memoWindow.setDate(selectDate);
                    memoWindow.btnDelete.setEnabled(false);
                    memoWindow.btnSave.setText("저장");
                    memoWindow.setVisible(true);
                }
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
}