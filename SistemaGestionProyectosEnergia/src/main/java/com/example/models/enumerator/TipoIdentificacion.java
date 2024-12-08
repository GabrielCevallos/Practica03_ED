package com.example.models.enumerator;

public enum TipoIdentificacion {
    CEDULA("CEDULA"), PASAPORTE("PASAPORTE"), RUC("RUC");
    private String name;
    //CONSTRUCTOR
    TipoIdentificacion(String name) {
        this.name = name;
    }
    //GETTER
    public String getName() {
        return name;
    }
}
