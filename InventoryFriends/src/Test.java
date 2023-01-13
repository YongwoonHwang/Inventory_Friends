import java.awt.*;
import javax.swing.*;

public class Test
{
    public static void main(String[] args)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.pink);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0,0,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        for(int j = 0; j < 1; j++)
        {
            JLabel label = new JLabel("label 1111111111" + (j + 1), JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setPreferredSize(new Dimension(100,40));
            label.setBorder(BorderFactory.createEtchedBorder());
            panel.add(label, gbc);
        }
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(panel));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}