//import java.awt.*;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.io.File;
//
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//
//import javax.swing.*;
//
//public class Test{
//    static WebView webView;
//    static JFXPanel fxPanel;
//    static JPanel panel1;
//
//    Test(JPanel panel){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                initAndShowGUI(panel);
//                JFrame frame = new JFrame("FX");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.addComponentListener(new ComponentAdapter() {
//
//                    @Override
//                    public void componentResized(ComponentEvent e) {
//                        try {
//                            if (webView.isVisible()) {
//                                webView.setPrefSize(791, frame.getHeight());
//                                System.out.println(frame.getSize());
//                                System.out.println(webView.getWidth() + ", " + webView.getHeight());
//                            }
//                        } catch (Exception ex) {
//                            System.out.println(ex);
//                        }
//                    }
//                });
//                frame.setLayout(new BorderLayout());
//                frame.add(fxPanel, BorderLayout.CENTER);
//                frame.setVisible(true);
//
//                frame.setSize(new Dimension(791, 1120));
//            }
//        });
//
//    }
//
//    /* Create a JFrame with a JButton and a JFXPanel containing the WebView. */
//    private void initAndShowGUI(JPanel panel) {
//        System.out.println("123");
//        // This method is invoked on Swing thread
//
////        frame.getContentPane().setLayout(null); // do the layout manually
//        fxPanel = new JFXPanel();
//        panel.add(fxPanel);
////        frame.pack();
////        frame.setResizable(false);
//
//        Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
//            @Override
//            public void run() {
//                initFX(fxPanel);
//            }
//        });
//    }
//
//    /* Creates a WebView and fires up google.com */
//    private void initFX(final JFXPanel fxPanel) {
//        Group group = new Group();
//        Scene scene = new Scene(group);
//        fxPanel.setScene(scene);
//
//        webView = new WebView();
//
//        group.getChildren().add(webView);
//        webView.setPrefSize(791, 1120);
//
//        // Obtain the webEngine to navigate
//        File f = new File("C:/Users/KSY/Desktop/java/InventoryFriends/src/통합-문서1.html");
//        WebEngine webEngine = webView.getEngine();
//        webEngine.load(f.toURI().toString());
//    }
//
//    /* Start application */
//    public static void main(final String[] args) {
//        panel1 = new JPanel();
//        Test test = new Test(panel1);
//
////        JFrame frame = new JFrame("FX");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.addComponentListener(new ComponentAdapter() {
////
////            @Override
////            public void componentResized(ComponentEvent e) {
////                try {
////                    if (webView.isVisible()) {
////                        webView.setPrefSize(791, frame.getHeight());
////                    System.out.println(frame.getSize());
////                    System.out.println(webView.getWidth() + ", " + webView.getHeight());
////                    }
////                } catch (Exception ex) {
////                    System.out.println(ex);
////                }
////            }
////        });
////        frame.setLayout(new BorderLayout());
////        frame.add(fxPanel, BorderLayout.CENTER);
////        frame.setVisible(true);
////
////        frame.setSize(new Dimension(791, 1120));
//    }
//}