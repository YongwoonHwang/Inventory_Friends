import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MemoTab extends JTabbedPane{

    JPanel jpMemoTab, jpMemoTabBorder, jpMemoTabBtn;
    JScrollPane jspMemoTab;
    JButton btnAdd;
    GridBagConstraints gbc;
    static MemoWindow memoWindow;
    public MemoTab(){
        jpMemoTab = new JPanel(new GridBagLayout());
        jspMemoTab = new JScrollPane(jpMemoTab);
        jpMemoTabBorder = new JPanel(new BorderLayout());
        jpMemoTabBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd =new JButton("등록");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoWindow.jtaMemoWin.setText("");
                memoWindow.setDate(LocalDate.now());
                memoWindow.setVisible(true);
            }
        });
        jpMemoTabBtn.add(btnAdd);
        jspMemoTab.setBorder(BorderFactory.createEmptyBorder());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        jpMemoTabBorder.add(jspMemoTab, BorderLayout.CENTER);
        jpMemoTabBorder.add(jpMemoTabBtn, BorderLayout.SOUTH);
        addMemo("1");
        addMemo("2");
        addTab("메모", jpMemoTabBorder);
    }

    public void setMemoWindow(MemoWindow mw){ memoWindow = mw; }

    public void addMemo(String contents){
        JLabel label = new JLabel(contents);
        label.setPreferredSize(new Dimension(100,25));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        label.setBackground(Color.YELLOW);
//            label.setBorder(BorderFactory.createEtchedBorder());  // 테두리 설정
        jpMemoTab.add(label, gbc);
    }

    public static void main(String[] args)
    {
        MemoTab tmp = new MemoTab();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(tmp);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
