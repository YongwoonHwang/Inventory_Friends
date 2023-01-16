import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class OrderConsolidationPanel extends JPanel {
    String market[] = {"마켓 1", "마켓 2", "정말 긴 마켓 이름"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    JComboBox jcbMarket;
    JButton btnSearch;
    JTextField jtfOrderNum, jtfItemCode, jtfOrderer, jtfPhoneNum, jtfInvoiceNum, jtfOrderDate;
    JPanel jpOCSearch;
    OrderConsolidationTable jtOrderCon;
    public OrderConsolidationPanel(){
        Font font1 = new Font("돋움", Font.PLAIN, 12);

        jtOrderCon = new OrderConsolidationTable();

        jcbMarket = new JComboBox<String>(market);
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        btnSearch = new JButton(imgSearch1);
        btnSearch.setRolloverIcon(imgSearch2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSearch.setBorderPainted(false); // 버튼 테두리 제거
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(56, 24)); // 버튼 크기 지정

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

        setLayout(new BorderLayout());
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
