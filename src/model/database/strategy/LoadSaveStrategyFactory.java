package model.database.strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/* Authors: Nino Verstraeten, Arno Robeys */
public class LoadSaveStrategyFactory {

    private static LoadSaveStrategyFactory instance = null;
    private final Properties properties = new Properties();
    private final String LOADSAVESTRATEGY;
    private static LoadSaveStrategy loadSaveStrategy;

    private LoadSaveStrategyFactory() {
        loadProperties();
        LOADSAVESTRATEGY = properties.getProperty("settings");
    }

     public static LoadSaveStrategyFactory getInstance() {
        if (instance == null) {
            instance = new LoadSaveStrategyFactory();
        }
        return instance;
     }

    /* String format can be "txt" or "xls" */
    public LoadSaveStrategy createLoadSaveStrategy() {
        LoadSaveStrategy instance = null;
        LoadSaveStrategyEnum classType = LoadSaveStrategyEnum.valueOf(LOADSAVESTRATEGY);
        try{
            Class<?> clazz = classType.getClassName();
            Constructor<?> object = clazz.getConstructor();
            instance = (LoadSaveStrategy) object.newInstance();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return instance;
    }

    public void saveStrategy(String strategy) {
        loadProperties();
        try {
            properties.setProperty("settings", strategy);
            FileOutputStream os = new FileOutputStream("src/bestanden/config.properties");
            properties.store(os, "settings");
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadProperties() {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("src/bestanden/config.properties"));
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
