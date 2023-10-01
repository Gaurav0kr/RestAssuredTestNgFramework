package com.spotify.auth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader()
    {
        properties = PropertyUtils.propertyLoader("C:\\Users\\Admin\\IdeaProjects\\RestAssuredFramework\\src\\test\\resources\\config.properties");
    }
    public static ConfigLoader getInstance()
    {
        if(configLoader ==null)
            configLoader = new ConfigLoader();
        return configLoader;
    }
    public String getgrant_type()
    {
        String prop = properties.getProperty("grant_type");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property grant_type is not specified in config.properties File");
    }
    public String getrefresh_token()
    {
        String prop = properties.getProperty("refresh_token");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property refresh_token is not specified in config.properties File");
    }
    public String getClientId()
    {
        String prop = properties.getProperty("client_id");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property client id is not specified in config.properties File");
    }
    public String getclient_secret()
    {
        String prop = properties.getProperty("client_secret");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property client_secret is not specified in config.properties File");
    }
    public String getuser_id()
    {
        String prop = properties.getProperty("user_id");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property user_id is not specified in config.properties File");
    }
    public String getplayList_Id()
    {
        String prop = properties.getProperty("playList_Id");
        if(prop !=null)
            return prop;
        else
            throw new RuntimeException("Property playList_Id is not specified in config.properties File");
    }

}
