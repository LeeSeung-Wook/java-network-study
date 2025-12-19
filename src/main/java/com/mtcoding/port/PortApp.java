package com.mtcoding.port;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class PortApp {
    public static void main(String[] args) {

        try {
            // 1. 공항정보 다운로드 (완)
            PortRepository prp = new PortRepository();
            String portJson = prp.download("https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&_type=json");
            System.out.println(1);

            // 2. PortDTO에 파싱 (완)
            Gson gson = new Gson();
            PortDTO pd = gson.fromJson(portJson, PortDTO.class);
            System.out.println(2);

            // 3. HashMap에 옮기기 (완)
            List<PortDTO.Item> list = pd.getResponse().getBody().getItems().getItem();
            HashMap<String, String> map = new HashMap<>();

            for(PortDTO.Item item : list){
                String key = item.getAirportNm(); // 무안
                String value = item.getAirportId(); // NAARKJB
                map.put(key,value);
            }
            System.out.println(3);

            //map.put("무안","NAARKJB");
            //map.put("광주","NAARKJJ");

            // 4. 출발지(김해), 목적지(김포), 출발시간(20251220) 키보드로 입력받기
            String dep = "김해";
            String arr = "김포";
            String time = "20251220";

            // 5. 해시aoq에서 김해 -> value, 김포 -> value (완)
            String depId = map.get(dep);
            String arrId = map.get(arr);
            System.out.println(5);

            // 6. 항공스케줄 주소만들기 (완)
            String site = "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&pageNo=1&numOfRows=10&_type=json&depAirportId="+depId+"&arrAirportId="+arrId+"&depPlandTime="+time;
            System.out.println(6);

            // 7. 항공스케줄 다운로드 (완)
            String scheduleJson = prp.download(site);
            System.out.println(7);

            // 8. ScheduleDTO에 파싱 (완)
            ScheduleDTO sd = gson.fromJson(scheduleJson, ScheduleDTO.class);
            System.out.println(8);

            // 9. 항공 스케줄 출력
            List<ScheduleDTO.Item> list2 = sd.getResponse().getBody().getItems().getItem();
            for (ScheduleDTO.Item item: list2){

                if(item.getEconomyCharge() == null){
                    System.out.println("항공권이 없습니다.");
                    System.out.println();
                    continue;
                }

                System.out.println("출발지 : "+item.getDepAirportNm());
                System.out.println("도착지 : "+item.getArrAirportNm());
                System.out.println("출발시간 : "+item.getDepPlandTime());
                System.out.println("도착시간 : "+item.getArrPlandTime());
                System.out.println("이코노미가격 : "+item.getEconomyCharge());
                System.out.println();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
