package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderConsolidationTable extends JTable {
    Object headerOrderCon[] = {"주문 번호", "상품 코드", "주문 수량", "주문자", "전화번호", "주소", "송장 번호", "주문일", "마켓"};
    Object contentsOrderCon[][] = {
            {"01", "couch-01-08-beige", "1", "황용운", "010-9574-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
            {"02", "couch-03-01-black", "2", "김만조", "010-4313-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"},
            {"03", "chair-03-03-blue", "5", "권순용", "010-4109-****", "서울시 강서구 공항대로60길", "EG033025977JA", "230104", "쿠팡"}
    };
    static JTabbedPane jtpSubTab;
    static int chk = 0;
    OrderDetailPanel jpOrderDetail, jpOrderDetail2;
    JSplitPane jspRight;
    public OrderConsolidationTable(){
        jpOrderDetail = new OrderDetailPanel();
        jpOrderDetail2 = new OrderDetailPanel();
        OrderDetailPrintWindow winOD = new OrderDetailPrintWindow(jpOrderDetail2, "123");
        JScrollPane jsp = new JScrollPane(jpOrderDetail);
        jsp.setBorder(null);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnPrint = new JButton("자세히 보기");
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winOD.setVisible(true);
            }
        });
        jpNorth.add(btnPrint);
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(jpNorth, BorderLayout.NORTH);

        DefaultTableModel modelOrderCon = new DefaultTableModel(contentsOrderCon, headerOrderCon){
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
                chk++;
                if (e.getClickCount() == 2) {
                    System.out.println(convertRowIndexToModel(getSelectedRow()));

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
