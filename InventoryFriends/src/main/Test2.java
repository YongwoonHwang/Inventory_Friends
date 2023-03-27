package main;

import com.squareup.okhttp.*;

public class Test2 {
    String key = "1651913e2b0e429287285fd6518f1417";

    Test2(){
        Long timestamp = System.currentTimeMillis()-1000*3;
        apiPOSTAccessKey_getAccessKey(key);
    }

    public void apiPOSTAccessKey_getAccessKey(String key) {


        String apiUrl = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key="+key+"&apiCode=ProductSearch&keyword=SSD";    // 각자 상황에 맞는 IP & url 사용

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody resBody = response.body();
            String jsonText = resBody.string();

            System.out.println(response.code());
            System.out.println(jsonText);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test2();
    }

}