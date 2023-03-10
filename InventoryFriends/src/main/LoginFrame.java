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
            System.out.println("?????? ??????" + e);
            return null;

        } finally {
//            username = this.findusername();
            System.out.println("?????? ?????? ?????????? = " + username);
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
        setLocationRelativeTo(null); /* java.awt??? window, null ?????? ?????? ???????????? ????????? ???????????? ?????? */


        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane); /* ??? ???????????? contentPane??? ?????????*/
        contentPane.setLayout(null);

        JCheckBox chkbox = new JCheckBox("?????? ?????? ????????????");
        chkbox.setBounds(101, 400, 200, 30);
        chkbox.setFont(new Font("Dialog", Font.BOLD, 12));
        chkbox.setBackground(Color.LIGHT_GRAY);
        contentPane.add(chkbox);

        JLabel JoinInfo = new JLabel("????????? ????????????????");
        JoinInfo.setFont(new Font("Dialog", Font.BOLD, 12));
        JoinInfo.setBounds(315, 550, 150, 30);
        contentPane.add(JoinInfo);

        JLabel JoinLink = new JLabel("????????????");
        JoinLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JoinLink.setFont(new Font("Dialog", Font.BOLD, 12));
        JoinLink.setBounds(450, 550, 69, 30);
        JoinLink.setForeground(new Color(0, 103, 163));
        contentPane.add(JoinLink);
        JoinWebsite(JoinLink);

        JLabel ForgetInfo = new JLabel("???????????? ??????????????????????");
        ForgetInfo.setFont(new Font("Dialog", Font.BOLD, 12));
        ForgetInfo.setBounds(280, 580, 150, 30);
        contentPane.add(ForgetInfo);

        JLabel ForgetLink = new JLabel("?????? ??????");
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

        JLabel lblId = new JLabel("?????? ID");
        lblId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblId.setBounds(101, 202, 69, 45);
        contentPane.add(lblId);

        JLabel lblPassword = new JLabel("????????????");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPassword.setBounds(101, 300, 69, 45);
        contentPane.add(lblPassword);

        tfUsername = new HintTextField("???????????? ??????????????????");
        tfUsername.setBounds(101, 250, 400, 30);
        tfUsername.setColumns(20);
        contentPane.add(tfUsername);



        /*joinBtn = new JButton("????????????");
        joinBtn.setBounds(229, 154, 104, 29);
        contentPane.add(joinBtn); */

        loginBtn = new JButton("?????????");
        loginBtn.setFont(new Font("Dialog", Font.BOLD, 16));
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(new Color(0, 103, 163));
        loginBtn.setBounds(101, 500, 400, 45);
        contentPane.add(loginBtn);


        tfPassword = new HintTextFieldForPassword("password");
        tfPassword.setColumns(20);
        tfPassword.setBounds(101, 350, 400, 30);
        contentPane.add(tfPassword);


        //Enter??? ????????? ?????? ?????????

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
        //???????????? ??????
        /*joinBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JoinFrame frame = new JoinFrame();
            }
        }); */


        //????????? ??????


        loginBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                userid = tfUsername.getText().toLowerCase();
                String upass = "";
                for (int i = 0; i < tfPassword.getPassword().length; i++) {
                    upass = upass + tfPassword.getPassword()[i];
                }

                if (userid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null, "???????????? ???????????? ?????? ??????????????????."
                            , "????????? ??????", JOptionPane.ERROR_MESSAGE);
                    System.out.println("????????? ?????? > ????????? ?????? ?????????");
                } else if (userid != null && upass != null) {
                    if (o.db.logincheck(userid, upass)) {
                        System.out.println("????????? ??????");
                        JOptionPane.showMessageDialog(null, "???????????? ??????????????????");
                        setVisible(false);
                        dispose(); /* ?????? ???????????? ??????????????? ??????*/

                        /////////////////////////////////////////


                        ///////////////////////////////////////////

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection(url, user, passwd);
                            stmt = con.createStatement(); //db??? ????????? conn??????????????? stmt?????? ??????


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

                            stmt.execute(sql); //query??? ?????????

//                            pstmt = con.prepareStatement(sql);
//                            pstmt.execute("USE " + dbName); // ????????? DB??? ????????????.
                            System.out.println(userid + "??? ?????????????????? ?????? ??????");
                        } catch (ClassNotFoundException k) {
                            System.out.println("???????????? ???????????? ??????????????????????????? ????????? ???????????? ??????????????? " + k.toString());
                        } catch (SQLException k) {
                            k.printStackTrace();
                        } finally {
                            try { //????????????
                                if (con != null && !con.isClosed())
                                    con.close();
                            } catch (SQLException k) {
                                k.printStackTrace();
                            }
                        }
                        tfUsername.reset();
                        tfPassword.reset();
                        mainWindow = new MainWindow(userid);
                        mainWindow.jmbMenuBar.loginFrame = loginFrame;
                        mainWindow.setUseridx(o.db.getUseridx(userid));
                        String username = findusername();
                        System.out.println("Loginframe????????? ?????????= " + userid);
                        mainWindow.findusername(username);
                        mainWindow.setVisible(true);

//                        new Main();
                    } else {
                        System.out.println("????????? ?????? > ????????? ?????? ?????????");
                        System.out.println(upass);
//                        System.out.println(userid);
                        JOptionPane.showMessageDialog(null, "???????????? ??????????????????");
                    }
                }
            }
        });
    }
}
