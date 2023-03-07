import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Test1 {

    public Test1() {
        OrderDetailPanel panel = new OrderDetailPanel();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);



        /* This was just a text panel to make sure the full panel was
         * drawn to the panel.
         */


        /* Print Image to PDF */
        String fileName = "newfile.pdf";


        /* This was just a test to show the newPanel drew the entire
         * original panel (scaled)
         */
        JButton btn = new JButton("123");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final java.awt.Image image = getImageFromPanel(panel);

                JPanel newPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                        System.out.println("Test = " + getWidth() + "  " + getHeight());
                    }

                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(300, 300);
                    }

                };

                JOptionPane.showMessageDialog(null, newPanel);


                printToPDF(image, fileName);
            }
        });
        frame.add(btn, BorderLayout.SOUTH);


    }

    public void printToPDF(java.awt.Image awtImage, String fileName) {
        try {
            Document d = new Document();
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
                    fileName));
            d.open();


            Image iTextImage = Image.getInstance(writer, awtImage, 1);
            iTextImage.setAbsolutePosition(0, 0);
            iTextImage.scalePercent(75);
            d.add(iTextImage);

            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static java.awt.Image getImageFromPanel(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(),
                component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }

    /**
     * Demo panel that is 2000x2000 px with alternating squares
     * to check all squares are drawn to image
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Test1();
            }
        });
    }
}