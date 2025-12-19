package com.mtcoding.ex11;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostNetworkApp {
    public static void main(String[] args) {
        try {
            Repository repo = new Repository();
            String json = repo.download("https://jsonplaceholder.typicode.com/posts/1");
            Gson gson = new Gson();
            Post p = gson.fromJson(json, Post.class);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
