package main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class AlarmPanel extends JPanel {
    JLabel jlX;

    public AlarmPanel(){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(382, 60));
        setMaximumSize(new Dimension(382, 60));
        setMinimumSize(new Dimension(382, 60));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

//        JPanel topX = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        jlX = new JLabel("X");
//        jlX.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                removeAll();
//                repaint();
//            }
//        });
        top.add(jlX, BorderLayout.EAST);
        top.add(new JLabel(LocalDate.now().toString()), BorderLayout.WEST);
        add(top, BorderLayout.NORTH);

        JLabel label = new JLabel("내용");
        add(label, BorderLayout.CENTER);
    }

    public void setMouseListener(MouseAdapter mouseAdapter){
        jlX.addMouseListener(mouseAdapter);
    }

}
