package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        String[] opcoesMoeda = {"USD", "EUR", "JPY", "ARS", "BRL"};
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Scanner ler = new Scanner(System.in);
        boolean moeda1Escolhida = false;
        boolean moeda2Escolhida = false;
        String moeda1 = "";
        String moeda2 = "";
        float valorInicial = 0;

            System.out.println("Bem vindo ao app Conversor de moedas Java");
            System.out.println("Qual moeda gostaria de converter ?");

            moeda1 = Conversor.escolherMoeda();

            System.out.println("Para qual moeda gostaria de converter ?");

            moeda2 = Conversor.escolherMoeda();

            System.out.println("Qual valor gostaria de converter ?");
            valorInicial = ler.nextFloat();

                
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/699c5288a384c71e109fada0/latest/" + moeda1))
                    .build();

                HttpResponse<String> response = client
                    .send(request, BodyHandlers.ofString());

                FileWriter moedasJson = new FileWriter("Filmes.json");
                    moedasJson.write(response.body());
                    moedasJson.close();
                
                String json = response.body();

                Conversor test2 = gson.fromJson(json, Conversor.class);
                Conversor test = new Conversor(moeda1, moeda2, valorInicial, test2.conversion_rates);

                System.out.println(test);
    }
}
