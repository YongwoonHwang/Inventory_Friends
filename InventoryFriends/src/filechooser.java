import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class filechooser {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        //window.setVisible(true);


        JFileChooser fileChooser = new JFileChooser();

        //파일오픈 다이얼로그 를 띄움
        int result = fileChooser.showOpenDialog(window);

        if (result == JFileChooser.APPROVE_OPTION) {
            //선택한 파일의 경로 반환
            File selectedFile = fileChooser.getSelectedFile();

            //경로 출력
            System.out.println(selectedFile);
        }

    }

}