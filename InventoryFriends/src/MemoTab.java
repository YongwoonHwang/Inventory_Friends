import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemoTab extends JTabbedPane{
    JPanel jpMemoTab, jpMemoTabBorder, jpMemoTabBtn;
    JScrollPane jspMemoTab;
    JButton btnAdd;
    GridBagConstraints gbc;
    static MemoWindow memoWindow;
    static ArrayList<MemoLabel> jlList = new ArrayList<>();
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
                memoWindow.btnDelete.setEnabled(false);
                memoWindow.btnSave.setText("저장");
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
        addTab("메모", jpMemoTabBorder);
    }

    public void setMemoWindow(MemoWindow mw){ memoWindow = mw; }

    public void addMemo(String contents, String date){
        MemoLabel label = new MemoLabel(contents, date);
        label.setPreferredSize(new Dimension(100,25));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        label.setBackground(Color.YELLOW);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    System.out.println(jlList.indexOf(label));
//                    System.out.println(label.getDate());
                    memoWindow.jtaMemoWin.setText(label.getContents());
                    memoWindow.btnDelete.setEnabled(true);
                    memoWindow.setIndex(jlList.indexOf(label));
                    memoWindow.btnSave.setText("수정");
                    memoWindow.setDate(label.getDate());
                    memoWindow.setVisible(true);
//                    jlList.indexOf(0);
                }
            }
        });
//            label.setBorder(BorderFactory.createEtchedBorder());  // 테두리 설정
        jlList.add(label);
        showMemo();
    }
    public void changeMemo(Integer idx, String contents, String date){
        jlList.get(idx).setText(contents);
//        showMemo();
    }
    public void deleteMemo(Integer idx){
        jpMemoTab.remove(jlList.get(idx));
        jpMemoTab.validate();
        jpMemoTab.repaint();
        int idx2 = idx;
        jlList.remove(idx2);
//        showMemo();
    }
    public void showMemo(){
        for(int i = 0; i<jlList.size(); i++){
            jpMemoTab.add(jlList.get(i), gbc);
        }
//        System.out.println(jlList.size());
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
