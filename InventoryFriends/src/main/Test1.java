package main;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import com.squareup.okhttp.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class Test1 {
    String clientId = "27W4iBjRHFPU9iKvVFJi9U";
    String clientSecret = "$2a$04$j8XAvbSRCcg6rEn1TW9TDO";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX");

    Test1(){
        Long timestamp = System.currentTimeMillis();
        System.out.println(getOneMonthAgo());

        try {
//            apiGetOrder(getNow(), apiPostAccessKey(clientId, clientSecret, timestamp));
            apiPostProducts(apiPostAccessKey(clientId, clientSecret, timestamp));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String apiPostAccessKey(String clientId, String clientSecret, Long timestamp) throws Exception {

        RequestBody requestBody = new FormEncodingBuilder()
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
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();

            System.out.println(response.code());

            ResponseBody body = response.body();
            String jsonText = body.string();

            System.out.println(jsonText);

            if (response.isSuccessful()) {
                if (body != null) {
                    body.close();
                    JSONParser jsonParser = new JSONParser();
                    Object obj = jsonParser.parse(jsonText);
                    JSONObject jsonObj = (JSONObject) obj;

                    String accessKey = jsonObj.get("access_token").toString();
                    return accessKey;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void apiPostProducts(String accessKey) throws Exception{
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,
                "{\"searchKeywordType\":\"CHANNEL_PRODUCT_NO\"," +
                "\"channelProductNos\":[0]," +
//                "\"originProductNos\":[0]," +
//                "\"sellerManagementCode\":\"string\"," +
                "\"productStatusTypes\":[\"WAIT\"]," +
                "\"page\":1,\"size\":50," +
                "\"orderType\":\"NO\"," +
                "\"periodType\":\"PROD_REG_DAY\"," +
                "\"fromDate\":\"2023-03-13\"," +
                "\"toDate\":\"2023-03-13\"}");
        Request request = new Request.Builder()
                .url("https://api.commerce.naver.com/external/v1/products/search")
                .post(requestBody)
                .addHeader("Authorization", "Bearer "+ accessKey)
                .addHeader("content-type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            String jsonText = body.string();

            System.out.println(jsonText);
            body.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void apiGetOrder(String lastChangeFrom, String accessKey) throws Exception{

        String str1 = URLEncoder.encode(lastChangeFrom, "UTF-8");
//        String str2 = URLEncoder.encode(lastChangedTo, "UTF-8");

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

            System.out.println(jsonText);
            body.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String generateSignature(String clientId, String clientSecret, Long timestamp) {
        // 밑줄로 연결하여 password 생성
        String password = StringUtils.joinWith("_", clientId, timestamp);
        // bcrypt 해싱
        String hashedPw = BCrypt.hashpw(password, clientSecret);
        // base64 인코딩
        return Base64.getUrlEncoder().encodeToString(hashedPw.getBytes(StandardCharsets.UTF_8));
    }

    public String getNow(){
        Date date = Date.from(ZonedDateTime.now().toInstant());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -24);

        return format.format(cal.getTime());
    }

    public String getOneMonthAgo(){
        Date date = Date.from(ZonedDateTime.now().toInstant());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);

        return format.format(cal.getTime());
    }

    public static void main(String[] args) {
        new Test1();
    }

}