package main;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MarketInformationWindow extends JFrame {
    JPanel jpNorth, jpCenter, jpInput, jpCenterTmp, jpInputBtn;
    String[] mallName = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    String[] columnsList = {"11st_API_Key", "Gmarket_API_Key", "Naver_Client_Id", "Naver_Client_Secret", "Auction_API_Key", "WMP_API_Key", "Coupang_API_Key", "Tmon_API_Key"};
    JLabel jLabel1, jLabel2;
    Font font1, font2;
    ImageIcon imgConfirm1, imgConfirm2;
    JButton btnConfirm;
    String dbName = "ifdb";
    String dbTableName = "MarketInformation";
    String dbUserIdx;
    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";
    ArrayList<JTextField> textList = new ArrayList<>();
    ArrayList<JCheckBox> checkList = new ArrayList<>();
    OrderConsolidationTable jtOC;
    public MarketInformationWindow(String userName, String useridx){

        font1 = new Font("돋움", Font.BOLD, 20);
        font2 = new Font("돋움", Font.BOLD, 15);

        imgConfirm1 = new ImageIcon(getClass().getClassLoader().getResource("img/img_Confirm1.jpg"));
        imgConfirm2 = new ImageIcon(getClass().getClassLoader().getResource("img/img_Confirm2.jpg"));

        setSize(500, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        jpNorth = new JPanel();
        jpCenter = new JPanel(new BorderLayout());
        jpCenterTmp = new JPanel(new BorderLayout());
        jpInput = new JPanel();
        jpInputBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 0, 0, 0);
        jpInput.setLayout(gb);
        jLabel1 = new JLabel("<html><body style='text-align:center;'><br>" + userName + "님 반갑습니다!</body></html>", JLabel.CENTER);
        jLabel1.setFont(font1);
        jLabel2 = new JLabel("<html><br>" + userName + "님의 재고관리를 도울 재고관리 도우미입니다.<br> 먼저, 원활한 사용을 위하여 이용중인 쇼핑몰의 정보를<br>입력해주세요.</html>");
        jLabel2.setFont(font2);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 10));
        jpNorth.setLayout(new BoxLayout(jpNorth, BoxLayout.Y_AXIS));
        jpNorth.add(jLabel1);
        jpNorth.add(jLabel2);
        for(int i = 0; i < 7; i++){
            if (i == 2) {
                JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                panel1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
                JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
                JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                panel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

                JCheckBox checkBox = new JCheckBox();
                JTextField text = new JTextField();
                JTextField text1 = new JTextField();

                checkList.add(checkBox);
                textList.add(text);
                textList.add(text1);
                text.setColumns(11);
                text.setEnabled(false);
                text1.setColumns(11);
                text1.setEnabled(false);
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            text.setEnabled(true);
                            text1.setEnabled(true);
                        } else {
                            text.setEnabled(false);
                            text1.setEnabled(false);
                        }
                    }
                });

                panel1.add(checkBox);
                panel1.add(new JLabel(mallName[i]));


                panel2.add(new JLabel("Client ID : "));
                panel3.add(new JLabel("Client Secret : "));


                panel2.add(text);
                panel3.add(text1);

                panel1.setAlignmentX(LEFT_ALIGNMENT);
                panel2.setAlignmentX(RIGHT_ALIGNMENT);
                panel3.setAlignmentX(RIGHT_ALIGNMENT);

                gbc.gridx = 0;
                gbc.gridy = i;
                jpInput.add(panel1, gbc);
                gbc.gridx = 1;
                jpInput.add(panel2, gbc);
                gbc.gridx = 1;
                gbc.gridy = i+1;
                jpInput.add(panel3, gbc);
            } else{
                JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                panel1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
                JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
                JCheckBox checkBox = new JCheckBox();
                checkList.add(checkBox);
                JTextField text = new JTextField();
                textList.add(text);
                text.setColumns(11);
                text.setEnabled(false);
                checkBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED){
                            text.setEnabled(true);
                        } else{
                            text.setEnabled(false);
                        }
                    }
                });


                panel1.add(checkBox);
                panel1.add(new JLabel(mallName[i]));
                panel2.add(new JLabel(mallName[i]+"API Key : "));
                panel2.add(text);

                panel1.setAlignmentX(LEFT_ALIGNMENT);
                panel2.setAlignmentX(RIGHT_ALIGNMENT);

                if(i>2){
                    gbc.gridx = 0;
                    gbc.gridy = i+1;
                    jpInput.add(panel1, gbc);
                    gbc.gridx = 1;
                    jpInput.add(panel2, gbc);
                } else {
                    gbc.gridx = 0;
                    gbc.gridy = i;
                    jpInput.add(panel1, gbc);
                    gbc.gridx = 1;
                    jpInput.add(panel2, gbc);
                }
            }
        }
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        JLabel label = new JLabel("<html><br>* 고객님의 마켓정보를 불러오기 위해 관리자 ID를 정확히 입력해 주세요.<br><br><br>&nbsp;</html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.RED);
        jpInput.add(label, gbc);

        btnConfirm = new JButton(imgConfirm1);
        btnConfirm.setRolloverIcon(imgConfirm2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnConfirm.setBorderPainted(false); // 버튼 테두리 제거
        btnConfirm.setFocusPainted(false);
        btnConfirm.setContentAreaFilled(false);
        btnConfirm.setPreferredSize(new Dimension(63, 29)); // 버튼 크기 지정
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet result = null;
                Statement stmt = null;
                String flag = "";
                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection(url, user, passwd);
                    stmt = con.createStatement(); //db와 연결된 conn객체로부터 stmt객체 획득


                    StringBuilder sb1 = new StringBuilder();
                    String sql1 = sb1.append("SELECT EXISTS (select * from MarketInformation where user_idx = '" + useridx + "') as success").toString();
                    result = stmt.executeQuery(sql1); //query문 날리기
                    while (result.next()) {
                        flag = result.getString("success");
                    }
                } catch (Exception ex){
                    System.out.println(ex);
                }

                if(flag.equals("0")){
                    String sql1 = "INSERT INTO " + dbTableName + " (user_idx, 11st_API_Key, Gmarket_API_Key, Naver_Client_Id, Naver_Client_Secret, Auction_API_Key, WMP_API_Key, Coupang_API_Key, Tmon_API_Key) VALUES (?,?,?,?,?,?,?,?,?)";

                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                        // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                        //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                        pstmt = con.prepareStatement(sql1);
                        pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                        // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                        pstmt.setString(1, dbUserIdx);
                        for(int i = 0; i<8;i++){
                            pstmt.setString(i+2, textList.get(i).getText());
                        }

                        int cnt = pstmt.executeUpdate();


                    } catch (ClassNotFoundException cnfe2) {
                        System.out.println("DB 드라이버 로딩 실패 :" + cnfe2);

                    } catch (SQLException sqle) {
                        System.out.println("DB 접속실패 : " + sqle);

                    } finally {
                        try{
                            result.close();
                            pstmt.close();
                            con.close();
                        } catch (Exception e2) {}
                    }

                } else if (flag.equals("1")) {
                    System.out.println("정보 있음");
                    String sql2 = "UPDATE " + dbTableName + " SET 11st_API_Key = ?, Gmarket_API_Key = ?, Naver_Client_Id = ?, Naver_Client_Secret = ?," +
                            " Auction_API_Key =?, WMP_API_Key = ?, Coupang_API_Key = ?, Tmon_API_Key =? WHERE user_idx = ?";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/", "admin", "admin1470!");
                        // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                        //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                        pstmt = con.prepareStatement(sql2);
                        pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                        // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.


                        for (int i = 0; i < 8; i++) {
                            pstmt.setString(i+1, textList.get(i).getText());
                        }
                        pstmt.setString(9, dbUserIdx);

                        int cnt = pstmt.executeUpdate();


                    } catch (ClassNotFoundException cnfe2) {
                        System.out.println("DB 드라이버 로딩 실패 :" + cnfe2);

                    } catch (SQLException sqle) {
                        System.out.println("DB 접속실패 : " + sqle);

                    } finally {
                        try {
                            result.close();
                            pstmt.close();
                            con.close();
                        } catch (Exception e2) {
                        }
                    }

                }

                jtOC.naverAPI = new NaverOrderInfo(useridx);

                setVisible(false);
                dispose();
            }
        });

        jpInputBtn.add(btnConfirm, gbc);
        jpCenterTmp.setBorder(BorderFactory.createEtchedBorder(0));
        jpCenterTmp.add(jpInput, BorderLayout.CENTER);
        jpCenterTmp.add(jpInputBtn, BorderLayout.SOUTH);
        jpCenter.add(jpCenterTmp, BorderLayout.CENTER);
        jpCenter.add(new JPanel(), BorderLayout.NORTH);
        jpCenter.add(new JPanel(), BorderLayout.SOUTH);
        jpCenter.add(new JPanel(), BorderLayout.EAST);
        jpCenter.add(new JPanel(), BorderLayout.WEST);
//        jpBorder.add(jpNorth, BorderLayout.NORTH);
        add(jpNorth, BorderLayout.NORTH);
        add(jpCenter, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
        getIDs(dbUserIdx);
        setVisible(false);
    }

    public void getIDs(String useridx){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
//        String sql2 = "SELECT * FROM " + dbTableName + " ORDER BY id DESC LIMIT 1";
        String sql2 = "SELECT * FROM " + dbTableName + " WHERE user_idx = " + useridx ;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            pstmt = con.prepareStatement(sql2);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
            while (result.next()){
                for(int i = 0; i < 7; i++){
                    if(!result.getString(columnsList[i]).equals("")){
                        if(i==3){
                            checkList.get(i-1).setSelected(true);
                            textList.get(i).setText(result.getString(columnsList[i]));
                        } else{
                            checkList.get(i).setSelected(true);
                            textList.get(i).setText(result.getString(columnsList[i]));
                        }
                    }
                }
            }
        }catch(Exception cnfe) {
            System.out.println(cnfe.getMessage());
        } finally {
            try{
                result.close();
                pstmt.close();
                con.close();
            } catch (Exception e2) {}
        }
    }
}
