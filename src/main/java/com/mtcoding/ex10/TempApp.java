package com.mtcoding.ex10;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class TempApp {
    public static void main(String[] args) {
        try {
            // 1. ip와 port를 통해 소켓을 만들고 스트림을 연결한다.
            URL url = new URL("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date=20251218&base_time=1200&nx=98&ny=75");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            // 2. 버퍼를 달아야한다.
            Scanner sc = new Scanner(conn.getInputStream());
            String json = "";
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                json = json + line;
            }
            //System.out.println(json);

            Gson gson = new Gson();
            // 1. (String타입의 json을 -> Hello 오브젝트로 변경)(통신으로 데이터를 다운받을때만)
            Hello hello = gson.fromJson(json, Hello.class);

            List<Hello.Response.Body.Items.Item> itemsList = hello.getResponse().getBody().getItems().getItem();
            String temp = itemsList.get(3).getObsrValue();
            System.out.println(temp);
            // 2. (Hello 오브젝트를 -> String타입의 json)(데이터를 보낼때)
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
