import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;

public class MallInformationWindow extends JFrame {
    JPanel jpNorth, jpCenter, jpInput, jpCenterTmp, jpInputBtn;
    String[] mallName = {"11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};
    JLabel jLabel1, jLabel2;
    String userID;
    Font font1, font2;
    public MallInformationWindow(){

        font1 = new Font("돋움", Font.BOLD, 20);
        font2 = new Font("돋움", Font.BOLD, 15);

        setSize(500, 600);
        setVisible(true);
//        setResizable(false);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        userID = "ID";
        jpNorth = new JPanel();
        jpCenter = new JPanel(new BorderLayout());
        jpCenterTmp = new JPanel(new BorderLayout());
        jpInput = new JPanel();
        jpInputBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 0, 0, 0);
        jpInput.setLayout(gb);
        jLabel1 = new JLabel("<html><body style='text-align:center;'><br>" + userID + "님 반갑습니다!</body></html>", JLabel.CENTER);
        jLabel1.setFont(font1);
        jLabel2 = new JLabel("<html><br>" + userID + "님의 재고관리를 도울 재고관리 도우미입니다.<br> 먼저, 원활한 사용을 위하여 이용중인 쇼핑몰의 정보를<br>입력해주세요.</html>");
        jLabel2.setFont(font2);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jpNorth.setLayout(new BoxLayout(jpNorth, BoxLayout.Y_AXIS));
        jpNorth.add(jLabel1);
        jpNorth.add(jLabel2);
        for(int i = 0; i < 7; i++){
            JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panel1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
            JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            panel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            JCheckBox checkBox = new JCheckBox();
            JTextField text = new JTextField();
            text.setColumns(11);
            text.setEnabled(false);
            checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        text.setEnabled(true);
                    } else{
                        text.setEnabled(false);
                    }
                }
            });


            panel1.add(checkBox);
            panel1.add(new JLabel(mallName[i]));
            panel2.add(new JLabel(mallName[i]+"관리자 ID : "));
            panel2.add(text);

            panel1.setAlignmentX(LEFT_ALIGNMENT);
            panel2.setAlignmentX(RIGHT_ALIGNMENT);

            gbc.gridx = 0;
            gbc.gridy = i;
//            gbc.gridwidth = 1;
//            gbc.gridheight = 1;
            jpInput.add(panel1, gbc);
            gbc.gridx = 1;
            jpInput.add(panel2, gbc);
        }
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        JLabel label = new JLabel("<html><br>* 고객님의 마켓정보를 불러오기 위해 관리자 ID를 정확히 입력해 주세요.<br><br><br>&nbsp;</html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.RED);
        jpInput.add(label, gbc);

        jpInputBtn.add(new JButton("확인"), gbc);
        jpCenterTmp.setBorder(BorderFactory.createEtchedBorder(0));
        jpCenterTmp.add(jpInput, BorderLayout.CENTER);
        jpCenterTmp.add(jpInputBtn, BorderLayout.SOUTH);
        jpCenter.add(jpCenterTmp, BorderLayout.CENTER);
        jpCenter.add(new JPanel(), BorderLayout.NORTH);
        jpCenter.add(new JPanel(), BorderLayout.SOUTH);
        jpCenter.add(new JPanel(), BorderLayout.EAST);
        jpCenter.add(new JPanel(), BorderLayout.WEST);
//        jpBorder.add(jpNorth, BorderLayout.NORTH);
        add(jpNorth, BorderLayout.NORTH);
        add(jpCenter, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        new MallInformationWindow();
    }
}
