package project_csc309_spring_2024;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;
import com.opencsv.*;

public class LeaderBoard {

    private static final String BUCKET_NAME = "mathmadness";
    private static final String OBJECT_KEY = "leaderboard.csv";
    private static final Region REGION = Region.US_EAST_1;

    public LeaderBoard() {
        //getLeaderboard();
    }

    public static void getLeaderboard() throws Exception {
        // here is the link to my s3 bucket!
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

    public static void updateLeaderboard(String oldContent, String newContent) {
        // save content to a temp file
        File tempFile = null;
        try {
            tempFile = File.createTempFile("leaderboard", ".csv");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                writer.write(newContent);
            }

            // init s3 client with anonymous credential provider (to avoid using my credentials)
            S3Client s3 = S3Client.builder()
                                  .region(REGION)
                                  .credentialsProvider(AnonymousCredentialsProvider.create())
                                  .build();

            // create a put object request, given the bucket name and object key
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                                .bucket(BUCKET_NAME)
                                                                .key(OBJECT_KEY)
                                                                .build();

            // upload the file and display a "success" message to terminal
            s3.putObject(putObjectRequest, RequestBody.fromFile(tempFile));
            System.out.println("Successfully placed " + OBJECT_KEY + " into bucket " + BUCKET_NAME);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (tempFile != null) {
                tempFile.delete(); // remove the temp file
            }
        }
    }

    private static void parse(String response) {
        System.out.println(response);
    }

    public static void main(String[] args) throws Exception {
        getLeaderboard();
    }


}
