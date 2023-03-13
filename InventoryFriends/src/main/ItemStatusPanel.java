package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;

public class ItemStatusPanel extends JPanel {
    JLabel jlCategory, jlItemCode, jlItemName, jlItemQuantity, jlMarket, jlItemLocation, jlLastReceivingDate, jlNextReceivingDate, jlImage,
            dbCategory,dbItemCode,dbItemName, dbItemQuantity, dbMarket, dbItemLocation, dbLastReceivingDate, dbNextReceivingDate, dbImage;
    String dbIdno;
    int selectRow;
    String dbName = "ifdb";
    String dbTableName;
    private  PreparedStatement pstmt = null;
    private  Connection con = null;
    ImageIcon imgChange1 = new ImageIcon("./img/img_Change1.jpg");
    ImageIcon imgChange2 = new ImageIcon("./img/img_Change2.jpg");
    ImageIcon imgDel1 = new ImageIcon("./img/img_Del1.jpg");
    ImageIcon imgDel2 = new ImageIcon("./img/img_Del2.jpg");
    ImageIcon icon = new ImageIcon("./img/img_default.jpg");

    JSplitPane jspRight;
    JScrollPane scrItemStatus;
    ImageIcon updateIcon;
    Image img,updateimg;
    JButton btnChange,btnDel;
    ItemListTable jtItemList;
    ModifyPanel modifyPanel;
    JTabbedPane jtpMainTab;
    JTabbedPane jtpSubTab;
    JPanel jpItemStatus, jpCenter, jpWest;

    public ItemStatusPanel(){
        setLayout(new BorderLayout());
        jpItemStatus = new JPanel(new BorderLayout());
        jpCenter = new JPanel();
        jpWest = new JPanel();
        scrItemStatus = new JScrollPane(jpItemStatus);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        jpWest.setLayout(gb);
        jpCenter.setLayout(gb);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jlImage = new JLabel("이미지");
        jlImage.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(jlImage);
        jpWest.add(panel, gbc);

        gbc.gridy = 1;
        img = icon.getImage();
        updateimg = img.getScaledInstance(250,250,Image.SCALE_SMOOTH);
        updateIcon = new ImageIcon(updateimg);
        dbImage = new JLabel();
        dbImage.setIcon(updateIcon);
        jpWest.add(dbImage, gbc);


        jpItemStatus.add(jpWest, BorderLayout.WEST);
        jpItemStatus.add(jpCenter, BorderLayout.CENTER);

        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);

        JPanel panel1 = new JPanel(fl);
        panel1.setAlignmentX(LEFT_ALIGNMENT);
        gbc.insets = new Insets(20, 20, 0, 0);
        gbc.gridy = 0;
        jlCategory = new JLabel("카테고리 : ");
        panel1.add(jlCategory);
        dbCategory = new JLabel("");
        panel1.add(dbCategory);
        jpCenter.add(panel1, gbc);

        JPanel panel2 = new JPanel(fl);
        panel2.setAlignmentX(LEFT_ALIGNMENT);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.gridy = 1;
        jlItemCode = new JLabel("코드 : ");
        panel2.add(jlItemCode);
        dbItemCode = new JLabel("");
        panel2.add(dbItemCode);
        jpCenter.add(panel2, gbc);

        JPanel panel3 = new JPanel(fl);
        panel3.setAlignmentX(LEFT_ALIGNMENT);
        gbc.gridy = 2;
        jlItemName = new JLabel("품명 : ");
        panel3.add(jlItemName);
        dbItemName = new JLabel("");
        panel3.add(dbItemName);
        jpCenter.add(panel3, gbc);

        JPanel panel4 = new JPanel(fl);
        panel4.setAlignmentX(LEFT_ALIGNMENT);
        gbc.gridy = 3;
        jlItemQuantity = new JLabel("수량 : ");
        panel4.add(jlItemQuantity);
        dbItemQuantity = new JLabel("");
        panel4.add(dbItemQuantity);
        jpCenter.add(panel4, gbc);

        JPanel panel5 = new JPanel(fl);
        panel5.setAlignmentX(LEFT_ALIGNMENT);
        gbc.gridy = 4;
        jlMarket = new JLabel("마켓 : ");
        panel5.add(jlMarket);
        dbMarket = new JLabel("");
        panel5.add(dbMarket);
        jpCenter.add(panel5, gbc);

        JPanel panel6 = new JPanel(fl);
        panel6.setAlignmentX(LEFT_ALIGNMENT);
        gbc.gridy = 5;
        jlItemLocation = new JLabel("재고 위치 : ");
        panel6.add(jlItemLocation);
        dbItemLocation = new JLabel("");
        panel6.add(dbItemLocation);
        jpCenter.add(panel6, gbc);

        JPanel panel7 = new JPanel(fl);
        panel7.setAlignmentX(LEFT_ALIGNMENT);
        gbc.gridy = 6;
        jlLastReceivingDate = new JLabel("최근 입고일 : ");
        panel7.add(jlLastReceivingDate);
        dbLastReceivingDate = new JLabel("");
        panel7.add(dbLastReceivingDate);
        jpCenter.add(panel7, gbc);

        JPanel panel8 = new JPanel(fl);
        panel8.setAlignmentX(LEFT_ALIGNMENT);
        gbc.insets = new Insets(0, 20, 20, 0);
        gbc.gridy = 7;
        jlNextReceivingDate = new JLabel("다음 입고 예정일 : ");
        panel8.add(jlNextReceivingDate);
        dbNextReceivingDate = new JLabel("");
        panel8.add(dbNextReceivingDate);
        jpCenter.add(panel8, gbc);

        JPanel panel9 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel9.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.gridy = 8;
        btnChange = new JButton(imgChange1);
        btnChange.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnChange.setRolloverIcon(imgChange2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnChange.setBorderPainted(false); // 버튼 테두리 제거
        btnChange.setFocusPainted(false);
        btnChange.setContentAreaFilled(false);
        btnChange.setPreferredSize(new Dimension(48, 24));

        modifyPanel = new ModifyPanel();
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IMTitle = new String("재고 수정");

                String category = dbCategory.getText(); /*선택한줄의 2번째 값*/
                String code = dbItemCode.getText();
                String name = dbItemName.getText();
                String quantity = dbItemQuantity.getText();
                String market = dbMarket.getText();
                String location = dbItemLocation.getText();
                String lastReceiveDate = dbLastReceivingDate.getText();
                String nextReceivDate = dbNextReceivingDate.getText();
                String idno = dbIdno;
                String dbtablename = dbTableName;

                modifyPanel.setTexts(category, code, name, quantity, market, location,
                        lastReceiveDate, nextReceivDate,idno,selectRow,dbtablename);
                modifyPanel.repaint();

                jtpMainTab.setVisible(true);
                if (findTabByName(IMTitle, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(IMTitle, jtpMainTab));
                } else {
                    jtpMainTab.addTab(IMTitle, modifyPanel);
                    jtpMainTab.setSelectedIndex(findTabByName(IMTitle, jtpMainTab));
                }
            }

        });
        panel9.add(btnChange);

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

                int input = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?",
                        "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if(input == JOptionPane.OK_OPTION){
                    String sql = "DELETE FROM " + dbTableName + " WHERE id = " + dbIdno;
                    System.out.println(sql);
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                        // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                        //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.
                        pstmt = con.prepareStatement(sql);
                        pstmt.execute("USE " + dbName);
                        int cnt = pstmt.executeUpdate();
                        // 사용할 DB를 선택한다.
                        if(cnt != 0){
                            jtItemList.modelItemList.removeRow(selectRow);
                            String ISTitle = "재고 상세";
                            jtpSubTab.removeTabAt(findTabByName(ISTitle, jtpSubTab));
                            try{
                                jtpSubTab.isEnabledAt(0);
                            } catch (Exception exception){
                                jspRight.setDividerSize(0);
                                jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                                jtpSubTab.setVisible(false);
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
        });
        panel9.add(btnDel);
        jpCenter.add(panel9, gbc);
        JScrollPane jScrollPane = new JScrollPane(jpItemStatus);
        jScrollPane.setBorder(null);
        add(jScrollPane, BorderLayout.CENTER);

    }
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
    public void setTexts(String category, String code, String name,String quantity,
                         String market, String location, String lastDate, String nextDate, String image, String idno,int index,String dbtname){

        dbCategory.setText(category);
        dbItemCode.setText(code);
        dbItemName.setText(name);
        dbItemQuantity.setText(quantity);
        dbMarket.setText(market);
        dbItemLocation.setText(location);
        dbLastReceivingDate.setText(lastDate);
        dbNextReceivingDate.setText(nextDate);
        if(image.equals(""))
            image = "./img/img_default.jpg";
        else if(!isImage(image))
            image = "./img/img_default.jpg";
        ImageIcon newIcon = new ImageIcon(image);
        img = newIcon.getImage();
        updateimg = img.getScaledInstance(250,250,Image.SCALE_SMOOTH);
        ImageIcon updatenewIcon = new ImageIcon(updateimg);
        dbImage.setIcon(updatenewIcon);
        dbIdno = idno;
        selectRow = index;
        dbTableName = dbtname;

        revalidate();
        repaint();
    }

    public static boolean isImage(String filepath){
        boolean result = false;
        try {
            File f = new File(filepath);
            BufferedImage buf = ImageIO.read(f);
            if(buf == null){
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


    public static void main(String[] args) {
        ItemStatusPanel p = new ItemStatusPanel();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.add(p);
        f.setVisible(true);
    }
}
