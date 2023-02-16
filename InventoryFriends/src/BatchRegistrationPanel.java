import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.*;
import javax.swing.table.*;

public class BatchRegistrationPanel extends JPanel {

    JButton btnSubmit2, btnSubmit4;
    JLabel text10, text11, text12;
    JTextField jtfCSVpath;
    ImageIcon imgAdd1, imgAdd2, imgFile1, imgFile2;
    JFileChooser imgfilechooser;

    public BatchRegistrationPanel() {

        imgAdd1 = new ImageIcon("./img/img_Add1.jpg");
        imgAdd2 = new ImageIcon("./img/img_Add2.jpg");
        imgFile1 = new ImageIcon("./img/img_File1.jpg");
        imgFile2 = new ImageIcon("./img/img_File2.jpg");

        imgfilechooser = new JFileChooser();
//        jlfilechooser = new JLabel();
        imgfilechooser.setAcceptAllFileFilterUsed(false);
        imgfilechooser.setFileFilter(new FileNameExtensionFilter("Excel File(*.xls;*.xlsx;*.csv)", "xls", "xlsx", "csv"));
        imgfilechooser.setMultiSelectionEnabled(false);

        setLayout(null);

        jtfCSVpath = new JTextField();
        jtfCSVpath.setBounds(140, 100, 200, 30);
        jtfCSVpath.setEditable(false);
        add(jtfCSVpath);


        text10 = new JLabel("일괄 등록 : ");
        text10.setBounds(20, 100, 120, 30);
        add(text10);

        text11 = new JLabel("* excel 혹은 csv 파일만 지원합니다. 파일 업로드 전에 지원 양식을 다시 한 번 확인해주시기 바랍니다.");
        text11.setBounds(20, 140, 600, 30);
        add(text11);

        text12 = new JLabel("자세히 보기");
        text12.setBounds(625, 140, 150, 30);
        add(text12);


        btnSubmit2 = new JButton(imgFile1);
        btnSubmit2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit2.setRolloverIcon(imgFile2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit2.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit2.setFocusPainted(false);
        btnSubmit2.setContentAreaFilled(false);
        btnSubmit2.setPreferredSize(new Dimension(48, 24));
        btnSubmit2.setBounds(340, 100, 90, 30);
        btnSubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //파일오픈 다이얼로그 를 띄움
                int result = imgfilechooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    //선택한 파일의 경로 반환
                    File selectedFile = imgfilechooser.getSelectedFile();

                    //경로 출력
                    jtfCSVpath.setText(selectedFile.getPath());
                } else{
                    jtfCSVpath.setText("");
                }
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



