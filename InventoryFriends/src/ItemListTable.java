import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ItemListTable extends JTable{
    Object headerItemList[] = {"", "카테고리", "코드", "품명", "수량", "마켓", "재고 위치", "최근 입고일", "다음 입고 예정일"};
    Object contentsItemList[][] = {
            {false, "category1", "couch-01-08-beige", "쿠션 A형 선인장 베이지", "117EA", "쿠팡", "A rack 2번 선반", "230101", "230104"},
            {false, "category1", "couch-01-09-beige", "쿠션 B형 선인장 베이지", "118EA", "쿠팡", "B rack 2번 선반", "230102", "230105"},
            {false, "category2", "couch-01-10-beige", "쿠션 C형 선인장 베이지", "112EA", "쿠팡", "B rack 3번 선반", "230103", "230106"}
    };
    DefaultTableModel modelItemList;
    DefaultTableCellRenderer dcr;
    public ItemListTable(){
        modelItemList = new DefaultTableModel(contentsItemList, headerItemList){
            @Override
            public boolean isCellEditable(int row, int col){
                if (col == 0){
                    return true;
                }
                else{
                    return false;
                }
//                return true;
            }
        };
        dcr = new DefaultTableCellRenderer()
        {
            public Component getTableCellRendererComponent  // 셀렌더러
            (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                JCheckBox box= new JCheckBox();
                box.setSelected(((Boolean)value).booleanValue());
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

    }
}
