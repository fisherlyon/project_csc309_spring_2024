package project_csc309_spring_2024;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
import org.json.*;

public class LeaderBoard {

    private final String BUCKET_NAME = "mathmadness";
    private final String OBJECT_KEY = "leaderboard.json";
    private final Region REGION = Region.US_EAST_1;

    public LeaderBoard() {
        try {
            getLeaderboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLeaderboard() throws Exception {
        // here is the link to my s3 bucket!
        String objectURL = "https://mathmadness.s3.amazonaws.com/leaderboard.json";
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
        GameData.getInstance().setlbes(parse(response));
    }

    public void updateLeaderboard(String newContent) {
        // save content to a temp file
        File tempFile = null;
        try {
            tempFile = File.createTempFile("leaderboard", ".json");
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

    private ArrayList<LeaderBoardEntry> parse(String response) {
        ArrayList<LeaderBoardEntry> lbes = new ArrayList<>();
        JSONArray jsonResponse = new JSONArray(response);
        for (Object obj : jsonResponse) {
            JSONObject jsonObj = (JSONObject) obj;
            LeaderBoardEntry lbe = 
                new LeaderBoardEntry(Integer.parseInt(jsonObj.getString("pos")), 
                                     jsonObj.getString("name"), 
                                     Integer.parseInt(jsonObj.getString("score")));
            lbes.add(lbe);
        }
        return lbes;
    }

    public String formatlbes(ArrayList<LeaderBoardEntry> lbes) {
        String formattedString = "Position\tName\tScore\n";
        for (LeaderBoardEntry lbe : lbes) {
            formattedString += lbe.toString() + "\n";
        }
        return formattedString;
    }
}
