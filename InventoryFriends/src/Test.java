import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


class MyFrame6 extends JFrame {

    public MyFrame6() {

        setSize(400, 100);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("MyFrameTest4");

        JPanel j = new JPanel();

        ArrayList<JButton> btnList = new ArrayList<>();

        JButton btn1 = new JButton("1");

        JButton btn2 = new JButton("2");

        j.add(btn1);
        btnList.add(btn1);
        j.add(btn2);
        btnList.add(btn2);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(btnList.size());
                j.remove(btn1);
                btnList.remove(0);
                j.validate();
                j.repaint();
                System.out.println(btnList.size());
            }
        });

        add(j);

        setVisible(true);


    }

}



public class Test {

    public static void main(String[] args) {

        MyFrame6 f = new MyFrame6();

    }



}