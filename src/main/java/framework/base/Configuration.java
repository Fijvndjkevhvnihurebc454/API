package framework.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final String envPropertiesPath = "src/test/resources/env.properties";
    private static String url;
    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static void getResourcesFromPropertyFile() throws IOException {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            inputStream = new FileInputStream(envPropertiesPath);
            properties.load(inputStream);
            url = properties.getProperty("url");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

    public static String getUrl() {
        LOG.info(String.format("Getting current URL %s", url));
        return url;
    }

}
