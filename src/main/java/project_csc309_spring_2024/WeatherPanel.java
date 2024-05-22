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
  private PropertyReader pr = new PropertyReader("weather.properties");

    private Font customFont;


    public WeatherPanel() {
    setBackground(Color.black);
    GameData.getInstance().addPropertyChangeListener(this);
  }

  @Override
    protected void paintComponent(Graphics g) {
      this.customFont = GameData.getInstance().getCustomFont();
      super.paintComponent(g);
        if (locKey == null) {
            g.setFont(customFont.deriveFont(16f));  // use custom font here
            g.setColor(Color.white);
            g.drawString("Press buttons on the world map to display scene preview.", 0, 30);
        }
        
    }

  private void updateWeather(String loc) {
    removeAll();
    try {
        if (locKey != null) {
            double value = getTemperature();
            JLabel label = new JLabel(loc + ": " + value + "°F");
            label.setFont(customFont.deriveFont(50f));  // use custom font here
            label.setForeground(Color.RED);
            add(label);
        } else {
            JLabel waitingLabel = new JLabel("Waiting for location...");
            waitingLabel.setFont(customFont.deriveFont(20f));  // use custom font here
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

    String urlStr = pr.getDescription("URL")
      + locKey
      + "?apikey=" + pr.getDescription("KEY");

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
    return ((9/5) * c) + 32;
  }

  @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("sceneButtonPressed".equals(evt.getPropertyName())) {
            switch ((String) evt.getNewValue()) {
                case "Moon": 
                    locKey = "1-337853_1_AL"; // Mauna Loa, Hawaii
                    break;
                case "North Pole":
                    locKey = "336714"; // North Pole, Alaska
                    break;
                case "CSC 309":
                    locKey = "331999"; // SLO, California
                    break;
                case "Brazil":
                    locKey = "45449"; // Rio de Janeiro, Brazil
                    break;
            }
            updateWeather((String) evt.getNewValue());
        }
    }
}