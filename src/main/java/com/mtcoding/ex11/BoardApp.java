package com.mtcoding.ex11;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// DTO
@Getter @Setter
class Common {
    private Integer status;
    private String msg;
    private List<Body> body;

    @Getter @Setter
    public static class Body{
        private Integer id;
        private String title;
        private String content;
        private String author;
    }
}

public class BoardApp {
    public static void main(String[] args) {
        try {
            // 1. 다운로드
            Repository rp = new Repository();
            String json = rp.download("http://192.168.0.99:8080/api/boards");

            // 2. 다운로드 확인
            //System.out.println(1);

            // 3. Common 클래스 작성

            // 4. 파싱
            Gson gson = new Gson();
            Common com = gson.fromJson(json, Common.class);

            // 5. 파싱 확인
            //System.out.println(1);

            // 6. 출력
            System.out.println(com.getStatus());
            System.out.println(com.getMsg());
            for (int i = 0; i < com.getBody().size(); i++) {
                System.out.print("아이디: " + com.getBody().get(i).getId()+", ");
                System.out.print("제목: " + com.getBody().get(i).getTitle()+", ");
                System.out.print("컨텐츠: " + com.getBody().get(i).getContent()+", ");
                System.out.println("저자(?): " + com.getBody().get(i).getAuthor());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
