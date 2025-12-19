package com.mtcoding.ex11;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class User {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Company company;

    @Getter
    @Setter
    public static class Company{
        private String name;
        private String catchPhrase;
        private String bs;
    }
}

public class UserApp {
    public static void main(String[] args) {
        try {
            // 1. 다운로드 Repository download()
            Repository rp = new Repository();
            String json = rp.download("https://jsonplaceholder.typicode.com/users/1");
            // 2. 다운로드 확인
            //System.out.println(1);
            // 3. 오브젝트로 변환 (json -> user) - gson필요
            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);
            // 4. 변환 확인
            //System.out.println(1);
            // 5. 콘솔에 유저 정보 확인
            System.out.print("[ ");
            System.out.print("아이디 : "+user.getId()+", ");
            System.out.print("이름 : "+user.getName()+", ");
            System.out.print("유저이름 : "+user.getUsername()+", ");
            System.out.print("이메일 : "+user.getEmail()+", ");
            System.out.print("전화번호 : "+user.getPhone()+", ");
            System.out.println("웹사이트 : "+user.getWebsite()+", ");
            System.out.print("company.name : "+user.getCompany().getName()+", ");
            System.out.print("company.catchphrase : "+user.getCompany().getCatchPhrase()+", ");
            System.out.println("company.bs : "+user.getCompany().getBs()+" ]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
