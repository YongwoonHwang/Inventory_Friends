package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextFieldForPassword extends JPasswordField{
    Font gainFont = new Font("맑은 고딕", Font.PLAIN, 12);
    Font lostFont = new Font("맑은 고딕", Font.ITALIC, 12);
    String hintText;
    String pw;

    public HintTextFieldForPassword(final String hint) {
        hintText = hint;
        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (chkpw().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                } else {
                    setText(chkpw());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (chkpw().equals(hint)|| chkpw().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(chkpw());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
    }

    public String chkpw(){
        //tf_pw 필드에서 패스워드를 얻어옴, char[] 배열에 저장
        pw = "";
        char[] secret_pw = getPassword();

        //secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
        for(char cha : secret_pw){
            Character.toString(cha);       //cha 에 저장된 값 string으로 변환
            //pw 에 저장하기, pw 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
            pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
        }
        return pw;
    }

    public String getHint(){
        return hintText;
    }
    public void reset(){
        setText(hintText);
        setFont(lostFont);
        setForeground(Color.GRAY);
    }

    public void forcedGainFocus(){
        setFont(gainFont);
        setForeground(Color.BLACK);
    }

    public boolean getLostFocus(){
        if(getForeground() == Color.BLACK){
            return false;
        }else{
            return true;
        }
    }
}
