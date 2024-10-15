package by.farshyniou.jdbc.utils.impl;

import by.farshyniou.jdbc.exceptions.PropertyNotFoundException;
import by.farshyniou.jdbc.utils.ConfigFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReaderImpl implements ConfigFileReader {
    private Properties properties;
    private final String configFilePath = "./project-utils/src/main/resources/config.properties";
    private static final Logger logger = LoggerFactory.getLogger(ConfigFileReaderImpl.class);

     public ConfigFileReaderImpl() {
         File configFile = new File(configFilePath);
         try (FileInputStream configFileReader = new FileInputStream(configFile)) {
             properties = new Properties();
             properties.load(configFileReader);
         } catch (FileNotFoundException exception) {
             logger.warn("Can't find config.properties file, {}", exception.getMessage());
         } catch (IOException ioException) {
             logger.warn("Exception occurred during reading config.properties file, {}", ioException.getMessage());
         }
     }

    public String getJDBCUrl() {
        String jdbcUrl = properties.getProperty("jdbc.url");
        if (jdbcUrl != null) {
            return jdbcUrl;
        } else {
            throw new PropertyNotFoundException("Can't find jdbc.url property in config.properties file!");
        }
    }

    public String getJDBCName() {
        String jdbcName = properties.getProperty("jdbc.name");
        if (jdbcName != null) {
            return jdbcName;
        } else {
            throw new PropertyNotFoundException("Can't find jdbc.name property in config.properties file!");
        }
    }

    public String getJDBCPassword() {
        String jdbcPassword = properties.getProperty("jdbc.password");
        if (jdbcPassword != null) {
            return jdbcPassword;
        } else {
            throw new PropertyNotFoundException("Can't find jdbc.password property in config.properties file!");
        }
    }

    public int getJDBCPoolSize() {
         String poolSize = properties.getProperty("jdbc.connection_pool_size");
         if (poolSize != null) {
            return Integer.parseInt(poolSize);
         } else {
             throw new PropertyNotFoundException("Can't find jdbc.connection_pool_size property in config.properties file!");
         }
    }
}
