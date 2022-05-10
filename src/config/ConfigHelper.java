package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper
{
    private static ConfigHelper configHelper;
    private final Properties configProp = new Properties();

    private ConfigHelper()
    {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config/config.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigHelper getInstance() {
        if (configHelper == null) {
            configHelper = new ConfigHelper();
        }
        return configHelper;
    }

    public int getInt(String key){
        return Integer.parseInt(configProp.getProperty(key));
    }

    public void setInt(String key, Integer value){
        configProp.setProperty(key, value.toString());
    }

    public String getStr(String key){
        return configProp.getProperty(key);
    }
}