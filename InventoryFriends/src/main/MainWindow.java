package main;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainWindow extends JFrame {
    JPanel jpMain, jpRD, jpBottom;
    MainTabPanel jpMainTab;
    MemoTabPanel jpMemoTab;
    MenuBtnPanel jpMenuBtn;
    OrderConsolidationPanel jpOrderConsolidation;
    BatchRegistrationPanel jpBatchRegistration;
    IndividualRegistrationPanel jpIndividualRegistration;
    ItemStatusPanel jpItemStatusPanel;
    MenuBar jmbMenuBar;
    JButton btnAlarm;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlCalendar;
    JTabbedPane jtpSubTab;
    CalendarWindowForMemo winCalendar;
    MarketInformationWindow winMarketInfo;
    AlarmWindow winAlarm;
    ImageIcon imgAlarm1, imgAlarm2, imgCal;
    Font font2;
    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";
    Connection con = null;
    Statement stmt = null;

    int sizeWidth = 1280;
    int sizeHeight = 720;
    public MainWindow(String userid, String username, String useridx) {
        super("Inventory Friends 0.0.1");
        winMarketInfo = new MarketInformationWindow(username, useridx);
        winMarketInfo.dbUserIdx = useridx;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement(); //db와 연결된 conn객체로부터 stmt객체 획득


            StringBuilder sb1 = new StringBuilder();
            String sql1 = sb1.append("SELECT EXISTS (select * from MarketInformation where user_idx = '" + useridx + "') as success").toString();
            System.out.println("sql 입력문 = " + sql1);
            ResultSet result = stmt.executeQuery(sql1); //query문 날리기
            while(result.next()) {
                String flag = result.getString("success");
                System.out.println("데이터존재유무 값은? = "+ flag);

                if(Integer.parseInt(flag) == 0){
                    System.out.println("사용자의 마켓정보를 검색하지못했으므로 마켓정보 입력창을 출력합니다");
                    winMarketInfo.setVisible(true);
                }else{
                    System.out.println(userid + "의 사용자테이블 검색 성공. 마켓정보 입력창을 띄우지 않습니다");
                }

            }


        } catch (ClassNotFoundException k) {
            System.out.println("사용자의 마켓정보를 검색하지못했으므로 마켓정보 입력창을 출력합니다 " + k.toString());
        } catch (SQLException k) {
            k.printStackTrace();
        } finally {
            try { //자원해제
                if (con != null && !con.isClosed())
                    con.close();
            } catch (SQLException k) {
                k.printStackTrace();
            }
        }

        createPanel(userid, username, useridx);
        setSize(sizeWidth, sizeHeight);
        setLocationRelativeTo(null);        // 화면 가운데에 창 배치
        setResizable(false);                // 화면 크기 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                winCalendar.setLocation(jlCalendar.getLocationOnScreen().x+1, jlCalendar.getLocationOnScreen().y-181);
                winAlarm.setLocation(btnAlarm.getLocationOnScreen().x-326, btnAlarm.getLocationOnScreen().y-300);
                if(findTabByName("재고 관리(개별 등록)", jpMainTab.jtpMainTab) == jpMainTab.jtpMainTab.getSelectedIndex()){
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
                if(findTabByName("재고 관리(개별 등록)", jpMainTab.jtpMainTab) == jpMainTab.jtpMainTab.getSelectedIndex()){
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

                jspLeft.setDividerLocation(getSize().height/5*3+6);
                if(jspRight.getDividerSize() == 0){
                    jspRight.setDividerLocation(getSize().height);
                }
                else {
                    jspRight.setDividerLocation(getRootPane().getSize().height - 400);
                }
            }
        });
        setVisible(true);
    }
    private void createPanel(String userid, String username, String useridx){

        jmbMenuBar = new MenuBar(useridx);
        jmbMenuBar.winMarketInfo = winMarketInfo;
        jmbMenuBar.setJFrame(this);
        jmbMenuBar.mainWindow = this;
        jmbMenuBar.findusername(username);
        setJMenuBar(jmbMenuBar);
        // 폰트 설정
        font2 = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트

        jtpSubTab = new JTabbedPane();

        jpMain = new JPanel();
        jpMenuBtn = new MenuBtnPanel();
        jpMemoTab = new MemoTabPanel();
        jpMainTab = new MainTabPanel(userid);
        jpMainTab.setSubTab(jtpSubTab);
        jpRD = new JPanel();
        jpBottom = new JPanel();
        jpBottom.setLayout(new BorderLayout());
        jpOrderConsolidation = new OrderConsolidationPanel(useridx);
        winMarketInfo.jtOC = jpOrderConsolidation.jtOrderCon;
        jpIndividualRegistration = new IndividualRegistrationPanel(userid);
        jpIndividualRegistration.dbUserIdx = useridx;
        jpBatchRegistration = new BatchRegistrationPanel(userid);
        jpBatchRegistration.dbUserIdx = useridx;
        jpItemStatusPanel = new ItemStatusPanel();

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
        jpOrderConsolidation.jtOrderCon.jspRight = jspRight;

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
            // 재고 목록 패널
        jpMainTab.jpItemList.jspRight = jspRight;
        jpMainTab.jpItemList.jtpSubTab = jtpSubTab;
        jpMainTab.jpItemList.jpItemStatusPanel = jpItemStatusPanel;
            // 개별등록 패널
        jpIndividualRegistration.setSubTab(jtpSubTab);
        jpIndividualRegistration.setJspRight(jspRight);
        jpIndividualRegistration.jpItemStatusPanel = jpItemStatusPanel;
        jpIndividualRegistration.setModelItemList((DefaultTableModel)jpMainTab.jpItemList.getTableModel());
        jpIndividualRegistration.jtpMainTab = jpMainTab.jtpMainTab;
        jpIndividualRegistration.jcbCategory2 = jpItemStatusPanel.modifyPanel.jcbCategory;
        jpIndividualRegistration.jcbCategory3 = jpMainTab.jpItemList.jcbCategory;
        jpMainTab.jpItemList.jtItemList.jspRight = jspRight;
            // 카테코리 콤보박스 데이터 설정
        jpMainTab.jpItemList.setComboboxData(jpIndividualRegistration.jcbCategory);
            // 일괄등록 패널
        jpBatchRegistration.jcbCategory = jpIndividualRegistration.jcbCategory;
        jpBatchRegistration.jcbCategory2 = jpItemStatusPanel.modifyPanel.jcbCategory;
        jpBatchRegistration.jcbCategory3 = jpMainTab.jpItemList.jcbCategory;
        jpBatchRegistration.modelItemList = (DefaultTableModel)jpMainTab.jpItemList.getTableModel();
        jpBatchRegistration.categoryList = jpIndividualRegistration.categoryList;
            // 수정 패널
        jpItemStatusPanel.modifyPanel.modelItemList = (DefaultTableModel)jpMainTab.jpItemList.getTableModel();
        jpItemStatusPanel.modifyPanel.jcbCategory2 = jpMainTab.jpItemList.jcbCategory;
        jpItemStatusPanel.modifyPanel.jcbCategory3 = jpIndividualRegistration.jcbCategory;
        jpItemStatusPanel.modifyPanel.jtpMainTab = jpMainTab.jtpMainTab;
            //카테코리 콤보박스 데이터 설정
        jpItemStatusPanel.modifyPanel.setComboboxData(jpIndividualRegistration.jcbCategory);

        // 우하단 패널
        jpRD.setLayout(new BorderLayout());
            // 재고 현황 패널
        jpItemStatusPanel.jspRight = jspRight;
        jpItemStatusPanel.jtItemList = jpMainTab.jpItemList.jtItemList;
        jpItemStatusPanel.jtpSubTab = jtpSubTab;
        jpItemStatusPanel.jtpMainTab = jpMainTab.jtpMainTab;
        jpItemStatusPanel.modifyPanel.categoryList = jpIndividualRegistration.categoryList;
        jpItemStatusPanel.modifyPanel.jspRight = jspRight;
        jpItemStatusPanel.modifyPanel.jpItemStatusPanel = jpItemStatusPanel;
        jpItemStatusPanel.modifyPanel.jtpSubTab = jtpSubTab;


        jpMainTab.jpItemList.jpItemStatusPanel = jpItemStatusPanel;
        jpMainTab.jpItemList.jtItemList.jpItemStatusPanel = jpItemStatusPanel;

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
        new MainWindow("sy999", "권순용", "1");
    }
}
