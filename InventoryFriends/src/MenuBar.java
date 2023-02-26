import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MenuBar extends JMenuBar {
    JMenu jmFileMenu, jmResolution;
    JMenuItem Item1, Item2, Item3, Item4;
    MenuAction menuAct;
    JSplitPane jspLeft, jspRight;
    JFrame jFrame;
    JButton btnSignOut;
    JLabel jlUserName;
    ImageIcon imgSO1, imgSO2;
    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";
    Connection con;
    MainWindow mainWindow;
    LoginFrame loginFrame;
    public MenuBar(String userid){
        jmFileMenu = new JMenu("파일"); // "파일" 메뉴 컴포넌트 생성
//
//        JMenuItem[] menuItems = new JMenuItem[3];
//        String[] items = {"열기", "해상도", "닫기"};
        menuAct = new MenuAction();

        Item1 = new JMenuItem("열기");
        Item1.addActionListener(menuAct);
        jmFileMenu.add(Item1);
        jmResolution = new JMenu("해상도");
        jmFileMenu.add(jmResolution);
        jmFileMenu.addSeparator(); // 구분선 추가
        Item2 = new JMenuItem("닫기");
        Item2.addActionListener(menuAct);
        jmFileMenu.add(Item2);
        Item3 = new JMenuItem("1280x720");
        Item4 = new JMenuItem("1920x1050");
        Item3.addActionListener(menuAct);
        Item4.addActionListener(menuAct);
        jmResolution.add(Item3);
        jmResolution.add(Item4);

//        for(int i=0; i<menuItems.length; i++) {
//            if(i == 1){
//                jmFileMenu.addSeparator(); // 숨기기와 닫기 사이에 구분선 추가
//            } else{
//                menuItems[i] = new JMenuItem(items[i]); // 메뉴 아이템 컴포넌트 생성
//                menuItems[i].addActionListener(menuAct); // 리스너 등록
//                jmFileMenu.add(menuItems[i]);
//            }
//        }

        imgSO1 = new ImageIcon("./img/img_SO1.jpg");
        imgSO2 = new ImageIcon("./img/img_SO2.jpg");

        jlUserName = new JLabel(userid + " 님");
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
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(url, user, passwd);
                    con.isClosed();
                    mainWindow.setVisible(false);
                    loginFrame.setVisible(true);
//                    new Operator();

                } catch (ClassNotFoundException k) {
                    System.out.println("문제가 발생하였습니다" + k.toString());
                } catch (SQLException k) {
                    k.printStackTrace();
                }
            }
        });

//        jmbMenuBar.setBackground(new Color(238, 238, 238));
        setBackground(Color.WHITE);
        add(jmFileMenu); // 메뉴바에 메뉴 추가
        add(new JMenu("수정"));
        add(new JMenu("업데이트"));
        add(new JMenu("도움"));

        add(Box.createHorizontalGlue());
        add(jlUserName);
        add(btnSignOut);
//        setJMenuBar(jmbMenuBar); // 메뉴바 설정

    }

    public void setJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }
    public void setJspLeft(JSplitPane jspLeft){
        this.jspLeft = jspLeft;
    }
    public void setJspRight(JSplitPane jspRight){
        this.jspRight = jspRight;
    }

    class MenuAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch(command) { // 메뉴 아이템 구분
                case "열기":
                    break;
                case "1280x720" :
                    jFrame.setSize(1280, 720);
                    jFrame.setLocationRelativeTo(null);
                    jspLeft.setDividerLocation(720/5*3+6);
//                    if(jspRight.getDividerSize() == 0){
//                        jspRight.setDividerLocation(721);
//                    } else {
//                        jspRight.setDividerLocation(720/2);
//                    }
                    break;
                case "1920x1050":
                    jFrame.setSize(1920, 1050);
                    jFrame.setLocation(0, 0);
                    break;
                case "닫기":
                    System.exit(0); // 시스템 종료
                    break;
            }
        }
    }

}
