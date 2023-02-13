import java.awt.BorderLayout;
/* w w  w  . j ava2 s.  c  o m*/
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        String[] list = { "Hello 1", "Hello 2", "Hello 3", "Hello 4" };
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list);
        JComboBox<String> comboBox = new JComboBox<>(model);
        frame.add(comboBox, BorderLayout.NORTH);

        final JTextField textField = new JTextField(30);
        frame.add(textField, BorderLayout.SOUTH);
        frame.add(new JLabel("Type something, then press enter", JLabel.CENTER));

        textField.addActionListener(ae -> {
            String text = textField.getText();
            model.addElement(text);
            comboBox.setSelectedItem(text);
            textField.setText("");

        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}