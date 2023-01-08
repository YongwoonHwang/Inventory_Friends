package LoginUi;
import java.sql.*;
public class Database {
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:mysql://localhost/solodb?useSSL=false&serverTimezone=Asia/Seoul";


    String user = "root";
    String passwd = "db비밀번호";

    Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement();
            System.out.println("MySQL 서버 연동 성공");
        } catch (Exception e) {
            System.out.println("MySQL 서버 연동 실패 > " + e.toString());
        }
    }
    boolean logincheck(String _i,String _p) {
        boolean flag = false;

        String id = _i;
        String pw = _p;

        try {
            String checkingStr = "SELECT CONVERT(AES_DECRYPT(UNHEX(Pw),SHA2(\"abcabc\",256)) USING UTF8) DECPASS FROM userlogin WHERE UserID = '" + id + "'";
            System.out.println(checkingStr);
            ResultSet result = stmt.executeQuery(checkingStr);


            int count = 0;
            while(result.next()) {
//                System.out.println(result.getString("DECPASS"));
                if(pw.equals(result.getString("DECPASS"))) {

                    flag = true;
                    System.out.println("로그인 성공");
                }
                else {
                    flag = false;
                    System.out.println("로그인 실패");
                }
                count++;
            }
        } catch (Exception e) {
            flag =false;
            System.out.println("로그인 실패 > " + e.toString());
        }
        return flag;
    }
}
