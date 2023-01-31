package com.localapp.localtest.LoginUi;


import com.localapp.localtest.MainFrame.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RequiredArgsConstructor
public class LoginFrame extends JFrame {
    
    

    private JPanel contentPane;
    private JTextField tfUsername;

    private JPasswordField tfPassword;
    private JButton loginBtn;

    Connection con = null;
    Statement stmt = null;

    String url = "jdbc:mysql://inventoryfriends.cxtfsxnxj3jt.ap-northeast-1.rds.amazonaws.com/kmj?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470";

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



    public class HintTextField extends JTextField{
        Font gainFont = new Font("Dialog",Font.BOLD,12);
        Font lostFont = new Font("Dialog",Font.ITALIC,11);

        public  HintTextField(final String hint){

            setText(hint);
            setFont(lostFont);
            setForeground(Color.GRAY);

            this.addFocusListener(new FocusAdapter() {


                public void focusGained(FocusEvent e){
                    if (getText().equals((hint))) {
                        setText("");
                        setFont(gainFont);
                    }else {
                        setText(getText());
                        setFont(gainFont);
                    }
                }


                public void focusLost(FocusEvent e){
                    if (getText().equals(hint) || getText().length() == 0){
                        setText(hint);
                        setFont(lostFont);
                        setForeground(Color.GRAY);
                    } else {
                        setText(getText());
                        setFont(gainFont);
                        setForeground(Color.BLACK);

                    }
                }
            });
        }
    }

    public class HintPassField extends JPasswordField{
        Font gainFont = new Font("Dialog",Font.BOLD,12);
        Font lostFont = new Font("Dialog",Font.ITALIC,11);

        public  HintPassField(final String hint){

            setText(hint);
            setFont(lostFont);
            setForeground(Color.GRAY);

            this.addFocusListener(new FocusAdapter() {


                public void focusGained(FocusEvent e){
                    if (getText().equals((hint))) {
                        setText("");
                        setFont(gainFont);
                    }else {
                        setText(getText());
                        setFont(gainFont);
                    }
                }


                public void focusLost(FocusEvent e){
                    if (getText().equals(hint) || getText().length() == 0){
                        setText(hint);
                        setFont(lostFont);
                        setForeground(Color.GRAY);
                    } else {
                        setText(getText());
                        setFont(gainFont);
                        setForeground(Color.BLACK);

                    }
                }
            });
        }
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
        tfUsername.setBounds(101, 250, 400, 45);
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


        tfPassword = new HintPassField("비밀번호를 입력해주세요");
        tfPassword.setColumns(20);
        tfPassword.setBounds(101, 350, 400, 45);
        contentPane.add(tfPassword);


        //Enter키 입력시 발생 이벤트

        Action Enter = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginBtn.doClick();
            }
        };

        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false);
        tfUsername.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,"Enter");
        tfPassword.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,"Enter");
        tfUsername.getActionMap().put("Enter",Enter);
        tfPassword.getActionMap().put("Enter",Enter);
        






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

                String uid = tfUsername.getText();
                String upass = "";
                for (int i = 0; i < tfPassword.getPassword().length; i++) {
                    upass = upass + tfPassword.getPassword()[i];
                }
               
                if(uid.equals("") || upass.equals("")) {
                    JOptionPane.showMessageDialog(null,"아이디와 비밀번호 모두 입력해주세요."
                            ,"로그인 실패",JOptionPane.ERROR_MESSAGE);
                   System.out.println("로그인 실패 > 로그인 정보 미입력");
                } else if (uid != null && upass != null) {
                    if(o.db.logincheck(uid,upass)){
                        System.out.println("로그인 성공");
                        JOptionPane.showMessageDialog(null,"로그인에 성공했습니다");
                        dispose(); /* 현재 윈도우만 닫히게하는 코드*/

                        new Main();
                    }
                    else{
                        System.out.println("로그인 실패 > 로그인 정보 불일치");
                        System.out.println(upass);
//                        System.out.println(uid);
                        JOptionPane.showMessageDialog(null,"로그인에 실패했습니다");
                    }
                }
            }
        });
    }
}

