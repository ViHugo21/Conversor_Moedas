package com.example;

import java.util.Scanner;

import com.google.gson.JsonObject;

public class Conversor {
    
    private JsonObject conversion_rates;
    private String moeda1;
    private String moeda2;
    private float valorInicial;
    private float valorConvertido;


    public Conversor (String moeda1, String moeda2, float valorInicial, JsonObject conversion_rates) {
        setMoeda1(moeda1);
        setMoeda2(moeda2);
        setValorInicial(valorInicial);
        converterMoeda(valorInicial, conversion_rates.get(moeda2).getAsFloat());
    }

    public JsonObject getConversion_rates() {
        return conversion_rates;
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

    static String escolherMoeda () {
        boolean moedaEscolhida = false;
        String[] opcoesMoeda = {"USD", "EUR", "JPY", "ARS", "BRL"};
        String moeda = "USD";
        @SuppressWarnings("resource")
        Scanner ler = new Scanner(System.in);
        
        try {
            do {
                
                System.out.println("Dólar americano (USD)");
                System.out.println("Euro (EUR)");
                System.out.println("Iene japonês (JPY)");
                System.out.println("Peso argentino (ARS)");
                System.out.println("Real brasileiro (BRL)");
                System.out.println("Por favor digite apenas a sigla da moeda de sua escolha");
                moeda = ler.nextLine();
                moeda = moeda.toUpperCase();
                for(String m: opcoesMoeda) {
                    if (moeda.equals(m)) {
                        moedaEscolhida = true;
                        break;
                    } 
                }
            } while (!moedaEscolhida);

            return moeda;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao selecionar a moeda");
            System.out.println("Será atribuido a escolha padrão USD");
            return moeda;
        }
            
    }

    static float valorInicial () {
        
        @SuppressWarnings("resource")
        Scanner ler = new Scanner(System.in);
        
        try {
            System.out.println("Qual valor gostaria de converter ?");
            return Float.parseFloat(ler.nextLine().replace(',', '.'));   
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao receber o valor");
            System.out.println("O valor será definido para 1");
            return 1;
        }
    }

    static boolean repetir () {
        
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        String resposta = "";
        
        try {
            System.out.println("Deseja fazer outra conversão ? S/N");
            resposta = input.nextLine().toLowerCase();
            switch (resposta) {
                case "sim":
                    return true;
                case "s":
                    return true;
                case "n":
                    return false;
                case "não":
                    return false;
                case "nao":
                    return false;
                default:
                    System.out.println("Desculpe, não entendi sua resposta, encerrando programa");
                    return false;
            }
        } catch (Exception e) {
            System.out.println("Desculpe ocorreu um erro, encerrando repetição do programa");
            System.out.println(resposta);
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return false;
        }
        
    }

    @Override
    public String toString () {
        return "Valor em " + getMoeda1() + ": " + getValorInicial() + 
        "\nValor em " + getMoeda2() + ": " + getValorConvertido();
    }

}
