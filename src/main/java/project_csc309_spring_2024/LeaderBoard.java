package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;
import com.opencsv.*;

public class LeaderBoard {

    public LeaderBoard() {
        //getLeaderboard();
    }

    public static void getLeaderboard() throws Exception {
        String objectURL = "https://mathmadness.s3.amazonaws.com/leaderboard.csv";
        URL url = new URL(objectURL);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String response = "";
        String inputLine = "";
        while ((inputLine = reader.readLine()) != null) {
            response += inputLine;
        }
        inputStream.close();
        parse(response);
    }

    private static void parse(String response) {
        System.out.println(response);
    }

    public static void main(String[] args) throws Exception {
        getLeaderboard();
    }


}
