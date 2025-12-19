package com.mtcoding.airplane;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirInfo {
    private Response response;

    @Getter
    @Setter
    public static class Response{
        private Header header;
        private Body body;

        @Getter
        @Setter
        public static class Header{
            private String resultCode;
            private String resultMsg;
        }
        @Getter
        @Setter
        public static class Body{
            private Items items;
            private Integer numOfRows;
            private Integer pageNo;
            private Integer totalCount;

            @Getter
            @Setter
            public static class Items {
                private List<Item> item;

                @Getter
                @Setter
                public static class Item {
                    private String airlineNm;
                    private String arrAirportNm;
                    private Long arrPlandTime;
                    private String depAirportNm;
                    private Long depPlandTime;
                    private Integer economyCharge;
                    private Integer prestigeCharge;
                    private String vihicleId;
                }
            }
        }
    }
}
