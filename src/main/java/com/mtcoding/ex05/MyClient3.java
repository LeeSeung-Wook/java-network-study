package com.mtcoding.ex05;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",20000);

            Scanner sc = new Scanner(System.in);

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            Scanner bs = new Scanner(socket.getInputStream());

            while(true){
                String keyboardData = sc.nextLine();
                pw.println(keyboardData); // ln이 \n을 넣어주고, autoFlush가 된다.
                String recv = bs.nextLine();
                System.out.println("서버로 부터 받은 메시지 : " + recv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
