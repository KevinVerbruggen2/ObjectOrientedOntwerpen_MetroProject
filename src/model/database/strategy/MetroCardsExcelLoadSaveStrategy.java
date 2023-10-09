package model.database.strategy;

import model.MetroCard;
import model.database.utilities.ExcelLoadSaveTemplate;

/* Authors: Arno Robeys */
public class MetroCardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {
    @Override
    public MetroCard maakObject(String[] tokens) {
        return new MetroCard(Integer.parseInt(tokens[0]),tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    public Integer getKey(String[] tokens) {
        return Integer.parseInt(tokens[0]);
    }
}
