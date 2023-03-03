import javax.swing.*;

public class Test3 extends JFrame {
    Test3(String path){
        OrderDetailPanel ODPanel = new OrderDetailPanel(path);
        getContentPane().add(ODPanel);
        setLocationRelativeTo(null);
        setSize(791, 950);
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        Test3 test4 = new Test3("./HTML.html");
    }
}



