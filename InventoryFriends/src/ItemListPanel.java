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
import java.util.ArrayList;

public class ItemListPanel extends JPanel {
    String market[] = {"", "마켓 1", "마켓 2", "정말 긴 마켓 이름"};
    String category[] = {"", "category1", "category2", "category3"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    JButton btnSearch;
    JComboBox jcbMarket, jcbCategory;
    JPanel jpILSearch;
    ItemListTable jtItemList;
    HintTextField jtfItemCode, jtfItemName, jtfItemQuantity, jtfItemLocation,
            jtfLastReceivingDate, jtfNextReceivingDate;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;

    public ItemListPanel(){
        Font font1 = new Font("돋움", Font.PLAIN, 12);
        filters = new ArrayList<>();

        setLayout(new BorderLayout());
        jtItemList = new ItemListTable();
        resizeColumnWidth(jtItemList);
        jtItemList.getColumn("").setPreferredWidth(1);  // 체크박스 컬럼 크기 줄이기
        rowSorter = new TableRowSorter<>(jtItemList.getModel());
        jtItemList.setRowSorter(rowSorter);

        // 상단 검색 패널
        jpILSearch = new JPanel();

        jcbMarket = new JComboBox(market);
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        jcbCategory = new JComboBox(category);
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

        add(jpILSearch, BorderLayout.NORTH);
        add(new JScrollPane(jtItemList), BorderLayout.CENTER);
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
