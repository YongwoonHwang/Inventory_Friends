import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {

    private JTextField inputSpace;
    //계산식의 숫자를 담을 변수 num
    private String num = "";
    //방금 누른 버튼을 기억하는 변수 prev_operation
    private String prev_operation = "";
    //계산 기능을 구현하기 위해 ArrayList에 숫자와 연산 기호를 하나씩 구분해 담음
    private ArrayList<String> equation = new ArrayList<String>();

    public Calculator() {
        //계산기의 화면과 버튼을 붙임 - 기본 레이아웃 사용
        setLayout(null);

        //빈공간의 JTextField 생성
        inputSpace = new JTextField();
        //편집가능여부 : 불가능 (버튼만 사용)
        inputSpace.setEditable(false);
        //배경색 설정
        inputSpace.setBackground(Color.WHITE);
        //정렬위치 설정
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        //글씨체 설정
        inputSpace.setFont(new Font("Arial", Font.BOLD, 50));
        //위치와 크기(x:8,y:10의 위치에 270x70의 크기)
        inputSpace.setBounds(8, 10, 270, 70);


        //버튼을 만들 패널
        JPanel buttonPanel = new JPanel();
        //레이아웃 지정 - 격자형태로 배치해주는 GridLayout사용
        //GridLayout(4, 4, 10, 10) -> 가로 칸수, 세로 칸수, 좌우 간격, 상하 간격
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        //위치와 크기 설정
        buttonPanel.setBounds(8, 90, 270, 235);


        //계산기 버튼의 글자를 차례대로 배열에 저장
        String button_names[] = { "C", "÷", "×", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
        //버튼들의 배열
        JButton buttons[] = new JButton[button_names.length];

        //배열을 이용하여 버튼 생성
        for (int i = 0; i < button_names.length; i++) {
            buttons[i] = new JButton(button_names[i]);
            //글씨체
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            //버튼의 색 지정
            if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
            else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14)) buttons[i].setBackground(Color.BLACK);
            else buttons[i].setBackground(Color.GRAY);
            //글자색 지정
            buttons[i].setForeground(Color.WHITE);
            //테두리 없앱
            buttons[i].setBorderPainted(false);
            //밑에서 만든 액션리스너를 버튼에 추가
            buttons[i].addActionListener(new PadActionListener());
            //버튼들을 버튼패널에 추가
            buttonPanel.add(buttons[i]);
        }

        add(inputSpace);
        //버튼 패널 추가
        add(buttonPanel);

        //창의 제목, 사이즈, 보이기 여부 등을 지정해줌
        setTitle("계산기");
        setVisible(true);
        setSize(300,370);
        //화면의 가운데에 띄움
        setLocationRelativeTo(null);
        //사이즈조절 불가능
        setResizable(false);
        //창을 닫을 때 실행 중인 프로그램도 같이 종료되도록 함
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //만들어놓은 버튼에 액션 리스너 기능 추가
    //액션리스너를 상속시켜주고 actionPerformed(ActionEvent e)메소드로 이벤트 처리
    class PadActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            //어떤 버튼이 눌렸는지를 알아냄
            String operation = e.getActionCommand();

            //C가 눌렸을 경우 위의 계산식 내용을 지워줌
            if (operation.equals("C")) {
                inputSpace.setText("");
                //=이 눌렸을 경우 위에 입력된 식을 계산, 계산값이 나오도록 함
            } else if (operation.equals("=")) {
                //밑의 메소드들을 이용하여 계산, 계산식 화면에 값을 띄워줌
                String result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText("" + result);
                num = "";

                //지금 누른 버튼이 연산자일 때의 조건
            } else if (operation.equals("+") || operation.equals("-") || operation.equals("×") || operation.equals("÷")) {
                //첫 값을 음수로 입력할 수 있음
                if (inputSpace.getText().equals("") && operation.equals("-")) {
                    inputSpace.setText(inputSpace.getText() + e.getActionCommand());

                    //이전에 누른 버튼이 연산자가 아니고 && 위의 계산식이 비버있지 않을 때의 조건문
                } else if (!inputSpace.getText().equals("") && !prev_operation.equals("+") && !prev_operation.equals("-") && !prev_operation.equals("×") && !prev_operation.equals("÷")) {
                    inputSpace.setText(inputSpace.getText() + e.getActionCommand());
                }
            }
            //나버지 버튼은 눌렀을 때 계산식에 추가됨
            else {
                inputSpace.setText(inputSpace.getText() + e.getActionCommand());
            }
            //마지막으로 누른 버튼을 기억
            prev_operation = e.getActionCommand();
        }
    }


    private void fullTextParsing(String inputText) {
        equation.clear();

        //계산식의 글자를 하나하나 거쳐감
        for (int i = 0; i < inputText.length(); i++) {
            char ch = inputText.charAt(i);

            //연산기호가 나오면 ArrayList에 추가되고 초기화
            if (ch == '-' || ch == '+' || ch == '×' || ch == '÷') {
                //연산기호를 만났다 : 앞은 숫자라는 것을 의미
                //숫자를 ArrayList에 추가
                equation.add(num);
                //num 초기화
                num = "";
                //연산기호를 ArrayList에 추가
                equation.add(ch + "");
            } else {
                //나머지는 그냥 숫자 처리
                num = num + ch;
            }
        }
        //반복문 끝나고 남아있는 숫자값 추가
        equation.add(num);
        //연산자가 있을 때 num을 ArrayList에 추가하는데, 처음에 -가 있으면 ""가 추가되어 에러가 발생함
        //즉 ""을 제거
        equation.remove("");
    }

    //계산 기능
    public double calculate(String inputText) {
        fullTextParsing(inputText);

        //위의 메소드를 실행하면 ArrayList에 숫자와 연산 기호가 담김
        double prev = 0;
        double current = 0;
        //연산 기호에 대한 처리를 위한 변수
        String mode = "";

        //연산자 우선순위 적용
        for (int i = 0; i < equation.size(); i++) {
            String s = equation.get(i);

            //연산자가 있을 때마다 mode의 값을 변경
            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";
            } else if (s.equals("×")) {
                mode = "mul";
            } else if (s.equals("÷")) {
                mode = "div";
            } else {
                //전에 있던 연산자가 곱셈 혹은 나눗셈이고 현재 인덱스의 값이 숫자일 때 연산 진행
                if ((mode.equals("mul") || mode.equals("div")) && !s.equals("+") && !s.equals("-") && !s.equals("×") && !s.equals("÷")) {
                    Double one = Double.parseDouble(equation.get(i - 2));
                    Double two = Double.parseDouble(equation.get(i));
                    Double result = 0.0;

                    //mode에 따라서 계산
                    if (mode.equals("mul")) {
                        result = one * two;
                    } else if (mode.equals("div")) {
                        result = one / two;
                    }
                    //result값을 ArrayList에 추가
                    equation.add(i + 1, Double.toString(result));

                    for (int j = 0; j < 3; j++) {
                        equation.remove(i - 2);
                    }

                    //예를 들어 3 + 5 x 6에서  3 + 30이 되었으므로 인덱스를 2만큼 되돌아감
                    i -= 2;	// 결과값이 생긴 인덱스로 이동
                }
            }
        }	// 곱셈 나눗셈을 먼저 계산한다

        //+일경우 add, -일경우 sub
        for (String s : equation) {
            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";

                //곱셈, 나눗셈 연산 삭제됨
            }  else {
                //숫자일 경우 문자열을 Double로 형변환
                current = Double.parseDouble(s);

                //mode값에 따라 처리, prev는 계속 계산값이 갱신됨
                if (mode.equals("add")) {
                    prev += current;
                } else if (mode.equals("sub")) {
                    prev -= current;
                } else {
                    prev = current;
                }
            }
            //소수점 여섯번 째 자리에서 반올림 -> 화면 표시 제한이 있기때문
            prev = Math.round(prev * 100000) / 100000.0;
        }
        //값 반환
        return prev;
    }

    public static void main(String[] args) {
        new Calculator();

    }

}

// 출처 : https://code-review.tistory.com/84