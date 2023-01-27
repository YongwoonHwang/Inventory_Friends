import java.io.*;

public class Test3
{
    public static void main(String[] args)
    {
        String path = "test.txt";
        File file = new File(path);
        String dummy = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            //1. 삭제하고자 하는 position 이전까지는 이동하며 dummy에 저장
            String line;
            for(int i=0; i<2; i++) {
                line = br.readLine(); //읽으며 이동
                dummy += (line + "\r\n" );
            }

            //2. 삭제하고자 하는 데이터는 건너뛰기
            for(int i=0; i<2; i++) {
                br.readLine(); //읽으며 이동
            }

            //3. 삭제하고자 하는 position 이후부터 dummy에 저장
            while((line = br.readLine())!=null) {
                dummy += (line + "\r\n" );
            }
            //4. FileWriter를 이용해서 덮어쓰기
            FileWriter fw = new FileWriter(path);
            fw.write(dummy);
            //bw.close();
            fw.close();
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}