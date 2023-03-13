package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MemoWindow extends JFrame{
    JPanel jpMemoWinDate, jpMemoWin, jpMemoWinBtn;
    static JLabel jlDate;
    JButton btnSave, btnCancle, btnDelete;
    ImageIcon imgDelete1, imgDelete2, imgCancel1, imgCancel2, imgSave1, imgSave2;
    JTextArea jtaMemoWin;
    JScrollPane jspMemoWin;
    static Integer selectIndex;
    static MemoTab memoTab;
    static String seleteDate;
    static String path = "Memo.txt";
    String endMemo = ">->end<-<";


    public MemoWindow(){
        setSize(300, 400);

        jpMemoWin = new JPanel(new BorderLayout());
        jpMemoWinBtn = new JPanel();
        jpMemoWinDate = new JPanel();
        jtaMemoWin = new JTextArea();
        jspMemoWin = new JScrollPane(jtaMemoWin);
        jtaMemoWin.setLineWrap(true);           //자동 줄바꿈
        jlDate = new JLabel();
        jpMemoWinBtn.setLayout(new BoxLayout(jpMemoWinBtn, BoxLayout.LINE_AXIS));
        jpMemoWinDate.setBackground(Color.WHITE);
        jpMemoWinBtn.setBackground(Color.WHITE);

        jspMemoWin.setBorder(BorderFactory.createEmptyBorder());
        jtaMemoWin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        imgDelete1 = new ImageIcon("./img/img_Del1.jpg");
        imgDelete2 = new ImageIcon("./img/img_Del2.jpg");
        imgCancel1 = new ImageIcon("./img/img_Cancel1.jpg");
        imgCancel2 = new ImageIcon("./img/img_Cancel2.jpg");
        imgSave1 = new ImageIcon("./img/img_Save1.jpg");
        imgSave2 = new ImageIcon("./img/img_Save2.jpg");

        btnDelete = new JButton(imgDelete1);
        btnDelete.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 0));
        btnDelete.setRolloverIcon(imgDelete2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnDelete.setBorderPainted(false); // 버튼 테두리 제거
        btnDelete.setFocusPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setPreferredSize(new Dimension(48, 26));
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectIndex != null){
                    int input = JOptionPane.showConfirmDialog(null, "이 메모를 삭제하시겠습니까?",
                            "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if(input == JOptionPane.OK_OPTION){
                        deleteMemo(memoTab);
                        setVisible(false);
                        selectIndex = null;
                    }
                }
            }
        });
        btnSave = new JButton(imgSave1);
        btnSave.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 2));
        btnSave.setRolloverIcon(imgSave2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSave.setBorderPainted(false); // 버튼 테두리 제거
        btnSave.setFocusPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setPreferredSize(new Dimension(48, 26));
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectIndex != null){
                    changeMemo(memoTab, jtaMemoWin.getText(), jlDate.getText());
                    setVisible(false);
                    dispose();
                } else {
                    saveMemo(memoTab, jtaMemoWin.getText(), jlDate.getText());
                    setVisible(false);
                    dispose();
                }
                selectIndex = null;
//                System.out.println(memoCont);
            }
        });
        btnCancle = new JButton(imgCancel1);
        btnCancle.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 2));
        btnCancle.setRolloverIcon(imgCancel2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnCancle.setBorderPainted(false); // 버튼 테두리 제거
        btnCancle.setFocusPainted(false);
        btnCancle.setContentAreaFilled(false);
        btnCancle.setPreferredSize(new Dimension(48, 26));
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

    public void saveMemo(MemoTab mt, String memoCont, String date){
        mt.addMemo(memoCont, date);
        mt.revalidate();
        mt.repaint();

        try (FileWriter fw = new FileWriter(path, true)){
            fw.write( date + "\r\n" + memoCont + "\r\n" + endMemo +"\r\n");
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void changeMemo(MemoTab mt, String memoCont, String date){
        mt.changeMemo(selectIndex, memoCont, date);
        mt.revalidate();
        mt.repaint();

        File file = new File(path);
        String dummy = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            //1. 삭제하고자 하는 position 이전까지는 이동하며 dummy에 저장
            String line;
            for(int i=0; i<selectIndex; i++) {
                while(!(line = br.readLine()).equals(endMemo)){
                    dummy += (line + "\r\n");
                }
                dummy += (endMemo + "\r\n");
            }

            //2. 삭제하고자 하는 데이터는 건너뛰기
            line = br.readLine();
//            System.out.println(line);
            dummy += (line + "\r\n" );
            dummy += (memoCont + "\r\n" + endMemo + "\r\n");
            while(!(line = br.readLine()).equals(endMemo)){
                continue;
            }
            //3. 삭제하고자 하는 position 이후부터 dummy에 저장
            while((line = br.readLine())!=null) {
                dummy += (line + "\r\n" );
            }
            //4. FileWriter를 이용해서 덮어쓰기
            FileWriter fw = new FileWriter(path);
            fw.write(dummy);
            fw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteMemo(MemoTab mt){
        mt.deleteMemo(selectIndex);

        File file = new File(path);
        String dummy = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            //1. 삭제하고자 하는 position 이전까지는 이동하며 dummy에 저장
            String line;
            for(int i=0; i<selectIndex; i++) {
                while(!(line = br.readLine()).equals(endMemo)){
                    dummy += (line + "\r\n");
                }
                dummy += (endMemo + "\r\n");
            }

            //2. 삭제하고자 하는 데이터는 건너뛰기
            while(!(line = br.readLine()).equals(endMemo)){
                continue;
            }
            //3. 삭제하고자 하는 position 이후부터 dummy에 저장
            while((line = br.readLine())!=null) {
                dummy += (line + "\r\n" );
            }
            //4. FileWriter를 이용해서 덮어쓰기
            FileWriter fw = new FileWriter(path);
            fw.write(dummy);
            fw.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setMemoTab(MemoTab mt){
        memoTab = mt;
    }
    public void setIndex(int idx){
        selectIndex = idx;
    }
    public void setIndex(Integer idx){
        selectIndex = idx;
    }
    public void setDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        seleteDate = date.format(formatter);
        jlDate.setText(seleteDate);
//        jlDate.repaint();
    }
    public void setDate(String date){
        jlDate.setText(date);
//        jlDate.repaint();
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
