import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;

public class Main extends JFrame{
    JPanel jpLU, jpLD, jpRU, jpRD;
    JMenuBar jmbMenuBar;
    JMenu jmFileMenu;
    JButton btnSignOut, btnInventoryManagement, btnOrderConsolidation;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlUserName, jlIMopt1, jlIMopt2;
    JTabbedPane jtpMainTab, jtpMainTab2;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2, imgSO1, imgSO2;
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
        setVisible(true);
    }
    private void createMenu() {
        jmbMenuBar = new JMenuBar(); // MenuBar 컴포넌트 생성
        jmFileMenu = new JMenu("File"); // "파일" 메뉴 컴포넌트 생성

        JTextArea tmp = new JTextArea();


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

        imgSO1 = new ImageIcon("./img/img_Button5.jpg");
        imgSO2 = new ImageIcon("./img/img_Button6.jpg");

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
        font1 = new Font("돋움", Font.BOLD, 20);   // 왼쪽 하위메뉴 라벨 폰트
        font2 = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트

        jtpMainTab = new JTabbedPane();
        jtpMainTab2 = new JTabbedPane();

        jpLU = new JPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();

        jspCenter = new JSplitPane();
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();

        imgIM1 = new ImageIcon("./img/img_Button1.png");
        imgIM2 = new ImageIcon("./img/img_Button2.png");
        imgOC1 = new ImageIcon("./img/img_Button3.png");
        imgOC2 = new ImageIcon("./img/img_Button4.png");


        jpLU.setBackground(Color.WHITE);
        jpLD.setBackground(Color.WHITE);
        jpRU.setBackground(Color.WHITE);
        jpRD.setBackground(Color.WHITE);

        jspCenter.setDividerSize(7);
        jspLeft.setDividerSize(7);
        jspRight.setDividerSize(7);

        btnInventoryManagement = new JButton(imgIM1);
        btnOrderConsolidation = new JButton(imgOC1);
        jlIMopt1 = new JLabel("    > 개별 등록");
        jlIMopt1.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        jlIMopt1.setFont(font1);
        jlIMopt2 = new JLabel("    > 일괄 등록");
        jlIMopt2.setFont(font1);
        jlIMopt2.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        jlIMopt1.setVisible(false);
        jlIMopt2.setVisible(false);

        // 재고 관리 버튼
        btnInventoryManagement.setRolloverIcon(imgIM2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnInventoryManagement.setBorderPainted(false); // 버튼 테두리 제거
        btnInventoryManagement.setFocusPainted(false);
        btnInventoryManagement.setContentAreaFilled(false);

        btnInventoryManagement.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnInventoryManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jlIMopt1.isVisible()) {
                    jlIMopt1.setVisible(false);
                    jlIMopt2.setVisible(false);
                }
                else {
                    jlIMopt1.setVisible(true);
                    jlIMopt2.setVisible(true);
                }
            }
        });

        // 개별 등록 라벨
        jlIMopt1.addMouseListener(new MouseAdapter() {
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

        // 일괄 등록 라벨
        jlIMopt2.addMouseListener(new MouseAdapter() {
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
                    jtpMainTab.addTab(OCTitle, new JPanel());
                    jtpMainTab.setSelectedIndex(findTabByName(OCTitle, jtpMainTab));
                }
            }
        });

        jtpMainTab.addTab("tab1", new JPanel());
        jtpMainTab.addTab("tab2", new JPanel());

        jtpMainTab2.addTab("tab1", new JPanel());
        jtpMainTab2.addTab("tab2", new JPanel());

        // jtp 스타일 지정
        jtpMainTab.setFont(font2);
        jtpMainTab2.setFont(font2);
        jtpMainTab.setBackground(Color.LIGHT_GRAY);
        jtpMainTab2.setBackground(Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 3, 3, 40));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.DARK_GRAY));
        SwingUtilities.updateComponentTreeUI(jtpMainTab);
        SwingUtilities.updateComponentTreeUI(jtpMainTab2);


        // 좌상단 패널
        jpLU.setLayout(new BoxLayout(jpLU,BoxLayout.Y_AXIS));

        jpLU.add(btnInventoryManagement);    // 재고 관리 버튼 추가
        jpLU.add(jlIMopt1);
        jpLU.add(jlIMopt2);
        jpLU.add(btnOrderConsolidation);
        
        // 우상단 패널
        jpRU.setLayout(new BorderLayout());

        jpRU.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpRU.add(new JLayer<JTabbedPane>(jtpMainTab, new CloseableTabbedPaneLayerUIuseDefault()));
        
        // 우하단 패널
        jpRD.setLayout(new BorderLayout());

        jpRD.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jpRD.add(new JLayer<JTabbedPane>(jtpMainTab2, new CloseableTabbedPaneLayerUI()));

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

        getContentPane().add(jspCenter);
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
                JTabbedPane tabbedPane = (JTabbedPane) l.getView();
                int index = tabbedPane.indexAtLocation(pt.x, pt.y);
                if (index >= 1) {
                    Rectangle rect = tabbedPane.getBoundsAt(index);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
                    if (r.contains(pt)) {
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
                JTabbedPane tabbedPane = (JTabbedPane) l.getView();
                int index = tabbedPane.indexAtLocation(pt.x, pt.y);
                if (index >= 0) {
                    Rectangle rect = tabbedPane.getBoundsAt(index);
                    Dimension d = button.getPreferredSize();
                    int x = rect.x + rect.width - d.width - 2;
                    int y = rect.y + (rect.height - d.height) / 2;
                    Rectangle r = new Rectangle(x, y, d.width, d.height);
                    if (r.contains(pt)) {
                        tabbedPane.removeTabAt(index);
                        try{
                            jtpMainTab2.isEnabledAt(0);
                        } catch (Exception exception){
                            jspRight.setDividerSize(0);
                            jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                            jtpMainTab2.setVisible(false);
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
                    if (jtpMainTab2.isVisible() == false) {
                        jtpMainTab2.setVisible(true);
                        jtpMainTab2.addTab("tab1", new JPanel());
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
    public int findTabByName(String title, JTabbedPane tab)
    {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++)
        {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        new Main();


    }
}




