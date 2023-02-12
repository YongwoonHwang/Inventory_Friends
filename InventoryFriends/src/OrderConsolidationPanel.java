import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderConsolidationPanel extends JPanel {
    String market[] = {"", "11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    JComboBox jcbMarket;
    JButton btnSearch;
    HintTextField jtfOrderNum, jtfItemCode, jtfOrderer, jtfPhoneNum, jtfInvoiceNum, jtfOrderDate;
    JPanel jpOCSearch;
    OrderConsolidationTable jtOrderCon;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;
    public OrderConsolidationPanel(){
        JPanel panel1 = new JPanel();
        Font font1 = new Font("돋움", Font.PLAIN, 12);
        filters = new ArrayList<>();

        setLayout(new BorderLayout());
        jtOrderCon = new OrderConsolidationTable();
        rowSorter = new TableRowSorter<>(jtOrderCon.getModel());
        jtOrderCon.setRowSorter(rowSorter);

        jcbMarket = new JComboBox<String>(market);
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        btnSearch = new JButton(imgSearch1);
        btnSearch.setRolloverIcon(imgSearch2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSearch.setBorderPainted(false); // 버튼 테두리 제거
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(56, 24)); // 버튼 크기 지정
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jtfOrderNum.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfOrderNum.getText(),0));
                }
                if(!jtfItemCode.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemCode.getText(),1));
                }
                if(!jtfOrderer.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfOrderer.getText(),3));
                }
                if(!jtfPhoneNum.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfPhoneNum.getText(),4));
                }
                if(!jtfInvoiceNum.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfInvoiceNum.getText(),6));
                }
                if(!jtfOrderDate.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfOrderDate.getText(),7));
                }
                if(jcbMarket.getSelectedIndex() != 0){
                    filters.add(RowFilter.regexFilter("(?i)" + jcbMarket.getSelectedItem(),8));
                }

                rowSorter.setRowFilter(RowFilter.andFilter(filters));
                filters.clear();
            }
        });

        jtfOrderNum = new HintTextField("주문 번호");
        jtfOrderNum.setColumns(10);
        jtfItemCode = new HintTextField("상품 코드");
        jtfItemCode.setColumns(10);
        jtfOrderer = new HintTextField("주문자");
        jtfOrderer.setColumns(10);
        jtfPhoneNum = new HintTextField("전화번호");
        jtfPhoneNum.setColumns(10);
        jtfInvoiceNum = new HintTextField("송장 번호");
        jtfInvoiceNum.setColumns(10);
        jtfOrderDate = new HintTextField("주문일");
        jtfOrderDate.setColumns(10);

        jpOCSearch = new JPanel();

        // 상단 검색 패널
        jpOCSearch.add(jtfOrderNum);
        jpOCSearch.add(jtfItemCode);
        jpOCSearch.add(jtfOrderer);
        jpOCSearch.add(jtfPhoneNum);
        jpOCSearch.add(jtfInvoiceNum);
        jpOCSearch.add(jtfOrderDate);
        jpOCSearch.add(jcbMarket);
        jpOCSearch.add(btnSearch);

        resizeColumnWidth(jtOrderCon);

        add(jpOCSearch, BorderLayout.NORTH);
        add(new JScrollPane(jtOrderCon), BorderLayout.CENTER);
    }

    public void setJtpSubTab(JTabbedPane SubTab){
        jtOrderCon.setJtpSubTab(SubTab);
    }

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
