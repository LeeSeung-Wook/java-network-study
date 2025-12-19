package com.mtcoding.port;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 책임 : 저장소(다른 사람 서버, 하드디스크, 데이터베이스)에서 데이터 가져오기
public class PortRepository {

    //책임 : 통신해서 다운로드하는 책임
    public String download(String site) throws Exception{

        // 1. 소켓 연결완료
        URL url = new URL(site);
        HttpURLConnection socket = (HttpURLConnection)url.openConnection();

        // 2. 읽기 버퍼 연결
        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        // 3. 다운로드
        String json = "";
        while(true){
            String line = br.readLine(); // 값이 없으면 null을 준다.
            if(line==null)
                break;
            json = json + line;
        }
        return json;
    }
}
