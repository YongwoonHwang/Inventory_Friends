import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class MemoWindow extends JFrame{
    JPanel jpMemoWinDate, jpMemoWin, jpMemoWinBtn;
    JLabel jlDate;
    JButton btnSave, btnCancle, btnDelete;
    JTextArea jtaMemoWin;
    JScrollPane jspMemoWin;
    static MemoTab memoTab;
    static String seleteDate;


    public MemoWindow(){
        setSize(300, 400);

        jpMemoWin = new JPanel(new BorderLayout());
        jpMemoWinBtn = new JPanel();
        jpMemoWinDate = new JPanel();
        jtaMemoWin = new JTextArea();
        jspMemoWin = new JScrollPane(jtaMemoWin);
        jlDate = new JLabel();
        jpMemoWinBtn.setLayout(new BoxLayout(jpMemoWinBtn, BoxLayout.LINE_AXIS));
        jpMemoWinDate.setBackground(Color.WHITE);
        jpMemoWinBtn.setBackground(Color.WHITE);

        jspMemoWin.setBorder(BorderFactory.createEmptyBorder());
        jtaMemoWin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnDelete = new JButton("삭제");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(LocalDate.now());
            }
        });
        btnSave = new JButton("저장");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMemo(memoTab, jlDate.getText()+" || "+jtaMemoWin.getText());
                setVisible(false);
                dispose();
//                System.out.println(memoCont);
            }
        });
        btnCancle = new JButton("취소");
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        jpMemoWinDate.add(jlDate);
        jpMemoWinBtn.add(btnDelete);
        jpMemoWinBtn.add(Box.createHorizontalGlue());
        jpMemoWinBtn.add(btnSave);
        jpMemoWinBtn.add(btnCancle);
        jpMemoWin.add(jpMemoWinDate, BorderLayout.NORTH);
        jpMemoWin.add(jspMemoWin, BorderLayout.CENTER);
        jpMemoWin.add(jpMemoWinBtn, BorderLayout.SOUTH);

        add(jpMemoWin);
        setResizable(false);
        setLocationRelativeTo(null);            //창을 화면 중앙으로
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    public static void saveMemo(MemoTab mt, String memoCont){
        mt.addMemo(memoCont);
        mt.revalidate();
        mt.repaint();
//        System.out.println("Saved");
//        System.out.println(memoCont);
    }
    public static void setMemoTab(MemoTab mt){
        memoTab = mt;
    }
    public void setDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        seleteDate = date.format(formatter);
        jlDate.setText(seleteDate);
    }
    public void setDate(Object date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        seleteDate = df.format(date);
        jlDate.setText(seleteDate);
    }
    public static void main(String[] args) {
        MemoWindow f =new MemoWindow();
        f.setDate(LocalDate.now());
        f.setVisible(true);
    }
}
