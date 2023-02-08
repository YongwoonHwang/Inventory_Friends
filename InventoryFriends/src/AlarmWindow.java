import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlarmWindow extends JWindow {
    JSplitPane alarmWinSplit;
    JScrollPane alarmWinScroll;
    JPanel content, top;
    GroupLayout gLayout;
    GroupLayout.ParallelGroup hGroup;
    GroupLayout.SequentialGroup vGroup;
    MouseAdapter btnX;
    public AlarmWindow(){
        setSize(200, 300);
        alarmWinSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        alarmWinSplit.setDividerSize(0);
        alarmWinSplit.setEnabled(false);
        content = new JPanel();
        alarmWinScroll = new JScrollPane(alarmWinSplit, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        alarmWinScroll.setBorder(BorderFactory.createEmptyBorder());
        top = new JPanel();
        alarmWinSplit.setTopComponent(top);
        alarmWinSplit.setBottomComponent(content);
        setContentPane(alarmWinScroll);
        setSize(400, 300);
        gLayout = new GroupLayout(content);
        content.setLayout(gLayout);
        hGroup = gLayout.createParallelGroup();
        gLayout.setHorizontalGroup(hGroup);
        vGroup = gLayout.createSequentialGroup();
        gLayout.setVerticalGroup(vGroup);
        for(int i = 0; i<8; i++){
            addAlarm();
        }

        // getMaximumDividerLocation()의 값을 제대로 불러오기 위해 창을 한번 띄워줌
        setVisible(true);
        setVisible(false);
        setAlarmLocate();
    }
    public void setAlarmLocate(){
        alarmWinSplit.setDividerLocation(alarmWinSplit.getMaximumDividerLocation());
        alarmWinScroll.getVerticalScrollBar().setValue(alarmWinScroll.getVerticalScrollBar().getMaximum());
    }

    public void addAlarm(){
        AlarmPanel panel = new AlarmPanel();
        btnX = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                content.remove(panel);
                alarmWinSplit.setDividerLocation(alarmWinSplit.getMaximumDividerLocation());
                alarmWinScroll.revalidate();
                alarmWinScroll.repaint();
            }
        };
        panel.setMouseListener(btnX);
        hGroup.addComponent(panel);
        vGroup.addComponent(panel, GroupLayout.PREFERRED_SIZE,
                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
        alarmWinScroll.revalidate();
        alarmWinScroll.repaint();
    }

    public static void main(String[] args) {
        AlarmWindow alarmWindow = new AlarmWindow();
        alarmWindow.setVisible(true);

    }
}
