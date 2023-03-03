//import com.itextpdf.layout.Document;
//import com.lowagie.text.pdf.PdfWriter;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.FileOutputStream;
//
//import javax.swing.*;
//import javax.swing.text.html.HTMLEditorKit;
//import javax.swing.text.html.StyleSheet;
//
//public class Test2 {
//
//    public Test2() {
//        Test4 panel = new Test4();
//        JFrame frame = new JFrame();
//        frame.setLayout(new BorderLayout());
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.setSize(100, 200);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setVisible(true);
//
//        final java.awt.Image image = getImageFromPanel(panel);
//
//
//
//        /* Print Image to PDF */
//        String fileName = "./newfile.pdf";
//        printToPDF(image, fileName);
//
//        JButton btn = new JButton("123");
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JPanel newPanel = new JPanel() {
//                    @Override
//                    protected void paintComponent(Graphics g) {
//                        super.paintComponent(g);
//                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//                        System.out.println("Test = " + getWidth() + "  " + getHeight());
//                    }
//
//                    @Override
//                    public Dimension getPreferredSize() {
//                        return new Dimension(300, 300);
//                    }
//                };
//
//                JOptionPane.showMessageDialog(null, newPanel);
//            }
//        });
//        frame.add(btn, BorderLayout.SOUTH);
//
//    }
//
//    public void printToPDF(java.awt.Image awtImage, String fileName) {
//        try {
//            Document d = new Document();
//            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
//                    fileName));
//            d.open();
//
//
//            Image iTextImage = Image.getInstance(writer, awtImage, 1);
//            iTextImage.setAbsolutePosition(0, 0);
//            iTextImage.scalePercent(75);
//            d.add(iTextImage);
//
//            d.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static java.awt.Image getImageFromPanel(Component component) {
//
//        BufferedImage image = new BufferedImage(791, 1120, BufferedImage.TYPE_INT_RGB);
//        component.paint(image.getGraphics());
//        return image;
//    }
//
//    /**
//     * Demo panel that is 2000x2000 px with alternating squares
//     * to check all squares are drawn to image
//     */
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new Test2();
//            }
//        });
//    }
//}