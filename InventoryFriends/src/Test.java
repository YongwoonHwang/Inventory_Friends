import java.awt.BorderLayout;
import java.awt.Color;
//from ww  w .j  ava 2  s  .c om
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;

@SuppressWarnings("serial")
public class Test extends JTabbedPane {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().setBackground(Color.RED);
        frame.getContentPane().add(new Test());
        frame.setVisible(true);
    }

    public Test() {
//        UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(5, 0, 0, 0));
        UIManager.put("TabbedPane.contentAreaColor", new ColorUIResource(new Color(238, 238, 238)));
        UIManager.put("TabbedPane.selected", new ColorUIResource(new Color(238, 238, 238)));
//        UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(238, 238, 238)));
//        UIManager.put("TabbedPane.darkShadow", new ColorUIResource(Color.RED));
        UIManager.put("TabbedPane.borderHightlightColor", new ColorUIResource(Color.RED));
//        UIManager.put("TabbedPane.light", new ColorUIResource(Color.RED));
//        UIManager.put("TabbedPane.tabAreaBackground", new ColorUIResource(Color.RED));
//        UIManager.put("ToolTip.background", Color.RED);
//        UIManager.put("ToolTip.border", new BorderUIResource(new LineBorder(Color.RED)));
        this.updateUI();

//        this.setBackground(Color.BLUE);

        JPanel testPanel = new JPanel();
        testPanel.setLayout(new BorderLayout());
        testPanel.add(new JLabel("Hello World"), BorderLayout.NORTH);
//        testPanel
//                .add(new JTextArea("Looks nice out there :)"), BorderLayout.CENTER);

        JPanel testPanel2 = new JPanel();
        testPanel2.setLayout(new BorderLayout());
        testPanel2.add(new JLabel("Good Bye World"), BorderLayout.NORTH);
////        testPanel2.add(new JTextArea("OK"),
//                BorderLayout.CENTER);

        this.addTab("Hello World", testPanel);
        this.addTab("World", testPanel2);
    }
}