package main;

import com.squareup.okhttp.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ItemListPanel extends JPanel {
    String market[] = {"", "11번가", "G마켓", "네이버", "옥션", "위메프", "쿠팡", "티몬"};

//    String category[] = {"", "category1", "category2", "category3"};
    ImageIcon imgSearch1 = new ImageIcon("./img/img_Search1.jpg");
    ImageIcon imgSearch2 = new ImageIcon("./img/img_Search2.jpg");
    ImageIcon imgClear1 = new ImageIcon("./img/img_X1.jpg");
    ImageIcon imgClear2 = new ImageIcon("./img/img_X2.jpg");
    ImageIcon imgDel1 = new ImageIcon("./img/img_Del1.jpg");
    ImageIcon imgDel2 = new ImageIcon("./img/img_Del2.jpg");
    ImageIcon imgRefresh1 = new ImageIcon("./img/img_Refresh1.jpg");
    ImageIcon imgRefresh2 = new ImageIcon("./img/img_Refresh2.jpg");
    JButton btnSearch, btnClear, btnDel,btnRefresh;
    JComboBox jcbMarket, jcbCategory;
    JPanel jpILSearch, jpSouthRight,jpSouthLeft;
    JLabel refreshTime;
    ItemListTable jtItemList;
    HintTextField jtfItemCode, jtfItemName, jtfItemQuantity, jtfItemLocation,
            jtfLastReceivingDate, jtfNextReceivingDate;
    TableRowSorter<TableModel> rowSorter;
    ArrayList<RowFilter<Object, Object>> filters;
    List<Integer> selectRows = new ArrayList<>();
    List<String> selectID = new ArrayList<>();
    ItemStatusPanel jpItemStatusPanel;
    JTabbedPane jtpSubTab;
    JSplitPane jspRight;
    String dbName = "ifdb";
    String dbTableName;

    JSplitPane jsp;

    Timestamp timestamp;
    SimpleDateFormat sdf;

    int testnumber = -10;
    String productName = "쿠션 A형 선인장 베이지";


    private PreparedStatement pstmt = null;
    private Connection con = null;
    public ItemListPanel(String userid){
        ///////////////////////////////////////
        ////////////////////////////////////////
        dbTableName = userid + "_ItemList";
        Font font1 = new Font("돋움", Font.PLAIN, 12);
        filters = new ArrayList<>();

        setLayout(new BorderLayout());
        jtItemList = new ItemListTable(userid);

        resizeColumnWidth(jtItemList);
        jtItemList.getColumn("").setPreferredWidth(1);  // 체크박스 컬럼 크기 줄이기
        rowSorter = new TableRowSorter<>(jtItemList.getModel());
        jtItemList.setRowSorter(rowSorter);

        // 상단 검색 패널
        jpILSearch = new JPanel();

        jcbMarket = new JComboBox(market);
        jcbMarket.setPreferredSize(new Dimension(100, 20));
        jcbMarket.setBackground(Color.WHITE);
        jcbMarket.setFont(font1);
        jcbCategory = new JComboBox();
        jcbCategory.setPreferredSize(new Dimension(100, 20));
        jcbCategory.setBackground(Color.WHITE);
        jcbCategory.setFont(font1);

        btnSearch = new JButton(imgSearch1);
        btnSearch.setRolloverIcon(imgSearch2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnSearch.setBorderPainted(false); // 버튼 테두리 제거
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setPreferredSize(new Dimension(56, 24)); // 버튼 크기 지정
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(jcbCategory.getSelectedIndex() != 0){
                    filters.add(RowFilter.regexFilter("(?i)" + jcbCategory.getSelectedItem(),1));
                }
                if(!jtfItemCode.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemCode.getText(),2));
                }
                if(!jtfItemName.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemName.getText(),3));
                }
                if(!jtfItemQuantity.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemQuantity.getText(),4));
                }
                if(jcbMarket.getSelectedIndex() != 0){
                    filters.add(RowFilter.regexFilter("(?i)" + jcbMarket.getSelectedItem(),5));
                }
                if(!jtfItemLocation.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfItemLocation.getText(),6));
                }
                if(!jtfLastReceivingDate.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfLastReceivingDate.getText(),7));
                }
                if(!jtfNextReceivingDate.getLostFocus()){
                    filters.add(RowFilter.regexFilter("(?i)" + jtfNextReceivingDate.getText(),8));
                }

                rowSorter.setRowFilter(RowFilter.andFilter(filters));
                filters.clear();
            }
        });
        
        btnClear = new JButton(imgClear1);
        btnClear.setRolloverIcon(imgClear2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnClear.setBorderPainted(false); // 버튼 테두리 제거
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.setPreferredSize(new Dimension(23, 23)); // 버튼 크기 지정
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                rowSorter.setRowFilter(RowFilter.andFilter(filters));
                filters.clear();

                jtfItemName.reset();
                jtfItemCode.reset();
                jtfItemQuantity.reset();
                jtfItemLocation.reset();
                jtfLastReceivingDate.reset();
                jtfNextReceivingDate.reset();
                jcbCategory.setSelectedIndex(0);
                jcbMarket.setSelectedIndex(0);
            }
        });

        jtfItemCode = new HintTextField("상품 코드");
        jtfItemCode.setColumns(9);
        jtfItemName = new HintTextField("상품명");
        jtfItemName.setColumns(9);
        jtfItemQuantity = new HintTextField("수량");
        jtfItemQuantity.setColumns(9);
        jtfItemLocation = new HintTextField("재고 위치");
        jtfItemLocation.setColumns(9);
        jtfLastReceivingDate = new HintTextField("최근 입고일");
        jtfLastReceivingDate.setColumns(9);
        jtfNextReceivingDate = new HintTextField("다음 입고 예정일");
        jtfNextReceivingDate.setColumns(9);

        jpILSearch.add(jcbCategory);
        jpILSearch.add(jtfItemCode);
        jpILSearch.add(jtfItemName);
        jpILSearch.add(jtfItemQuantity);
        jpILSearch.add(jtfItemLocation);
        jpILSearch.add(jtfLastReceivingDate);
        jpILSearch.add(jtfNextReceivingDate);
        jpILSearch.add(jcbMarket);
        jpILSearch.add(btnSearch);
        jpILSearch.add(btnClear);

        add(jpILSearch, BorderLayout.NORTH);
        add(new JScrollPane(jtItemList), BorderLayout.CENTER);

        jsp = new JSplitPane();
        jsp.setDividerSize(0);
        jpSouthRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDel = new JButton(imgDel1);
        btnDel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnDel.setRolloverIcon(imgDel2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnDel.setBorderPainted(false); // 버튼 테두리 제거
        btnDel.setFocusPainted(false);
        btnDel.setContentAreaFilled(false);
        btnDel.setPreferredSize(new Dimension(48, 24));
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < jtItemList.getRowCount(); i++) {
                    if ((boolean) jtItemList.getValueAt(i, 0)) {
                        selectRows.add(jtItemList.convertRowIndexToModel(i));
                        selectID.add((String)jtItemList.getValueAt(i, 10));
                    }
                }
                if(selectRows.size() != 0){
                    Collections.sort(selectRows, Collections.reverseOrder());
                    int input = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?",
                            "삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if(input == JOptionPane.OK_OPTION){
                        for(int i = 0; i < selectRows.size(); i++){
                            String sql = "DELETE FROM " + dbTableName + " WHERE id = " + selectID.get(i);

                            try{
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
                                // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
                                //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.
                                pstmt = con.prepareStatement(sql);
                                pstmt.execute("USE " + dbName);
                                int cnt = pstmt.executeUpdate();
                                // 사용할 DB를 선택한다.
                                if(cnt != 0) {
                                    jtItemList.modelItemList.removeRow(selectRows.get(i));
                                    if (selectID.get(i) == jpItemStatusPanel.dbIdno) {
                                        String ISTitle = "재고 상세";
                                        jtpSubTab.removeTabAt(findTabByName(ISTitle, jtpSubTab));
                                        try {
                                            jtpSubTab.isEnabledAt(0);
                                        } catch (Exception exception) {
                                            jspRight.setDividerSize(0);
                                            jspRight.setDividerLocation(jspRight.getLocation().y + jspRight.getSize().width + 1);
                                            jtpSubTab.setVisible(false);
                                        }
                                    }

                                }


                            } catch (ClassNotFoundException cnfe) {
                                System.out.println("DB 드라이버 로딩 실패 :" + cnfe);

                            } catch (SQLException sqle) {
                                System.out.println("DB 접속실패 : " + sqle);
                            } finally {
                                try{
                                    pstmt.close();
                                    con.close();
                                } catch (Exception e2) {}
                            }
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "삭제할 항목을 선택 후 진행해주세요");
                }
                selectRows.clear();
                selectID.clear();
            }
        });
        jpSouthRight.add(btnDel);
        jsp.setRightComponent(jpSouthRight);

        jpSouthLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefresh = new JButton(imgRefresh1);
        btnRefresh.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnRefresh.setRolloverIcon(imgRefresh2); // 버튼에 마우스가 올라갈떄 이미지 변환
        btnRefresh.setBorderPainted(false); // 버튼 테두리 제거
        btnRefresh.setFocusPainted(false);
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setPreferredSize(new Dimension(48, 24));
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                refresh(userid);
            }
        });
        jpSouthLeft.add(btnRefresh);

        timestamp = new Timestamp(System.currentTimeMillis());
        sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        refreshTime = new JLabel("마지막 갱신 일자: " + sdf.format(timestamp));
        jpSouthLeft.add(refreshTime);
        jsp.setLeftComponent(jpSouthLeft);
        add(jsp,BorderLayout.SOUTH);

        refresh(userid);
    }

    public void refresh(String userid){
        //타임스탬프
        Timestamp newtimestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat newsdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        LinkedHashMap arrQuantityInformation = new LinkedHashMap<>();

        String clientId = "51R5hsaIubpFY6TEIwO7vn";
        String clientSecret = "$2a$04$x.bI3ARXtJmnhu4.DcXsNe";
        Long timestamp = System.currentTimeMillis()-1000*3;

        String accesskey = apiPOSTAccessKey_getAccessKey(clientId, clientSecret, timestamp);

        try{
            arrQuantityInformation = apiPOSTProducts_getQuantityInformation(accesskey);
        } catch (Exception e){
            e.printStackTrace();
        }

        arrQuantityInformation.forEach((key, value)->{
            String quantity = getQuantity_Naver(key.toString());
            if(quantity == null){
                modifyQuantity_Naver(key.toString(), value.toString());
                quantity = getQuantity_Naver(key.toString());
            }
            int dbQuantity = Integer.parseInt(quantity);
            int marketQuantity = Integer.parseInt(value.toString());

            if(!(dbQuantity == marketQuantity)){
                // 수량 감소
                int q = dbQuantity-marketQuantity;
                decreaseQuantity(key.toString(), q);
            }
            modifyQuantity_Naver(key.toString(), value.toString());
        });

        //테이블 내용 변경
        jtItemList.modelItemList.setRowCount(0);
        jtItemList.select(userid);
        refreshTime.setText("마지막 갱신 일자: " + newsdf.format(newtimestamp));
        refreshTime.revalidate();
        refreshTime.repaint();
    }

    public String getQuantity_Naver(String productCode){
        String Quantity_Naver = "";
        try {
            String sql = "SELECT Quantity_Naver FROM sy999_ItemList WHERE code = \""+productCode+"\"";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            pstmt = con.prepareStatement(sql);
            pstmt.execute("USE " + dbName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Quantity_Naver = rs.getString("Quantity_Naver");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Quantity_Naver;
    }

    public void modifyQuantity_Naver(String itemCode, String quantity){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String sql = "UPDATE " + dbTableName + " SET Quantity_Naver = " + quantity + " WHERE Code = \""+ itemCode +"\"";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/","admin","admin1470!");
            // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
            //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

            pstmt = con.prepareStatement(sql);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

            int cnt = pstmt.executeUpdate();


        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe);
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle);
        }finally {
            try{
                result.close();
                pstmt.close();
                con.close();
            } catch (Exception e2) {}
        }
    }

    public void decreaseQuantity(String productCode, int num){
        //DB내용 변경
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String sql = "UPDATE " + dbTableName + " SET QUANTITY = QUANTITY - "+ num +" WHERE CODE = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://iftest.cn9z6e29xfig.ap-northeast-2.rds.amazonaws.com:3306/", "admin", "admin1470!");
            // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다.
            //Statement하나당 한개의 ResultSet 객체만을 열 수 있다.

            pstmt = con.prepareStatement(sql);
            pstmt.execute("USE " + dbName); // 사용할 DB를 선택한다.
            // executeQuery : 쿼리를 실행하고 결과를 ResultSet 객체로 반환한다.

            pstmt.setString(1,productCode);

            pstmt.executeUpdate();

        }catch (ClassNotFoundException cnfe){
            System.out.println("DB 드라이버 로딩 실패 :" + cnfe);
        }catch (SQLException sqle) {
            System.out.println("DB 접속실패 : " + sqle);
        }finally {
            try{
                result.close();
                pstmt.close();
                con.close();
            } catch (Exception e2) {}
        }

    }


    public void setSubTab(JTabbedPane SubTab){
        jtItemList.setSubTab(SubTab);
    }

    public TableModel getTableModel(){
        return jtItemList.getModel();
    }

    public void setComboboxData(JComboBox comboBox){
        jcbCategory.removeAllItems();
        for (int t = 0; t < comboBox.getItemCount(); t++)
        {
            jcbCategory.addItem(comboBox.getItemAt(t));
        }
    }
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }

    // 테이블 너비를 내용에 맞춰주는 함수
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public String apiPOSTAccessKey_getAccessKey(String clientId, String clientSecret, Long timestamp) {

        RequestBody reqBody = new FormEncodingBuilder()
                .add("client_id", clientId)
                .add("timestamp", timestamp.toString())
                .add("client_secret_sign", generateSignature(clientId, clientSecret, timestamp))
                .add("grant_type","client_credentials")
                .add("type", "SELF")
                .build();

        String apiUrl = "https://api.commerce.naver.com/external/v1/oauth2/token";    // 각자 상황에 맞는 IP & url 사용

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("content-type", "application/x-www-form-urlencod")
                .post(reqBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            ResponseBody resBody = response.body();
            String jsonText = resBody.string();

            if (response.isSuccessful()) {
                if (resBody != null) {
                    resBody.close();
                    JSONParser jsonParser = new JSONParser();
                    Object obj = jsonParser.parse(jsonText);
                    JSONObject jsonObj = (JSONObject) obj;

                    String accessKey = jsonObj.get("access_token").toString();
                    return accessKey;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public LinkedHashMap apiPOSTProducts_getQuantityInformation(String accessKey){
        LinkedHashMap arrQuantityInformation = new LinkedHashMap<>();

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody reqBody = RequestBody.create(mediaType, "{\"sellerManagementCode\":\"Test\",\"productStatusTypes\":[\"SALE\"],\"orderType\":\"NAME\"}");
        RequestBody reqBody = RequestBody.create(mediaType, "{\"productStatusTypes\":[\"SALE\"],\"orderType\":\"NAME\"}");
        Request request = new Request.Builder()
                .url("https://api.commerce.naver.com/external/v1/products/search")
                .post(reqBody)
                .addHeader("Authorization", "Bearer "+accessKey)
                .addHeader("content-type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody resBody = response.body();
            String jsonText = resBody.string();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonText);

            String contents = jsonObj.get("contents").toString();
            JSONArray jsonContents = (JSONArray) jsonParser.parse(contents);

            for(int i = 0; i<jsonContents.size(); i++){
                String content = jsonContents.get(i).toString();
                JSONObject jsonContent = (JSONObject) jsonParser.parse(content);

                String channelProducts = jsonContent.get("channelProducts").toString();
                JSONArray jsonChannelProducts = (JSONArray) jsonParser.parse(channelProducts);

                for(int j = 0; j<jsonChannelProducts.size(); j++){
                    String channelProduct = jsonChannelProducts.get(j).toString();
                    JSONObject jsonChannelProduct = (JSONObject) jsonParser.parse(channelProduct);

                    if(jsonChannelProduct.containsKey("sellerManagementCode"))
                        arrQuantityInformation.put(jsonChannelProduct.get("sellerManagementCode").toString(), jsonChannelProduct.get("stockQuantity").toString());
                    else
                        arrQuantityInformation.put("Test", jsonChannelProduct.get("stockQuantity").toString());

                }
            }

            return arrQuantityInformation;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String generateSignature(String clientId, String clientSecret, Long timestamp) {
        // 밑줄로 연결하여 password 생성
        String password = StringUtils.joinWith("_", clientId, timestamp);
        // bcrypt 해싱
        String hashedPw = BCrypt.hashpw(password, clientSecret);
        // base64 인코딩
        return Base64.getUrlEncoder().encodeToString(hashedPw.getBytes(StandardCharsets.UTF_8));
    }
}
