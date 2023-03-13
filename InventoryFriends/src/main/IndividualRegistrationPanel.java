package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class IndividualRegistrationPanel extends JPanel {
    JPanel jpCenter;
    JTabbedPane jtpSubTab;
    JButton btnSubmit1, btnSubmit3, btnCal1, btnCal2, btnCalc;
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage;
    HintTextField htfItemCode, htfItemName, htfItemQuantity, htfItemLocation;
    JComboBox jcbCategory, jcbCategory2, jcbCategory3;
    JTabbedPane jtpMainTab;
    ArrayList<String> categoryList =  new ArrayList();
    JSplitPane jspRight;
    CheckableComboBox chkcomMarket;
    ItemStatusPanel jpItemStatusPanel;
    CalculatorWindow winCalc;
    String market[] = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal, imgAdd1, imgAdd2, imgFile1, imgFile2;
    JTextField jtfLastReceivingDate, jtfNextReceivingDate, jtfImg;
    JFileChooser imgfilechooser;
    CalendarWindowForChoose winCalendar1, winCalendar2;
    DefaultTableModel modelItemList;
    String dbName = "ifdb";
    String dbTableName;
    String dbUserIdx;
    static String error;
    public IndividualRegistrationPanel(String userid) {
        setLayout(new BorderLayout());

        dbTableName = userid + "_ItemList";

        jpCenter = new JPanel();
        imgSubmit = new ImageIcon("./img/img_submit.jpg");
        imgAttach1 = new ImageIcon("./img/img_attach.jpg");
        imgAttach2 = new ImageIcon("./img/img_attach.jpg");
        imgCalc = new ImageIcon("./img/img_Calc.jpg");
        imgCal = new ImageIcon("./img/img_Cal.jpg");

        imgAdd1 = new ImageIcon("./img/img_Add1.jpg");
        imgAdd2 = new ImageIcon("./img/img_Add2.jpg");
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
        addItemsAtComboBox();
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
                dbTableName = userid + "_ItemList";
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet result = null;
                String sql = "INSERT INTO " + dbTableName + " (user_id, CATEGORY, CODE, ProductName, QUANTITY, MARKET, PRODUCTLocation, STOCKINGDate, EDA, IMAGE) VALUES (?,?,?,?,?,?,?,?,?,?)";
                String sql2 = "SELECT * FROM " + dbTableName + " ORDER BY id DESC LIMIT 1";

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                    pstmt.setString(1, dbUserIdx);

                    String str = jcbCategory.getSelectedItem().toString();
                    pstmt.setString(2, str);

                    if (htfItemCode.getForeground() != Color.GRAY){
                        pstmt.setString(3, htfItemCode.getText());
                    } else {
                        error = "코드는 필수 항목 입니다";
                        throw new SQLException();
                    }
                    if (htfItemName.getForeground() != Color.GRAY){
                        pstmt.setString(4, htfItemName.getText());
                    } else {
                        error = "품명은 필수 항목 입니다";
                        throw new SQLException();
                    }
                    if (htfItemQuantity.getForeground() != Color.GRAY){
                        pstmt.setInt(5, Integer.parseInt(htfItemQuantity.getText()));
                    } else {
                        error = "수량은 필수 항목 입니다";
                        throw new SQLException();
                    }

                    pstmt.setString(6, (String) chkcomMarket.getSelectItems());

                    if (htfItemLocation.getForeground() != Color.GRAY){
                        pstmt.setString(7, htfItemLocation.getText());
                    } else {
                        pstmt.setString(7, "");
                    }
                    pstmt.setString(8, jtfLastReceivingDate.getText());
                    pstmt.setString(9, jtfNextReceivingDate.getText());
                    pstmt.setString(10, jtfImg.getText());

                    int cnt = pstmt.executeUpdate();
                    System.out.println("SUCCESS");

                    jcbCategory.setSelectedIndex(0);
                    htfItemCode.reset();
                    htfItemLocation.reset();
                    htfItemName.reset();
                    htfItemQuantity.reset();
                    jtfImg.setText("");
                    jtfLastReceivingDate.setText("");
                    jtfNextReceivingDate.setText("");
                    chkcomMarket.Clear();

                    if (cnt == 1){
                        boolean chk = false;
                        for (int i = 0; i < categoryList.size();i++){
                            if(categoryList.get(i).equals(str))
                                chk = true;
                        }
                        if(categoryList.size() == 0) {
                            jcbCategory.addItem("");
                            jcbCategory.addItem(str);
                            jcbCategory2.addItem("");
                            jcbCategory2.addItem(str);
                            jcbCategory3.addItem("");
                            jcbCategory3.addItem(str);
                            categoryList.add("");
                            categoryList.add(str);
                        }
                        else if(!chk){
                            jcbCategory.addItem(str);
                            jcbCategory2.addItem(str);
                            jcbCategory3.addItem(str);
                            categoryList.add(str);
                        }
                        try{
                            pstmt = con.prepareStatement(sql2);
                            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                            result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
                            while (result.next()){
                                modelItemList.addRow(new Object[]{false, result.getString("CATEGORY"), result.getString("CODE"), result.getString("ProductName"),
                                        result.getString("QUANTITY"), result.getString("MARKET"), result.getString("ProductLocation"),
                                        result.getString("STOCKINGDate"), result.getString("EDA"), result.getString("IMAGE"), result.getString("ID")});

                                jpItemStatusPanel.setTexts(result.getString("CATEGORY"), result.getString("CODE"), result.getString("ProductName"),
                                        result.getString("QUANTITY"), result.getString("MARKET"), result.getString("ProductLocation"),
                                        result.getString("STOCKINGDate"), result.getString("EDA"),result.getString("IMAGE"),
                                        result.getString("ID"),modelItemList.getRowCount()-1, dbTableName);
                                jpItemStatusPanel.repaint();

                                String ISTitle = "재고 상세";

                                jtpSubTab.setVisible(true);
                                jspRight.setDividerSize(7);
                                jspRight.setDividerLocation(getRootPane().getSize().height-400);
                                if (findTabByName(ISTitle, jtpSubTab) != -1) {
                                    jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
                                } else {
                                    jtpSubTab.addTab(ISTitle, jpItemStatusPanel);
                                    jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
                                }
                            }

                        }catch(Exception cnfe){
                            System.out.println(cnfe.getMessage());
                        }finally {
                            try{
                                result.close();
                                pstmt.close();
                                con.close();
                            } catch (Exception e2) {}
                        }
                    }

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe);

                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle);
                    if (error != null){
                        JOptionPane.showMessageDialog(null, error,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        error = null;
                    }
                }

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
                if(findTabByName("재고 관리(개별 등록)", jtpMainTab) == jtpMainTab.getSelectedIndex()) {
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

    public void addItemsAtComboBox(){
        boolean chk = false;
        String sql2 = "SELECT DISTINCT Category FROM " + dbTableName + " ksy_test ORDER BY Category";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            pstmt = con.prepareStatement(sql2);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
            while (result.next()){
                String tmp = result.getString("Category");
                if(!tmp.equals("") && !chk)
                    jcbCategory.addItem("");
                chk = true;
                jcbCategory.addItem(tmp);
                categoryList.add(tmp);
            }

        }catch(Exception cnfe){
            System.out.println(cnfe.getMessage());
        }finally {
            try{
                result.close();
                pstmt.close();
                con.close();
            } catch (Exception e2) {}
        }
    }

    public void setSubTab(JTabbedPane SubTab){
        jtpSubTab = SubTab;
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
    public void setModelItemList(DefaultTableModel model){ modelItemList = model; }
    public void setJspRight(JSplitPane jsp) { jspRight = jsp; }


    // 탭 타이틀 이름을 찾아 인덱스를 반환하는 함수
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
}
