import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class ItemStatusPanel extends JPanel {
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage,
            dbCategory,dbItemCode,dbItemName, dbItemQuantity, dbMarket, dbItemLocation, dbLastReceivingDate, dbNextReceivingDate, dbImage,dbIdno,getIndex;
    String dbName = "ifdb";
    String dbTableName = "ItemList";
    JPanel jpInventoryStatus;
    private Vector data = null;
//    Vector result = selectAll();

    static String error;
    private  PreparedStatement pstmt = null;
    private  Connection con = null;

    ImageIcon imgChange1 = new ImageIcon("./img/img_Change1.jpg");
    ImageIcon imgChange2 = new ImageIcon("./img/img_Change2.jpg");
    ImageIcon imgDel1 = new ImageIcon("./img/img_Del1.jpg");
    ImageIcon imgDel2 = new ImageIcon("./img/img_Del2.jpg");
    ImageIcon icon = new ImageIcon("./img/mugcup.jpg");

    ImageIcon updateIcon;
    Image img,updateimg;
    JButton btnChange,btnDel;
    ItemListTable jtItemList;
    ModifyPanel modifyPanel;
    MainTabPanel mainTabPanel;
    ItemListPanel  jtItemListPane;
    MainWindow mainWindow;
    JTabbedPane jtpMainTab;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;

    public ItemStatusPanel(){



        jpInventoryStatus = new JPanel();

        setLayout(null);

        String header2[] = {"카테고리", "코드", "상품 이름", "수량", "마켓", "재고 위치", "최근 입고일", "다음 입고 예정일", "이미지"};



        jlCategory = new JLabel("카테고리 : ");
        jlCategory.setBounds(320,35,120,60);
        add(jlCategory);

        jlItemCode = new JLabel("코드 : ");
        jlItemCode.setBounds(320,65,120,60);
        add(jlItemCode);

        jlItemName = new JLabel("품명 : ");
        jlItemName.setBounds(320,95,120,60);
        add(jlItemName);

        jlItemQuantity = new JLabel("수량 : ");
        jlItemQuantity.setBounds(320,125,120,60);
        add(jlItemQuantity);

        jlMarket = new JLabel("마켓 : ");
        jlMarket.setBounds(320,155,120,60);
        add(jlMarket);

        jlItemLocation = new JLabel("재고 위치 : ");
        jlItemLocation.setBounds(320,185,120,60);
        add(jlItemLocation);

        jlLastReceivingDate = new JLabel("최근 입고일 : ");
        jlLastReceivingDate.setBounds(320,215,120,60);
        add(jlLastReceivingDate);

        jlNextReceivingDate = new JLabel("다음 입고 예정일 : ");
        jlNextReceivingDate.setBounds(320,245, 120, 60);
        add(jlNextReceivingDate);

        dbCategory = new JLabel("");
        dbCategory.setBounds(385, 35, 700, 60);
        add(dbCategory);

        dbItemCode = new JLabel("");
        dbItemCode.setBounds(360, 65, 700, 60);
        add(dbItemCode);

        dbItemName = new JLabel("");
        dbItemName.setBounds(360, 95, 700, 60);
        add(dbItemName);

        dbItemQuantity = new JLabel("");
        dbItemQuantity.setBounds(360, 125, 678, 60);
        add(dbItemQuantity);

        dbMarket = new JLabel("");
        dbMarket.setBounds(360, 155, 678, 60);
        add(dbMarket);

        dbItemLocation = new JLabel("");
        dbItemLocation.setBounds(390, 185, 700, 60);
        add(dbItemLocation);

        dbLastReceivingDate = new JLabel("");
        dbLastReceivingDate.setBounds(400, 215, 700, 60);
        add(dbLastReceivingDate);

        dbNextReceivingDate = new JLabel("");
        dbNextReceivingDate.setBounds(430, 245, 700, 60);
        add(dbNextReceivingDate);

        /*식별용*/

        dbIdno = new JLabel("");
        getIndex = new JLabel("");

        /*식별용*/


        jlImage = new JLabel("이미지");
        jlImage.setBounds(30,10,700,30);
        add(jlImage);

        img = icon.getImage();
        updateimg = img.getScaledInstance(265,250,Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateimg);

        dbImage = new JLabel();

        dbImage.setIcon(updateIcon);

        dbImage.setBounds(25,25,270,300);
        dbImage.setHorizontalAlignment(JLabel.CENTER);

        this.add(dbImage);


        btnChange = new JButton(imgChange1);
        btnChange.setBounds(900, 280, 75, 25);
        btnChange.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnChange.setRolloverIcon(imgChange2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnChange.setBorderPainted(false); // 버튼 테두리 제거
        btnChange.setFocusPainted(false);
        btnChange.setContentAreaFilled(false);
        btnChange.setPreferredSize(new Dimension(48, 24));
        btnChange.setBounds(850, 280, 75, 25);
        modifyPanel = new ModifyPanel();
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        add(btnChange);

        btnDel = new JButton(imgDel1);
        btnDel.setBounds(900, 280, 75, 25);
        btnDel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDel.setRolloverIcon(imgDel2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnDel.setBorderPainted(false); // 버튼 테두리 제거
        btnDel.setFocusPainted(false);
        btnDel.setContentAreaFilled(false);
        btnDel.setPreferredSize(new Dimension(48, 24));
        btnDel.setBounds(900, 280, 75, 25);
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = Integer.parseInt(getIndex.getText());

                System.out.println("가져온 줄값 = " + row);

//                jtItemList = new ItemListTable();

                jtItemList.modelItemList.removeRow(row);


                System.out.println("현재 선택된 행의 id = " + dbIdno.getText());


                System.out.println(e.getActionCommand());  //선택된 버튼의 테스트값 출력



                String sql = "DELETE FROM " + dbTableName + " WHERE id = " + dbIdno.getText();
                System.out.println(sql);

                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.
                    pstmt = con.prepareStatement(sql);
                    pstmt.execute("USE " + dbName);
                    int cnt = pstmt.executeUpdate();
                    System.out.println(cnt);
                    // 사용할 DB를 선택한다.

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe);

                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle);
                    if (error != null){
                        JOptionPane.showMessageDialog(null, error,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        error = null;
                    }
                }

            }
        });
        add(btnDel);


    }
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    public void setTexts(String category, String code, String name,String quantity,
                         String market, String location, String lastDate,String nextDate,String idno,String index){

        dbCategory.setText(category);
        dbItemCode.setText(code);
        dbItemName.setText(name);
        dbItemQuantity.setText(quantity);
        dbMarket.setText(market);
        dbItemLocation.setText(location);
        dbLastReceivingDate.setText(lastDate);
        dbNextReceivingDate.setText(nextDate);
        dbIdno.setText(idno);
        getIndex.setText(index);

        revalidate();
        repaint();
    }


    public static void main(String[] args) {
        ItemStatusPanel p = new ItemStatusPanel();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.add(p);
        f.setVisible(true);
    }
}
