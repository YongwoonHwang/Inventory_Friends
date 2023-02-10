import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    public OrderConsolidationTable(){
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
                if (e.getClickCount() == 2) {
                    System.out.println(getSelectedRow());
                    jtpSubTab.setVisible(true);
                    if (findTabByName(ODTitle, jtpSubTab) != -1) {
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    } else {
                        jtpSubTab.addTab(ODTitle, new JPanel());
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
