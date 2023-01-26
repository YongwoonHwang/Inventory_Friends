import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;

public class Main extends JFrame{
    JPanel jpMain, jpLD, jpRU, jpRD, jpBottom, jpItemList;
    MenuBtnPanel jpLU;
    OrderConsolidationPanel jpOrderConsolidation;
    JMenuBar jmbMenuBar;
    JMenu jmFileMenu;
    JButton btnSignOut, btnAlarm;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlUserName, jlCalendar;
    JTabbedPane jtpMainTab, jtpSubTab;
    MemoTab memoTab;
    MemoWindow memoWindow;
    CalendarWindow windowCal;
    AlarmWindow windowAlarm;
    ImageIcon imgSO1, imgSO2, imgAlarm1, imgAlarm2, imgCal;
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
                windowAlarm.setLocation(btnAlarm.getLocationOnScreen().x-326, btnAlarm.getLocationOnScreen().y-300);
            }
            @Override
            public void componentResized(ComponentEvent e) {
                windowCal.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
                windowAlarm.setLocation(btnAlarm.getLocationOnScreen().x-326, btnAlarm.getLocationOnScreen().y-300);
            }
        });
        setVisible(true);
    }
    private void createMenu() {
        jmbMenuBar = new JMenuBar(); // MenuBar 컴포넌트 생성
        jmFileMenu = new JMenu("File"); // "파일" 메뉴 컴포넌트 생성

        JMenuItem[] menuItems = new JMenuItem[4];
        String[] items = {"Debug Button", "Alarm Debug Button", "Open", "Close"};

        menuAct = new MenuAction();

        for(int i=0; i<menuItems.length; i++) {
            menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
            menuItems[i].addActionListener(menuAct); // 리스너 등록
            jmFileMenu.add(menuItems[i]);

            if(i == 2)
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
        jpLU = new MenuBtnPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();
        jpBottom = new JPanel();
        jpBottom.setLayout(new BorderLayout());
        jpOrderConsolidation = new OrderConsolidationPanel();
        jpItemList = new ItemListPanel();

        jspCenter = new JSplitPane();
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();

        imgAlarm1 = new ImageIcon("./img/img_Alarm1.jpg");
        imgAlarm2 = new ImageIcon("./img/img_Alarm2.jpg");
        imgCal = new ImageIcon("./img/img_Cal.jpg");

        jpLU.setBackground(Color.WHITE);
        jpLD.setBackground(Color.WHITE);
        jpRU.setBackground(Color.WHITE);
        jpRD.setBackground(Color.WHITE);

        jspCenter.setDividerSize(7);
        jspLeft.setDividerSize(7);
        jspRight.setDividerSize(7);

        jlCalendar = new JLabel(imgCal);
        jlCalendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        windowCal = new CalendarWindow();
        windowCal.setMemoWindow(memoWindow);
        memoTab.setMemoWindow(memoWindow);
        windowCal.setMemoTab(memoTab);

        windowAlarm = new AlarmWindow();
        jlCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(windowCal.isVisible()){
                    windowCal.setVisible(false);
                }else{
                    windowCal.setVisible(true);
                }
            }
        });

        // 주문 통합 탭
        jpOrderConsolidation.setJtpSubTab(jtpSubTab);
        
        // 알림 버튼
        btnAlarm = new JButton(imgAlarm1);
        btnAlarm.setRolloverIcon(imgAlarm2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnAlarm.setPreferredSize(new Dimension(74, 29)); // 버튼 크기 지정
        btnAlarm.setBorderPainted(false); // 버튼 테두리 제거
        btnAlarm.setFocusPainted(false);
        btnAlarm.setContentAreaFilled(false);
        btnAlarm.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        btnAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowAlarm.setAlarmLocate();

                if(windowAlarm.isVisible()){
                    windowAlarm.setVisible(false);
                } else {
                    windowAlarm.setVisible(true);
                }
            }
        });
        
        // 재고 목록 탭
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
        jpLU.setJtpMainTab(jtpMainTab);
        jpLU.setJpOrderConsolidation(jpOrderConsolidation);

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
                case "Alarm Debug Button":
                    windowAlarm.addAlarm();
                    windowAlarm.revalidate();
                    windowAlarm.repaint();
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
    public static void main(String[] args) {
        new Main();
    }
}




