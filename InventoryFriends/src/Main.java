import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.*;

public class Main extends JFrame{
    JPanel jpMain, jpLU, jpLD, jpRU, jpRD, jpBottom, jpOrderConsolidation, jpOCSearch, jpOCTable, jpItemList;
    JMenuBar jmbMenuBar;
    JMenu jmFileMenu;
    JButton btnSignOut, btnInventoryManagement, btnOrderConsolidation, btnAlarm, btnIMopt1, btnIMopt2,
            btnSearch;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlUserName, jlCalendar;
    JTabbedPane jtpMainTab, jtpSubTab;
    MemoTab memoTab;
    MemoWindow memoWindow;
    JTextField jtfOrderNum, jtfItemCode, jtfOrderer, jtfPhoneNum, jtfInvoiceNum, jtfOrderDate;
    JComboBox jcbMarket;
    JTable jtOrderCon, jtItemList;
    CalendarWindow windowCal;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2, imgSO1, imgSO2, imgAlarm1, imgAlarm2, imgCal,
            imgIMopt1_1, imgIMopt1_2, imgIMopt2_1, imgIMopt2_2, imgSearch1, imgSearch2;
    MenuAction menuAct;
    Font font1, font2;

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
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                windowCal.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
            }
            @Override
            public void componentResized(ComponentEvent e) {
                windowCal.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
            }
        });
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
        memoTab = new MemoTab();
        memoWindow = new MemoWindow();

        jpMain = new JPanel();
        jpLU = new JPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();
        jpBottom = new JPanel();
        jpBottom.setLayout(new BorderLayout());
        jpOrderConsolidation = new JPanel();
        jpItemList = new JPanel();

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
        windowCal = new CalendarWindow();
        windowCal.setMemoWindow(memoWindow);
        memoTab.setMemoWindow(memoWindow);
        windowCal.setMemoTab(memoTab);
        jlCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(windowCal.isVisible()){
                    windowCal.setVisible(false);
                    System.out.println(windowCal.selectDate);
                }else{
                    windowCal.setVisible(true);
                    System.out.println(windowCal.selectDate);
                }
            }
        });

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
                    jtpMainTab.addTab(opt1Title, new JPanel());
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
                    jtpMainTab.addTab(opt2Title, new JPanel());
                    jtpMainTab.setSelectedIndex(findTabByName(opt2Title, jtpMainTab));
                }

            }
        });

        // 주문 통합 탭
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
        jtfItemCode = new HintTextField("상품 코드");
        jtfItemCode.setColumns(10);
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

        jpOrderConsolidation.setLayout(new BorderLayout());
        jpOrderConsolidation.add(jpOCSearch, BorderLayout.NORTH);
        jpOrderConsolidation.add(jpOCTable, BorderLayout.CENTER);

        // 검색 패널
        jpOCSearch.add(jtfOrderNum);
        jpOCSearch.add(jtfItemCode);
        jpOCSearch.add(jtfOrderer);
        jpOCSearch.add(jtfPhoneNum);
        jpOCSearch.add(jtfInvoiceNum);
        jpOCSearch.add(jtfOrderDate);
        jpOCSearch.add(jcbMarket);
        jpOCSearch.add(btnSearch);

        // 주문 통합 테이블 패널
        Object headerOrderCon[] = {"주문 번호", "상품 코드", "주문 수량", "주문자", "전화번호", "주소", "송장 번호", "주문일", "마켓"};
        Object contentsOrderCon[][] = {
                {"01", "couch-01-08-beige", "1", "황용운", "010-9574-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
                {"02", "couch-03-01-black", "2", "김만조", "010-4313-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
                {"03", "chair-03-03-blue", "5", "권순용", "010-4109-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"}
        };

        jtOrderCon = new JTable();
        DefaultTableModel modelOrderCon = new DefaultTableModel(contentsOrderCon, headerOrderCon){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jtOrderCon.setModel(modelOrderCon);
        resizeColumnWidth(jtOrderCon);
        jtOrderCon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   // 다중 선택 안되게
        jtOrderCon.setRowSorter(new TableRowSorter<>(modelOrderCon));   // 테이블 정렬 기능 추가
        jtOrderCon.getTableHeader().setReorderingAllowed(false);    // 테이블 열 이동 안되게
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
        
        // 재고 목록 탭
        Object headerItemList[] = {"", "코드", "품명", "수량", "마켓", "카테고리", "재고 위치", "최근 입고일", "다음 입고 예정일"};
        Object contentsItemList[][] = {
                {false, "couch-01-08-beige", "쿠션 A형 선인장 베이지", "117EA", "쿠팡", "", "A rack 2번 선반", "230101", "230104"},
                {false, "couch-01-09-beige", "쿠션 B형 선인장 베이지", "118EA", "쿠팡", "", "B rack 2번 선반", "230102", "230105"},
                {false, "couch-01-10-beige", "쿠션 C형 선인장 베이지", "112EA", "쿠팡", "", "B rack 3번 선반", "230103", "230106"}
        };

        jtItemList = new JTable();
        DefaultTableModel modelItemList = new DefaultTableModel(contentsItemList, headerItemList){
            @Override
            public boolean isCellEditable(int row, int col){
                if (col == 0){
                    return true;
                }
                else{
                    return false;
                }
//                return true;
            }
        };
        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
        {
            public Component getTableCellRendererComponent  // 셀렌더러
            (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                JCheckBox box= new JCheckBox();
                box.setSelected(((Boolean)value).booleanValue());
                box.setHorizontalAlignment(JLabel.CENTER);
                return box;
            }
        };

        jtItemList.setModel(modelItemList);
//        jtItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   // 다중 선택 안되게
        jtItemList.setRowSorter(new TableRowSorter<>(modelItemList));   // 테이블 정렬 기능 추가
        jtItemList.getTableHeader().setReorderingAllowed(false);    // 테이블 열 이동 안되게
        jtItemList.getColumn("").setCellRenderer(dcr);

        JCheckBox box = new JCheckBox();
        box.setHorizontalAlignment(JLabel.CENTER);
        jtItemList.getColumn("").setCellEditor(new DefaultCellEditor(box));
        resizeColumnWidth(jtItemList);
        jtItemList.getColumn("").setPreferredWidth(1);


        jpItemList.setLayout(new BorderLayout());
        jpItemList.add(new JScrollPane(jtItemList), BorderLayout.CENTER);


        jtpMainTab.addTab("재고 목록", jpItemList);

        jtpSubTab.addTab("tab1", new JPanel());

        // jtp 스타일 지정
        jtpMainTab.setFont(font2);
        jtpSubTab.setFont(font2);
        memoTab.setFont(font2);
        jtpMainTab.setBackground(Color.LIGHT_GRAY);
        jtpSubTab.setBackground(Color.LIGHT_GRAY);
        memoTab.setBackground(Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 3, 3, 40));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.DARK_GRAY));
        SwingUtilities.updateComponentTreeUI(jtpMainTab);
        SwingUtilities.updateComponentTreeUI(jtpSubTab);
        SwingUtilities.updateComponentTreeUI(memoTab);

        // 좌상단 패널
        jpLU.setLayout(new BoxLayout(jpLU,BoxLayout.Y_AXIS));

        jpLU.add(btnInventoryManagement);    // 재고 관리 버튼 추가
        jpLU.add(btnIMopt1);
        jpLU.add(btnIMopt2);
        jpLU.add(btnOrderConsolidation);

        // 좌하단 패널
        jpLD.setLayout(new BorderLayout());
        jpLD.add(memoTab);
        
        // 우상단 패널
        jpRU.setLayout(new BorderLayout());

        jpRU.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpRU.add(new JLayer<JTabbedPane>(jtpMainTab, new CloseableTabbedPaneLayerUIuseDefault()));
        
        // 우하단 패널
        jpRD.setLayout(new BorderLayout());

        jpRD.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        CloseableTabbedPaneLayerUI tmp = new CloseableTabbedPaneLayerUI();
        tmp.setComponent(jtpSubTab, jspRight);
        jpRD.add(new JLayer<JTabbedPane>(jtpSubTab,tmp));

        jspLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspRight.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jspLeft.setMinimumSize(new Dimension(sizeWidth/5-1, 100));

        jspCenter.setLeftComponent(jspLeft);
        jspCenter.setRightComponent(jspRight);
        jspCenter.setDividerLocation(sizeWidth/5);          // 중앙 jsp의 위치 설정

        jspLeft.setLeftComponent(jpLU);
        jspLeft.setRightComponent(jpLD);
        jspLeft.setDividerLocation(sizeHeight/5*3+6);         // 왼쪽 jsp의 높이 설정

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
    // 탭 메뉴에 x버튼 추가하는 클래스


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




