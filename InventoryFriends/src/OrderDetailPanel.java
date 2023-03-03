import com.sun.javafx.application.PlatformImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;

public class OrderDetailPanel extends JPanel {

    private Stage stage;
    private WebView browser;
    private JFXPanel jfxPanel;
    private WebEngine webEngine;

    public OrderDetailPanel(String path) {
        initComponents(path);
    }

    public static void main(String... args) {
        // Run this later:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                OrderDetailPanel detailPanel = new OrderDetailPanel("./HTML.html");

                frame.setLayout(new BorderLayout());
                frame.getContentPane().add(detailPanel, BorderLayout.CENTER);
                frame.setMinimumSize(new Dimension(791, 800));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                JButton btn = new JButton("!23");
                frame.add(btn, BorderLayout.SOUTH);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        detailPanel.setBrowserPath("./HTML1.html");
                        detailPanel.repaint();
                    }
                });


            }
        });
    }

    private void initComponents(String path) {

        jfxPanel = new JFXPanel();
        createScene(path);

        setLayout(new BorderLayout());
        add(jfxPanel, BorderLayout.CENTER);
    }

    /**
     * createScene
     * <p>
     * Note: Key is that Scene needs to be created and run on "FX user thread"
     * NOT on the AWT-EventQueue Thread
     */
    private void createScene(String path) {
        PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {

                stage = new Stage();

                stage.setTitle("Hello Java FX");
                stage.setResizable(true);

                Group root = new Group();
                Scene scene = new Scene(root, 80, 20);
                stage.setScene(scene);

                // Set up the embedded browser:
                browser = new WebView();
                browser.setPrefSize(791, 950);
                webEngine = browser.getEngine();
                File f = new File(path);
                webEngine.load(f.toURI().toString());

                ObservableList<Node> children = root.getChildren();
                children.add(browser);

                jfxPanel.setScene(scene);
            }
        });
    }

    public void setBrowserSize(double d1, double d2) {
        browser.setPrefSize(d1, d2);
    }

    public void setBrowserPath(String path) {
        new Runnable() {
            @Override
            public void run() {
                webEngine = browser.getEngine();
                File f = new File(path);
                webEngine.loadContent(f.toURI().toString());
                webEngine.reload();
            }
        };
    }
}