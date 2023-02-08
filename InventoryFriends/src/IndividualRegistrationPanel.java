import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class IndividualRegistrationPanel extends JPanel {
    JPanel jpInventoryStatus;
    JTable jtInventoryStatus;
    JTabbedPane jtpMainTab, jtpSubTab;
    JButton btnSubmit1, btnSubmit3, btnCal1, btnCal2, btnCalc;
    JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9,
            jlCalendar2, jlCalendar3, jlfilechooser;
    HintTextField htfCategory, htfCode, htfProductName, htfQuantity, htfProductLocation;
    JComboBox jcbMarketChoose;
    ImageIcon imgSubmit, imgAttach1, imgAttach2, imgCalc, imgCal1, imgCal2;
    JTextField jTextField7, jTextField8, jTextField9;
    JFileChooser filechooser;
    JFrame windowCalc;
    Font font1;
    CalendarWindow irCal;
    public IndividualRegistrationPanel() {
        // 폰트 설정
        font1 = new Font("돋움", Font.PLAIN, 12);   // 왼쪽 하위메뉴 라벨 폰트
        imgSubmit = new ImageIcon("./img/img_submit.jpg");
        imgAttach1 = new ImageIcon("./img/img_attach.jpg");
        imgAttach2 = new ImageIcon("./img/img_attach.jpg");
        imgCalc = new ImageIcon("./img/img_Calc.jpg");
        imgCal1 = new ImageIcon("./img/img_Cal.jpg");
        imgCal2 = new ImageIcon("./img/img_Cal.jpg");

        jpInventoryStatus = new JPanel();
        setLayout(null);

        jlCalendar2 = new JLabel(imgSubmit);
        jlCalendar2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.add(jlCalendar2);

        jlCalendar3 = new JLabel(imgAttach1);
        jlCalendar3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jlCalendar3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        this.add(jlCalendar3);

        filechooser = new JFileChooser();
        jlfilechooser = new JLabel();


        //테이블 패널
        String header2[] = {"CATEGORY", "CODE", "NAME", "QUANTITY", "MARKET", "LOCATION", "STOCKING_DATE", "EDA", "IMAGE"};

        jtInventoryStatus = new JTable();
        // 테이블 속성 오버라이드
        DefaultTableModel model2 = new DefaultTableModel(header2,0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        jtInventoryStatus.setModel(model2);
        resizeColumnWidth(jtInventoryStatus);

        jpInventoryStatus.setLayout(new BorderLayout());
        jpInventoryStatus.add(new JScrollPane(jtInventoryStatus), BorderLayout.CENTER);

        jcbMarketChoose = new JComboBox();
        jcbMarketChoose.addItem("");
        jcbMarketChoose.addItem("쿠팡");
        jcbMarketChoose.addItem("네이버");
        jcbMarketChoose.addItem("11번가");
        jcbMarketChoose.addItem("위메프");
        jcbMarketChoose.addItem("옥션");
        jcbMarketChoose.addItem("G마켓");
        jcbMarketChoose.addItem("티몬");
        jcbMarketChoose.setBounds(140, 135, 700, 25);
        jcbMarketChoose.setBackground(Color.WHITE);
        jcbMarketChoose.setFont(font1);
        this.add(jcbMarketChoose);

        jTextField7 = new JTextField();
        jTextField7.setBounds(140,195,675,25);
        this.add(jTextField7);

        jTextField8 = new JTextField();
        jTextField8.setBounds(140,225,675,25);
        this.add(jTextField8);

        jTextField9 = new JTextField();
        jTextField9.setBounds(140,255,200,25);
        jTextField9.setEditable(false);
        this.add(jTextField9);

        text1 = new JLabel("카테고리 : ");
        text1.setBounds(20,15,120,30);
        this.add(text1);

        text2 = new JLabel("코드 : ");
        text2.setBounds(20,45,120,30);
        this.add(text2);

        text3 = new JLabel("품명 : ");
        text3.setBounds(20,75,120,30);
        this.add(text3);

        text4 = new JLabel("수량 : ");
        text4.setBounds(20,105,120,30);
        this.add(text4);

        text5 = new JLabel("마켓 선택 : ");
        text5.setBounds(20,135,120,30);
        this.add(text5);

        text6 = new JLabel("재고 위치 : ");
        text6.setBounds(20,165,120,30);
        this.add(text6);

        text7 = new JLabel("최근 입고일 : ");
        text7.setBounds(20,195,120,30);
        this.add(text7);

        text8 = new JLabel("다음 입고 예정일 : ");
        text8.setBounds(20,225, 120, 30);
        this.add(text8);

        text9 = new JLabel("이미지 : ");
        text9.setBounds(20,255, 120, 30);
        this.add(text9);

        htfCategory = new HintTextField("카테고리는 필수 입력 항목입니다. (ex. Chair)");
        htfCategory.setBounds(140,15,700,25);
        this.add(htfCategory);

        htfCode = new HintTextField("코드는 필수 입력 항목입니다. (ex. Cover-A-01-Black)");
        htfCode.setBounds(140,45,700,25);
        this.add(htfCode);

        htfProductName = new HintTextField("품명은 필수 입력 항목입니다. (ex. 의자 커버 원형 플로럴 검정)");
        htfProductName.setBounds(140,75,700,25);
        this.add(htfProductName);

        htfQuantity = new HintTextField("수량은 필수 입력 항목입니다.(ex. 10EA");
        htfQuantity.setBounds(140,105,678,25);
        this.add(htfQuantity);

        htfProductLocation = new HintTextField("ex.Rack-01-A-05");
        htfProductLocation.setBounds(140,165,700,25);
        this.add(htfProductLocation);


        btnSubmit1 = new JButton("파일첨부");
//        btnSubmit1.setRolloverIcon(imgAttach2); // 버튼에 마우스가 올라갈떄 이미지 변환
//        btnSubmit1.setBorderPainted(false); // 버튼 테두리 제거
//        btnSubmit1.setFocusPainted(false);
//        btnSubmit1.setContentAreaFilled(false);
//        btnSubmit1.setPreferredSize(new Dimension(85, 25)); // 버튼 크기 지정

        btnSubmit1.setBounds(340, 255, 85, 25);
//        windowCalc.add(jlfilechooser);
        btnSubmit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //파일오픈 다이얼로그 를 띄움
                int result = filechooser.showOpenDialog(windowCalc);

                if (result == JFileChooser.APPROVE_OPTION) {
                    //선택한 파일의 경로 반환
                    File selectedFile = filechooser.getSelectedFile();

                    //경로 출력
                    System.out.println(selectedFile);
//                    jlfilechooser.setText("저장 경로:" +filechooser.getSelectedFile().toString()+"."+filechooser.getFileFilter().getDescription());
                }
            }
        });
        this.add(btnSubmit1);


        btnSubmit3 = new JButton("등록");
        btnSubmit3.setBounds(900, 280, 75, 25);
        btnSubmit3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());  //선택된 버튼의 테스트값 출력
                Connection con = null;
                PreparedStatement pstmt = null;
                ResultSet result = null;
                String sql = "INSERT INTO yongun7_Inventory_List (CATEGORY, CODE, PRODUCT_NAME, QUANTITY, MARKET, PRODUCT_LOCATION, STOCKING_DATE, EDA, IMAGE) " + "VALUES (?,?,?,?,?,?,?,?,?)";
                String sql2 = "SELECT * FROM yongun7_Inventory_List ORDER BY 'ID' ASC LIMIT 1";

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://inventoryfriends.cxtfsxnxj3jt.ap-northeast-1.rds.amazonaws.com:3306/","admin","admin1470");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE Inventory_List"); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

                    pstmt.setString(1, htfCategory.getText());
                    pstmt.setString(2, htfCode.getText());
                    pstmt.setString(3, htfProductName.getText());
                    pstmt.setInt(4, Integer.parseInt(htfQuantity.getText()));
                    pstmt.setString(5, (String) jcbMarketChoose.getSelectedItem());
                    pstmt.setString(6, htfProductLocation.getText());
                    pstmt.setString(7, jTextField7.getText());
                    pstmt.setString(8, jTextField8.getText());
                    pstmt.setString(9, jTextField9.getText());

                    int cnt = pstmt.executeUpdate();
                    System.out.println("SUCCESS");

//                    if(jTextField1.getText().isEmpty()) return;
//                    jTextField1.setText("");//비우기

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle.toString());
                }

                try{
                    pstmt = con.prepareStatement(sql2);
                    pstmt.execute("USE Inventory_List"); // 사용할 DB를 선택한다.
                    result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
                    while (result.next()){
                        model2.addRow(new Object[]{result.getString("CATEGORY"), result.getString("CODE"), result.getString("Product_NAME"),
                                result.getString("QUANTITY"), result.getString("MARKET"), result.getString("Product_Location"), result.getString("STOCKING_DATE"), result.getString("EDA"), result.getString("IMAGE")});

                    }

                }catch(Exception cnfe){
                    System.out.println(cnfe.getMessage());
                }finally {
                    try{
                        result.close();
                        pstmt.close();
                        con.close();
                    } catch (Exception e2) {}
                }

                String STTitle = new String("재고 현황");
                if (findTabByName(STTitle, jtpSubTab) != -1) {
                    jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
                } else {
                    jtpSubTab.addTab(STTitle, jpInventoryStatus);
                    jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
                }

            }
        });
        this.add(btnSubmit3);


        btnCal1 = new JButton(imgCal1);
        btnCal1.setBounds(815, 195, 23, 23);
        btnCal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentMoved(ComponentEvent e) {
                            irCal.setLocation(jlCalendar2.getLocationOnScreen().x+1, jlCalendar2.getLocationOnScreen().y-181);
                        }
                        @Override
                        public void componentResized(ComponentEvent e) {
                            irCal.setLocation(jlCalendar2.getLocationOnScreen().x+1, jlCalendar2.getLocationOnScreen().y-181);
                    }
                });
            }
        });
        this.add(btnCal1);

        btnCal2 = new JButton(imgCal2);
        btnCal2.setBounds(815, 225, 23, 23);
        btnCal2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(btnCal2);

        btnCalc = new JButton(imgCalc);
        btnCalc.setBounds(818, 105, 20, 23);
        btnCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JCalc.setLocation(jlCalc.getLocationOnScreen().x+1, jlCalc.getLocationOnScreen().y-181);
            }
        });
        this.add(btnCalc);
    }

    // 탭 타이틀 이름을 찾아 인덱스를 반환하는 함수
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    // 테이블 너비를 내용에 맞춰주는 함수
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
