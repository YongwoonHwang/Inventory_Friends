import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class MarketInformationWindow extends JFrame {
    JPanel jpNorth, jpCenter, jpInput, jpCenterTmp, jpInputBtn;
    String[] mallName = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    String[] columnsList = {"11st_id", "Gmarket_id", "Naver_id", "Auction_id", "WMP_id", "Coupang_id", "Tmon_id"};
    JLabel jLabel1, jLabel2;
    String userID;
    Font font1, font2;
    ImageIcon imgConfirm1, imgConfirm2;
    JButton btnConfirm;
    String dbName = "ifdb";
    String dbTableName = "MarketInformation";
    String dbUserIdx = "1";
    ArrayList<JTextField> textList = new ArrayList<>();
    ArrayList<JCheckBox> checkList = new ArrayList<>();
    public MarketInformationWindow(){

        font1 = new Font("돋움", Font.BOLD, 20);
        font2 = new Font("돋움", Font.BOLD, 15);

        imgConfirm1 = new ImageIcon("./img/img_Confirm1.jpg");
        imgConfirm2 = new ImageIcon("./img/img_Confirm2.jpg");

        setSize(500, 600);
        setVisible(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        userID = "ID";
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
        jLabel1 = new JLabel("<html><body style='text-align:center;'><br>" + userID + "님 반갑습니다!</body></html>", JLabel.CENTER);
        jLabel1.setFont(font1);
        jLabel2 = new JLabel("<html><br>" + userID + "님의 재고관리를 도울 재고관리 도우미입니다.<br> 먼저, 원활한 사용을 위하여 이용중인 쇼핑몰의 정보를<br>입력해주세요.</html>");
        jLabel2.setFont(font2);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 10));
        jpNorth.setLayout(new BoxLayout(jpNorth, BoxLayout.Y_AXIS));
        jpNorth.add(jLabel1);
        jpNorth.add(jLabel2);
        for(int i = 0; i < 7; i++){
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
            panel2.add(new JLabel(mallName[i]+"관리자 ID : "));
            panel2.add(text);

            panel1.setAlignmentX(LEFT_ALIGNMENT);
            panel2.setAlignmentX(RIGHT_ALIGNMENT);

            gbc.gridx = 0;
            gbc.gridy = i;
            jpInput.add(panel1, gbc);
            gbc.gridx = 1;
            jpInput.add(panel2, gbc);
        }
        getIDs();
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
                String sql = "INSERT INTO " + dbTableName + " (user_id, 11st_id, Gmarket_id, Naver_id, Auction_id, WMP_id, Coupang_id, Tmon_id) VALUES (?,?,?,?,?,?,?,?)";

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                    pstmt.setString(1, dbUserIdx);
                    for(int i = 0; i<7;i++){
                        pstmt.setString(i+2, textList.get(i).getText());
                    }

                    int cnt = pstmt.executeUpdate();
                    System.out.println("SUCCESS");


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
    }

    public void getIDs(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String sql2 = "SELECT * FROM " + dbTableName + " ORDER BY id DESC LIMIT 1";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            pstmt = con.prepareStatement(sql2);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
            while (result.next()){
                for(int i = 0; i < 7; i++){
                    if(!result.getString(columnsList[i]).equals("")){
                        checkList.get(i).setSelected(true);
                        textList.get(i).setText(result.getString(columnsList[i]));
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

    public static void main(String[] args) {
        new MarketInformationWindow();
    }
}
