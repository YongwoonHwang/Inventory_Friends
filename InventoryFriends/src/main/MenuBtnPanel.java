package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBtnPanel extends JPanel {
    JButton btnInventoryManagement, btnOrderConsolidation, btnIMopt1, btnIMopt2;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2, imgIMopt1_1, imgIMopt1_2, imgIMopt2_1, imgIMopt2_2;
    JTabbedPane jtpMainTab;
    OrderConsolidationPanel jpOrderConsolidation;
    IndividualRegistrationPanel jpIndividualRegistration;
    BatchRegistrationPanel jpBatchRegistration;

    public MenuBtnPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        imgIM1 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IM1.jpg"));
        imgIM2 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IM2.jpg"));
        imgOC1 = new ImageIcon(getClass().getClassLoader().getResource("img/img_OC1.jpg"));
        imgOC2 = new ImageIcon(getClass().getClassLoader().getResource("img/img_OC2.jpg"));
        imgIMopt1_1 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IMopt1_1.jpg"));
        imgIMopt1_2 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IMopt1_2.jpg"));
        imgIMopt2_1 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IMopt2_1.jpg"));
        imgIMopt2_2 = new ImageIcon(getClass().getClassLoader().getResource("img/img_IMopt2_2.jpg"));

        btnInventoryManagement = new JButton(imgIM1);
        btnInventoryManagement.setBorder(BorderFactory.createEmptyBorder(5, 5, 5,5));
        btnInventoryManagement.setRolloverIcon(imgIM2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnInventoryManagement.setBorderPainted(false); // 버튼 테두리 제거
        btnInventoryManagement.setFocusPainted(false);
        btnInventoryManagement.setContentAreaFilled(false);
        btnInventoryManagement.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnInventoryManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnIMopt1.isVisible()) {
                    btnIMopt1.setVisible(false);
                    btnIMopt2.setVisible(false);
                }
                else {
                    btnIMopt1.setVisible(true);
                    btnIMopt2.setVisible(true);
                }
            }
        });

        // 개별 등록 버튼
        btnIMopt1 = new JButton(imgIMopt1_1);
        btnIMopt1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnIMopt1.setRolloverIcon(imgIMopt1_2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnIMopt1.setBorderPainted(false); // 버튼 테두리 제거
        btnIMopt1.setFocusPainted(false);
        btnIMopt1.setContentAreaFilled(false);
        btnIMopt1.setVisible(false);
        btnIMopt1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                String opt1Title = new String("재고 관리(개별 등록)");
                if (findTabByName(opt1Title, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(opt1Title, jtpMainTab));
                } else {
                    jtpMainTab.addTab(opt1Title, jpIndividualRegistration);
                    jtpMainTab.setSelectedIndex(findTabByName(opt1Title, jtpMainTab));
                }

            }
        });

        // 일괄 등록 버튼
        btnIMopt2 = new JButton(imgIMopt2_1);
        btnIMopt2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnIMopt2.setRolloverIcon(imgIMopt2_2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnIMopt2.setBorderPainted(false); // 버튼 테두리 제거
        btnIMopt2.setFocusPainted(false);
        btnIMopt2.setContentAreaFilled(false);
        btnIMopt2.setVisible(false);
        btnIMopt2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                String opt2Title = new String("재고 관리(일괄 등록)");
                if (findTabByName(opt2Title, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(opt2Title, jtpMainTab));
                } else {
                    jtpMainTab.addTab(opt2Title, jpBatchRegistration);
                    jtpMainTab.setSelectedIndex(findTabByName(opt2Title, jtpMainTab));
                }

            }
        });

        // 주문 통합 버튼
        btnOrderConsolidation = new JButton(imgOC1);
        btnOrderConsolidation.setBorder(BorderFactory.createEmptyBorder(5, 5, 5,5));
        btnOrderConsolidation.setRolloverIcon(imgOC2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnOrderConsolidation.setBorderPainted(false); // 버튼 테두리 제거
        btnOrderConsolidation.setFocusPainted(false);
        btnOrderConsolidation.setContentAreaFilled(false);

        btnOrderConsolidation.setPreferredSize(new Dimension(242, 45)); // 버튼 크기 지정
        btnOrderConsolidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String OCTitle = new String("주문 통합");
                if (findTabByName(OCTitle, jtpMainTab) != -1) {
                    jtpMainTab.setSelectedIndex(findTabByName(OCTitle, jtpMainTab));
                } else {
                    jtpMainTab.addTab(OCTitle, jpOrderConsolidation);
                    jtpMainTab.setSelectedIndex(findTabByName(OCTitle, jtpMainTab));
                }
            }
        });

        add(btnInventoryManagement);    // 재고 관리 버튼 추가
        add(btnIMopt1);
        add(btnIMopt2);
        add(btnOrderConsolidation);
    }

    public void setJtpMainTab(JTabbedPane MainTab){
        jtpMainTab = MainTab;
    }

    public void  setJpOrderConsolidation(OrderConsolidationPanel OrderConsolidation){
        jpOrderConsolidation = OrderConsolidation;
    }

    public void  setJpIndividualRegistration(IndividualRegistrationPanel IndividualRegistration){
        jpIndividualRegistration = IndividualRegistration;
    }

    public void setJpBatchRegistration(BatchRegistrationPanel Batchregistration){
        jpBatchRegistration = Batchregistration;
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
