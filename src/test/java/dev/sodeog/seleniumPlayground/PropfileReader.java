package dev.sodeog.seleniumPlayground;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * TO DO: Implement Lombok
 */

public class PropfileReader {
	static Properties prop = new Properties();

    public static String getUserData(String userDetailsToken) {
        Map<String, String> env = System.getenv();
        String environment = env.get("ENV");

        try {
            prop.load(new FileInputStream("./resources/" + environment + ".properties"));
            System.getProperty("user.dir");
            System.out.println("userId: " + prop.getProperty("userId"));
//            prop.getProperty("userId: " + )
//            this.getClass().getClassLoader().
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found\n"
                    + e.getMessage()
                    + "\n" + System.getProperty("user.dir"));
        }
//        System.out.println("user.dir: " + );
        return prop.getProperty(userDetailsToken);
    }

    public static String getSetting(String settingToken) {
        try {
            prop.load(new FileInputStream("src/test/resources/testdata/DefaultSettings.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        return prop.getProperty(settingToken);
    }

}
