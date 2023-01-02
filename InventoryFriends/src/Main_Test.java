import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.*;

public class Main_Test extends JFrame{
    JPanel jpLU, jpLD, jpRU, jpRD;
    JMenuBar jmbMenuBar;
    JMenu jmFileMenu;
    JButton btnSignOut, btnInventoryManagement, btnOrderConsolidation;
    JSplitPane jspCenter, jspLeft, jspRight;
    JLabel jlUserName, jlIMopt1, jlIMopt2;
    JTextField jTextField, jTextField1, jTextField2, jTextField3;
    JTabbedPane jtpMainTab, jtpMainTab2;
    ImageIcon imgIM1, imgIM2, imgOC1, imgOC2, imgSO1, imgSO2;
    Main.MenuAction menuAct;
    Font font1, font2;

    private void createPanel(){

        jpLU = new JPanel();
        jpLD = new JPanel();
        jpRU = new JPanel();
        jpRD = new JPanel();

        jTextField1 = new JTextField();
        jTextField1.setBounds(50,70,200,40);
        jTextField1.setLocation(110, 30);
        jTextField1 = new JTextField();

        jTextField2 = new JTextField();
        jTextField2.setBounds(50,70,200,40);
        jTextField2.setLocation(110, 30);
        jTextField2 = new JTextField();

        jTextField3 = new JTextField();
        jTextField3.setBounds(50,70,200,40);
        jTextField3.setLocation(110, 30);
        jTextField3 = new JTextField();
    }
}
