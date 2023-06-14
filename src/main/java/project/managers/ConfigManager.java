package project.managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private Properties properties = new Properties();
    private FileInputStream inputStream;

    public ConfigManager(String s) throws FileNotFoundException {
        inputStream = new FileInputStream(s);
    }

    public int getInt(String s) throws IOException {
        properties.load(inputStream);
        return Integer.parseInt(properties.getProperty(s));
    }
}

