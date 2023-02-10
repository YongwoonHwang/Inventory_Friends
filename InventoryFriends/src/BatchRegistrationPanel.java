import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;

public class BatchRegistrationPanel extends JPanel {

    JButton btnSubmit2, btnSubmit4;
    JLabel text10, text11, text12;
    JTextField jTextField10;
    ImageIcon imgAdd1, imgAdd2;

    public BatchRegistrationPanel() {

        imgAdd1 = new ImageIcon("./img/img_Add1.jpg");
        imgAdd2 = new ImageIcon("./img/img_Add2.jpg");

        setLayout(null);

        jTextField10 = new JTextField();
        jTextField10.setBounds(140, 100, 200, 30);
        jTextField10.setEditable(false);
        add(jTextField10);


        text10 = new JLabel("일괄 등록 : ");
        text10.setBounds(20, 100, 120, 30);
        add(text10);

        text11 = new JLabel("* excel 혹은 csv 파일만 지원합니다. 파일 업로드 전에 지원 양식을 다시 한 번 확인해주시기 바랍니다.");
        text11.setBounds(20, 140, 600, 30);
        add(text11);

        text12 = new JLabel("자세히 보기");
        text12.setBounds(625, 140, 150, 30);
        add(text12);


        btnSubmit2 = new JButton("파일첨부");
        btnSubmit2.setBounds(340, 100, 90, 30);
        btnSubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        add(btnSubmit2);

        btnSubmit4 = new JButton(imgAdd1);
        btnSubmit4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit4.setRolloverIcon(imgAdd2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit4.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit4.setFocusPainted(false);
        btnSubmit4.setContentAreaFilled(false);
        btnSubmit4.setPreferredSize(new Dimension(48, 24));
        btnSubmit4.setBounds(900, 280, 75, 25);
        btnSubmit4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(btnSubmit4);
    }
}



