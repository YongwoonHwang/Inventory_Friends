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
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal1, imgCal2;
    JTextField jTextField10;

    public BatchRegistrationPanel() {
        imgSubmit = new ImageIcon("./img/img_submit.jpg");
        imgAttach1 = new ImageIcon("./img/img_attach.jpg");
        imgAttach2 = new ImageIcon("./img/img_attach.jpg");
        imgCalc = new ImageIcon("./img/img_Calc.jpg");
        imgCal1 = new ImageIcon("./img/img_Cal.jpg");
        imgCal2 = new ImageIcon("./img/img_Cal.jpg");


        this.setLayout(null);


        jTextField10 = new JTextField();
        jTextField10.setBounds(140, 100, 200, 30);
        jTextField10.setEditable(false);
        this.add(jTextField10);


        text10 = new JLabel("일괄 등록 : ");
        text10.setBounds(20, 100, 120, 30);
        this.add(text10);

        text11 = new JLabel("* excel 혹은 csv 파일만 지원합니다. 파일 업로드 전에 지원 양식을 다시 한 번 확인해주시기 바랍니다.");
        text11.setBounds(20, 140, 570, 30);
        this.add(text11);

        text12 = new JLabel("자세히 보기");
        text12.setBounds(600, 140, 150, 30);
        this.add(text12);


        btnSubmit2 = new JButton("파일첨부");
        btnSubmit2.setBounds(340, 100, 90, 30);
        btnSubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(btnSubmit2);


        btnSubmit4 = new JButton("등록");
        btnSubmit4.setBounds(900, 280, 75, 25);
        btnSubmit4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(btnSubmit4);
    }



    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    // 테이블 너비를 내용에 맞춰주는 함수
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}



