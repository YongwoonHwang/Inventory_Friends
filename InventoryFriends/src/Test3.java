import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerSize(0);
        JPanel content = new JPanel();
        JScrollPane jScrollPane = new JScrollPane(jsp);
        JPanel top = new JPanel();
        JButton btn = new JButton("123");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jsp.getMaximumDividerLocation());
                jsp.setDividerLocation(jsp.getMaximumDividerLocation());
                jsp.setDividerSize(0);
                jsp.setEnabled(false);
            }
        });
        top.add(btn);
        jsp.setTopComponent(top);
        jsp.setBottomComponent(content);
        frame.setContentPane(jScrollPane);
        frame.setSize(400, 300);
        GroupLayout gLayout = new GroupLayout(content);
        content.setLayout(gLayout);
        GroupLayout.ParallelGroup hGroup = gLayout.createParallelGroup();
        gLayout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = gLayout.createSequentialGroup();
        gLayout.setVerticalGroup(vGroup);
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("label"+String.valueOf(i+1)));

            panel.setBorder(BorderFactory.createLineBorder(Color.red));
            hGroup.addComponent(panel);
            vGroup.addComponent(panel, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
            vGroup.addGap(5);
        }

        frame.setVisible(true);
    }
}
