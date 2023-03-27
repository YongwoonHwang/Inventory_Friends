package main;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderConsolidationPanel extends JPanel {
    String market[] = {"", "11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    ImageIcon imgClear1 = new ImageIcon("./img/img_X1.jpg");
    ImageIcon imgClear2 = new ImageIcon("./img/img_X2.jpg");
    ImageIcon imgRefresh1 = new ImageIcon("./img/img_Refresh1.jpg");
    ImageIcon imgRefresh2 = new ImageIcon("./img/img_Refresh2.jpg");
    ImageIcon imgCal = new ImageIcon("./img/img_Cal.jpg");
    Timestamp timestamp;
    SimpleDateFormat sdf;
    JLabel refreshTime;
    JComboBox jcbMarket;
    JButton btnSearch, btnClear, btnRefresh, btnCal;
    JTextField jtfDate;
    HintTextField jtfOrderNum, jtfItemCode, jtfOrderer, jtfPhoneNum, jtfInvoiceNum, jtfOrderDate;
    JPanel jpOCSearch, jpSouth, jpSouthLeft, jpSouthRight;
    OrderConsolidationTable jtOrderCon;
    CalendarWindowForChoose winCalendar;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;
    public OrderConsolidationPanel(String useridx){
        Font font1 = new Font("돋움", Font.PLAIN, 12);
        filters = new ArrayList<>();

        jtfDate = new JTextField();
        winCalendar = new CalendarWindowForChoose();
        winCalendar.setTextField(jtfDate);

        setLayout(new BorderLayout());
        jtOrderCon = new OrderConsolidationTable(LocalDate.now().toString(), useridx);
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

                jtfOrderNum.reset();
                jtfItemCode.reset();
                jtfOrderer.reset();
                jtfPhoneNum.reset();
                jtfInvoiceNum.reset();
                jtfOrderDate.reset();
                jcbMarket.setSelectedIndex(0);
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
        jpOCSearch.add(btnClear);

        add(jpOCSearch, BorderLayout.NORTH);
        add(new JScrollPane(jtOrderCon), BorderLayout.CENTER);

        jpSouth = new JPanel(new BorderLayout());
        jpSouthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpSouthRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnRefresh = new JButton(imgRefresh1);
        btnRefresh.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRefresh.setRolloverIcon(imgRefresh2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnRefresh.setBorderPainted(false); // 버튼 테두리 제거
        btnRefresh.setFocusPainted(false);
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setPreferredSize(new Dimension(48, 24));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                jtOrderCon.modelOrderCon.setRowCount(0);
                jtOrderCon.makeRowData(jtfDate.getText());
                refreshTime.setText("마지막 갱신 일자: " + sdf.format(new Timestamp(System.currentTimeMillis())));
                refreshTime.revalidate();
                refreshTime.repaint();
            }
        });
        jpSouthLeft.add(btnRefresh);
//        add(jpSouthLeft,BorderLayout.SOUTH);

        timestamp = new Timestamp(System.currentTimeMillis());
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        refreshTime = new JLabel("마지막 갱신 일자: " + sdf.format(timestamp));
        jpSouthLeft.add(refreshTime);

        jpSouthRight.add(new JLabel("마지막 주문상태 변경일"));
        jtfDate.setEditable(false);
        jtfDate.setColumns(20);
        jtfDate.setText(LocalDate.now().toString());
        jpSouthRight.add(jtfDate);
        btnCal = new JButton(imgCal);
        btnCal.setFocusPainted(false);
        btnCal.setPreferredSize(new Dimension(23, 24));
        btnCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (winCalendar.isVisible()){
                    winCalendar.setVisible(false);
                }else{
                    winCalendar.setLocation(btnCal.getLocationOnScreen().x, btnCal.getLocationOnScreen().y-180);
                    winCalendar.setVisible(true);
                }
            }
        });
        jpSouthRight.add(btnCal);
        jpSouth.add(jpSouthLeft, BorderLayout.CENTER);
        jpSouth.add(jpSouthRight, BorderLayout.EAST);
        add(jpSouth,BorderLayout.SOUTH);
        resizeColumnWidth(jtOrderCon);
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
