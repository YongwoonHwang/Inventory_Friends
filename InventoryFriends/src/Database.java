import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    BCryptPasswordEncoder passwordEncoder;
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com/ifdb?characterEncoding=utf8&useUnicode=true&mysqlEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Seoul";
    String user = "admin";
    String passwd = "admin1470!";
    String useridx;


    Database() {
        passwordEncoder = new BCryptPasswordEncoder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);
            stmt = con.createStatement();
            System.out.println("MySQL 서버 연동 성공");
        } catch (Exception e) {
            System.out.println("MySQL 서버 연동 실패 > " + e.toString());
        }
    }
    String getUseridx(String userid){
        try {
            String checkingStr = "SELECT user_idx FROM user WHERE userID = '" + userid + "'";
            ResultSet result = stmt.executeQuery(checkingStr);

            while(result.next()) {
                useridx = result.getString("user_idx");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return useridx;
    }
    boolean logincheck(String _i,String _p) {
        boolean flag = false;

        String id = _i;
        String pw = _p;

        try {
            String checkingStr = "SELECT password FROM user WHERE userID = '" + id + "'";
            System.out.println(checkingStr);
            ResultSet result = stmt.executeQuery(checkingStr);


            int count = 0;
            while(result.next()) {

                if(passwordEncoder.matches(_p,result.getString("password"))) {

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

        }
        return flag;
    }
}
