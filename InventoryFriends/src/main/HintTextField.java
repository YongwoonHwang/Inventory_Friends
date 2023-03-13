package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class HintTextField extends JTextField {

    Font gainFont = new Font("맑은 고딕", Font.PLAIN, 12);
    Font lostFont = new Font("맑은 고딕", Font.ITALIC, 12);
    String hintText;

    public HintTextField(final String hint) {
        hintText = hint;
        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    setText("");
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });
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