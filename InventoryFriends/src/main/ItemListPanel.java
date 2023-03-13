package main;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemListPanel extends JPanel {
    String market[] = {"", "11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
//    String category[] = {"", "category1", "category2", "category3"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    ImageIcon imgClear1 = new ImageIcon("./img/img_X1.jpg");
    ImageIcon imgClear2 = new ImageIcon("./img/img_X2.jpg");
    ImageIcon imgDel1 = new ImageIcon("./img/img_Del1.jpg");
    ImageIcon imgDel2 = new ImageIcon("./img/img_Del2.jpg");
    JButton btnSearch, btnClear, btnDel;
    JComboBox jcbMarket, jcbCategory;
    JPanel jpILSearch, jpSouth;
    ItemListTable jtItemList;
    HintTextField jtfItemCode, jtfItemName, jtfItemQuantity, jtfItemLocation,
            jtfLastReceivingDate, jtfNextReceivingDate;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;
    List<Integer> selectRows = new ArrayList<>();
    List<String> selectID = new ArrayList<>();
    ItemStatusPanel jpItemStatusPanel;
    JTabbedPane jtpSubTab;
    JSplitPane jspRight;
    String dbName = "ifdb";
    String dbTableName;
    private PreparedStatement pstmt = null;
    private Connection con = null;
    public ItemListPanel(String userid){
        dbTableName = userid + "_ItemList";
        Font font1 = new Font("돋움", Font.PLAIN, 12);
        filters = new ArrayList<>();

        setLayout(new BorderLayout());
        jtItemList = new ItemListTable(userid);

        resizeColumnWidth(jtItemList);
        jtItemList.getColumn("").setPreferredWidth(1);  // 체크박스 컬럼 크기 줄이기
        rowSorter = new TableRowSorter<>(jtItemList.getModel());
        jtItemList.setRowSorter(rowSorter);

        // 상단 검색 패널
        jpILSearch = new JPanel();

        jcbMarket = new JComboBox(market);
        jcbMarket.setPreferredSize(new Dimension(100, 20));
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        jcbCategory = new JComboBox();
        jcbCategory.setPreferredSize(new Dimension(100, 20));
        jcbCategory.setBackground(Color.WHITE);
        jcbCategory.setFont(font1);

        btnSearch = new JButton(imgSearch1);
        btnSearch.setRolloverIcon(imgSearch2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSearch.setBorderPainted(false); // 버튼 테두리 제거
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(56, 24)); // 버튼 크기 지정
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(jcbCategory.getSelectedIndex() != 0){
                    filters.add(RowFilter.regexFilter("(?i)" + jcbCategory.getSelectedItem(),1));
                }
                if(!jtfItemCode.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemCode.getText(),2));
                }
                if(!jtfItemName.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemName.getText(),3));
                }
                if(!jtfItemQuantity.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemQuantity.getText(),4));
                }
                if(jcbMarket.getSelectedIndex() != 0){
                    filters.add(RowFilter.regexFilter("(?i)" + jcbMarket.getSelectedItem(),5));
                }
                if(!jtfItemLocation.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemLocation.getText(),6));
                }
                if(!jtfLastReceivingDate.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfLastReceivingDate.getText(),7));
                }
                if(!jtfNextReceivingDate.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfNextReceivingDate.getText(),8));
                }

                rowSorter.setRowFilter(RowFilter.andFilter(filters));
                filters.clear();
            }
        });
        
        btnClear = new JButton(imgClear1);
        btnClear.setRolloverIcon(imgClear2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnClear.setBorderPainted(false); // 버튼 테두리 제거
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.setPreferredSize(new Dimension(23, 23)); // 버튼 크기 지정
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rowSorter.setRowFilter(RowFilter.andFilter(filters));
                filters.clear();

                jtfItemName.reset();
                jtfItemCode.reset();
                jtfItemQuantity.reset();
                jtfItemLocation.reset();
                jtfLastReceivingDate.reset();
                jtfNextReceivingDate.reset();
                jcbCategory.setSelectedIndex(0);
                jcbMarket.setSelectedIndex(0);
            }
        });

        jtfItemCode = new HintTextField("상품 코드");
        jtfItemCode.setColumns(9);
        jtfItemName = new HintTextField("상품명");
        jtfItemName.setColumns(9);
        jtfItemQuantity = new HintTextField("수량");
        jtfItemQuantity.setColumns(9);
        jtfItemLocation = new HintTextField("재고 위치");
        jtfItemLocation.setColumns(9);
        jtfLastReceivingDate = new HintTextField("최근 입고일");
        jtfLastReceivingDate.setColumns(9);
        jtfNextReceivingDate = new HintTextField("다음 입고 예정일");
        jtfNextReceivingDate.setColumns(9);

        jpILSearch.add(jcbCategory);
        jpILSearch.add(jtfItemCode);
        jpILSearch.add(jtfItemName);
        jpILSearch.add(jtfItemQuantity);
        jpILSearch.add(jtfItemLocation);
        jpILSearch.add(jtfLastReceivingDate);
        jpILSearch.add(jtfNextReceivingDate);
        jpILSearch.add(jcbMarket);
        jpILSearch.add(btnSearch);
        jpILSearch.add(btnClear);

        add(jpILSearch, BorderLayout.NORTH);
        add(new JScrollPane(jtItemList), BorderLayout.CENTER);

        jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDel = new JButton(imgDel1);
        btnDel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDel.setRolloverIcon(imgDel2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnDel.setBorderPainted(false); // 버튼 테두리 제거
        btnDel.setFocusPainted(false);
        btnDel.setContentAreaFilled(false);
        btnDel.setPreferredSize(new Dimension(48, 24));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < jtItemList.getRowCount(); i++) {
                    if ((boolean) jtItemList.getValueAt(i, 0)) {
                        selectRows.add(jtItemList.convertRowIndexToModel(i));
                        selectID.add((String)jtItemList.getValueAt(i, 10));
                    }
                }
                if(selectRows.size() != 0){
                    Collections.sort(selectRows, Collections.reverseOrder());
                    int input = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?",
                            "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if(input == JOptionPane.OK_OPTION){
                        for(int i = 0; i < selectRows.size(); i++){
                            String sql = "DELETE FROM " + dbTableName + " WHERE id = " + selectID.get(i);

                            try{
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                                // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                                //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.
                                pstmt = con.prepareStatement(sql);
                                pstmt.execute("USE " + dbName);
                                int cnt = pstmt.executeUpdate();
                                // 사용할 DB를 선택한다.
                                if(cnt != 0) {
                                    jtItemList.modelItemList.removeRow(selectRows.get(i));
                                    if (selectID.get(i) == jpItemStatusPanel.dbIdno) {
                                        String ISTitle = "재고 상세";
                                        jtpSubTab.removeTabAt(findTabByName(ISTitle, jtpSubTab));
                                        try {
                                            jtpSubTab.isEnabledAt(0);
                                        } catch (Exception exception) {
                                            jspRight.setDividerSize(0);
                                            jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                                            jtpSubTab.setVisible(false);
                                        }
                                    }

                                }


                            } catch (ClassNotFoundException cnfe) {
                                System.out.println("DB 드라이버 로딩 실패 :" + cnfe);

                            } catch (SQLException sqle) {
                                System.out.println("DB 접속실패 : " + sqle);
                            } finally {
                                try{
                                    pstmt.close();
                                    con.close();
                                } catch (Exception e2) {}
                            }
                        }
                    }
                }
                selectRows.clear();
                selectID.clear();
            }
        });
        jpSouth.add(btnDel);
        add(jpSouth, BorderLayout.SOUTH);
    }

    public void setSubTab(JTabbedPane SubTab){
        jtItemList.setSubTab(SubTab);
    }

    public TableModel getTableModel(){
        return jtItemList.getModel();
    }

    public void setComboboxData(JComboBox comboBox){
        jcbCategory.removeAllItems();
        for (int t = 0; t < comboBox.getItemCount(); t++)
        {
            jcbCategory.addItem(comboBox.getItemAt(t));
        }
    }
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
