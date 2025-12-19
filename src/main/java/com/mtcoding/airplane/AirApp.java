package com.mtcoding.airplane;

import com.google.gson.Gson;
import com.mtcoding.ex10.Hello;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AirApp {

    // key = 무안, value = NAARKJB
    static Map<String, String> ports = new HashMap<>();

    public static void main(String[] args) {
        // 1. 공항정보를 다운 - HttpUrlConnection으로!!
        String site = "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&_type=json";
        String line;
        String json = "";
        String line2;
        String json2 = "";
        try {
            URL url = new URL(site);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            while(sc.hasNextLine()){
                line = sc.nextLine();
                json = json + line;
            }

            Gson gson = new Gson();
            PortInfo portInfo = gson.fromJson(json, PortInfo.class);

            // 2. PortInfo로 오브젝트화 시켜!!

            // 3. ports에 airportId에 있는 값, airportNm에 있는 값
            List<PortInfo.Response.Body.Items.Item> air = portInfo.getResponse().getBody().getItems().getItem();

            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < air.size(); i++) {
                map.put(air.get(i).getAirportNm(), air.get(i).getAirportId());
            }

            System.out.print("[ ");
            for (String key : map.keySet()) {
                System.out.print(key+" ");
            }
            System.out.println("]");


            Scanner board = new Scanner(System.in);
            // 4. 스캐너로 출발지를 받기
            System.out.println("출발지를 입력하세요 : ");
            String dep = "김해"; // 무안
            //String dep = board.nextLine(); // 무안
            String depKey = map.get(dep);

            // 5. 스캐너로 목적지 받기
            System.out.println("도착지를 입력하세요 : ");
            String arr = "제주"; // 김해
            //String arr = board.nextLine(); // 김해
            String arrKey = map.get(arr);

            // 6. 날짜 받기
            System.out.println("출발 날짜를 입력하세요 : ");
            String time = "20251227"; // 20251219
            //String time = board.nextLine(); // 20251219

            System.out.println(depKey);
            System.out.println(arrKey);
            System.out.println(time);

            // 7. 동적인 URL 만들기
            String site2 = """
                https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&pageNo=1&numOfRows=10&_type=json&depAirportId=${depKey}&arrAirportId=${arrKey}&depPlandTime=${time}""".replace("${depKey}", depKey).replace("${arrKey}", arrKey).replace("${time}", time);

            // 8. 항공스케줄 다운 - HttpUrlConnection 호출
            URL url2 = new URL(site2);
            HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();

            conn2.setRequestMethod("GET");

            Scanner dc = new Scanner(conn2.getInputStream());
            while(dc.hasNextLine()){
                line2 = dc.nextLine();
                json2 = json2 + line2;
            }
            // 9. AirInfo로 파싱
            AirInfo airInfo = gson.fromJson(json2, AirInfo.class);
            // 10. 항공 스케줄 예쁘게 전체 출력
            List<AirInfo.Response.Body.Items.Item> airIn = airInfo.getResponse().getBody().getItems().getItem();
            for (int i = 0; i < airIn.size(); i++){
                System.out.print("[ ");
                System.out.print(airIn.get(i).getAirlineNm()+" ");
                System.out.print(airIn.get(i).getArrAirportNm()+" ");
                System.out.print(airIn.get(i).getArrPlandTime()+" ");
                System.out.print(airIn.get(i).getDepAirportNm()+" ");
                System.out.print(airIn.get(i).getDepPlandTime()+" ");
                System.out.print(airIn.get(i).getEconomyCharge()+" ");
                System.out.print(airIn.get(i).getPrestigeCharge()+" ");
                System.out.print(airIn.get(i).getVihicleId());
                System.out.println(" ]");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
