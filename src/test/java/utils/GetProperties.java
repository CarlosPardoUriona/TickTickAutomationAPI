package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties getProperties = null;
    private String tokenName;
    private String tokenValue;

    private GetProperties(){
        Properties properties = new Properties();
        String nameFile="todoly.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        if(inputStream!= null){
            try {
                properties.load(inputStream);
                tokenName=properties.getProperty("tokenName");
                tokenValue=properties.getProperty("tokenValue");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static GetProperties getInstance(){
        if (getProperties == null)
            getProperties=new GetProperties();
        return getProperties;
    }

    public String getTokenName() {
        return tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }


}
