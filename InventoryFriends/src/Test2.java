import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class Test2 {
//    PrintBoard pb;
//    PdfBoxService pbs;

    public Test2() {
        OrderDetailPanel panel = new OrderDetailPanel();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
        frame.setResizable(false);
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
        JButton btn = new JButton("인쇄");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final java.awt.Image image = getImageFromPanel(panel);
                printToPDF(image, fileName);

//                try {
//
//                    // 로컬 시스템에 이미지 파일을 생성하는 예
//                    ImageIO.write((RenderedImage) image, "jpg", new File("./img/test_img"));
//                    System.out.println("이미지 파일 생성 성공");
//                } catch (Exception k) {
//                    k.printStackTrace();
//                }

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
//
//                };

//                pbs = new PdfBoxService();

//                try {
////                    pbs.images2Pdf("./img"); //이미지 -> pdf 변환 메소드
//                    PDFPrint pdfPrint = new PDFPrint("./"+fileName,null); //pdf를 프린터블하게끔 바꿔주는 메소드
//                    pdfPrint.printToDefaultPrinter(new PrintSettings()); //default속성의 프린트 설정
//                }catch (PDFException k){
//                    //TODO Auto-generated catch block
//                }catch (PrinterException k){
//                    //TODO Auto-generated catch block
//                    k.printStackTrace();
//                }catch (Exception ex){
//                    throw new RuntimeException(ex);
//                }
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
            iTextImage.setAbsolutePosition(0, 125);
            iTextImage.scalePercent(75);
            d.add(iTextImage);

            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    private PrinterJob createPDFPrinterJob(PDDocument pdfDocument) {
//
//        PrinterJob printJob = PrinterJob.getPrinterJob();
//
//        PageFormat pageFormat = new PageFormat();
//        pageFormat.setOrientation(PageFormat.PORTRAIT);
//
//        Paper paper = new Paper();
//        pageFormat.setPaper(paper);
//
//        try {
//            printJob.setPrintable(new PDPageable(pdfDocument), pageFormat);
//        }
//        catch(PrinterException ex) {
//            ex.printStackTrace();
//        }
//
//        return printJob;
//    }

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
                new Test2();
            }
        });
    }
}