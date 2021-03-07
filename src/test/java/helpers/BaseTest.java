package helpers;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.*;
import static java.lang.System.setProperty;

public class BaseTest {

    @BeforeAll
    static void setup() {
        startMaximized=true;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/properties/common.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setProps(properties);
    }

    private static void setProps(Properties properties) {
        properties.forEach((key, value) -> setProperty((String) key, (String) value));
    }
}