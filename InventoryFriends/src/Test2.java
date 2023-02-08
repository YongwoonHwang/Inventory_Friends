import java.awt.*;
import javax.swing.*;

public class Test2
{
    public static void main(String[] args)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.pink);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;


        gbc.gridwidth = GridBagConstraints.REMAINDER;
        for(int j = 0; j < 3; j++)
        {
//            gbc.gridx = 0;
//            gbc.gridy = j-1;
//            gbc.gridheight = j+1;
            JLabel label = new JLabel("label " + (j + 1), JLabel.CENTER);
            label.setPreferredSize(new Dimension(100,40));
            label.setBorder(BorderFactory.createEtchedBorder());
            panel.add(label, gbc);
        }
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JSplitPane tmp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//        tmp.setTopComponent(new JPanel());
//        tmp.setBottomComponent(panel);
//        f.add(tmp);
        f.add(new JScrollPane(panel));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}