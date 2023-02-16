import jdk.jfr.Category;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class IndividualRegistrationPanel extends JPanel {
    JPanel jpInventoryStatus;
    JTable jtInventoryStatus;
    JTabbedPane jtpSubTab;
    JButton btnSubmit1, btnSubmit3, btnCal1, btnCal2, btnCalc;
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage,
            jlCalendar2, jlCalendar3;
    HintTextField htfItemCode, htfItemName, htfItemQuantity, htfItemLocation;
    JComboBox jcbCategory;
    ArrayList<String> categoryList =  new ArrayList();
    JSplitPane jspRight;
    CheckableComboBox chkcomMarket;
    CalculatorWindow winCalc;
    String market[] = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal, imgAdd1, imgAdd2, imgFile1, imgFile2;
    JTextField jtfLastReceivingDate, jtfNextReceivingDate, jtfImg;
    JFileChooser imgfilechooser;
    CalendarWindowForChoose winCalendar1, winCalendar2;
    DefaultTableModel modelItemList;
    Font font;
    String dbName = "ifdb";
    String dbTableName = "ItemList";
    String dbUserID = "1";
    static String error;
    public IndividualRegistrationPanel() {
        // 폰트 설정
        font = new Font("돋움", Font.PLAIN, 12);   // 왼쪽 하위메뉴 라벨 폰트
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

        jpInventoryStatus = new JPanel();
        setLayout(null);

        jlCalendar2 = new JLabel(imgSubmit);
        jlCalendar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(jlCalendar2);

        jlCalendar3 = new JLabel(imgAttach1);
        jlCalendar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        add(jlCalendar3);

        imgfilechooser = new JFileChooser();
//        jlfilechooser = new JLabel();
        imgfilechooser.setAcceptAllFileFilterUsed(false);
        imgfilechooser.setFileFilter(new FileNameExtensionFilter("Image File(*.jpg;*.jpeg;*.png)", "jpg", "jpeg", "png"));
        imgfilechooser.setMultiSelectionEnabled(false); // 다중 선택 불가


        //테이블 패널
        String header[] = {"카테고리", "코드", "상품 이름", "수량", "마켓", "재고 위치", "최근 입고일", "다음 입고 예정일", "이미지"};

        jtInventoryStatus = new JTable();
        // 테이블 속성 오버라이드
        DefaultTableModel model2 = new DefaultTableModel(header,0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jtInventoryStatus.setModel(model2);
        resizeColumnWidth(jtInventoryStatus);

        jpInventoryStatus.setLayout(new BorderLayout());
        jpInventoryStatus.add(new JScrollPane(jtInventoryStatus), BorderLayout.CENTER);

        chkcomMarket = new CheckableComboBox(market);
        chkcomMarket.setBounds(140, 135, 700, 25);
        add(chkcomMarket);

        jtfLastReceivingDate = new JTextField();
        jtfLastReceivingDate.setBounds(140,195,675,25);
        jtfLastReceivingDate.setEditable(false);
        add(jtfLastReceivingDate);

        jtfNextReceivingDate = new JTextField();
        jtfNextReceivingDate.setBounds(140,225,675,25);
        jtfNextReceivingDate.setEditable(false);
        add(jtfNextReceivingDate);

        jtfImg = new JTextField();
        jtfImg.setBounds(140,255,200,25);
        jtfImg.setEditable(false);
        add(jtfImg);

        jlCategory = new JLabel("카테고리 : ");
        jlCategory.setBounds(20,15,120,30);
        add(jlCategory);

        jlItemCode = new JLabel("코드 : ");
        jlItemCode.setBounds(20,45,120,30);
        add(jlItemCode);

        jlItemName = new JLabel("품명 : ");
        jlItemName.setBounds(20,75,120,30);
        add(jlItemName);

        jlItemQuantity = new JLabel("수량 : ");
        jlItemQuantity.setBounds(20,105,120,30);
        add(jlItemQuantity);

        jlMarket = new JLabel("마켓 선택 : ");
        jlMarket.setBounds(20,135,120,30);
        add(jlMarket);

        jlItemLocation = new JLabel("재고 위치 : ");
        jlItemLocation.setBounds(20,165,120,30);
        add(jlItemLocation);

        jlLastReceivingDate = new JLabel("최근 입고일 : ");
        jlLastReceivingDate.setBounds(20,195,120,30);
        add(jlLastReceivingDate);

        jlNextReceivingDate = new JLabel("다음 입고 예정일 : ");
        jlNextReceivingDate.setBounds(20,225, 120, 30);
        add(jlNextReceivingDate);

        jlImage = new JLabel("이미지 : ");
        jlImage.setBounds(20,255, 120, 30);
        add(jlImage);


        jcbCategory = new JComboBox();
        jcbCategory.setEditable(true);
        addItemsAtComboBox();
        jcbCategory.setBounds(140,15,700,25);
        add(jcbCategory);

        htfItemCode = new HintTextField("코드는 필수 입력 항목입니다. (ex. Cover-A-01-Black)");
        htfItemCode.setBounds(140,45,700,25);
        add(htfItemCode);

        htfItemName = new HintTextField("품명은 필수 입력 항목입니다. (ex. 의자 커버 원형 플로럴 검정)");
        htfItemName.setBounds(140,75,700,25);
        add(htfItemName);

        htfItemQuantity = new HintTextField("수량은 필수 입력 항목입니다.(ex. 10EA)");
        htfItemQuantity.setBounds(140,105,678,25);
        add(htfItemQuantity);
        winCalc.setTextField(htfItemQuantity);

        htfItemLocation = new HintTextField("ex.Rack-01-A-05");
        htfItemLocation.setBounds(140,165,700,25);
        add(htfItemLocation);

        btnSubmit1 = new JButton(imgFile1);
        btnSubmit1.setRolloverIcon(imgFile2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit1.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit1.setFocusPainted(false);
        btnSubmit1.setContentAreaFilled(false);
        btnSubmit1.setPreferredSize(new Dimension(70, 24)); // 버튼 크기 지정

        btnSubmit1.setBounds(340, 255, 85, 25);
//        windowCalc.add(jlfilechooser);
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
        add(btnSubmit1);


        btnSubmit3 = new JButton(imgAdd1);
        btnSubmit3.setBounds(900, 280, 75, 25);
        btnSubmit3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit3.setRolloverIcon(imgAdd2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit3.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit3.setFocusPainted(false);
        btnSubmit3.setContentAreaFilled(false);
        btnSubmit3.setPreferredSize(new Dimension(48, 24));
        btnSubmit3.setBounds(900, 280, 75, 25);
        btnSubmit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(categoryList);  //선택된 버튼의 테스트값 출력
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet result = null;
                String sql = "INSERT INTO " + dbTableName + " (user_id, CATEGORY, CODE, PRODUCT_NAME, QUANTITY, MARKET, PRODUCT_LOCATION, STOCKING_DATE, EDA, IMAGE) VALUES (?,?,?,?,?,?,?,?,?,?)";
                String sql2 = "SELECT * FROM " + dbTableName + " ORDER BY id DESC LIMIT 1";

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                    pstmt.setString(1, dbUserID);

                    String str = jcbCategory.getSelectedItem().toString();
                    boolean chk = false;
                    pstmt.setString(2, str);
                    for (int i = 0; i < categoryList.size();i++){
                        if(!categoryList.get(i).equals(str))
                            chk = true;
                    }
                    if(categoryList.size() == 0) {
                        jcbCategory.addItem("");
                        jcbCategory.addItem(str);
                    }
                    else if(chk){
                        jcbCategory.addItem(str);
                    }

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
                        String ISTitle = new String("재고 현황");
//                        if (findTabByName(ISTitle, jtpSubTab) != -1) {
//                            jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
//                        } else {
//                            jtpSubTab.addTab(ISTitle, jpInventoryStatus);
//                            jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
//                            if (jspRight.getDividerSize() == 0){
//                                jtpSubTab.setVisible(true);
//                                jspRight.setDividerLocation(jspRight.getSize().height/2);
//                                jspRight.setDividerSize(7);
//                            }
//                        }

                        try{
                            pstmt = con.prepareStatement(sql2);
                            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                            result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
                            while (result.next()){
                                modelItemList.addRow(new Object[]{false, result.getString("CATEGORY"), result.getString("CODE"), result.getString("Product_NAME"),
                                        result.getString("QUANTITY"), result.getString("MARKET"), result.getString("Product_Location"), result.getString("STOCKING_DATE"), result.getString("EDA"), result.getString("IMAGE")});
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
        add(btnSubmit3);

        winCalendar1.setTextField(jtfLastReceivingDate);
        btnCal1 = new JButton(imgCal);
        btnCal1.setBounds(815, 195, 23, 24);
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
        add(btnCal1);

        winCalendar2.setTextField(jtfNextReceivingDate);
        btnCal2 = new JButton(imgCal);
        btnCal2.setBounds(815, 225, 23, 24);
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
        add(btnCal2);

        btnCalc = new JButton(imgCalc);
        btnCalc.setBounds(818, 105, 22, 24);
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
        add(btnCalc);
    }

    public void addItemsAtComboBox(){
//        jcbCategory.addItem("");
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


    public ComboBoxModel getComboboxModle(){
        return jcbCategory.getModel();
    }

    // 탭 타이틀 이름을 찾아 인덱스를 반환하는 함수
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
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
