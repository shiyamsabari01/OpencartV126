package utilities;

import java.util.Properties;

public class ReadConfigProperties {
    Properties properties;

    public void loadProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Constants.APP_URL = properties.getProperty("URL");
        Constants.BROWSER = properties.getProperty("browser");
        Constants.USERNAME= properties.getProperty("username");
        Constants.PASSWORD= properties.getProperty("password");
        Constants.EXECUTION_ENV= properties.getProperty("execution_env");
        Constants.GRID_URL= properties.getProperty("grid_url");
        Constants.OS=properties.getProperty("os");
    }
}
