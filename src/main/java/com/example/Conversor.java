package com.example;

import java.text.DecimalFormat;

import com.google.gson.JsonObject;

public class Conversor {
    
    JsonObject conversion_rates;
    String moeda1;
    String moeda2;
    float valorInicial;
    float valorConvertido;


    public Conversor (String moeda1, String moeda2, float valorInicial, JsonObject conversion_rates) {
        setConversion_rates(conversion_rates);
        setMoeda1(moeda1);
        setMoeda2(moeda2);
        setValorInicial(valorInicial);
        converterMoeda(valorInicial, conversion_rates.get(moeda2).getAsFloat());
    }

    public Object getConversion_rates() {
        return conversion_rates;
    }
    private void setConversion_rates(Object conversion_rates) {
        this.conversion_rates = (JsonObject) conversion_rates;
    }
    

    public String getMoeda1() {
        return moeda1;
    }

    private void setMoeda1(String moeda1) {
        this.moeda1 = moeda1;
    }


    public String getMoeda2() {
        return moeda2;
    }

    private void setMoeda2(String moeda2) {
        this.moeda2 = moeda2;
    }


    public float getValorInicial() {
        return valorInicial;
    }

    private void setValorInicial(float valorInicial) {
        this.valorInicial = valorInicial;
    }

    public float getValorConvertido () {
        return this.valorConvertido;
    }

    private void setValorConvertido (float valorConvertido) {
        this.valorConvertido = valorConvertido;
    }


    public void converterMoeda (float valor1, float valor2) {
        float resultado = valor1 * valor2;
        String result = String.format("%.2f", resultado);
        setValorConvertido(Float.parseFloat(result.replace(',', '.')));
    }

    @Override
    public String toString () {
        return "Valor inicial em " + getMoeda1() + ": " + getValorInicial() + 
        "\nValor final em " + getMoeda2() + ": " + getValorConvertido();
    }

}
