package model.database.strategy;

/* Authors: Gerald Waerseggers, Arno Robeys, Nino Verstraeten*/
public enum LoadSaveStrategyEnum {
    TXTMetroCard("MetroCardsTekstLoadSaveStrategy"),
    XLSMetroCard("MetroCardsExcelLoadSaveStrategy");

    private final String name;

    LoadSaveStrategyEnum(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    public Class<?> getClassName() throws ClassNotFoundException {
        return Class.forName("model.database.strategy."+ getName());
    }

}
