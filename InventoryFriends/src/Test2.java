import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Test2 {
    private JFrame frame;
    private JPanel pane;
    private JButton button1;
    private JButton button2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Test2()::createAndShowGui);
    }

    public void createAndShowGui() {
        frame = new JFrame(getClass().getSimpleName());

        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));

        button1 = new JButton("Button1");
        button2 = new JButton("Button2");

        pane.add(button1);
        pane.add(Box.createHorizontalGlue());
        pane.add(button2);

        frame.add(pane, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}