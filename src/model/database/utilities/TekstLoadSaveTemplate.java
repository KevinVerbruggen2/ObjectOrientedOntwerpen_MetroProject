package model.database.utilities;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;

import java.io.*;
import java.util.*;

/* Authors: Arno Robeys, Kevin Verbruggen */
public abstract class TekstLoadSaveTemplate<K, V> {

    private final File file = new File("src/bestanden/metrocards.txt");

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);

    public final Map<K,V> load() throws IOException, BiffException {
        Map<K,V> map = new TreeMap<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()){
                String[] delen = s.nextLine().split(";");
                map.put(getKey(delen), maakObject(delen));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public final void save(Map<K,V> map) throws WriteException, IOException, BiffException {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (V v : map.values()) {
                pw.println(v.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
