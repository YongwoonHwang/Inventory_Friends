package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.FileOutputStream;
import java.nio.file.*;
import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.qoppa.pdf.PDFException;
import com.qoppa.pdf.PrintSettings;
import com.qoppa.pdfPrint.PDFPrint;

public class OrderDetailPrintWindow extends JFrame {

    public OrderDetailPrintWindow(OrderDetailPanel panel, String fileName) {
        setLayout(new BorderLayout());
        add(new JScrollPane(panel), BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(835, 1000);
        setResizable(false);
        setVisible(false);

        String pdfFileName = fileName+".pdf";

        JButton btn = new JButton("인쇄");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final java.awt.Image image = getImageFromPanel(panel);
                printToPDF(image, pdfFileName);
                Path filePath = Paths.get("./"+pdfFileName);

                try {
                    PDFPrint pdfPrint = new PDFPrint(filePath.toString(), null); //pdf를 프린터블하게끔 바꿔주는 메소드
                    pdfPrint.printToDefaultPrinter(new PrintSettings()); //default속성의 프린트 설정
                }catch (PDFException k){
                    //TODO Auto-generated catch block
                }catch (PrinterException k){
                    //TODO Auto-generated catch block
                    k.printStackTrace();
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }

            }
        });
        add(btn, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });


    }

    public void printToPDF(java.awt.Image awtImage, String fileName) {
        try {
            Document d = new Document();
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
                    fileName));
            d.open();


            Image iTextImage = Image.getInstance(writer, awtImage, 1);
            iTextImage.setAbsolutePosition(0, 50);
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

}