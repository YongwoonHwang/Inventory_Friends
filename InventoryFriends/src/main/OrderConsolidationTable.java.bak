package main;

import com.squareup.okhttp.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class OrderConsolidationTable extends JTable {
    Object headerOrderCon[] = {"주문 번호", "코드", "수량", "주문자", "전화번호", "주소", "주문 상태", "송장 번호", "주문일", "마켓"};
    Object contentsOrderCon[][];
    static JTabbedPane jtpSubTab;
//    static int chk = 0;
    OrderDetailPanel jpOrderDetail1, jpOrderDetail2;
    JSplitPane jspRight;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX");
//    String selectDate;
    DefaultTableModel modelOrderCon;
    LinkedHashMap<Integer, LinkedHashMap> rowData;
    public OrderConsolidationTable(String selectDate){
        jpOrderDetail1 = new OrderDetailPanel();
        jpOrderDetail2 = new OrderDetailPanel();
        OrderDetailPrintWindow winOD = new OrderDetailPrintWindow(jpOrderDetail2, "123");
        JScrollPane jsp = new JScrollPane(jpOrderDetail1);
        jsp.setBorder(null);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetail = new JButton("자세히 보기");
        btnDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winOD.setVisible(true);
            }
        });
        jpNorth.add(btnDetail);
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(jpNorth, BorderLayout.NORTH);

        modelOrderCon = new DefaultTableModel(contentsOrderCon, headerOrderCon){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        setModel(modelOrderCon);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   // 다중 선택 안되게
        setRowSorter(new TableRowSorter<>(modelOrderCon));   // 테이블 정렬 기능 추가
        getTableHeader().setReorderingAllowed(false);    // 테이블 열 이동 안되게
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String ODTitle = new String("주문 상세");
//                chk++;
                if (e.getClickCount() == 2) {
//                    System.out.println(rowData.get(convertRowIndexToModel(getSelectedRow())));
                    String orderId = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrderId").toString();
                    String sellerProductCode = rowData.get(convertRowIndexToModel(getSelectedRow())).get("SellerProductCode").toString();
                    String quantity = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Quantity").toString();
                    String ordererName = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererName").toString();
                    String ordererTel = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererTel").toString();
                    String baseAddress = rowData.get(convertRowIndexToModel(getSelectedRow())).get("BaseAddress").toString();
                    String productOrderStatus = modelOrderCon.getValueAt(convertRowIndexToModel(getSelectedRow()), 6).toString();
                    String trackingNumber = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TrackingNumber").toString();
                    String orderDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrderDate").toString();
                    String market = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Market").toString();
                    String image = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Image").toString();
                    String productName = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ProductName").toString();
                    String totalPaymentAmount = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TotalPaymentAmount").toString();
                    String totalProductAmount = rowData.get(convertRowIndexToModel(getSelectedRow())).get("TotalProductAmount").toString();
                    String unitPrice = rowData.get(convertRowIndexToModel(getSelectedRow())).get("UnitPrice").toString();
                    String productOption = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ProductOption").toString();
                    String ordererId = rowData.get(convertRowIndexToModel(getSelectedRow())).get("OrdererId").toString();
                    String name = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Name").toString();
                    String tel1 = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Tel1").toString();
                    String email = rowData.get(convertRowIndexToModel(getSelectedRow())).get("Email").toString();
                    String shippingMemo = rowData.get(convertRowIndexToModel(getSelectedRow())).get("ShippingMemo").toString();
                    String pickupDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("PickupDate").toString();
                    String sendDate = rowData.get(convertRowIndexToModel(getSelectedRow())).get("SendDate").toString();;
                    String individualCustomUniqueCode = rowData.get(convertRowIndexToModel(getSelectedRow())).get("IndividualCustomUniqueCode").toString();
                    String deliveryCompany = rowData.get(convertRowIndexToModel(getSelectedRow())).get("DeliveryCompany").toString();
                    jpOrderDetail1.setText(image, productName, quantity, totalPaymentAmount,
                            totalProductAmount, unitPrice, market, orderId,
                            productOrderStatus,  productOption, sellerProductCode,
                            ordererId, ordererName, name, ordererTel, tel1, email, orderDate,
                            pickupDate, sendDate, trackingNumber, individualCustomUniqueCode,
                            deliveryCompany, baseAddress, shippingMemo);
                    jpOrderDetail2.setText(image, productName, quantity, totalPaymentAmount,
                            totalProductAmount, unitPrice, market, orderId,
                            productOrderStatus,  productOption, sellerProductCode,
                            ordererId, ordererName, name, ordererTel, tel1, email, orderDate,
                            pickupDate, sendDate, trackingNumber, individualCustomUniqueCode,
                            deliveryCompany, baseAddress, shippingMemo);

                    jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMinimum());
                    jtpSubTab.setVisible(true);
                    jspRight.setDividerSize(7);
                    jspRight.setDividerLocation(getRootPane().getSize().height-400);
                    if (findTabByName(ODTitle, jtpSubTab) != -1) {
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    } else {
                        jtpSubTab.addTab(ODTitle, panel);
                        jtpSubTab.setSelectedIndex(findTabByName(ODTitle, jtpSubTab));
                    }
                }
            }
        });

        makeRowData(selectDate);

    }

    public void makeRowData(String selectDate){
        rowData = getOrderDetail(selectDate);
        if (rowData != null) {
            for (int i = 0; i < rowData.size(); i++) {
                String orderId = rowData.get(i).get("OrderId").toString();
                String sellerProductCode = rowData.get(i).get("SellerProductCode").toString();
                String quantity = rowData.get(i).get("Quantity").toString();
                String ordererName = rowData.get(i).get("OrdererName").toString();
                String ordererTel = rowData.get(i).get("OrdererTel").toString();
                String baseAddress = rowData.get(i).get("BaseAddress").toString();
                String productOrderStatus = rowData.get(i).get("ProductOrderStatus").toString();
                String trackingNumber = rowData.get(i).get("TrackingNumber").toString();
                String orderDate = rowData.get(i).get("OrderDate").toString();
                String market = rowData.get(i).get("Market").toString();

                modelOrderCon.addRow(new Object[]{orderId, sellerProductCode, quantity, ordererName, ordererTel, baseAddress, productOrderStatus, trackingNumber, orderDate, market});
            }
        }
    }

    public void setJtpSubTab(JTabbedPane SubTab){
        jtpSubTab = SubTab;
    }

    public LinkedHashMap getOrderDetail(String selectDate){
        String clientId = "51R5hsaIubpFY6TEIwO7vn";
        String clientSecret = "$2a$04$x.bI3ARXtJmnhu4.DcXsNe";
        Long timestamp = System.currentTimeMillis()-1000*3;

        String accessKey = apiPOSTAccessKey_getAccessKey(clientId, clientSecret, timestamp);
        try{
            ArrayList<String> arrProductOrderId = apiGETOrder_getArrayProductOrderId(selectDate+"T00:00:00.000+09:00", accessKey);
            if(arrProductOrderId != null){
                LinkedHashMap arrOrderDetail = apiPOSTOrderDetail_getArrayOrderDetail(accessKey, arrProductOrderId);
                LinkedHashMap arrOrderDetailforTable = reBuildforTable(arrOrderDetail, arrProductOrderId.size());

                return arrOrderDetailforTable;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public LinkedHashMap reBuildforTable(LinkedHashMap<String, ArrayList<String>> target, int length){
        //{0 = {OrderID=123, ProductCode=1}, 2 = {OrderID=456, ProductCode=5}}
        if (target != null && target.size() != 0){
            Set keySet = target.keySet();

            Object[] arrKey = keySet.toArray(new Object[keySet.size()]);

            LinkedHashMap<Integer, LinkedHashMap<String, String>> result = new LinkedHashMap();
            LinkedHashMap<String, String> contents = new LinkedHashMap();

            for(int i = 0; i < target.size(); i++){

                for(int j = 0; j<length; j++){
                    if(i != 0)
                        contents = (LinkedHashMap) result.get(j).clone();
                    if(arrKey[i].toString().equals("OrderDate") || arrKey[i].toString().equals("PickupDate") || arrKey[i].toString().equals("SendDate")){
                        String strDate = "";
                        try{
                            if(!target.get(arrKey[i]).get(j).toString().equals("")){
                                Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX").parse(target.get(arrKey[i]).get(j).toString());
                                strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        contents.put(arrKey[i].toString(), strDate);
                    } else if (arrKey[i].toString().equals("ProductOrderStatus")) {
                        String strProductOrderStatus = "";
                        switch (target.get(arrKey[i]).get(j).toString()){
                            case "PAYMENT_WAITING" :
                                strProductOrderStatus = "결제 대기";
                                break;
                            case "PAYED" :
                                strProductOrderStatus = "결제 완료";
                                break;
                            case "DELIVERING" :
                                strProductOrderStatus = "배송 중";
                                break;
                            case "DELIVERED" :
                                strProductOrderStatus = "배송 완료";
                                break;
                            case "PURCHASE_DECIDED" :
                                strProductOrderStatus = "구매 확정";
                                break;
                            case "EXCHANGED" :
                                strProductOrderStatus = "교환";
                                break;
                            case "CANCELED" :
                                strProductOrderStatus = "취소";
                                break;
                            case "RETURNED" :
                                strProductOrderStatus = "반품";
                                break;
                            case "CANCELED_BY_NOPAYMENT" :
                                strProductOrderStatus = "미결제 취소";
                                break;
                            default:
                                strProductOrderStatus = "";
                                break;
                        }
                        contents.put(arrKey[i].toString(), strProductOrderStatus);
                    } else if (arrKey[i].toString().equals("DeliveryCompany")) {
                        String strDeliveryCompany = "";
                        switch (target.get(arrKey[i]).get(j).toString()){
                            case "CJGLS" : strDeliveryCompany = "CJ 대한통운";
                                break;
                            case "KGB" : strDeliveryCompany = "로젠택배";
                                break;
                            case "EPOST" : strDeliveryCompany = "우체국택배";
                                break;
                            case "REGISTPOST" : strDeliveryCompany = "우편등기";
                                break;
                            case "HANJIN" : strDeliveryCompany = "한진택배";
                                break;
                            case "HYUNDAI" : strDeliveryCompany = "롯데택배";
                                break;
                            case "DAESIN" : strDeliveryCompany = "대신택배";
                                break;
                            case "ILYANG" : strDeliveryCompany = "일양로지스";
                                break;
                            case "KDEXP" : strDeliveryCompany = "경동택배";
                                break;
                            case "CHUNIL" : strDeliveryCompany = "천일택배";
                                break;
                            case "CH1" : strDeliveryCompany = "기타 택배";
                                break;
                            case "HDEXP" : strDeliveryCompany = "합동택배";
                                break;
                            case "CVSNET" : strDeliveryCompany = "GSPostbox 택배";
                                break;
                            case "DHL" : strDeliveryCompany = "DHL";
                                break;
                            case "FEDEX" : strDeliveryCompany = "FEDEX";
                                break;
                            case "GSMNTON" : strDeliveryCompany = "GSMNTON";
                                break;
                            case "WARPEX" : strDeliveryCompany = "워펙스코리아";
                                break;
                            case "WIZWA" : strDeliveryCompany = "WIZWA";
                                break;
                            case "EMS" : strDeliveryCompany = "EMS";
                                break;
                            case "DHLDE" : strDeliveryCompany = "DHL(독일)";
                                break;
                            case "ACIEXPRESS" : strDeliveryCompany = "ACI";
                                break;
                            case "EZUSA" : strDeliveryCompany = "EZUSA";
                                break;
                            case "PANTOS" : strDeliveryCompany = "범한판토스";
                                break;
                            case "UPS" : strDeliveryCompany = "UPS";
                                break;
                            case "HLCGLOBAL" : strDeliveryCompany = "롯데글로벌로지스(국제택배)";
                                break;
                            case "KOREXG" : strDeliveryCompany = "CJ 대한통운(국제택배)";
                                break;
                            case "TNT" : strDeliveryCompany = "TNT";
                                break;
                            case "SWGEXP" : strDeliveryCompany = "성원글로벌";
                                break;
                            case "DAEWOON" : strDeliveryCompany = "대운글로벌";
                                break;
                            case "USPS" : strDeliveryCompany = "USPS";
                                break;
                            case "IPARCEL" : strDeliveryCompany = "i-parcel";
                                break;
                            case "KUNYOUNG" : strDeliveryCompany = "건영택배";
                                break;
                            case "HPL" : strDeliveryCompany = "한의사랑택배";
                                break;
                            case "SLX" : strDeliveryCompany = "SLX 택배";
                                break;
                            case "HONAM" : strDeliveryCompany = "호남택배";
                                break;
                            case "GSIEXPRESS" : strDeliveryCompany = "GSI 익스프레스";
                                break;
                            case "SEBANG" : strDeliveryCompany = "세방택배";
                                break;
                            case "NONGHYUP" : strDeliveryCompany = "농협택배";
                                break;
                            case "CUPARCEL" : strDeliveryCompany = "CU 편의점택배";
                                break;
                            case "AIRWAY" : strDeliveryCompany = "AIRWAY 익스프레스";
                                break;
                            case "HOMEPICK" : strDeliveryCompany = "홈픽택배";
                                break;
                            case "APEX" : strDeliveryCompany = "APEX";
                                break;
                            case "CWAYEXPRESS" : strDeliveryCompany = "CwayExpress";
                                break;
                            case "YONGMA" : strDeliveryCompany = "용마로지스";
                                break;
                            case "EUROPARCEL" : strDeliveryCompany = "EuroParcel";
                                break;
                            case "KGSL" : strDeliveryCompany = "로젝스";
                                break;
                            case "GOS" : strDeliveryCompany = "GOS 당일택배";
                                break;
                            case "GSPOSTBOX" : strDeliveryCompany = "GS Postbox 퀵";
                                break;
                            case "ADCAIR" : strDeliveryCompany = "ADC 항운택배";
                                break;
                            case "DONGGANG" : strDeliveryCompany = "동강물류";
                                break;
                            case "KIN" : strDeliveryCompany = "경인택배";
                                break;
                            case "HANWOORI" : strDeliveryCompany = "한우리물류";
                                break;
                            case "LGLOGISTICS" : strDeliveryCompany = "LG 전자물류";
                                break;
                            case "GENERALPOST" : strDeliveryCompany = "일반우편";
                                break;
                            case "HANDALUM" : strDeliveryCompany = "한달음택배";
                                break;
                            case "HOWSER" : strDeliveryCompany = "하우저택배";
                                break;
                            case "WEVILL" : strDeliveryCompany = "우리동네택배";
                                break;
                            case "ACE" : strDeliveryCompany = "ACE Express";
                                break;
                            case "QXPRESS" : strDeliveryCompany = "큐익스프레스";
                                break;
                            case "LINEEXP" : strDeliveryCompany = "라인익스프레스";
                                break;
                            case "SMARTLOGIS" : strDeliveryCompany = "스마트로지스";
                                break;
                            case "DAELIM" : strDeliveryCompany = "대림통운";
                                break;
                            case "EUNHA" : strDeliveryCompany = "은하쉬핑";
                                break;
                            case "HOMEINNO" : strDeliveryCompany = "홈이노베이션로지스";
                                break;
                            case "HYBRID" : strDeliveryCompany = "HI 택배";
                                break;
                            case "WOORIHB" : strDeliveryCompany = "우리한방택배";
                                break;
                            case "YJSWORLD" : strDeliveryCompany = "YJS 글로벌";
                                break;
                            case "YJS" : strDeliveryCompany = "YJS 글로벌(영국)";
                                break;
                            case "CRLX" : strDeliveryCompany = "시알로지텍";
                                break;
                            case "ANYTRACK" : strDeliveryCompany = "애니트랙";
                                break;
                            case "BRIDGE" : strDeliveryCompany = "브릿지로지스";
                                break;
                            case "GPSLOGIX" : strDeliveryCompany = "GPS LOGIX";
                                break;
                            case "ESTHER" : strDeliveryCompany = "에스더쉬핑";
                                break;
                            case "LOTOS" : strDeliveryCompany = "로토스";
                                break;
                            case "UFREIGHT" : strDeliveryCompany = "유프레이트코리아";
                                break;
                            case "IK" : strDeliveryCompany = "IK 물류";
                                break;
                            case "SUNGHUN" : strDeliveryCompany = "성훈물류";
                                break;
                            default : strDeliveryCompany = "";
                                break;
                        }
                        contents.put(arrKey[i].toString(), strDeliveryCompany);
                    } else{
                        contents.put(arrKey[i].toString(), target.get(arrKey[i]).get(j));
                    }
                    result.put(j, (LinkedHashMap<String, String>) contents.clone());
//                System.out.println(result);
                }
            }
            return result;
        }
        return null;
    }

    public String getDate(int beforeDate){
        Date date1 = Date.from(ZonedDateTime.now().toInstant());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.DATE, (-1*beforeDate));

        String strDate = "";
        try {
            Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX").parse(format.format(cal.getTime()));
            strDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        }catch (Exception e){
            e.printStackTrace();
        }
        strDate += "T00:00:00.000+09:00";

        return strDate;
    }

    public String generateSignature(String clientId, String clientSecret, Long timestamp) {
        // 밑줄로 연결하여 password 생성
        String password = StringUtils.joinWith("_", clientId, timestamp);
        // bcrypt 해싱
        String hashedPw = BCrypt.hashpw(password, clientSecret);
        // base64 인코딩
        return Base64.getUrlEncoder().encodeToString(hashedPw.getBytes(StandardCharsets.UTF_8));
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

//            System.out.println(jsonText);

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

    public ArrayList apiGETOrder_getArrayProductOrderId(String lastChangedFrom, String accessKey) throws Exception{

        String str1 = URLEncoder.encode(lastChangedFrom, "UTF-8");
        ArrayList<String> arrProductOrderId = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.commerce.naver.com/external/v1/pay-order/seller/product-orders/last-changed-statuses?lastChangedFrom="+ str1)
                .get()
                .addHeader("Authorization", "Bearer "+accessKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            String jsonText = body.string();

//            System.out.println(jsonText);

            if (response.isSuccessful()) {
                if (body != null) {
                    body.close();
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonText);


                    if(jsonObj.containsKey("data")){
                        String data = jsonObj.get("data").toString();
                        JSONObject jsonData = (JSONObject) jsonParser.parse(data);

                        String lastChangeStatuses = jsonData.get("lastChangeStatuses").toString();
                        JSONArray jsonLastChangeStatuses = (JSONArray) jsonParser.parse(lastChangeStatuses);

                        for(int i = 0; i<jsonLastChangeStatuses.size();i++){
                            String jsonString = jsonLastChangeStatuses.get(i).toString();
                            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

                            arrProductOrderId.add(jsonObject.get("productOrderId").toString());
                        }
                    }
                    return arrProductOrderId;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public LinkedHashMap apiPOSTOrderDetail_getArrayOrderDetail(String accessKey, ArrayList<String> arrProductOrderId){
        ArrayList<String> arrOrderId = new ArrayList<>();
        ArrayList<String> arrOrdererName = new ArrayList<>();
        ArrayList<String> arrOrdererTel = new ArrayList<>();
        ArrayList<String> arrBaseAddress = new ArrayList<>();
        ArrayList<String> arrTrackingNumber = new ArrayList<>();
        ArrayList<String> arrQuantity = new ArrayList<>();
        ArrayList<String> arrOrderDate = new ArrayList<>();
        ArrayList<String> arrMarket = new ArrayList<>();
        ArrayList<String> arrSellerProductCode = new ArrayList<>();

        ArrayList<String> arrProductName = new ArrayList<>();
        ArrayList<String> arrProductOrderStatus = new ArrayList<>();
        ArrayList<String> arrTotalProductAmount = new ArrayList<>();
        ArrayList<String> arrTotalPaymentAmount = new ArrayList<>();
        ArrayList<String> arrProductOption = new ArrayList<>();
        ArrayList<String> arrShippingMemo = new ArrayList<>();
        ArrayList<String> arrOrdererId = new ArrayList<>();
        ArrayList<String> arrName = new ArrayList<>();
        ArrayList<String> arrTel1 = new ArrayList<>();
        ArrayList<String> arrPickupDate = new ArrayList<>();
        ArrayList<String> arrSendDate = new ArrayList<>();
        ArrayList<String> arrIndividualCustomUniqueCode = new ArrayList<>();
        ArrayList<String> arrDeliveryCompany = new ArrayList<>();
        ArrayList<String> arrEmail = new ArrayList<>();
        ArrayList<String> arrUnitPrice = new ArrayList<>();
        ArrayList<String> arrProductId = new ArrayList<>();

        LinkedHashMap arrOrderDetail = new LinkedHashMap<>();

        String contents = "";

        if(arrProductOrderId != null) {
            if (arrProductOrderId.size() != 0) {
                for (int i = 0; i < arrProductOrderId.size(); i++) {
                    contents += ("\"" + arrProductOrderId.get(i) + "\"" + ", ");
                }
                contents = contents.substring(0, contents.length() - 2);
            }

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody reqBody = RequestBody.create(mediaType, "{\"productOrderIds\":[" + contents + "]}");
            Request request = new Request.Builder()
                    .url("https://api.commerce.naver.com/external/v1/pay-order/seller/product-orders/query")
                    .post(reqBody)
                    .addHeader("Authorization", "Bearer " + accessKey)
                    .addHeader("content-type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                ResponseBody resBody = response.body();
                String jsonText = resBody.string();

                if (response.isSuccessful()) {
                    if (resBody != null) {
                        resBody.close();
                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonText);

                        String data = jsonObj.get("data").toString();
                        JSONArray jsonData = (JSONArray) jsonParser.parse(data);

                        for (int i = 0; i < jsonData.size(); i++) {
                            String jsonString = jsonData.get(i).toString();
                            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

                            // order
                            String order = jsonObject.get("order").toString();
                            JSONObject jsonOrder = (JSONObject) jsonParser.parse(order);

                            // productOrder
                            String productOrder = jsonObject.get("productOrder").toString();
                            JSONObject jsonProductOrder = (JSONObject) jsonParser.parse(productOrder);

                            String shippingAddress = jsonProductOrder.get("shippingAddress").toString();
                            JSONObject jsonShippingAddress = (JSONObject) jsonParser.parse(shippingAddress);

                            // delivery
                            if (jsonObject.containsKey("delivery")) {
                                String delivery = jsonObject.get("delivery").toString();
                                JSONObject jsonDelivery = (JSONObject) jsonParser.parse(delivery);

                                //송장번호
                                if (jsonDelivery.containsKey("trackingNumber"))
                                    arrTrackingNumber.add(jsonDelivery.get("trackingNumber").toString());
                                else
                                    arrTrackingNumber.add("");
                                // ---상세---
                                // 집화일
                                if (jsonDelivery.containsKey("pickupDate"))
                                    arrPickupDate.add(jsonDelivery.get("pickupDate").toString());
                                else
                                    arrPickupDate.add("");

                                // 발송일
                                arrSendDate.add(jsonDelivery.get("sendDate").toString());
                                // 택배사
                                if (jsonDelivery.containsKey("deliveryCompany"))
                                    arrDeliveryCompany.add(jsonDelivery.get("deliveryCompany").toString());
                                else
                                    arrDeliveryCompany.add("");

                            } else {
                                arrTrackingNumber.add("");
                                arrPickupDate.add("");
                                arrSendDate.add("");
                                arrDeliveryCompany.add("");
                            }


                            //주문번호
                            arrOrderId.add(jsonOrder.get("orderId").toString());
                            //상품코드
                            if (jsonProductOrder.containsKey("sellerProductCode"))
                                arrSellerProductCode.add(jsonProductOrder.get("sellerProductCode").toString());
                            else
                                arrSellerProductCode.add("Test");
                            //주문 수량
                            arrQuantity.add(jsonProductOrder.get("quantity").toString());
                            //주문자
                            arrOrdererName.add(jsonOrder.get("ordererName").toString());
                            //구매자 연락처
                            arrOrdererTel.add(jsonOrder.get("ordererTel").toString());
                            //주소
                            arrBaseAddress.add(jsonShippingAddress.get("baseAddress").toString() + " " + jsonShippingAddress.get("detailedAddress").toString());
                            //주문일
                            arrOrderDate.add(jsonOrder.get("orderDate").toString());
                            //마켓
                            arrMarket.add("네이버");

                            // ---상세---
                            // 상품명
                            arrProductName.add(jsonProductOrder.get("productName").toString());
                            // 결제금액
                            arrTotalPaymentAmount.add(jsonProductOrder.get("totalPaymentAmount").toString());
                            // 상품금액
                            arrTotalProductAmount.add(jsonProductOrder.get("totalProductAmount").toString());
                            // 단가
                            arrUnitPrice.add(jsonProductOrder.get("unitPrice").toString());
                            // 주문 상태
                            arrProductOrderStatus.add(jsonProductOrder.get("productOrderStatus").toString());
                            // 옵션명
                            arrProductOption.add(jsonProductOrder.get("productOption").toString());
                            // 구매자 ID
                            arrOrdererId.add(jsonOrder.get("ordererId").toString());
                            // 수취인 명
                            arrName.add(jsonShippingAddress.get("name").toString());
                            // 수취인 연락처
                            arrTel1.add(jsonShippingAddress.get("tel1").toString());
                            // 이메일
                            // 네이버는 이메일 없음
                            arrEmail.add("");
                            // 통관부호
                            if (jsonProductOrder.containsKey("IndividualCustomUniqueCode"))
                                arrIndividualCustomUniqueCode.add(jsonProductOrder.get("IndividualCustomUniqueCode").toString());
                            else
                                arrIndividualCustomUniqueCode.add("");
                            // 배송 메시지
                            if (Boolean.parseBoolean(jsonOrder.get("isDeliveryMemoParticularInput").toString()))
                                arrShippingMemo.add(jsonProductOrder.get("shippingMemo").toString());
                            else
                                arrShippingMemo.add("");

                            arrProductId.add(jsonProductOrder.get("productId").toString());

                        }
                        // 이미지
                        ArrayList<String> arrImage = FindImage(accessKey, arrProductId);

                        // 주문번호, 상품코드, 주문수량, 주문자, 전화번호, 주소, 주문상태, 송장번호, 주문일, 마켓
                        arrOrderDetail.put("OrderId", arrOrderId);
                        arrOrderDetail.put("SellerProductCode", arrSellerProductCode);
                        arrOrderDetail.put("Quantity", arrQuantity);
                        arrOrderDetail.put("OrdererName", arrOrdererName);
                        arrOrderDetail.put("OrdererTel", arrOrdererTel);
                        arrOrderDetail.put("BaseAddress", arrBaseAddress);
                        arrOrderDetail.put("ProductOrderStatus", arrProductOrderStatus);
                        arrOrderDetail.put("TrackingNumber", arrTrackingNumber);
                        arrOrderDetail.put("OrderDate", arrOrderDate);
                        arrOrderDetail.put("Market", arrMarket);

                        // 이미지, 상품명, 결제금액, 상품금액, 단가, 옵션명,
                        arrOrderDetail.put("Image", arrImage);
                        arrOrderDetail.put("ProductName", arrProductName);
                        arrOrderDetail.put("TotalPaymentAmount", arrTotalPaymentAmount);
                        arrOrderDetail.put("TotalProductAmount", arrTotalProductAmount);
                        arrOrderDetail.put("UnitPrice", arrUnitPrice);
                        arrOrderDetail.put("ProductOption", arrProductOption);
                        // 구매자ID, 수취인명, 수취인 연락처, 이메일, 배송메시지,
                        arrOrderDetail.put("OrdererId", arrOrdererId);
                        arrOrderDetail.put("Name", arrName);
                        arrOrderDetail.put("Tel1", arrTel1);
                        arrOrderDetail.put("Email", arrEmail);
                        arrOrderDetail.put("ShippingMemo", arrShippingMemo);
                        // 집화일, 발송일, 통관부호, 택배사
                        arrOrderDetail.put("PickupDate", arrPickupDate);
                        arrOrderDetail.put("SendDate", arrSendDate);
                        arrOrderDetail.put("IndividualCustomUniqueCode", arrIndividualCustomUniqueCode);
                        arrOrderDetail.put("DeliveryCompany", arrDeliveryCompany);

                        return arrOrderDetail;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList FindImage(String accessKey, ArrayList<String> arrProductId) {
        ArrayList<String> arrcompImages = new ArrayList<>();
        ArrayList<String> arrImages = new ArrayList<>();
        ArrayList<String> arrProductIdwithoutDuple = new ArrayList<>();
        LinkedHashSet<String> li_hs_productId = new LinkedHashSet<>(arrProductId);
        arrProductIdwithoutDuple.addAll(li_hs_productId);


        OkHttpClient client = new OkHttpClient();
        if(arrProductId.size() != 0) {

            for (int i = 0; i < arrProductIdwithoutDuple.size(); i++) {

                Request request = new Request.Builder()
                        .url("https://api.commerce.naver.com/external/v2/products/channel-products/" + arrProductIdwithoutDuple.get(i))
                        .get()
                        .addHeader("Authorization", "Bearer " + accessKey)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    ResponseBody resBody = response.body();
                    String jsonText = resBody.string();
//                System.out.println("4번째 jsonText = " + jsonText);

                    if (response.isSuccessful()) {
                        if (resBody != null) {
                            resBody.close();
                            JSONParser jsonParser = new JSONParser();
                            Object obj = jsonParser.parse(jsonText);
                            JSONObject jsonObj = (JSONObject) obj;


                            String originProduct = jsonObj.get("originProduct").toString();
                            JSONObject jsonOriginProduct = (JSONObject) jsonParser.parse(originProduct);
//                        System.out.println("jsonOriginProduct = " + jsonOriginProduct);

                            String images = jsonOriginProduct.get("images").toString();
                            JSONObject jsonImages = (JSONObject) jsonParser.parse(images);

//                        System.out.println("jsonImages = " + jsonImages);

                            String representativeImage = jsonImages.get("representativeImage").toString();
                            JSONObject jsonRepresentativeImage = (JSONObject) jsonParser.parse(representativeImage);

                            arrImages.add(jsonRepresentativeImage.get("url").toString());


                        }
                    }
                } catch (
                        Exception e) {
                    e.printStackTrace();
                }

            }


            int cnt = 0;
            arrcompImages.add(arrImages.get(cnt));

            for (int k = 1; k < arrProductId.size(); k++) {

                String after = arrProductId.get(k).toString();
                String before = arrProductId.get(k - 1).toString();

                if (after.equals(before) != true) {
                    cnt += 1;
                    arrcompImages.add(arrImages.get(cnt));

                } else {
                    arrcompImages.add(arrImages.get(cnt));
                }

            }
        }

        return arrcompImages;

    }

    // 탭 타이틀 이름을 찾아 인덱스를 반환하는 함수
    public int findTabByName(String title, JTabbedPane tab) {
        int tabCount = tab.getTabCount();
        for (int i=0; i < tabCount; i++) {
            String tabTitle = tab.getTitleAt(i);
            if (tabTitle.equals(title)) return i;
        }
        return -1;
    }
}
