import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;

public class InventoryStatusTable extends JTable {
    JPanel jpInventoryStatus;
    HintTextField htfCategory, htfCode, htfProductName, htfQuantity, htfProductLocation;
    JComboBox jcbMarketChoose;
    JTextField jTextField7, jTextField8, jTextField9;
    public InventoryStatusTable () {
        //테이블 패널
        String header2[] = {"CATEGORY", "CODE", "NAME", "QUANTITY", "MARKET", "LOCATION", "STOCKING_DATE", "EDA", "IMAGE"};

        // 테이블 속성 오버라이드
        DefaultTableModel model2 = new DefaultTableModel(header2,0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        this.setModel(model2);
        resizeColumnWidth(this);

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(this), BorderLayout.CENTER);


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

//        String STTitle = new String("재고 현황");
//        if (findTabByName(STTitle, jtpSubTab) != -1) {
//            jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
//        } else {
//            jtpSubTab.addTab(STTitle, jpInventoryStatus);
//            jtpSubTab.setSelectedIndex(findTabByName(STTitle, jtpSubTab));
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
