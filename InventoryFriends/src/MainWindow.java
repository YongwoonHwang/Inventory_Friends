import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    JPanel jpMain, jpRD, jpBottom;
    MainTabPanel jpMainTab;
    MemoTabPanel jpMemoTab;
    MenuBtnPanel jpMenuBtn;
    OrderConsolidationPanel jpOrderConsolidation;
    BatchRegistrationPanel jpBatchRegistration;
    IndividualRegistrationPanel jpIndividualRegistration;
//    InventoryStatusTable jpIndividualStatusTable;
    MenuBar jmbMenuBar;
    JButton btnAlarm;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlCalendar;
    JTabbedPane jtpSubTab;
    CalendarWindowForMemo winCalendar;
    AlarmWindow winAlarm;
    ImageIcon imgAlarm1, imgAlarm2, imgCal;
    Font font2;

    int sizeWidth = 1280;
    int sizeHeight = 720;
    public MainWindow() {
        super("Inventory Friends 0.0.1");
        createPanel();
        setSize(sizeWidth, sizeHeight);
        setLocationRelativeTo(null);        // 화면 가운데에 창 배치
        setResizable(false);                // 화면 크기 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                winCalendar.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
                winAlarm.setLocation(btnAlarm.getLocationOnScreen().x-326, btnAlarm.getLocationOnScreen().y-300);
                if(findTabByName("재고 관리(개별 등록)", jpMainTab.jtpMainTab) != -1){
                    jpIndividualRegistration.setLocationCalendar1(
                            jpIndividualRegistration.btnCal1.getLocationOnScreen().x,
                            jpIndividualRegistration.btnCal1.getLocationOnScreen().y-180);
                    jpIndividualRegistration.setLocationCalendar2(
                            jpIndividualRegistration.btnCal2.getLocationOnScreen().x,
                            jpIndividualRegistration.btnCal2.getLocationOnScreen().y+24);
                    jpIndividualRegistration.setLocationCalc(
                            jpIndividualRegistration.btnCalc.getLocationOnScreen().x,
                            jpIndividualRegistration.btnCalc.getLocationOnScreen().y+24);
                }
            }
            @Override
            public void componentResized(ComponentEvent e) {
                winCalendar.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
                winAlarm.setLocation(btnAlarm.getLocationOnScreen().x-326, btnAlarm.getLocationOnScreen().y-300);
                if(findTabByName("재고 관리(개별 등록)", jpMainTab.jtpMainTab) != -1){
                    jpIndividualRegistration.setLocationCalendar1(
                            jpIndividualRegistration.btnCal1.getLocationOnScreen().x,
                            jpIndividualRegistration.btnCal1.getLocationOnScreen().y-180);
                    jpIndividualRegistration.setLocationCalendar2(
                            jpIndividualRegistration.btnCal2.getLocationOnScreen().x,
                            jpIndividualRegistration.btnCal2.getLocationOnScreen().y+24);
                }
            }
        });
        setVisible(true);
    }
    private void createPanel(){
        jmbMenuBar = new MenuBar();
        jmbMenuBar.setJFrame(this);
        setJMenuBar(jmbMenuBar);
        // 폰트 설정
        font2 = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트

        jtpSubTab = new JTabbedPane();

        jpMain = new JPanel();
        jpMenuBtn = new MenuBtnPanel();
        jpMemoTab = new MemoTabPanel();
        jpMainTab = new MainTabPanel();
        jpRD = new JPanel();
        jpBottom = new JPanel();
        jpBottom.setLayout(new BorderLayout());
        jpOrderConsolidation = new OrderConsolidationPanel();
        jpIndividualRegistration = new IndividualRegistrationPanel();
//        jpIndividualStatusTable = new InventoryStatusTable();
        jpBatchRegistration = new BatchRegistrationPanel();

        jspCenter = new JSplitPane();
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();

        jmbMenuBar.setJspLeft(jspLeft);
        jmbMenuBar.setJspRight(jspRight);

        imgAlarm1 = new ImageIcon("./img/img_Alarm1.jpg");
        imgAlarm2 = new ImageIcon("./img/img_Alarm2.jpg");
        imgCal = new ImageIcon("./img/img_Cal.jpg");

        jpRD.setBackground(Color.WHITE);

        jspCenter.setDividerSize(7);
        jspLeft.setDividerSize(7);
        jspRight.setDividerSize(7);

        jlCalendar = new JLabel(imgCal);
        jlCalendar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        winCalendar = new CalendarWindowForMemo();
        winCalendar.setMemoWindow(jpMemoTab.memoWindow);
        winCalendar.setMemoTab(jpMemoTab.memoTab);

        winAlarm = new AlarmWindow();
        jlCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(winCalendar.isVisible()){
                    winCalendar.setVisible(false);
                }else{
                    winCalendar.setVisible(true);
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
                winAlarm.setAlarmLocate();

                if(winAlarm.isVisible()){
                    winAlarm.setVisible(false);
                } else {
                    winAlarm.setVisible(true);
                }
            }
        });

        // 재고 목록 탭
        jtpSubTab.addTab("tab1", new JPanel());

        // jtp 스타일 지정
        jtpSubTab.setFont(font2);
        jtpSubTab.setBackground(Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 3, 3, 40));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.DARK_GRAY));
        SwingUtilities.updateComponentTreeUI(jtpSubTab);

        // 좌상단 패널
        jpMenuBtn.setJtpMainTab(jpMainTab.jtpMainTab);
        jpMenuBtn.setJpOrderConsolidation(jpOrderConsolidation);
        jpMenuBtn.setJpIndividualRegistration(jpIndividualRegistration);
        jpMenuBtn.setJpBatchRegistration(jpBatchRegistration);

        //우상단 패널
        jpIndividualRegistration.setSubTab(jtpSubTab);

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

        jspLeft.setLeftComponent(jpMenuBtn);
        jspLeft.setRightComponent(jpMemoTab);
        jspLeft.setDividerLocation(sizeHeight/5*3+6);         // 왼쪽 jsp의 높이 설정

        jspRight.setLeftComponent(jpMainTab);
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

    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
