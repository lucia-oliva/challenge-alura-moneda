package com.alura.conversor;

import com.google.gson.annotations.SerializedName;

public class Monedas {

    @SerializedName("conversion_rates")
    private Conversion tasasDeConversion;
    @SerializedName("base_code")
    private String codigoMonedaBase;

    public Conversion getConversion() {
        return tasasDeConversion;
    }

    public String getMoneda() {
        return codigoMonedaBase;
    }

    public double convertir(double cantidad, int opcionMonedaDestino) {
        return switch (opcionMonedaDestino) {
            case 1 -> tasasDeConversion.getUSD() * cantidad;
            case 2 -> tasasDeConversion.getARS() * cantidad;
            case 3 -> tasasDeConversion.getBRL() * cantidad;
            case 4 -> tasasDeConversion.getCOP() * cantidad;
            case 5 -> tasasDeConversion.getCLP() * cantidad;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Monedas{" +
                "conversion=" + tasasDeConversion +
                ", moneda='" + codigoMonedaBase + '\'' +
                '}';
    }
}

class Conversion {
    private double USD;
    private double ARS;
    private double BRL;
    private double COP;
    private double CLP;

    @Override
    public String toString() {
        return  "1- USD=" + USD +
                ",\n2- ARS=" + ARS +
                ",\n3- BRL=" + BRL +
                ",\n4- COP=" + COP +
                ",\n5- CLP=" + CLP;
    }

    public double getUSD() {
        return USD;
    }

    public double getARS() {
        return ARS;
    }

    public double getBRL() {
        return BRL;
    }

    public double getCOP() {
        return COP;
    }

    public double getCLP() {
        return CLP;
    }
}
