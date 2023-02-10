import javax.swing.*;
import java.awt.*;

public class MemoTabPanel extends JPanel {
    MemoTab memoTab;
    MemoWindow memoWindow;
    public MemoTabPanel(){
        memoTab = new MemoTab();
        memoWindow = new MemoWindow();

        memoTab.setMemoWindow(memoWindow);

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(memoTab);
    }
}
