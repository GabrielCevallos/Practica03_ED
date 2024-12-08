package com.example.models.enumerator;

public enum Provincia {
    AZUAY("AZUAY"),
    BOLIVAR("BOLIVAR"),
    CANAR("CANAR"),
    CARCHI("CARCHI"),
    CHIMBORAZO("CHIMBORAZO"),
    COTOPAXI("COTOPAXI"),
    EL_ORO("EL ORO"),
    ESMERALDAS("ESMERALDAS"),
    GALAPAGOS("GALAPAGOS"),
    GUAYAS("GUAYAS"),
    IMBABURA("IMBABURA"),
    LOJA("LOJA"),
    LOS_RIOS("LOS RIOS"),
    MANABI("MANABI"),
    MORONA_SANTIAGO("MORONA SANTIAGO"),
    NAPO("NAPO"),
    ORELLANA("ORELLANA"),
    PASTAZA("PASTAZA"),
    PICHINCHA("PICHINCHA"),
    SANTA_ELENA("SANTA ELENA"),
    SANTO_DOMINGO_DE_LOS_TSACHILAS("SANTO DOMINGO DE LOS TSACHILAS"),
    SUCUMBIOS("SUCUMBIOS"),
    TUNGURAHUA("TUNGURAHUA"),
    ZAMORA_CHINCHIPE("ZAMORA CHINCHIPE");

    private String name;
    //CONSTRUCTOR
    Provincia(String name) {
        this.name = name;
    }
    //GETTER
    public String getName() {
        return name;
    }
}
