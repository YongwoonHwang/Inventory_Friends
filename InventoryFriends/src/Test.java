import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test {

    // 프레임 객체 설정
    Frame frame = new Frame("DB에서 데이터 불러오기");

    // 버튼, 리스트 컨트롤 선언
    JList hymnList = new JList();
    JButton loadBtn = new JButton("로드");

    public void createFrame()
    {
        // 레이아웃 매니저를 사용하지 않기
        frame.setLayout(null);

        // 프레임 크기 지정
        frame.setSize(340, 400);

        // 종료 버튼에 동작을 할당한다.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        // 컴포넌트 크기, 위치 설정
        loadBtn.setBounds(20,30,300,40);
//        Font font2 = new Font("SansSerif", Font.BOLD, 14);   // 탭 타이틀 폰트
//        loadBtn.setFont(font2);
        hymnList.setBounds(20,80,300,300);

        // 버튼 이벤트 세팅
        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                hymnList.removeAll(); // 리스트 내용을 전부 제거한다.

                try{
                    Connection con = null;

                    Class.forName("com.mysql.cj.jdbc.Driver");

                    con = DriverManager.getConnection("jdbc:mysql://inventoryfriends.cxtfsxnxj3jt.ap-northeast-1.rds.amazonaws.com:3306/","admin","admin1470");

                    // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                    //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.
                    java.sql.Statement st = null;
                    ResultSet result = null;
                    st = con.createStatement();
                    st.execute("use example"); // 사용할 DB를 선택한다.
                    // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.
                    String sql = "INSERT INTO test (Email, Name) VALUES (1235544, \"권순용\")";
                    st.executeUpdate(sql);

//                    DefaultListModel listModel = new DefaultListModel();
//                    // 결과를 하나씩 출력한다.
//                    while (result.next()){
//                        System.out.println(result.getString(1).getClass().getName());
//                        String str = result.getString(1);
//                        listModel.addElement(str);
//                        hymnList.setModel(listModel); // 리스트에 데이터를 추가한다.
//                    }
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
                } catch (SQLException sqle) {
                    System.out.println("DB 접속실패 : " + sqle.toString());
                }
            }
        });

        //프레임에 컴포넌트 추가
        frame.add(loadBtn);
        frame.add(hymnList);

        //프레임 보이기
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //프레임 열기
        Test frm = new Test();
        frm.createFrame();
    }
}