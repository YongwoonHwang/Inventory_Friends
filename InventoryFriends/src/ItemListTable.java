import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ItemListTable extends JTable {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";
    String dbTableName;
    ItemStatusPanel jpItemStatusPanel;
    JTabbedPane jtpSubTab;
    Object headerItemList[] = {"", "카테고리", "코드", "품명", "수량", "마켓", "재고 위치", "최근 입고일", "다음 입고 예정일", "이미지", "식별번호"};
    Object ob[][] = new Object[0][11];
    DefaultTableModel modelItemList;
    DefaultTableCellRenderer dcr;
    JSplitPane jspRight;

    public ItemListTable(String userid) {

        modelItemList = new DefaultTableModel(ob, headerItemList) {
            public boolean isCellEditable(int row, int col) {
                if (col == 0) {
                    return true;
                } else {
                    return false;
                }
//                return true;
            }
        };

        dbconnect(); //db접속
        select(userid); //접속한 db로부터 column값을 읽어 밸류가져옴

        dcr = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent  // 셀렌더러
            (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox box = new JCheckBox();
                box.setSelected(((Boolean) value).booleanValue());
                box.setHorizontalAlignment(JLabel.CENTER);
                return box;
            }
        };
        setModel(modelItemList);
//        jtItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   // 다중 선택 안되게
        setRowSorter(new TableRowSorter<>(modelItemList));   // 테이블 정렬 기능 추가
        getTableHeader().setReorderingAllowed(false);    // 테이블 열 이동 안되게
        getColumn("").setCellRenderer(dcr);

        JCheckBox box = new JCheckBox();
        box.setHorizontalAlignment(JLabel.CENTER);
        getColumn("").setCellEditor(new DefaultCellEditor(box));
        getColumn("식별번호").setWidth(0);
        getColumn("식별번호").setMinWidth(0);
        getColumn("식별번호").setMaxWidth(0);
        getColumn("이미지").setWidth(0);
        getColumn("이미지").setMinWidth(0);
        getColumn("이미지").setMaxWidth(0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String ISTitle = "재고 상세"; /*생성되는 하단탭 제목*/

                int row = convertRowIndexToModel(getSelectedRow()); /*테이블로부터 선택한 줄 정보 표시*/
                TableModel data = getModel();  /*테이블로부터 값을 가져옴*/

                String category = (String)data.getValueAt(row,1); /*선택한줄의 2번째 값*/
                String code = (String)data.getValueAt(row,2);
                String name = (String)data.getValueAt(row,3);
                String quantity = (String)data.getValueAt(row,4);
                String market = (String)data.getValueAt(row,5);
                String location = (String)data.getValueAt(row,6);
                String lastReceiveDate = (String)data.getValueAt(row,7);
                String nextReceivDate = (String)data.getValueAt(row,8);
                String img = (String)data.getValueAt(row, 9);
                String idno = (String)data.getValueAt(row,10);
                String dbtablename = dbTableName;

                if (e.getClickCount() == 2) {
                    jpItemStatusPanel.setTexts(category, code, name, quantity, market, location,
                            lastReceiveDate, nextReceivDate,img,idno,row,dbtablename);
                    jpItemStatusPanel.repaint();

                    jtpSubTab.setVisible(true);
                    jspRight.setDividerSize(7);
                    jspRight.setDividerLocation(getRootPane().getSize().height-400);
                    if (findTabByName(ISTitle, jtpSubTab) != -1) {
                        jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
                    } else {
                        jtpSubTab.addTab(ISTitle, jpItemStatusPanel);
                        jtpSubTab.setSelectedIndex(findTabByName(ISTitle, jtpSubTab));
                    }
                }
            }
        });
    }

    public void setSubTab(JTabbedPane SubTab){
        jtpSubTab = SubTab;
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

    public void select(String userid) {
        dbTableName = userid + "_ItemList";
        String sql = "select * from " + dbTableName;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String category = rs.getString("Category");
                String code = rs.getString("Code");
                String name = rs.getString("ProductName");
                String quantity = rs.getString("Quantity");
                String market = rs.getString("Market");
                String location = rs.getString("ProductLocation");
                String lastreceive = rs.getString("StockingDate");
                String nextreceive = rs.getString("EDA");
                String image = rs.getString("Image");
                String idno = rs.getString("id");

                Object contentsItemList[] = {false, category, code, name, quantity, market, location,
                        lastreceive, nextreceive, image, idno};
                modelItemList.addRow(contentsItemList);

            }
        } catch (Exception e) {
            System.out.println("select() 실행 오류" + e);

        }

    }

    private void dbconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement();
            System.out.println("MySQL 서버 연동 성공");
        } catch (Exception e) {
            System.out.println("MySQL 서버 연동 실패 > " + e);
        }
    }
}




