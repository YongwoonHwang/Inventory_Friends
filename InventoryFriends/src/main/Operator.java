package main;
public class Operator {

    LoginFrame lf = null;
    Database db = null;
    public Operator(){

        this.lf = new LoginFrame(this);
        this.db = new Database();
        lf.loginFrame = lf;

    }

    public static void main(String[] args) {
        new Operator();

    }
}
