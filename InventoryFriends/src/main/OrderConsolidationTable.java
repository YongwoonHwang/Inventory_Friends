package main;

import com.squareup.okhttp.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class OrderConsolidationTable extends JTable {
    Object headerOrderCon[] = {"주문 번호", "코드", "수량", "주문자", "전화번호", "주소", "주문 상태", "송장 번호", "주문일", "마켓"};
    Object contentsOrderCon[][];
    static JTabbedPane jtpSubTab;
    NaverOrderInfo naverAPI;
//    static int chk = 0;
    OrderDetailPanel jpOrderDetail1, jpOrderDetail2;
    JSplitPane jspRight;
    DefaultTableModel modelOrderCon;
    LinkedHashMap<Integer, LinkedHashMap> rowData;
    public OrderConsolidationTable(String selectDate, String useridx){
        naverAPI = new NaverOrderInfo(useridx);
        jpOrderDetail1 = new OrderDetailPanel();
        jpOrderDetail2 = new OrderDetailPanel();
        OrderDetailPrintWindow winOD = new OrderDetailPrintWindow(jpOrderDetail2, "print");
        JScrollPane jsp = new JScrollPane(jpOrderDetail1);
        jsp.setBorder(null);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetail = new JButton("자세히 보기");
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winOD.setVisible(true);
            }
        });
        jpNorth.add(btnDetail);
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(jpNorth, BorderLayout.NORTH);

        modelOrderCon = new DefaultTableModel(contentsOrderCon, headerOrderCon){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        setModel(modelOrderCon);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   // 다중 선택 안되게
        setRowSorter(new TableRowSorter<>(modelOrderCon));   // 테이블 정렬 기능 추가
        getTableHeader().setReorderingAllowed(false);    // 테이블 열 이동 안되게
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String ODTitle = new String("주문 상세");
//                chk++;
                if (e.getClickCount() == 2) {
                    String orderId = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrderId").toString();
                    String sellerProductCode = rowData.get(convertRowIndexToModel(getSelectedRow())).get("SellerProductCode").toString();
                    String quantity = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Quantity").toString();
                    String ordererName = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererName").toString();
                    String ordererTel = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererTel").toString();
                    String baseAddress = rowData.get(convertRowIndexToModel(getSelectedRow())).get("BaseAddress").toString();
                    String productOrderStatus = modelOrderCon.getValueAt(convertRowIndexToModel(getSelectedRow()), 6).toString();
                    String trackingNumber = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TrackingNumber").toString();
                    String orderDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrderDate").toString();
                    String market = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Market").toString();
                    String image = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Image").toString();
                    String productName = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ProductName").toString();
                    String totalPaymentAmount = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TotalPaymentAmount").toString();
                    String totalProductAmount = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TotalProductAmount").toString();
                    String unitPrice = rowData.get(convertRowIndexToModel(getSelectedRow())).get("UnitPrice").toString();
                    String productOption = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ProductOption").toString();
                    String ordererId = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererId").toString();
                    String name = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Name").toString();
                    String tel1 = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Tel1").toString();
                    String email = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Email").toString();
                    String shippingMemo = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ShippingMemo").toString();
                    String pickupDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("PickupDate").toString();
                    String sendDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("SendDate").toString();;
                    String individualCustomUniqueCode = rowData.get(convertRowIndexToModel(getSelectedRow())).get("IndividualCustomUniqueCode").toString();
                    String deliveryCompany = rowData.get(convertRowIndexToModel(getSelectedRow())).get("DeliveryCompany").toString();
                    jpOrderDetail1.setText(image, productName, quantity, totalPaymentAmount,
                            totalProductAmount, unitPrice, market, orderId,
                            productOrderStatus,  productOption, sellerProductCode,
                            ordererId, ordererName, name, ordererTel, tel1, email, orderDate,
                            pickupDate, sendDate, trackingNumber, individualCustomUniqueCode,
                            deliveryCompany, baseAddress, shippingMemo);
                    jpOrderDetail2.setText(image, productName, quantity, totalPaymentAmount,
                            totalProductAmount, unitPrice, market, orderId,
                            productOrderStatus,  productOption, sellerProductCode,
                            ordererId, ordererName, name, ordererTel, tel1, email, orderDate,
                            pickupDate, sendDate, trackingNumber, individualCustomUniqueCode,
                            deliveryCompany, baseAddress, shippingMemo);

                    jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMinimum());
                    jtpSubTab.setVisible(true);
                    jspRight.setDividerSize(7);
                    jspRight.setDividerLocation(getRootPane().getSize().height-400);
                    if (findTabByName(ODTitle, jtpSubTab) != -1) {
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    } else {
                        jtpSubTab.addTab(ODTitle, panel);
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    }
                }
            }
        });

        makeRowData(selectDate);

    }

    public void makeRowData(String selectDate){
        if(!naverAPI.clientId.equals("") && !naverAPI.clientSecret.equals("")) {
            rowData = naverAPI.getOrderDetail(selectDate);
            if (rowData != null) {
                for (int i = 0; i < rowData.size(); i++) {
                    String orderId = rowData.get(i).get("OrderId").toString();
                    String sellerProductCode = rowData.get(i).get("SellerProductCode").toString();
                    String quantity = rowData.get(i).get("Quantity").toString();
                    String ordererName = rowData.get(i).get("OrdererName").toString();
                    String ordererTel = rowData.get(i).get("OrdererTel").toString();
                    String baseAddress = rowData.get(i).get("BaseAddress").toString();
                    String productOrderStatus = rowData.get(i).get("ProductOrderStatus").toString();
                    String trackingNumber = rowData.get(i).get("TrackingNumber").toString();
                    String orderDate = rowData.get(i).get("OrderDate").toString();
                    String market = rowData.get(i).get("Market").toString();

                    modelOrderCon.addRow(new Object[]{orderId, sellerProductCode, quantity, ordererName, ordererTel, baseAddress, productOrderStatus, trackingNumber, orderDate, market});
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "마켓 정보를 확인 할 수 없습니다.");
        }
    }

    public void setJtpSubTab(JTabbedPane SubTab){
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
}
