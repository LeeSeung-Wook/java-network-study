package com.mtcoding.port;

/**
 * https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=95nXeJ6mCMc03egZXX5bl%2FLG578C4gLz5D5PI3sivjeG%2FjECBCrwyj47E8wCrNfzb4A%2FHfdjmjoU0ELGmLUgTQ%3D%3D&pageNo=1&numOfRows=10&_type=json&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20251220
 */
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        private Items items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @Setter
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @Setter
    public static class Item {
        private String airlineNm;
        private String arrAirportNm;
        private Long arrPlandTime;   // int 사용 불가
        private String depAirportNm;
        private Long depPlandTime;   // int 사용 불가
        private Integer economyCharge;
        private Integer prestigeCharge;
        private String vihicleId;
    }
}

