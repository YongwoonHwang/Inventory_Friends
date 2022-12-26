import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Test extends JFrame{

    private JButton jta = new JButton();

    private JSplitPane jsp = new JSplitPane();
    private BorderLayout bl = new BorderLayout();
    JLabel lblId = new JLabel("아이디:");
    JLabel lblPass = new JLabel("패스워드:");
    JLabel lblName = new JLabel("성 명:");
    JTextField txtId = new JTextField(10);
    JTextField txtPass = new JTextField(10);
    JTextField txtName = new JTextField(10);
    JButton btnInsert = new JButton("가입");

    public Test(String title) {

        super(title);

        this.init();
        this.start();

        this.setSize(400,200);
        //JFrame이 보여질 위치를 Dimension클래스를 이용하여 스크린사이즈를
        //계산한 후 2로 나누어 중앙에 보여지도록 setLocation메소드를 이용하여
        //적용
        Dimension screen =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frm = this.getSize();
        //x좌표값을 계산하는 코드로 전체 스크린 width를 2로 나누고 JFrame의
        //너비를 2로 나누어 뺀 값을 적용함
        int xpos = (int)(screen.getWidth() / 2 - frm.getWidth() / 2);
        int ypos = (int)(screen.getHeight() / 2 - frm.getHeight() / 2);

        this.setLocation(xpos, ypos);
        //전체 프레임의 크기를 조절할 수 없도록 처리
        this.setResizable(false);
        this.setVisible(true);

    }

    private void init(){
        Container con = this.getContentPane();
        con.setLayout(bl);
        JPanel p = new JPanel(new GridLayout(3, 2));
        p.add(lblId);
        p.add(txtId);
        p.add(lblPass);
        p.add(txtPass);
        p.add(lblName);
        p.add(txtName);
        //패널에 setBorder메소드를 이용하여 선을 그려주고 있는 것을 볼 수 있다.
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("로그인"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
        //JSplitPane의 왼쪽 부분에 로그인 패널을 붙인다.
        jsp.setLeftComponent(p); //컴포넌트 왼쪽에 로그인패널 추가하기
        jsp.setRightComponent(jta); //컴포넌트 오른쪽에 텍스트 에어리어 삽입

        con.add("Center",jsp);
        con.add(btnInsert,BorderLayout.SOUTH);
    }

    private void start(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new Test("Text");
    }
}