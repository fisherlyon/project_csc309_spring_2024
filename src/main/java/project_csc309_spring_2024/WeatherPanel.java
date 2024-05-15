package project_csc309_spring_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;
import org.json.*;

public class WeatherPanel extends JPanel implements PropertyChangeListener {

  private String locKey = null;

  public WeatherPanel() {
    setBackground(Color.GRAY);
    setLayout(new GridLayout(1, 1));
    JLabel blankLabel = new JLabel("Waiting for location...");
    blankLabel.setFont(new Font("Arial", 1, 20));
    blankLabel.setForeground(Color.BLACK);
    add(blankLabel);
    GameData.getInstance().addPropertyChangeListener(this);
  }

  private void updateWeather() {
    removeAll();
    try {
        if (locKey != null) {
            double value = getTemperature();
            JLabel label = new JLabel(" " + value);
            label.setFont(new Font("Arial", 1, 50));
            label.setForeground(Color.RED);
            add(label);
        } else {
            JLabel waitingLabel = new JLabel("Waiting for location...");
            waitingLabel.setFont(new Font("Arial", 1, 20));
            waitingLabel.setForeground(Color.BLACK);
            add(waitingLabel);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    revalidate();
    repaint();
  }

  private double getTemperature() throws Exception {

    String apiKey = "BguRW30F9IVjlLAu73qaneIAf3BtkGug";
    String urlStr = "http://dataservice.accuweather.com/currentconditions/v1/"
      + locKey
      + "?apikey=" + apiKey;

    URL url = new URL(urlStr);
    URLConnection connection = url.openConnection();
    InputStream inputStream = connection.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    String response = "";
    String inputLine = "";
    while ((inputLine = reader.readLine()) != null) {
      response += inputLine;
    }
    inputStream.close();
    return getFahrenheit(parse(response));
  }

  private double parse(String response) {
    System.out.println(response);
    JSONArray jsonResponse = new JSONArray(response);
    JSONObject currentWeather = jsonResponse.getJSONObject(0);
    JSONObject termperatureObj = currentWeather.getJSONObject("Temperature");
    JSONObject metricObj = termperatureObj.getJSONObject("Metric");
    return metricObj.getDouble("Value");
  }

  private double getFahrenheit(double c) {
    return (c * (9/5)) + 32;
  }

  @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("sceneButtonPressed".equals(evt.getPropertyName())) {
            switch ((String) evt.getNewValue()) {
                case "Moon": 
                    locKey = "317705"; // based on Pamukkale, Turkey
                    break;
                case "North Pole":
                    locKey = "336714";
                    break;
                case "CSC 309":
                    locKey = "331999"; // based on SLO, California
                    break;
                case "Brazil":
                    locKey = "1122443";
                    break;
            }
            updateWeather();
        }
    }
}