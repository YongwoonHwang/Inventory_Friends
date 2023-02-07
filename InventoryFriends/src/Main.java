import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;

public class Main extends JFrame{
    JPanel jpMain, jpLU, jpLD, jpRU, jpRD, jpBottom, jpOrderConsolidation, jpOCSearch, jpOCTable;
    JMenuBar jmbMenuBar;
    JMenu jmFileMenu;
    JButton btnSignOut, btnInventoryManagement, btnOrderConsolidation, btnAlarm, btnIMopt1, btnIMopt2, btnSearch;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlUserName, jlCalendar;
    JTabbedPane jtpMainTab, jtpSubTab;
    JTextField jtfOrderNum, jtfProductCode, jtfOrderer, jtfPhoneNum, jtfInvoiceNum, jtfOrderDate;
    JComboBox jcbMarket;
    JTable jtOrderCon;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2, imgSO1, imgSO2, imgAlarm1, imgAlarm2, imgCal,
            imgIMopt1_1, imgIMopt1_2, imgIMopt2_1, imgIMopt2_2, imgSearch1, imgSearch2;
    MenuAction menuAct;
    Font font1, font2;

    //용운 변수 start //
    JPanel jpIndividualRegistration, jpBatchRegistration, jpInventoryStatus;
    JButton btnSubmit1, btnSubmit2, btnSubmit3, btnSubmit4, btnCal1, btnCal2, btnCalc;
    JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12,
            jlCalendar2, jlCalendar3, jlCalc, jlfilechooser;
    HintTextField htfCategory, htfCode, htfProductName, htfQuantity, htfProductLocation;
    JComboBox jcbMarketChoose;
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal1, imgCal2;
    JTextField jTextField7, jTextField8, jTextField9, jTextField10;
    JTable jtInventoryStatus;
    Calculator JCalc;
//    CalendarWindow regiCal1, regiCal2;
    JFileChooser filechooser;
    JFrame windowCalc;

//    DefaultTableModel jdtStockStat;
    //용운 변수 end //
    private int sizeWidth = 1280;
    private int sizeHeight = 720;
    public Main() {
        super("Inventory Friends 0.0.1");
        createMenu();
        createPanel();
        setSize(sizeWidth, sizeHeight);
        setLocationRelativeTo(null);        // 화면 가운데에 창 배치
//        setResizable(false);                // 화면 크기 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenu() {
        jmbMenuBar = new JMenuBar(); // MenuBar 컴포넌트 생성
        jmFileMenu = new JMenu("File"); // "파일" 메뉴 컴포넌트 생성

        JMenuItem[] menuItems = new JMenuItem[3];
        String[] items = {"Debug Button", "Open", "Close"};

        menuAct = new MenuAction();

        for(int i=0; i<menuItems.length; i++) {
            menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
            menuItems[i].addActionListener(menuAct); // 리스너 등록
            jmFileMenu.add(menuItems[i]);

            if(i == 1)
                jmFileMenu.addSeparator(); // 숨기기와 닫기 사이에 구분선 추가
        }

        imgSO1 = new ImageIcon("./img/img_SO1.jpg");
        imgSO2 = new ImageIcon("./img/img_SO2.jpg");

        jlUserName = new JLabel("UserNameTest");
        jlUserName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        btnSignOut = new JButton(imgSO1);
        btnSignOut.setRolloverIcon(imgSO2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSignOut.setBorderPainted(false); // 버튼 테두리 제거
        btnSignOut.setFocusPainted(false);
        btnSignOut.setContentAreaFilled(false);

        btnSignOut.setPreferredSize(new Dimension(81, 24)); // 버튼 크기 지정
        btnSignOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sign Out");
            }
        });

//        jmbMenuBar.setBackground(new Color(238, 238, 238));
        jmbMenuBar.setBackground(Color.WHITE);
        jmbMenuBar.add(jmFileMenu); // 메뉴바에 메뉴 추가
        jmbMenuBar.add(new JMenu("Edit"));
        jmbMenuBar.add(new JMenu("Update"));
        jmbMenuBar.add(new JMenu("Help"));

        jmbMenuBar.add(Box.createHorizontalGlue());
        jmbMenuBar.add(jlUserName);
        jmbMenuBar.add(btnSignOut);

        setJMenuBar(jmbMenuBar); // 메뉴바 설정
    }
    private void createPanel(){
        // 폰트 설정
        font1 = new Font("돋움", Font.PLAIN, 12);   // 왼쪽 하위메뉴 라벨 폰트
        font2 = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트

        jtpMainTab = new JTabbedPane();
        jtpSubTab = new JTabbedPane();

        jpMain = new JPanel();
        jpLU = new JPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();
        jpBottom = new JPanel();
        jpBottom.setLayout(new BorderLayout());
        jpOrderConsolidation = new JPanel();

        jspCenter = new JSplitPane();
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();

        imgIM1 = new ImageIcon("./img/img_IM1.jpg");
        imgIM2 = new ImageIcon("./img/img_IM2.jpg");
        imgOC1 = new ImageIcon("./img/img_OC1.jpg");
        imgOC2 = new ImageIcon("./img/img_OC2.jpg");
        imgAlarm1 = new ImageIcon("./img/img_Alarm1.jpg");
        imgAlarm2 = new ImageIcon("./img/img_Alarm2.jpg");
        imgCal = new ImageIcon("./img/img_Cal.jpg");
        imgIMopt1_1 = new ImageIcon("./img/img_IMopt1_1.jpg");
        imgIMopt1_2 = new ImageIcon("./img/img_IMopt1_2.jpg");
        imgIMopt2_1 = new ImageIcon("./img/img_IMopt2_1.jpg");
        imgIMopt2_2 = new ImageIcon("./img/img_IMopt2_2.jpg");
        imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
        imgSearch2 = new ImageIcon("./img/img_Search2.jpg");

        jpLU.setBackground(Color.WHITE);
        jpLD.setBackground(Color.WHITE);
        jpRU.setBackground(Color.WHITE);
        jpRD.setBackground(Color.WHITE);

        jspCenter.setDividerSize(7);
        jspLeft.setDividerSize(7);
        jspRight.setDividerSize(7);

        btnInventoryManagement = new JButton(imgIM1);
        btnInventoryManagement.setBorder(BorderFactory.createEmptyBorder(5, 5, 5,5));
        btnOrderConsolidation = new JButton(imgOC1);
        btnOrderConsolidation.setBorder(BorderFactory.createEmptyBorder(5, 5, 5,5));
        btnIMopt1 = new JButton(imgIMopt1_1);
        btnIMopt1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        jlIMopt1.setFont(font1);
        btnIMopt1.setRolloverIcon(imgIMopt1_2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnIMopt1.setBorderPainted(false); // 버튼 테두리 제거
        btnIMopt1.setFocusPainted(false);
        btnIMopt1.setContentAreaFilled(false);

        btnIMopt2 = new JButton(imgIMopt2_1);
        btnIMopt2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        jlIMopt2.setFont(font1);
        btnIMopt2.setRolloverIcon(imgIMopt2_2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnIMopt2.setBorderPainted(false); // 버튼 테두리 제거
        btnIMopt2.setFocusPainted(false);
        btnIMopt2.setContentAreaFilled(false);

        btnIMopt1.setVisible(false);
        btnIMopt2.setVisible(false);
        jlCalendar = new JLabel(imgCal);
        jlCalendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

// 용운 code start //

        imgSubmit = new ImageIcon("./img/img_submit.jpg");
        imgAttach1 = new ImageIcon("./img/img_attach.jpg");
        imgAttach2 = new ImageIcon("./img/img_attach.jpg");
        imgCalc = new ImageIcon("./img/img_Calc.jpg");
        imgCal1 = new ImageIcon("./img/img_Cal.jpg");
        imgCal2 = new ImageIcon("./img/img_Cal.jpg");

        jpIndividualRegistration = new JPanel();
        jpIndividualRegistration.setLayout(null);
        jpBatchRegistration = new JPanel();
        jpBatchRegistration.setLayout(null);
        jpInventoryStatus = new JPanel();

        jlCalendar2 = new JLabel(imgSubmit);
        jlCalendar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        jpIndividualRegistration.add(jlCalendar2);

        jlCalendar3 = new JLabel(imgAttach1);
        jlCalendar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        jpIndividualRegistration.add(jlCalendar3);

        JCalc = new Calculator();
        filechooser = new JFileChooser();
        jlfilechooser = new JLabel();


        //테이블 패널
        String header2[] = {"CATEGORY", "CODE", "NAME", "QUANTITY", "MARKET", "LOCATION", "STOCKING_DATE", "EDA", "IMAGE"};

        jtInventoryStatus = new JTable();
        // 테이블 속성 오버라이드
        DefaultTableModel model2 = new DefaultTableModel(header2,0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jtInventoryStatus.setModel(model2);
        resizeColumnWidth(jtInventoryStatus);

        jpInventoryStatus.setLayout(new BorderLayout());
        jpInventoryStatus.add(new JScrollPane(jtInventoryStatus), BorderLayout.CENTER);

        jcbMarketChoose = new JComboBox();
        jcbMarketChoose.addItem("");
        jcbMarketChoose.addItem("쿠팡");
        jcbMarketChoose.addItem("네이버");
        jcbMarketChoose.addItem("11번가");
        jcbMarketChoose.addItem("위메프");
        jcbMarketChoose.addItem("옥션");
        jcbMarketChoose.addItem("G마켓");
        jcbMarketChoose.addItem("티몬");
        jcbMarketChoose.setBounds(140, 135, 700, 25);
        jcbMarketChoose.setBackground(Color.WHITE);
        jcbMarketChoose.setFont(font1);
        jpIndividualRegistration.add(jcbMarketChoose);

        jTextField7 = new JTextField();
        jTextField7.setBounds(140,195,675,25);
        jpIndividualRegistration.add(jTextField7);

        jTextField8 = new JTextField();
        jTextField8.setBounds(140,225,675,25);
        jpIndividualRegistration.add(jTextField8);

        jTextField9 = new JTextField();
        jTextField9.setBounds(140,255,200,25);
        jTextField9.setEditable(false);
        jpIndividualRegistration.add(jTextField9);

        jTextField10 = new JTextField();
        jTextField10.setBounds(140,100,200,30);
        jTextField10.setEditable(false);
        jpBatchRegistration.add(jTextField10);

        text1 = new JLabel("카테고리 : ");
        text1.setBounds(20,15,120,30);
        jpIndividualRegistration.add(text1);

        text2 = new JLabel("코드 : ");
        text2.setBounds(20,45,120,30);
        jpIndividualRegistration.add(text2);

        text3 = new JLabel("품명 : ");
        text3.setBounds(20,75,120,30);
        jpIndividualRegistration.add(text3);

        text4 = new JLabel("수량 : ");
        text4.setBounds(20,105,120,30);
        jpIndividualRegistration.add(text4);

        text5 = new JLabel("마켓 선택 : ");
        text5.setBounds(20,135,120,30);
        jpIndividualRegistration.add(text5);

        text6 = new JLabel("재고 위치 : ");
        text6.setBounds(20,165,120,30);
        jpIndividualRegistration.add(text6);

        text7 = new JLabel("최근 입고일 : ");
        text7.setBounds(20,195,120,30);
        jpIndividualRegistration.add(text7);

        text8 = new JLabel("다음 입고 예정일 : ");
        text8.setBounds(20,225, 120, 30);
        jpIndividualRegistration.add(text8);

        text9 = new JLabel("이미지 : ");
        text9.setBounds(20,255, 120, 30);
        jpIndividualRegistration.add(text9);

        text10 = new JLabel("일괄 등록 : ");
        text10.setBounds(20,100, 120, 30);
        jpBatchRegistration.add(text10);

        text11 = new JLabel("* excel 혹은 csv 파일만 지원합니다. 파일 업로드 전에 지원 양식을 다시 한 번 확인해주시기 바랍니다.");
        text11.setBounds(20,140, 570, 30);
        jpBatchRegistration.add(text11);

        text12 = new JLabel("자세히 보기");
        text12.setBounds(600,140, 150, 30);
        jpBatchRegistration.add(text12);

        htfCategory = new HintTextField("카테고리는 필수 입력 항목입니다. (ex. Chair)");
        htfCategory.setBounds(140,15,700,25);
        jpIndividualRegistration.add(htfCategory);

        htfCode = new HintTextField("코드는 필수 입력 항목입니다. (ex. Cover-A-01-Black)");
        htfCode.setBounds(140,45,700,25);
        jpIndividualRegistration.add(htfCode);

        htfProductName = new HintTextField("품명은 필수 입력 항목입니다. (ex. 의자 커버 원형 플로럴 검정)");
        htfProductName.setBounds(140,75,700,25);
        jpIndividualRegistration.add(htfProductName);

        htfQuantity = new HintTextField("수량은 필수 입력 항목입니다.(ex. 10EA");
        htfQuantity.setBounds(140,105,678,25);
        jpIndividualRegistration.add(htfQuantity);

        htfProductLocation = new HintTextField("ex.Rack-01-A-05");
        htfProductLocation.setBounds(140,165,700,25);
        jpIndividualRegistration.add(htfProductLocation);


        btnSubmit1 = new JButton("파일첨부");
//        btnSubmit1.setRolloverIcon(imgAttach2); // 버튼에 마우스가 올라갈떄 이미지 변환
//        btnSubmit1.setBorderPainted(false); // 버튼 테두리 제거
//        btnSubmit1.setFocusPainted(false);
//        btnSubmit1.setContentAreaFilled(false);
//        btnSubmit1.setPreferredSize(new Dimension(85, 25)); // 버튼 크기 지정

        btnSubmit1.setBounds(340, 255, 85, 25);
//        windowCalc.add(jlfilechooser);
        btnSubmit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //파일오픈 다이얼로그 를 띄움
                int result = filechooser.showOpenDialog(windowCalc);

                if (result == JFileChooser.APPROVE_OPTION) {
                    //선택한 파일의 경로 반환
                    File selectedFile = filechooser.getSelectedFile();

                    //경로 출력
                    System.out.println(selectedFile);
//                    jlfilechooser.setText("저장 경로:" +filechooser.getSelectedFile().toString()+"."+filechooser.getFileFilter().getDescription());
                }
            }
        });
        jpIndividualRegistration.add(btnSubmit1);

        btnSubmit2 = new JButton();
        btnSubmit2.setBounds(340, 100,90,30);
        btnSubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jpBatchRegistration.add(btnSubmit2);


        btnSubmit3 = new JButton("등록");
        btnSubmit3.setBounds(900, 280, 75, 25);
        btnSubmit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());  //선택된 버튼의 테스트값 출력
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet result = null;
                String sql = "INSERT INTO yongun7_Inventory_List (CATEGORY, CODE, PRODUCT_NAME, QUANTITY, MARKET, PRODUCT_LOCATION, STOCKING_DATE, EDA, IMAGE) " + "VALUES (?,?,?,?,?,?,?,?,?)";
                String sql2 = "SELECT * FROM yongun7_Inventory_List ORDER BY 'ID' ASC LIMIT 1";

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://inventoryfriends.cxtfsxnxj3jt.ap-northeast-1.rds.amazonaws.com:3306/","admin","admin1470");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE Inventory_List"); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                    pstmt.setString(1, htfCategory.getText());
                    pstmt.setString(2, htfCode.getText());
                    pstmt.setString(3, htfProductName.getText());
                    pstmt.setInt(4, Integer.parseInt(htfQuantity.getText()));
                    pstmt.setString(5, (String) jcbMarketChoose.getSelectedItem());
                    pstmt.setString(6, htfProductLocation.getText());
                    pstmt.setString(7, jTextField7.getText());
                    pstmt.setString(8, jTextField8.getText());
                    pstmt.setString(9, jTextField9.getText());

                    int cnt = pstmt.executeUpdate();
                    System.out.println("SUCCESS");

//                    if(jTextField1.getText().isEmpty()) return;
//                    jTextField1.setText("");//비우기

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle.toString());
                }

                try{
                    pstmt = con.prepareStatement(sql2);
                    pstmt.execute("USE Inventory_List"); // 사용할 DB를 선택한다.
                    result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
                    while (result.next()){
                        model2.addRow(new Object[]{result.getString("CATEGORY"), result.getString("CODE"), result.getString("Product_NAME"),
                                result.getString("QUANTITY"), result.getString("MARKET"), result.getString("Product_Location"), result.getString("STOCKING_DATE"), result.getString("EDA"), result.getString("IMAGE")});

                    }
//                    jdtStockStat = (DefaultTableModel)jtInventoryStatus.getModel();
//                    jdtStockStat.addRow(new String[]{"",""});

                }catch(Exception cnfe){
                    System.out.println(cnfe.getMessage());
                }finally {
                    try{
                        result.close();
                        pstmt.close();
                        con.close();
                    } catch (Exception e2) {}
                }

                String STTitle = new String("재고 현황");
                if (findTabByName(STTitle, jtpSubTab) != -1) {
                    jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
                } else {
                    jtpSubTab.addTab(STTitle, jpInventoryStatus);
                    jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
                }

            }
        });
        jpIndividualRegistration.add(btnSubmit3);



        btnSubmit4 = new JButton("등록");
        btnSubmit4.setBounds(900, 280, 75, 25);
        btnSubmit4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jpBatchRegistration.add(btnSubmit4);

        btnCal1 = new JButton(imgCal1);
        btnCal1.setBounds(815, 195, 23, 23);
        btnCal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                regiCal1.setLocation(jlCalendar2.getLocationOnScreen().x+1, jlCalendar2.getLocationOnScreen().y-181);
            }
        });
        jpIndividualRegistration.add(btnCal1);

        btnCal2 = new JButton(imgCal2);
        btnCal2.setBounds(815, 225, 23, 23);
        btnCal2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jpIndividualRegistration.add(btnCal2);

        btnCalc = new JButton(imgCalc);
        btnCalc.setBounds(818, 105, 20, 23);
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JCalc.setLocation(jlCalc.getLocationOnScreen().x+1, jlCalc.getLocationOnScreen().y-181);
            }
        });
        jpIndividualRegistration.add(btnCalc);


//용운 code end//

        // 재고 관리 버튼
        btnInventoryManagement.setRolloverIcon(imgIM2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnInventoryManagement.setBorderPainted(false); // 버튼 테두리 제거
        btnInventoryManagement.setFocusPainted(false);
        btnInventoryManagement.setContentAreaFilled(false);

        btnInventoryManagement.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnInventoryManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnIMopt1.isVisible()) {
                    btnIMopt1.setVisible(false);
                    btnIMopt2.setVisible(false);
                }
                else {
                    btnIMopt1.setVisible(true);
                    btnIMopt2.setVisible(true);
                }
            }
        });

        // 개별 등록 버튼
        btnIMopt1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                String opt1Title = new String("재고 관리(개별 등록)");
                if (findTabByName(opt1Title, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(opt1Title, jtpMainTab));
                } else {
                    jtpMainTab.addTab(opt1Title, jpIndividualRegistration);
                    jtpMainTab.setSelectedIndex(findTabByName(opt1Title, jtpMainTab));
                }

            }
        });

        // 일괄 등록 버튼
        btnIMopt2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                String opt2Title = new String("재고 관리(일괄 등록)");
                if (findTabByName(opt2Title, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(opt2Title, jtpMainTab));
                } else {
                    jtpMainTab.addTab(opt2Title, jpBatchRegistration);
                    jtpMainTab.setSelectedIndex(findTabByName(opt2Title, jtpMainTab));
                }

            }
        });

        // 주문 통합 패널
        String market[] = {"마켓 1", "마켓 2", "정말 긴 마켓 이름"};
        jcbMarket = new JComboBox<String>(market);
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        btnSearch = new JButton(imgSearch1);
        btnSearch.setRolloverIcon(imgSearch2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSearch.setBorderPainted(false); // 버튼 테두리 제거
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(56, 24)); // 버튼 크기 지정

        jtfOrderNum = new HintTextField("주문 번호");
        jtfOrderNum.setColumns(10);
        jtfProductCode = new HintTextField("상품 코드");
        jtfProductCode.setColumns(10);
        jtfOrderer = new HintTextField("주문자");
        jtfOrderer.setColumns(10);
        jtfPhoneNum = new HintTextField("전화번호");
        jtfPhoneNum.setColumns(10);
        jtfInvoiceNum = new HintTextField("송장 번호");
        jtfInvoiceNum.setColumns(10);
        jtfOrderDate = new HintTextField("주문일");
        jtfOrderDate.setColumns(10);

        jpOCSearch = new JPanel();
        jpOCSearch.setLayout(new FlowLayout());

        jpOCTable = new JPanel();
//        jpOCTable.setBackground(Color.WHITE);

        jpOrderConsolidation.setLayout(new BorderLayout());
        jpOrderConsolidation.add(jpOCSearch, BorderLayout.NORTH);
        jpOrderConsolidation.add(jpOCTable, BorderLayout.CENTER);

        // 검색 패널
        jpOCSearch.add(jtfOrderNum);
        jpOCSearch.add(jtfProductCode);
        jpOCSearch.add(jtfOrderer);
        jpOCSearch.add(jtfPhoneNum);
        jpOCSearch.add(jtfInvoiceNum);
        jpOCSearch.add(jtfOrderDate);
        jpOCSearch.add(jcbMarket);
        jpOCSearch.add(btnSearch);

        //테이블 패널
        String header[] = {"주문 번호", "상품 코드", "주문 수량", "주문자", "전화번호", "주소", "송장 번호", "주문일", "마켓"};
        String contents[][] = {
                {"01", "couch-01-08-beige", "1", "황용운", "010-9574-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
                {"02", "couch-03-01-black", "2", "김만조", "010-4313-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
                {"03", "chair-03-03-blue", "5", "권순용", "010-4109-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"}
        };

        jtOrderCon = new JTable();
        // 테이블 속성 오버라이드
        DefaultTableModel model = new DefaultTableModel(contents, header){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jtOrderCon.setModel(model);
        resizeColumnWidth(jtOrderCon);


        jtOrderCon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String ODTitle = new String("주문 상세");
                if (e.getClickCount() == 2) {
                    System.out.println(jtOrderCon.getSelectedRow());
                    if (findTabByName(ODTitle, jtpSubTab) != -1) {
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    } else {
                        jtpSubTab.addTab(ODTitle, new JPanel());
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    }

                }
            }
        });
        jpOCTable.setLayout(new BorderLayout());
        jpOCTable.add(new JScrollPane(jtOrderCon), BorderLayout.CENTER);

        // 주문 통합 버튼
        btnOrderConsolidation.setRolloverIcon(imgOC2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnOrderConsolidation.setBorderPainted(false); // 버튼 테두리 제거
        btnOrderConsolidation.setFocusPainted(false);
        btnOrderConsolidation.setContentAreaFilled(false);

        btnOrderConsolidation.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnOrderConsolidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String OCTitle = new String("주문 통합");
                if (findTabByName(OCTitle, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(OCTitle, jtpMainTab));
                } else {
                    jtpMainTab.addTab(OCTitle, jpOrderConsolidation);
                    jtpMainTab.setSelectedIndex(findTabByName(OCTitle, jtpMainTab));
                }
            }
        });   
        
        // 알림 버튼
        btnAlarm = new JButton(imgAlarm1);
        btnAlarm.setRolloverIcon(imgAlarm2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnAlarm.setPreferredSize(new Dimension(74, 29)); // 버튼 크기 지정
        btnAlarm.setBorderPainted(false); // 버튼 테두리 제거
        btnAlarm.setFocusPainted(false);
        btnAlarm.setContentAreaFilled(false);
        btnAlarm.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        jtpMainTab.addTab("재고 목록", new JPanel());

        jtpSubTab.addTab("tab1", new JPanel());
        jtpSubTab.addTab("tab2", new JPanel());

        // jtp 스타일 지정
        jtpMainTab.setFont(font2);
        jtpSubTab.setFont(font2);
        jtpMainTab.setBackground(Color.LIGHT_GRAY);
        jtpSubTab.setBackground(Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 3, 3, 40));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.DARK_GRAY));
        SwingUtilities.updateComponentTreeUI(jtpMainTab);
        SwingUtilities.updateComponentTreeUI(jtpSubTab);


        // 좌상단 패널
        jpLU.setLayout(new BoxLayout(jpLU,BoxLayout.Y_AXIS));

        jpLU.add(btnInventoryManagement);    // 재고 관리 버튼 추가
        jpLU.add(btnIMopt1);
        jpLU.add(btnIMopt2);
        jpLU.add(btnOrderConsolidation);
        
        // 우상단 패널
        jpRU.setLayout(new BorderLayout());

        jpRU.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpRU.add(new JLayer<JTabbedPane>(jtpMainTab, new CloseableTabbedPaneLayerUIuseDefault()));
        
        // 우하단 패널
        jpRD.setLayout(new BorderLayout());

        jpRD.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpRD.add(new JLayer<JTabbedPane>(jtpSubTab, new CloseableTabbedPaneLayerUI()));

        jspLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspRight.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jspLeft.setMinimumSize(new Dimension(sizeWidth/5-1, 100));

        jspCenter.setLeftComponent(jspLeft);
        jspCenter.setRightComponent(jspRight);
        jspCenter.setDividerLocation(sizeWidth/5);          // 중앙 jsp의 위치 설정

        jspLeft.setLeftComponent(jpLU);
        jspLeft.setRightComponent(jpLD);
        jspLeft.setDividerLocation(sizeHeight/6*4);         // 왼쪽 jsp의 높이 설정

        jspRight.setLeftComponent(jpRU);
        jspRight.setRightComponent(jpRD);
        jspRight.setDividerLocation(sizeHeight/2);          // 오른쪽 jsp의 높이 설정

        jpMain.setLayout(new BorderLayout());
        jpMain.add(jspCenter, BorderLayout.CENTER);
        jpBottom.add(jlCalendar, BorderLayout.WEST);
        jpBottom.add(Box.createHorizontalGlue());
        jpBottom.add(btnAlarm, BorderLayout.EAST);
        jpMain.add(jpBottom, BorderLayout.SOUTH);

        getContentPane().add(jpMain);
    }

    // 탭 메뉴에 x버튼 추가하는 클래스(처음 탭은 x버튼 없음)
    class CloseableTabbedPaneLayerUIuseDefault extends LayerUI<JTabbedPane> {
        private final JPanel p = new JPanel();
        private final Point pt = new Point(-100, -100);
        private final JButton button = new JButton("x") {
            @Override public Dimension getPreferredSize() {
                return new Dimension(16, 16);
            }
        };
        public CloseableTabbedPaneLayerUIuseDefault() {
            super();
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setRolloverEnabled(false);
        }
        @Override public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            if (c instanceof JLayer) {
                JLayer jlayer = (JLayer) c;
                JTabbedPane tabPane = (JTabbedPane) jlayer.getView();
                for (int i = 1; i < tabPane.getTabCount(); i++) {
                    Rectangle rect = tabPane.getBoundsAt(i);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
//                    button.setForeground(r.contains(pt) ? Color.RED : Color.BLACK);
                    SwingUtilities.paintComponent(g, button, p, r);
                }
            }
        }
        @Override public void installUI(JComponent c) {
            super.installUI(c);
            ((JLayer)c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
        @Override public void uninstallUI(JComponent c) {
            ((JLayer)c).setLayerEventMask(0);
            super.uninstallUI(c);
        }
        @Override protected void processMouseEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
            if (e.getID() == MouseEvent.MOUSE_CLICKED) {
                pt.setLocation(e.getPoint());
                final Point mousePos = l.getMousePosition();
                JTabbedPane tabbedPane = (JTabbedPane) l.getView();
//                int index = tabbedPane.indexAtLocation(pt.x, pt.y);
                int index = tabbedPane.indexAtLocation(mousePos.x, mousePos.y);
                if (index >= 1) {
                    Rectangle rect = tabbedPane.getBoundsAt(index);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
                    if (r.contains(pt)) {
                        System.out.println(x);
                        System.out.println(y);
                        tabbedPane.removeTabAt(index);
                    }
                }
                l.getView().repaint();
            }
        }
        @Override protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
            pt.setLocation(e.getPoint());
            JTabbedPane tabbedPane = (JTabbedPane) l.getView();
            int index = tabbedPane.indexAtLocation(pt.x, pt.y);
            if (index >= 1) {
                tabbedPane.repaint(tabbedPane.getBoundsAt(index));
            } else {
                tabbedPane.repaint();
            }
        }
    }
    // 탭 메뉴에 x버튼 추가하는 클래스
    class CloseableTabbedPaneLayerUI extends LayerUI<JTabbedPane> {
        private final JPanel p = new JPanel();
        private final Point pt = new Point(-100, -100);
        private final JButton button = new JButton("x") {
            @Override public Dimension getPreferredSize() {
                return new Dimension(16, 16);
            }
        };
        public CloseableTabbedPaneLayerUI() {
            super();
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setRolloverEnabled(false);
        }
        @Override public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            if (c instanceof JLayer) {
                JLayer jlayer = (JLayer) c;
                JTabbedPane tabPane = (JTabbedPane) jlayer.getView();
                for (int i = 0; i < tabPane.getTabCount(); i++) {
                    Rectangle rect = tabPane.getBoundsAt(i);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
//                    button.setForeground(r.contains(pt) ? Color.RED : Color.BLACK);
                    SwingUtilities.paintComponent(g, button, p, r);
                }
            }
        }
        @Override public void installUI(JComponent c) {
            super.installUI(c);
            ((JLayer)c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
        @Override public void uninstallUI(JComponent c) {
            ((JLayer)c).setLayerEventMask(0);
            super.uninstallUI(c);
        }
        @Override protected void processMouseEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
            if (e.getID() == MouseEvent.MOUSE_CLICKED) {
                pt.setLocation(e.getPoint());
                final Point mousePos = l.getMousePosition();
                JTabbedPane tabbedPane = (JTabbedPane) l.getView();
//                int index = tabbedPane.indexAtLocation(pt.x, pt.y);
                int index = tabbedPane.indexAtLocation(mousePos.x, mousePos.y);
                if (index >= 0) {
                    Rectangle rect = tabbedPane.getBoundsAt(index);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
                    if (r.contains(pt)) {
                        tabbedPane.removeTabAt(index);
                        try{
                            jtpSubTab.isEnabledAt(0);
                        } catch (Exception exception){
                            jspRight.setDividerSize(0);
                            jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                            jtpSubTab.setVisible(false);
                        };
                    }
                }
                l.getView().repaint();
            }
        }
        @Override protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
            pt.setLocation(e.getPoint());
            JTabbedPane tabbedPane = (JTabbedPane) l.getView();
            int index = tabbedPane.indexAtLocation(pt.x, pt.y);
            if (index >= 0) {
                tabbedPane.repaint(tabbedPane.getBoundsAt(index));
            } else {
                tabbedPane.repaint();
            }
        }
    }

    class MenuAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch(command) { // 메뉴 아이템 구분
                case "Debug Button":
                    jspRight.setDividerSize(7);
                    jspRight.setDividerLocation(360);
                    if (jtpSubTab.isVisible() == false) {
                        jtpSubTab.setVisible(true);
                        jtpSubTab.addTab("tab1", new JPanel());
                    }
                    break;
                case "Open":
                    break;
                case "Close":
                    System.exit(0); // 시스템 종료
                    break;
            }
        }
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

    public static void main(String[] args) {
        new Main();
    }
}




