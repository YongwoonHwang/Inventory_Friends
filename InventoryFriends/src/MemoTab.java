import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemoTab extends JTabbedPane{
    JPanel jpMemoTab, jpMemoTabBorder, jpMemoTabBtn;
    JScrollPane jspMemoTab;
    JButton btnAdd;
    GridBagConstraints gbc;
    static GroupLayout.ParallelGroup hGroup;
    static GroupLayout.SequentialGroup vGroup;
    static MemoWindow memoWindow;
    static ArrayList<JLabel> jlList = new ArrayList<>();
    static ArrayList<JPanel> jpList = new ArrayList<>();
    static String path = "Memo.txt";
    String endMemo = ">->end<-<";
    public MemoTab(){
        jpMemoTabBorder = new JPanel(new BorderLayout());
        jpMemoTab = new JPanel();
        jpMemoTabBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jspMemoTab = new JScrollPane(jpMemoTab);
        jspMemoTab.setBorder(BorderFactory.createEmptyBorder());
        btnAdd = new JButton("등록");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoWindow.jtaMemoWin.setText("");
                memoWindow.setDate(LocalDate.now());
                memoWindow.btnDelete.setEnabled(false);
                memoWindow.btnSave.setText("저장");
                memoWindow.setIndex(null);
                memoWindow.setVisible(true);
            }
        });
        jpMemoTabBtn.add(btnAdd);
        jpMemoTabBorder.add(jspMemoTab, BorderLayout.CENTER);
        jpMemoTabBorder.add(jpMemoTabBtn, BorderLayout.SOUTH);
        GroupLayout gLayout = new GroupLayout(jpMemoTab);
        jpMemoTab.setLayout(gLayout);
        hGroup = gLayout.createParallelGroup();
        gLayout.setHorizontalGroup(hGroup);
        vGroup = gLayout.createSequentialGroup();
        gLayout.setVerticalGroup(vGroup);

        addTab("메모", jpMemoTabBorder);
        loadMemo();
    }

    public void setMemoWindow(MemoWindow mw){ memoWindow = mw; }
    public void loadMemo(){
        File file = new File(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line, date, dummy;
            while (true){
                dummy = "";
                date = br.readLine();
                if (date == null){
                    break;
                }
                while(!(line = br.readLine()).equals(endMemo)){
                    dummy += line;
                    dummy += "\r\n";
                }
                dummy = dummy.substring(0, dummy.length()-1);
                addMemo(dummy, date);
            }

        } catch (FileNotFoundException e){
            File file1 = new File(path);

            try {
                if (file1.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addMemo(String contents, String date){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        MemoLabel label = new MemoLabel(contents, date);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
//                    System.out.println(jlList.indexOf(label));
                    if(label.date != null){
                        memoWindow.jtaMemoWin.setText(label.getContents());
                        memoWindow.btnDelete.setEnabled(true);
                        memoWindow.setIndex(jlList.indexOf(label));
                        memoWindow.btnSave.setText("수정");
                        memoWindow.setDate(label.getDate());
                        memoWindow.setVisible(true);
                    }
                }
            }
        });
        panel.add(label,BorderLayout.CENTER);
        hGroup.addComponent(panel);
        vGroup.addComponent(panel, GroupLayout.PREFERRED_SIZE,
                GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
//        label.setBorder(BorderFactory.createEtchedBorder());  // 테두리 설정
        jlList.add(label);
        jpList.add(panel);

        jspMemoTab.validate();
        jspMemoTab.repaint();
    }
    public void changeMemo(Integer idx, String contents, String date){
        jlList.get(idx).setText(contents);
    }
    public void deleteMemo(Integer idx){
        jpMemoTab.remove(jpList.get(idx));
        int idx2 = idx;
        jlList.remove(idx2);
        jpList.remove(idx2);
        jspMemoTab.validate();
        jspMemoTab.repaint();
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
