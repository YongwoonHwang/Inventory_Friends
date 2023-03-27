package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


public class LoginFrame extends JFrame {


    private JPanel contentPane;
    private HintTextField tfUsername;

    LoginFrame loginFrame;

    private HintTextFieldForPassword tfPassword;
    private JButton loginBtn;
    public MainWindow mainWindow;

    Connection con = null;
    Statement stmt = null;

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";

    String username;

    String dbName = "ifdb";
    String userid;

    Operator o = null;




    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    LoginFrame frame = new LoginFrame();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
    private void JoinWebsite(JLabel JoinLink) {
        JoinLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.google.com/"));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }


    public String findusername() {
        String sql2 = "SELECT user_name FROM user WHERE userID = '" + userid + "'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            pstmt = con.prepareStatement(sql2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                username = rs.getString("user_name");
                System.out.println(username);
                return username;

            }
        } catch (Exception e) {
            System.out.println("검색 오류" + e);
            return null;

        } finally {
//            username = this.findusername();
//            System.out.println("최종 반환 변수는? = " + username);
            return username;

        }
    }

    private void ForgetWebsite(JLabel ForgetLink) {
        ForgetLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.naver.com/"));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }


    /**
     * Create the frame.
     */

    public LoginFrame(Operator _o) {

        o = _o;

        setTitle("Inventory Friends(Login)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 720);
        setLocationRelativeTo(null); /* java.awt의 window, null 값인 경우 윈도우를 화면의 가운데에 띄움 */


        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane); /* 위 설정대로 contentPane을 바꾼다*/
        contentPane.setLayout(null);

        JCheckBox chkbox = new JCheckBox("계정 정보 기억하기");
        chkbox.setBounds(101, 400, 200, 30);
        chkbox.setFont(new Font("Dialog", Font.BOLD, 12));
        chkbox.setBackground(Color.LIGHT_GRAY);
        contentPane.add(chkbox);

        JLabel JoinInfo = new JLabel("계정이 없으신가요?");
        JoinInfo.setFont(new Font("Dialog", Font.BOLD, 12));
        JoinInfo.setBounds(315, 550, 150, 30);
        contentPane.add(JoinInfo);

        JLabel JoinLink = new JLabel("회원가입");
        JoinLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JoinLink.setFont(new Font("Dialog", Font.BOLD, 12));
        JoinLink.setBounds(450, 550, 69, 30);
        JoinLink.setForeground(new Color(0, 103, 163));
        contentPane.add(JoinLink);
        JoinWebsite(JoinLink);

        JLabel ForgetInfo = new JLabel("아이디를 잊어버리셨나요?");
        ForgetInfo.setFont(new Font("Dialog", Font.BOLD, 12));
        ForgetInfo.setBounds(280, 580, 150, 30);
        contentPane.add(ForgetInfo);

        JLabel ForgetLink = new JLabel("계정 찾기");
        ForgetLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ForgetLink.setFont(new Font("Dialog", Font.BOLD, 12));
        ForgetLink.setBounds(450, 580, 69, 30);
        ForgetLink.setForeground(new Color(0, 103, 163));
        contentPane.add(ForgetLink);
        ForgetWebsite(ForgetLink);

        JLabel TitleText = new JLabel("Sign in");
        TitleText.setFont(new Font("Dialog", Font.BOLD, 24));
        TitleText.setBounds(260, 90, 100, 55);
        contentPane.add(TitleText);

        JLabel lblId = new JLabel("계정 ID");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(101, 202, 69, 45);
        contentPane.add(lblId);

        JLabel lblPassword = new JLabel("비밀번호");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPassword.setBounds(101, 300, 69, 45);
        contentPane.add(lblPassword);

        tfUsername = new HintTextField("아이디를 입력해주세요");
        tfUsername.setBounds(101, 250, 400, 30);
        tfUsername.setColumns(20);
        contentPane.add(tfUsername);



        /*joinBtn = new JButton("회원가입");
        joinBtn.setBounds(229, 154, 104, 29);
        contentPane.add(joinBtn); */

        loginBtn = new JButton("로그인");
        loginBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(new Color(0, 103, 163));
        loginBtn.setBounds(101, 500, 400, 45);
        contentPane.add(loginBtn);


        tfPassword = new HintTextFieldForPassword("password");
        tfPassword.setColumns(20);
        tfPassword.setBounds(101, 350, 400, 30);
        contentPane.add(tfPassword);


        //Enter키 입력시 발생 이벤트

        Action Enter = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginBtn.doClick();
            }
        };

        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        tfUsername.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "Enter");
        tfPassword.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, "Enter");
        tfUsername.getActionMap().put("Enter", Enter);
        tfPassword.getActionMap().put("Enter", Enter);


        setVisible(true);
        //회원가입 액션
        /*joinBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JoinFrame frame = new JoinFrame();
            }
        }); */


        //로그인 액션


        loginBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                userid = tfUsername.getText().toLowerCase();
                String upass = "";
                for (int i = 0; i < tfPassword.getPassword().length; i++) {
                    upass = upass + tfPassword.getPassword()[i];
                }

                if (userid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요."
                            , "로그인 실패", JOptionPane.ERROR_MESSAGE);
                    System.out.println("로그인 실패 > 로그인 정보 미입력");
                } else if (userid != null && upass != null) {
                    if (o.db.logincheck(userid, upass)) {
                        System.out.println("로그인 성공");
                        JOptionPane.showMessageDialog(null, "로그인에 성공했습니다");
                        setVisible(false);
                        dispose(); /* 현재 윈도우만 닫히게하는 코드*/

                        /////////////////////////////////////////


                        ///////////////////////////////////////////

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection(url, user, passwd);
                            stmt = con.createStatement(); //db와 연결된 conn객체로부터 stmt객체 획득


                            StringBuilder sb = new StringBuilder();
                            String sql = sb.append("create table if not exists " + userid + "_ItemList(")
                                    .append("id int NOT NULL AUTO_INCREMENT PRIMARY KEY,")
                                    .append("User_id bigint NOT NULL,")
                                    .append("Category varchar(100),")
                                    .append("Code varchar(100) NOT NULL,")
                                    .append("ProductName varchar(100) NOT NULL,")
                                    .append("Quantity int NOT NULL,")
                                    .append("Market varchar(100),")
                                    .append("ProductLocation varchar(100),")
                                    .append("StockingDate varchar(100),")
                                    .append("EDA varchar(100),")
                                    .append("Image varchar(100),")
                                    .append("CONSTRAINT user_id_fk FOREIGN KEY(User_id) REFERENCES user(user_idx) ON DELETE CASCADE")
                                    .append(");").toString();

                            stmt.execute(sql); //query문 날리기

//                            pstmt = con.prepareStatement(sql);
//                            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                            System.out.println(userid + "의 사용자테이블 검색 성공");
                        } catch (ClassNotFoundException k) {
                            System.out.println("사용자의 테이블을 검색하지못했으므로 새로운 테이블을 생성합니다 " + k.toString());
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
                        username = findusername();
                        tfUsername.reset();
                        tfPassword.reset();
                        mainWindow = new MainWindow(userid, username, o.db.getUseridx(userid));
                        mainWindow.jmbMenuBar.loginFrame = loginFrame;

                        System.out.println("Loginframe에서의 반환값= " + userid);
                        mainWindow.setVisible(true);

//                        new Main();
                    } else {
                        System.out.println("로그인 실패 > 로그인 정보 불일치");
                        System.out.println(upass);
//                        System.out.println(userid);
                        JOptionPane.showMessageDialog(null, "로그인에 실패했습니다");
                    }
                }
            }
        });
    }
}
