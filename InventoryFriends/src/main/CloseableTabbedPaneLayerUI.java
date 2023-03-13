package main;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.awt.event.MouseEvent;

// 탭 메뉴에 x버튼 추가하는 클래스
public class CloseableTabbedPaneLayerUI extends LayerUI<JTabbedPane> {
    JTabbedPane jtpSubTab;
    JSplitPane jspRight;
    private final JPanel p = new JPanel();
    private final Point pt = new Point(-100, -100);
    private final JButton button = new JButton("x") {
        @Override public Dimension getPreferredSize() {
            return new Dimension(16, 16);
        }
    };
    public CloseableTabbedPaneLayerUI() {
        super();
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setRolloverEnabled(false);
    }
    public void setComponent(JTabbedPane jtp, JSplitPane jsp){
        jtpSubTab = jtp;
        jspRight = jsp;
    }
    @Override public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        if (c instanceof JLayer) {
            JLayer jlayer = (JLayer) c;
            JTabbedPane tabPane = (JTabbedPane) jlayer.getView();
            for (int i = 0; i < tabPane.getTabCount(); i++) {
                Rectangle rect = tabPane.getBoundsAt(i);
                Dimension d = button.getPreferredSize();
                int x = rect.x + rect.width - d.width - 2;
                int y = rect.y + (rect.height - d.height) / 2;
                Rectangle r = new Rectangle(x, y, d.width, d.height);
//                    button.setForeground(r.contains(pt) ? Color.RED : Color.BLACK);
                SwingUtilities.paintComponent(g, button, p, r);
            }
        }
    }
    @Override public void installUI(JComponent c) {
        super.installUI(c);
        ((JLayer)c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }
    @Override public void uninstallUI(JComponent c) {
        ((JLayer)c).setLayerEventMask(0);
        super.uninstallUI(c);
    }
    @Override protected void processMouseEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
            pt.setLocation(e.getPoint());
            final Point mousePos = l.getMousePosition();
            JTabbedPane tabbedPane = (JTabbedPane) l.getView();
//                int index = tabbedPane.indexAtLocation(pt.x, pt.y);
            int index = tabbedPane.indexAtLocation(mousePos.x, mousePos.y);
            if (index >= 0) {
                Rectangle rect = tabbedPane.getBoundsAt(index);
                Dimension d = button.getPreferredSize();
                int x = rect.x + rect.width - d.width - 2;
                int y = rect.y + (rect.height - d.height) / 2;
                Rectangle r = new Rectangle(x, y, d.width, d.height);
                if (r.contains(pt)) {
                    tabbedPane.removeTabAt(index);
                    try{
                        jtpSubTab.isEnabledAt(0);
                    } catch (Exception exception){
                        jspRight.setDividerSize(0);
                        jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                        jtpSubTab.setVisible(false);
                    };
                }
            }
            l.getView().repaint();
        }
    }
    @Override protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
        pt.setLocation(e.getPoint());
        JTabbedPane tabbedPane = (JTabbedPane) l.getView();
        int index = tabbedPane.indexAtLocation(pt.x, pt.y);
        if (index >= 0) {
            tabbedPane.repaint(tabbedPane.getBoundsAt(index));
        } else {
            tabbedPane.repaint();
        }
    }
}
