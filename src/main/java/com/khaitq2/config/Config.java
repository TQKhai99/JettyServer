package com.khaitq2.config;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static final String INI_FILE = "config.ini";
    private static Config instance;
    private Map<String, String> config;

    private Config(){
        try {
            Ini ini = new Ini(new File(INI_FILE));
            config = new HashMap<>();
            config.put("hPort", ini.get("HTTPServer", "port"));
            config.put("tPort", ini.get("ThriftService", "port"));
            config.put("tHostname", ini.get("ThriftService", "hostname"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance(){
        return (instance == null) ? new Config() : instance;
    }

    public Map<String, String> getConfig(){
        return config;
    }

//    public static void WriteIni(){
//        try {
//            Wini ini = new Wini(new File(INI_FILE));
//            ini.put("HTTPServer", "port", 8080);
//            ini.put("ThriftService", "port", 9090);
//            ini.put("ThriftService", "hostname", "localhost");
//            ini.store();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void main(String[] args) {
//        WriteIni();
//    }

}
