package model.database.utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/* Authors: Arno Robeys */
public abstract class ExcelLoadSaveTemplate<K,V> {
    public ExcelPlugin excelPlugin = new ExcelPlugin();

    private final File file = new File("src/bestanden/metrocards.xls");

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);

    public final Map<K, V> load() throws IOException, BiffException {
        Map<K, V> map = new TreeMap<>();

        ArrayList<ArrayList<String>> data = excelPlugin.read(file);
        for (ArrayList<String> value : data) {
            String[] valueArray = value.toArray(new String[0]);
            K key = getKey(valueArray);
            V object = maakObject(valueArray);
            map.put(key, object);
        }
        return map;
    }

    public final void save(Map<K,V> map) throws WriteException, IOException, BiffException {
        excelPlugin.write(file,mapToListArrayList(map));
    }

    private ArrayList<ArrayList<String>> mapToListArrayList(Map<K,V> map){
        ArrayList<ArrayList<String>> lijst = new ArrayList<>();
        for (V v : map.values()) {
            ArrayList<String> waardeOmgezetNaarArrayList = new ArrayList<String>(Arrays.asList(v.toString().split(";")));
            lijst.add(waardeOmgezetNaarArrayList);
        }
        return lijst;
    }
}
