//import com.sun.javafx.application.PlatformImpl;
//import java.awt.BorderLayout;
//import java.io.File;
//
//import javafx.collections.ObservableList;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;
//import javafx.stage.Stage;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.SwingUtilities;
//
//public class Test4 extends JPanel {
//
//    private Stage stage;
//    private WebView browser;
//    private JFXPanel jfxPanel;
//    private WebEngine webEngine;
//
//    public Test4(){
//        initComponents();
//    }
//
//    public static void main(String ...args){
//        // Run this later:
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                final JFrame frame = new JFrame();
//
//                frame.getContentPane().add(new Test4());
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//            }
//        });
//    }
//
//    private void initComponents(){
//
//        jfxPanel = new JFXPanel();
//        createScene();
//
//        setLayout(new BorderLayout());
//        add(jfxPanel, BorderLayout.CENTER);
//    }
//
//    /**
//     * createScene
//     *
//     * Note: Key is that Scene needs to be created and run on "FX user thread"
//     *       NOT on the AWT-EventQueue Thread
//     *
//     */
//    private void createScene() {
//        PlatformImpl.startup(new Runnable() {
//            @Override
//            public void run() {
//
//                stage = new Stage();
//
//                stage.setTitle("Hello Java FX");
//                stage.setResizable(true);
//
//                Group root = new Group();
//                Scene scene = new Scene(root,80,20);
//                stage.setScene(scene);
//
//                // Set up the embedded browser:
//                browser = new WebView();
//                webEngine = browser.getEngine();
//                File f = new File("C:/Users/KSY/Desktop/java/InventoryFriends/HTML.html");
//                webEngine.load(f.toURI().toString());
////                webEngine.load("http://www.google.com");
//
//                ObservableList<Node> children = root.getChildren();
//                children.add(browser);
//
//                jfxPanel.setScene(scene);
//            }
//        });
//    }
//}