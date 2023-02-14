import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TestPanel extends JPanel {
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage,
    dbCategory,dbItemCode,dbItemName, dbItemQuantity, dbMarket, dbItemLocation, dbLastReceivingDate, dbNextReceivingDate, dbImage;

    JTable jtInventoryStatus;
    JPanel jpInventoryStatus;

    ItemListTable itemListTable;

    ImageIcon imgChange1 = new ImageIcon("./img/img_Change1.jpg");
    ImageIcon imgChange2 = new ImageIcon("./img/img_Change2.jpg");
    ImageIcon imgDel1 = new ImageIcon("./img/img_Del1.jpg");
    ImageIcon imgDel2 = new ImageIcon("./img/img_Del2.jpg");
    ImageIcon icon = new ImageIcon("./img/mugcup.jpg");

    ImageIcon updateIcon;
    Image img,updateimg;
    JButton btnChange,btnDel;
    ItemListTable jtItemList;

    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;


    public TestPanel(){

        jpInventoryStatus = new JPanel();

        setLayout(null);

        String header2[] = {"카테고리", "코드", "상품 이름", "수량", "마켓", "재고 위치", "최근 입고일", "다음 입고 예정일", "이미지"};


//        jtInventoryStatus = new JTable();
//
//        int row = jtInventoryStatus.getSelectedRow();
//
//        TableModel data = jtInventoryStatus.getModel();
//
//
//        // 테이블 속성 오버라이드
//        DefaultTableModel model2 = new DefaultTableModel(header2,0){
//            @Override
//            public boolean isCellEditable(int row, int col){
//                return false;
//            }
//        };
//        jtInventoryStatus.setModel(model2);
//
//        jpInventoryStatus.setLayout(new BorderLayout());
//        jpInventoryStatus.add(new JScrollPane(jtInventoryStatus), BorderLayout.CENTER);

        jlCategory = new JLabel("카테고리 : ");
        jlCategory.setBounds(320,35,120,60);
        add(jlCategory);

        jlItemCode = new JLabel("코드 : ");
        jlItemCode.setBounds(320,65,120,60);
        add(jlItemCode);

        jlItemName = new JLabel("품명 : ");
        jlItemName.setBounds(320,95,120,60);
        add(jlItemName);

        jlItemQuantity = new JLabel("수량 : ");
        jlItemQuantity.setBounds(320,125,120,60);
        add(jlItemQuantity);

        jlMarket = new JLabel("마켓 : ");
        jlMarket.setBounds(320,155,120,60);
        add(jlMarket);

        jlItemLocation = new JLabel("재고 위치 : ");
        jlItemLocation.setBounds(320,185,120,60);
        add(jlItemLocation);

        jlLastReceivingDate = new JLabel("최근 입고일 : ");
        jlLastReceivingDate.setBounds(320,215,120,60);
        add(jlLastReceivingDate);

        jlNextReceivingDate = new JLabel("다음 입고 예정일 : ");
        jlNextReceivingDate.setBounds(320,245, 120, 60);
        add(jlNextReceivingDate);

        dbCategory = new JLabel("");
        dbCategory.setBounds(440,35,700,45);
        add(dbCategory);

        dbItemCode = new JLabel("");
        dbItemCode.setBounds(440,65,700,45);
        add(dbItemCode);

        dbItemName = new JLabel("");
        dbItemName.setBounds(440,95,700,45);
        add(dbItemName);

        dbItemQuantity = new JLabel("");
        dbItemQuantity.setBounds(440,125,678,45);
        add(dbItemQuantity);

        dbMarket = new JLabel("");
        dbMarket.setBounds(440,155,678,45);
        add(dbMarket);

        dbItemLocation = new JLabel("");
        dbItemLocation.setBounds(440,185,700,45);
        add(dbItemLocation);

        dbLastReceivingDate = new JLabel("");
        dbLastReceivingDate.setBounds(440,215,700,45);
        add(dbLastReceivingDate);

        dbNextReceivingDate = new JLabel("");
        dbNextReceivingDate.setBounds(440,245,700,45);
        add(dbNextReceivingDate);

//        if(data == null){
//            dbCategory = new JLabel("");
//            dbCategory.setBounds(440,35,700,45);
//            add(dbCategory);
//
//            dbItemCode = new JLabel("");
//            dbItemCode.setBounds(440,65,700,45);
//            add(dbItemCode);
//
//            dbItemName = new JLabel("");
//            dbItemName.setBounds(440,95,700,45);
//            add(dbItemName);
//
//            dbItemQuantity = new JLabel("");
//            dbItemQuantity.setBounds(440,125,678,45);
//            add(dbItemQuantity);
//
//            dbMarket = new JLabel("");
//            dbMarket.setBounds(440,155,678,45);
//            add(dbMarket);
//
//            dbItemLocation = new JLabel("");
//            dbItemLocation.setBounds(440,185,700,45);
//            add(dbItemLocation);
//
//            dbLastReceivingDate = new JLabel("");
//            dbLastReceivingDate.setBounds(440,215,700,45);
//            add(dbLastReceivingDate);
//
//            dbNextReceivingDate = new JLabel("");
//            dbNextReceivingDate.setBounds(440,245,700,45);
//            add(dbNextReceivingDate);
//
//        } else{
//            dbCategory = new JLabel(category);
//            dbCategory.setBounds(385,35,700,60);
//            add(dbCategory);
//
//            dbItemCode = new JLabel(code);
//            dbItemCode.setBounds(360,65,700,60);
//            add(dbItemCode);
//
//            dbItemName = new JLabel(name);
//            dbItemName.setBounds(360,95,700,60);
//            add(dbItemName);
//
//            dbItemQuantity = new JLabel(quantity);
//            dbItemQuantity.setBounds(360,125,678,60);
//            add(dbItemQuantity);
//
//            dbMarket = new JLabel(market);
//            dbMarket.setBounds(360,155,678,60);
//            add(dbMarket);
//
//            dbItemLocation = new JLabel(location);
//            dbItemLocation.setBounds(390,185,700,60);
//            add(dbItemLocation);
//
//            dbLastReceivingDate = new JLabel(lastreceivedate);
//            dbLastReceivingDate.setBounds(400,215,700,60);
//            add(dbLastReceivingDate);
//
//            dbNextReceivingDate = new JLabel(nextreceivdate);
//            dbNextReceivingDate.setBounds(430,245,700,60);
//            add(dbNextReceivingDate);
//        }

        jlImage = new JLabel("이미지");
        jlImage.setBounds(30,10,700,30);
        add(jlImage);

        img = icon.getImage();
        updateimg = img.getScaledInstance(265,250,Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateimg);

        dbImage = new JLabel();

        dbImage.setIcon(updateIcon);

        dbImage.setBounds(25,25,270,300);
        dbImage.setHorizontalAlignment(JLabel.CENTER);

        this.add(dbImage);


        btnChange = new JButton(imgChange1);
        btnChange.setBounds(900, 280, 75, 25);
        btnChange.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnChange.setRolloverIcon(imgChange2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnChange.setBorderPainted(false); // 버튼 테두리 제거
        btnChange.setFocusPainted(false);
        btnChange.setContentAreaFilled(false);
        btnChange.setPreferredSize(new Dimension(48, 24));
        btnChange.setBounds(850, 280, 75, 25);
        add(btnChange);

        btnDel = new JButton(imgDel1);
        btnDel.setBounds(900, 280, 75, 25);
        btnDel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDel.setRolloverIcon(imgDel2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnDel.setBorderPainted(false); // 버튼 테두리 제거
        btnDel.setFocusPainted(false);
        btnDel.setContentAreaFilled(false);
        btnDel.setPreferredSize(new Dimension(48, 24));
        btnDel.setBounds(900, 280, 75, 25);
        add(btnDel);
    }

    public void setTexts(String category, String code, String name,String quantity,
                    String market, String location, String lastDate,String nextDate){
        
        dbCategory.setText(category);
        dbItemCode.setText(code);
        dbItemName.setText(name);
        dbItemQuantity.setText(quantity);
        dbMarket.setText(market);
        dbItemLocation.setText(location);
        dbLastReceivingDate.setText(lastDate);
        dbNextReceivingDate.setText(nextDate);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        TestPanel p = new TestPanel();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.add(p);
        f.setVisible(true);
    }

}
