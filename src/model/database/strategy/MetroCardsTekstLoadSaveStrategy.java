package model.database.strategy;

import model.MetroCard;
import model.database.utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;


/* Authors: Arno Robeys  */
public class MetroCardsTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy {


    @Override
    public MetroCard maakObject(String[] tokens) {
        return new MetroCard(Integer.parseInt(tokens[0]),tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    public Integer getKey(String[] tokens) {
        return Integer.parseInt(tokens[0]);
    }
}
