package model.database.strategy;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.MetroCard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/* Authors: Arno Robeys */
public interface LoadSaveStrategy<K, V> {

    Map<K,V> load() throws IOException, BiffException;
    void save(Map<K,V> map) throws IOException, BiffException, WriteException;
}
