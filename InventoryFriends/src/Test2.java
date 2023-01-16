import java.awt.BorderLayout;

import java.awt.FlowLayout;




import javax.swing.JCheckBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;




public class Test2 extends JFrame{

    JLabel label2[];

    JTextField jtf2[];

    JCheckBox check1[];

    private void init() {

        label2 = new JLabel[8];

        jtf2 = new JTextField[8];

        check1 = new JCheckBox[8];

    }

    public Test2() {

        init();

        this.setSize(500,500);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        create();

        this.setVisible(true);

    }

    private void create() {

        JPanel p1 = new JPanel();

        p1.setSize(500,500);

        p1.setLayout(new FlowLayout());

        for(int i=0; i<8; i++) {

            label2[i] = new JLabel("label"+(i+1));

            p1.add(label2[i]);

            jtf2[i] = new JTextField(7);

            p1.add(jtf2[i]);

            check1[i] = new JCheckBox();

            p1.add(check1[i]);

        }

        this.add(p1);

    }




    public static void main(String[] args) {

// TODO Auto-generated method stub

        new Test2();

    }




}