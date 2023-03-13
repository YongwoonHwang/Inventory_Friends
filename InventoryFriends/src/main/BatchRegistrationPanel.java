package main;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

public class BatchRegistrationPanel extends JPanel {
    JPanel jpCenter;
    JButton btnSubmit1, btnSubmit2;
    JLabel jlBatchReg, jlExplain, jlLink;
    JTextField jtfCSVpath;
    ImageIcon imgAdd1, imgAdd2, imgFile1, imgFile2;
    JFileChooser imgfilechooser;
    JComboBox jcbCategory, jcbCategory2, jcbCategory3;
    DefaultTableModel modelItemList;
    String dbName = "ifdb";
    String dbTableName;
    String dbUserIdx;
    ArrayList<String> categoryList;

    public BatchRegistrationPanel(String userid) {
        dbTableName = userid+"_ItemList";
        setLayout(new BorderLayout());
        jpCenter = new JPanel();

        imgAdd1 = new ImageIcon("./img/img_Add1.jpg");
        imgAdd2 = new ImageIcon("./img/img_Add2.jpg");
        imgFile1 = new ImageIcon("./img/img_File1.jpg");
        imgFile2 = new ImageIcon("./img/img_File2.jpg");

        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
        FlowLayout fr = new FlowLayout(FlowLayout.RIGHT);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        jpCenter.setLayout(gb);

        imgfilechooser = new JFileChooser();
//        jlfilechooser = new JLabel();
        imgfilechooser.setAcceptAllFileFilterUsed(false);
        imgfilechooser.setFileFilter(new FileNameExtensionFilter("Excel File(*.xls;*.xlsx;*.csv)", "xls", "xlsx", "csv"));
        imgfilechooser.setMultiSelectionEnabled(false);

        JPanel panel1 = new JPanel(fl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(60, 40, 0, 0);
        jlBatchReg = new JLabel("일괄 등록 : ");
        panel1.add(jlBatchReg);
        jtfCSVpath = new JTextField();
        jtfCSVpath.setEditable(false);
        jtfCSVpath.setColumns(25);
        panel1.add(jtfCSVpath);
        btnSubmit1 = new JButton(imgFile1);
        btnSubmit1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit1.setRolloverIcon(imgFile2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit1.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit1.setFocusPainted(false);
        btnSubmit1.setContentAreaFilled(false);
        btnSubmit1.setPreferredSize(new Dimension(70, 24));
        btnSubmit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //파일오픈 다이얼로그 를 띄움
                int result = imgfilechooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    //선택한 파일의 경로 반환
                    File selectedFile = imgfilechooser.getSelectedFile();

                    //경로 출력
                    jtfCSVpath.setText(selectedFile.getPath());
                } else{
                    jtfCSVpath.setText("");
                }
            }
        });
        panel1.add(btnSubmit1);
        jpCenter.add(panel1, gbc);

        JPanel panel2 = new JPanel(fl);
        gbc.gridy = 1;
        gbc.insets = new Insets(30, 20, 40, 0);
        jlExplain = new JLabel("* excel 혹은 csv 파일만 지원합니다. 파일 업로드 전에 지원 양식을 다시 한 번 확인해주시기 바랍니다.");
        panel2.add(jlExplain);
        jlLink = new JLabel("자세히 보기");
        panel2.add(jlLink);
        jpCenter.add(panel2, gbc);

        JPanel panel3 = new JPanel(fr);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 30, 20);
        btnSubmit2 = new JButton(imgAdd1);
        btnSubmit2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSubmit2.setRolloverIcon(imgAdd2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSubmit2.setBorderPainted(false); // 버튼 테두리 제거
        btnSubmit2.setFocusPainted(false);
        btnSubmit2.setContentAreaFilled(false);
        btnSubmit2.setPreferredSize(new Dimension(48, 24));
        btnSubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBatchRegistration(jtfCSVpath.getText());
                jtfCSVpath.setText("");
            }
        });
        panel3.add(btnSubmit2);
        jpCenter.add(panel3, gbc);
        JScrollPane scrollPane = new JScrollPane(jpCenter);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

    }

    public void doBatchRegistration(String path){
        Iterator<Row> rowIterator;
        ArrayList<Object> rowData = new ArrayList<>();
        try {
            String ext = path.substring(path.lastIndexOf(".") + 1);
            if(ext.equals("csv")){
                List<List<String>> list = readCSVFile(path);

                for(int i=1; i < list.size(); i++){
                    for(int j=0; j < list.get(i).size(); j++){
                        rowData.add(list.get(i).get(j));
                    }
                    insertDB(rowData);
                    rowData.clear();
                }
            }else {
                FileInputStream file = new FileInputStream(path);
                IOUtils.setByteArrayMaxOverride(Integer.MAX_VALUE);

                if(ext.equals("xlsx")){
                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    rowIterator = sheet.iterator();
                } else {

                    HSSFWorkbook workbook = new HSSFWorkbook(file);
                    HSSFSheet sheet = workbook.getSheetAt(0);
                    rowIterator = sheet.iterator();
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                if(DateUtil.isCellDateFormatted(cell)) {
                                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                                    rowData.add(dateFormatter.format(cell.getDateCellValue()));
                                } else
                                    rowData.add(cell.getNumericCellValue());
                                break;
                            case STRING:
                                rowData.add(cell.getStringCellValue());
                                break;
                            case BLANK:
                                rowData.add("");
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + cell.getCellType());
                        }
                    }
                    insertDB(rowData);
                    rowData.clear();
                }
                file.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void insertDB(ArrayList arrayList){

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String sql = "INSERT INTO " + dbTableName + " (user_id, CATEGORY, CODE, ProductName, QUANTITY, MARKET, PRODUCTLOCATION, STOCKINGDATE, EDA, IMAGE) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String sql2 = "SELECT * FROM " + dbTableName + " ORDER BY id DESC LIMIT 1";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
            //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

            pstmt = con.prepareStatement(sql);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

            pstmt.setString(1, dbUserIdx);
            pstmt.setString(2, arrayList.get(0).toString());
            pstmt.setString(3, arrayList.get(1).toString());
            pstmt.setString(4, arrayList.get(2).toString());
            pstmt.setInt(5, (int) Double.parseDouble(arrayList.get(3).toString()));
            pstmt.setString(6, arrayList.get(4).toString());
            pstmt.setString(7, arrayList.get(5).toString());
            pstmt.setString(8, arrayList.get(6).toString());
            pstmt.setString(9, arrayList.get(7).toString());
            pstmt.setString(10, arrayList.get(8).toString());

            int cnt = pstmt.executeUpdate();
            System.out.println("SUCCESS");

            if (cnt == 1){
                boolean chk = false;
                for (int i = 0; i < categoryList.size();i++){
                    if(categoryList.get(i).equals(arrayList.get(0).toString()))
                        chk = true;
                }
                if(categoryList.size() == 0) {
                    jcbCategory.addItem("");
                    jcbCategory.addItem(arrayList.get(0).toString());
                    jcbCategory2.addItem("");
                    jcbCategory2.addItem(arrayList.get(0).toString());
                    jcbCategory3.addItem("");
                    jcbCategory3.addItem(arrayList.get(0).toString());
                    categoryList.add("");
                    categoryList.add(arrayList.get(0).toString());
                }
                else if(!chk){
                    jcbCategory.addItem(arrayList.get(0).toString());
                    jcbCategory2.addItem(arrayList.get(0).toString());
                    jcbCategory3.addItem(arrayList.get(0).toString());
                    categoryList.add(arrayList.get(0).toString());
                }
                try{
                    pstmt = con.prepareStatement(sql2);
                    pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
                    result = pstmt.executeQuery(); //리턴 받아와서 데이터를 사용할 객체 생성
                    while (result.next()){
                        modelItemList.addRow(new Object[]{false, result.getString("CATEGORY"), result.getString("CODE"), result.getString("ProductName"),
                                result.getString("QUANTITY"), result.getString("MARKET"), result.getString("ProductLocation"),
                                result.getString("STOCKINGDate"), result.getString("EDA"), result.getString("IMAGE"), result.getString("ID")});
                    }

                }catch(Exception cnfe){
                    System.out.println(cnfe.getMessage());
                }finally {
                    try{
                        result.close();
                        pstmt.close();
                        con.close();
                    } catch (Exception e2) {}
                }
            }

        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe);

        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle);
        }
    }

    private List<List<String>> readCSVFile(String filePath) {
        List<List<String>> csvList = new ArrayList<List<String>>();
        BufferedReader br = null;

        try {
            br = Files.newBufferedReader(Paths.get(filePath));
            String line = "";

            while ((line = br.readLine()) != null) {
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                //배열에서 리스트 반환
                tmpList = Arrays.asList(array);
//                System.out.println(tmpList);
                csvList.add(tmpList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}



