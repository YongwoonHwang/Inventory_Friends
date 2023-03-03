//import org.apache.pdfbox.Loader;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.printing.PDFPrintable;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//
//import java.awt.print.PageFormat;
//import java.awt.print.Paper;
//import java.awt.print.PrinterJob;
//import java.io.File;
//import java.io.FileOutputStream;
//
//public class Test5 {
//    Test5(){
//        printFile("./HTML");
//    }
//    public void printFile(String fileName) {
//        //Convert to PDF
//        try {
//            ITextRenderer renderer = new ITextRenderer();
//            renderer.setDocument(new File(fileName + ".html"));
//
//            renderer.layout();
//
//            String fileNameWithPath = fileName + ".pdf";
//            FileOutputStream fos = new FileOutputStream(fileNameWithPath);
//            renderer.createPDF(fos);
//
//            fos.close();
////        } catch (DocumentException x){
//
//        } catch (Exception x) {
//            x.printStackTrace();
//        }
//        //Print with Dialog
//        try {
//            PrinterJob printJob = PrinterJob.getPrinterJob();
//            PageFormat pageFormat = new PageFormat();
//            pageFormat.setOrientation(PageFormat.LANDSCAPE);
//            Paper paper = new Paper();
//            paper.setSize(595, 842);
//            paper.setImageableArea(0, 0, 595, 842);
//            pageFormat.setPaper(paper);
//
//            PDDocument doc = Loader.loadPDF(new File(fileName+".pdf"));
//
//
//            printJob.setPrintable(new PDFPrintable(doc), pageFormat);
//
//            if (printJob.printDialog()) {
//                printJob.print();
//            }
//            doc.close();
//
//        } catch (Exception x) {
//            x.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) {
//        new Test5();
//    }
//}