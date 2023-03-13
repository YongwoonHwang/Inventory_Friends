package main;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.TableModel;
import java.awt.*;

public class MainTabPanel extends JPanel {
    ItemListPanel jpItemList;
    JTabbedPane jtpMainTab;
    Font font;
    public MainTabPanel(String userid){

        font = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jtpMainTab = new JTabbedPane();
        jpItemList = new ItemListPanel(userid);
        jtpMainTab.addTab("재고 목록", jpItemList);
        jtpMainTab.setFont(font);
        jtpMainTab.setBackground(Color.LIGHT_GRAY);
        UIManager.put("TabbedPane.tabInsets", new Insets(3, 3, 3, 40));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(Color.LIGHT_GRAY));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.DARK_GRAY));
        SwingUtilities.updateComponentTreeUI(jtpMainTab);
        add(new JLayer<JTabbedPane>(jtpMainTab, new CloseableTabbedPaneLayerUIuseDefault()));
    }


    public void setSubTab(JTabbedPane SubTab){
        jpItemList.setSubTab(SubTab);
    }

}
