package project_csc309_spring_2024;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Collects data from a properties file.
 * 
 * @author Fisher Lyon
 */
public class PropertyReader {

    private Properties properties;

    public PropertyReader(String filename) {

        properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (input == null) {
                System.out.printf("Error: Unable to find file, %s\n", filename);
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDescription(String property) {
        return properties.getProperty(property);
    }
}
