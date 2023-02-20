import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ModifyPanel extends JPanel{
    JPanel jpCenter;
    JTabbedPane jtpSubTab;
    JButton btnSubmit1, btnSubmit3, btnCal1, btnCal2, btnCalc;
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage,
            jlCalendar2, jlCalendar3;
    HintTextField htfItemCode, htfItemName, htfItemQuantity, htfItemLocation;
    JComboBox jcbCategory;
    JTabbedPane jtpMainTab;
    String market[] = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    CheckableComboBox chkcomMarket;
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal, imgAdd1, imgAdd2, imgFile1, imgFile2;
    JTextField jtfLastReceivingDate, jtfNextReceivingDate, jtfImg;
    JFileChooser imgfilechooser;
    CalendarWindowForChoose winCalendar1, winCalendar2;
    CalculatorWindow winCalc;

    String dbName = "ifdb";
    String dbTableName = "ItemList";
    static String error;
    public ModifyPanel() {
        setLayout(new BorderLayout());
        jpCenter = new JPanel();

        imgSubmit = new ImageIcon("./img/img_submit.jpg");
        imgAttach1 = new ImageIcon("./img/img_attach.jpg");
        imgAttach2 = new ImageIcon("./img/img_attach.jpg");
        imgCalc = new ImageIcon("./img/img_Calc.jpg");
        imgCal = new ImageIcon("./img/img_Cal.jpg");

        imgAdd1 = new ImageIcon("./img/img_Change1.jpg");
        imgAdd2 = new ImageIcon("./img/img_Change2.jpg");
        imgFile1 = new ImageIcon("./img/img_File1.jpg");
        imgFile2 = new ImageIcon("./img/img_File2.jpg");

        winCalendar1 = new CalendarWindowForChoose();
        winCalendar2 = new CalendarWindowForChoose();

        winCalc = new CalculatorWindow();

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
//        gbc.insets = new Insets(0, 20, 0, 0);
        jpCenter.setLayout(gb);

        imgfilechooser = new JFileChooser();
        imgfilechooser.setAcceptAllFileFilterUsed(false);
        imgfilechooser.setFileFilter(new FileNameExtensionFilter("Image File(*.jpg;*.jpeg;*.png)", "jpg", "jpeg", "png"));
        imgfilechooser.setMultiSelectionEnabled(false); // 다중 선택 불가

        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
        FlowLayout fr = new FlowLayout(FlowLayout.RIGHT);


        JPanel panel1_1 = new JPanel(fr);
        JPanel panel1_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 0, 0);
        jlCategory = new JLabel("카테고리 : ");
        panel1_1.add(jlCategory);
        jcbCategory = new JComboBox();
        jcbCategory.setEditable(true);
        jcbCategory.setPreferredSize(new Dimension(443, 21));
//        addItemsAtComboBox();
        panel1_2.add(jcbCategory);
        jpCenter.add(panel1_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel1_2, gbc);


        JPanel panel2_1 = new JPanel(fr);
        JPanel panel2_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        jlItemCode = new JLabel("코드 : ");
        panel2_1.add(jlItemCode);
        htfItemCode = new HintTextField("코드는 필수 입력 항목입니다. (ex. Cover-A-01-Black)");
        htfItemCode.setColumns(40);
        panel2_2.add(htfItemCode);
        jpCenter.add(panel2_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel2_2, gbc);

        JPanel panel3_1 = new JPanel(fr);
        JPanel panel3_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        jlItemName = new JLabel("품명 : ");
        panel3_1.add(jlItemName);
        htfItemName = new HintTextField("품명은 필수 입력 항목입니다. (ex. 의자 커버 원형 플로럴 검정)");
        htfItemName.setColumns(40);
        panel3_2.add(htfItemName);
        jpCenter.add(panel3_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel3_2, gbc);

        JPanel panel4_1 = new JPanel(fr);
        JPanel panel4_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        jlItemQuantity = new JLabel("수량 : ");
        panel4_1.add(jlItemQuantity);
        htfItemQuantity = new HintTextField("수량은 필수 입력 항목입니다.(ex. 10EA)");
        htfItemQuantity.setColumns(40);
        panel4_2.add(htfItemQuantity);
        btnCalc = new JButton(imgCalc);
        btnCalc.setFocusPainted(false);
        btnCalc.setPreferredSize(new Dimension(22, 24));
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(winCalc.isVisible())
                    winCalc.setVisible(false);
                else{
                    winCalc.setLocation(btnCalc.getLocationOnScreen().x, btnCalc.getLocationOnScreen().y+24);
                    winCalc.setVisible(true);
                }
            }
        });
        panel4_2.add(btnCalc);
        jpCenter.add(panel4_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel4_2, gbc);

        JPanel panel5_1 = new JPanel(fr);
        JPanel panel5_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        jlMarket = new JLabel("마켓 선택 : ");
        panel5_1.add(jlMarket);
        chkcomMarket = new CheckableComboBox(market);
        chkcomMarket.setPreferredSize(new Dimension(443, 21));
        panel5_2.add(chkcomMarket);
        jpCenter.add(panel5_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel5_2, gbc);

        JPanel panel6_1 = new JPanel(fr);
        JPanel panel6_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        jlItemLocation = new JLabel("재고 위치 : ");
        panel6_1.add(jlItemLocation);
        htfItemLocation = new HintTextField("ex.Rack-01-A-05");
        htfItemLocation.setColumns(40);
        panel6_2.add(htfItemLocation);
        jpCenter.add(panel6_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel6_2, gbc);

        JPanel panel7_1 = new JPanel(fr);
        JPanel panel7_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        jlLastReceivingDate = new JLabel("최근 입고일 : ");
        panel7_1.add(jlLastReceivingDate);
        jtfLastReceivingDate = new JTextField();
        jtfLastReceivingDate.setEditable(false);
        jtfLastReceivingDate.setColumns(44);
        jtfLastReceivingDate.setPreferredSize(new Dimension(20, 20));
        panel7_2.add(jtfLastReceivingDate);
        btnCal1 = new JButton(imgCal);
        btnCal1.setFocusPainted(false);
        btnCal1.setPreferredSize(new Dimension(23, 24));
        btnCal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (winCalendar1.isVisible()){
                    winCalendar1.setVisible(false);
                }else{
                    winCalendar1.setLocation(btnCal1.getLocationOnScreen().x, btnCal1.getLocationOnScreen().y-180);
                    winCalendar1.setVisible(true);
                }
            }
        });
        panel7_2.add(btnCal1);
        jpCenter.add(panel7_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel7_2, gbc);

        JPanel panel8_1 = new JPanel(fr);
        JPanel panel8_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        jlNextReceivingDate = new JLabel("다음 입고 예정일 : ");
        panel8_1.add(jlNextReceivingDate);
        jtfNextReceivingDate = new JTextField();
        jtfNextReceivingDate.setEditable(false);
        jtfNextReceivingDate.setColumns(44);
        panel8_2.add(jtfNextReceivingDate);
        btnCal2 = new JButton(imgCal);
        btnCal2.setFocusPainted(false);
        btnCal2.setPreferredSize(new Dimension(23, 24));
        btnCal2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (winCalendar2.isVisible()){
                    winCalendar2.setVisible(false);
                }else{
                    winCalendar2.setLocation(btnCal2.getLocationOnScreen().x, btnCal2.getLocationOnScreen().y+24);
                    winCalendar2.setVisible(true);
                }
            }
        });
        panel8_2.add(btnCal2);
        jpCenter.add(panel8_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel8_2, gbc);

        JPanel panel9_1 = new JPanel(fr);
        JPanel panel9_2 = new JPanel(fl);
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridx = 0;
        gbc.gridy = 8;
        jlImage = new JLabel("이미지 : ");
        panel9_1.add(jlImage);
        jtfImg = new JTextField();
        jtfImg.setEditable(false);
        jtfImg.setColumns(25);
        panel9_2.add(jtfImg);
        btnSubmit1 = new JButton(imgFile1);
        btnSubmit1.setRolloverIcon(imgFile2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit1.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit1.setFocusPainted(false);
        btnSubmit1.setContentAreaFilled(false);
        btnSubmit1.setPreferredSize(new Dimension(70, 24)); // 버튼 크기 지정
        btnSubmit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //파일오픈 다이얼로그 를 띄움
                int result = imgfilechooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    //선택한 파일의 경로 반환
                    File selectedFile = imgfilechooser.getSelectedFile();

                    //경로 출력
                    jtfImg.setText(selectedFile.getPath());
                } else{
                    jtfImg.setText("");
                }
            }
        });
        panel9_2.add(btnSubmit1);
        jpCenter.add(panel9_1, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        jpCenter.add(panel9_2, gbc);

        JPanel panel10 = new JPanel(fr);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 0, 20);
        btnSubmit3 = new JButton(imgAdd1);
        btnSubmit3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit3.setRolloverIcon(imgAdd2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit3.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit3.setFocusPainted(false);
        btnSubmit3.setContentAreaFilled(false);
        btnSubmit3.setPreferredSize(new Dimension(48, 24));
        btnSubmit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        panel10.add(btnSubmit3);
        jpCenter.add(panel10, gbc);

        winCalc.setTextField(htfItemQuantity);
        winCalendar1.setTextField(jtfLastReceivingDate);
        winCalendar2.setTextField(jtfNextReceivingDate);
        JScrollPane scrollPane = new JScrollPane(jpCenter);
        scrollPane.setBorder(null);
        scrollPane.getViewport().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(findTabByName("재고 수정", jtpMainTab) == jtpMainTab.getSelectedIndex()) {
                    setLocationCalendar1(
                            btnCal1.getLocationOnScreen().x,
                            btnCal1.getLocationOnScreen().y-180);
                    setLocationCalendar2(
                            btnCal2.getLocationOnScreen().x,
                            btnCal2.getLocationOnScreen().y+24);
                    setLocationCalc(
                            btnCalc.getLocationOnScreen().x,
                            btnCalc.getLocationOnScreen().y+24);
                }
            }
        });
        add(scrollPane, BorderLayout.CENTER);

    }


    public void setLocationCalendar1(int x, int y){
        winCalendar1.setLocation(x, y);
    }
    public void setLocationCalendar2(int x, int y){
        winCalendar2.setLocation(x, y);
    }
    public void setLocationCalc(int x, int y) {
        winCalc.setLocation(x, y);
    }
//    public void setModelItemList(DefaultTableModel model){ modelItemList = model; }
//    public void setJspRight(JSplitPane jsp) { jspRight = jsp; }

    // 탭 타이틀 이름을 찾아 인덱스를 반환하는 함수
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    public void setTexts2(String category, String code, String name,String quantity,
                         String market, String location, String lastDate,String nextDate){

        jcbCategory.setSelectedItem(category);
        htfItemCode.setText(code);
        htfItemCode.forcedGainFocus();
        htfItemName.setText(name);
        htfItemName.forcedGainFocus();
        htfItemQuantity.setText(quantity);
        htfItemQuantity.forcedGainFocus();
        chkcomMarket.splitStrAndCheck(market);
        htfItemLocation.setText(location);
        htfItemLocation.forcedGainFocus();
        jtfLastReceivingDate.setText(lastDate);
        jtfNextReceivingDate.setText(nextDate);

        revalidate();
        repaint();
    }

    public void setComboboxData(JComboBox comboBox){
        jcbCategory.removeAllItems();
        for (int t = 0; t < comboBox.getItemCount(); t++)
        {
            jcbCategory.addItem(comboBox.getItemAt(t));
        }
    }


    // 테이블 너비를 내용에 맞춰주는 함수
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
