package LoginUi;


public class Operator {
    Database db = null;
    LoginUi.LoginFrame lf = null;

    public static void main(String[] args) {
        Operator opt = new Operator();
        opt.db = new Database();
        opt.lf = new LoginFrame(opt);
    }
}
