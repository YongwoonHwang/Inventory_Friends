import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main extends JFrame{
    JPanel jpLU, jpLD, jpRU, jpRD;
    JMenuBar menuBar;
    JMenu fileMenu;
    JButton signoutButton, btnIM, btnOC; //IM = Inventory Management,OC = Order Consolidation
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlIMopt1, jlIMopt2;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2;
    MenuAction menuAct;
    Font font;
    private int sizeWidth = 1280;
    private int sizeHeight = 720;
    public Main() {
        super("Inventory Friends 0.0.1");
        createMenu();
        createPanel();
        setSize(sizeWidth, sizeHeight);
        setLocationRelativeTo(null);        // 화면 가운데에 창 배치
        setResizable(false);                // 화면 크기 고정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void createMenu() {
        menuBar = new JMenuBar(); // MenuBar 컴포넌트 생성
        fileMenu = new JMenu("File"); // "파일" 메뉴 컴포넌트 생성
        signoutButton = new JButton("Sign Out");

        JMenuItem[] menuItems = new JMenuItem[2];
        String[] items = {"Open", "Close"};

        menuAct = new MenuAction();

        for(int i=0; i<menuItems.length; i++) {
            menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
            menuItems[i].addActionListener(menuAct); // 리스너 등록
            fileMenu.add(menuItems[i]);

            if(i == 0)
                fileMenu.addSeparator(); // 숨기기와 닫기 사이에 구분선 추가
        }

        menuBar.add(fileMenu); // 메뉴바에 메뉴 추가
        menuBar.add(new JMenu("Edit"));
        menuBar.add(new JMenu("Update"));
        menuBar.add(new JMenu("Help"));

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(signoutButton);

        setJMenuBar(menuBar); // 메뉴바 설정
    }
    private void createPanel(){
        font = new Font("돋움",Font.BOLD, 20);

        jpLU = new JPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();

        jpLU.setBackground(Color.WHITE);
        jpLD.setBackground(Color.WHITE);
        jpRU.setBackground(Color.WHITE);
        jpRD.setBackground(Color.WHITE);

        imgIM1 = new ImageIcon("./img/image_Button1.png");
        imgIM2 = new ImageIcon("./img/image_Button2.png");
        imgOC1 = new ImageIcon("./img/image_Button3.png");
        imgOC2 = new ImageIcon("./img/image_Button4.png");

        btnIM = new JButton(imgIM1);
        btnOC = new JButton(imgOC1);
        jlIMopt1 = new JLabel("    > 개별 등록");
        jlIMopt1.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        jlIMopt1.setFont(font);
        jlIMopt2 = new JLabel("    > 일괄 등록");
        jlIMopt2.setFont(font);
        jlIMopt2.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        jlIMopt1.setVisible(false);
        jlIMopt2.setVisible(false);

        btnIM.setRolloverIcon(imgIM2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnIM.setBorderPainted(false); // 버튼 테두리 제거
        btnIM.setFocusPainted(false);
        btnIM.setContentAreaFilled(false);

        btnIM.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnIM.addActionListener(new ActionListener() {
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

        btnOC.setRolloverIcon(imgOC2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnOC.setBorderPainted(false); // 버튼 테두리 제거
        btnOC.setFocusPainted(false);
        btnOC.setContentAreaFilled(false);

        btnOC.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnOC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Button");
            }
        });

        jpLU.setLayout(new BoxLayout(jpLU,BoxLayout.Y_AXIS));
        
        // 패널에 컴포넌트 추가
        jpLU.add(btnIM);    // 재고 관리 버튼 추가
        jpLU.add(jlIMopt1);
        jpLU.add(jlIMopt2);
        jpLU.add(btnOC);

        jspCenter = new JSplitPane();
        jspLeft = new JSplitPane();
        jspRight = new JSplitPane();

        jspLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspRight.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jspLeft.setMinimumSize(new Dimension(sizeWidth/5-1, 100));

        jspCenter.setLeftComponent(jspLeft);
        jspCenter.setRightComponent(jspRight);
        jspCenter.setDividerLocation(sizeWidth/5);          // 중앙 세로선의 위치 설정

        jspLeft.setLeftComponent(jpLU);
        jspLeft.setRightComponent(jpLD);
        jspLeft.setDividerLocation(sizeHeight/6*4);         // 왼쪽 jsp의 높이 설정

        jspRight.setLeftComponent(jpRU);
        jspRight.setRightComponent(jpRD);
        jspRight.setDividerLocation(sizeHeight/2);          // 오른쪽 jsp의 높이 설정

        getContentPane().add(jspCenter);
    }
    class MenuAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch(command) { // 메뉴 아이템 구분
                case "Open":
                    break;
                case "Close":
                    System.exit(0); // 시스템 종료
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}