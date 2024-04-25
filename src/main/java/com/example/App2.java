package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<String> conversoes = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println("Bem vindo ao programa Conversor de moedas Java");

        do {
            System.out.println("Qual moeda gostaria de converter ?");

            String moeda1 = Conversor.escolherMoeda();

            System.out.println("Para qual moeda gostaria de converter ?");

            String moeda2 = Conversor.escolherMoeda();

            float valorInicial = Conversor.valorInicial();
                    
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/699c5288a384c71e109fada0/latest/" + moeda1))
                    .build();

                HttpResponse<String> response = client
                    .send(request, BodyHandlers.ofString());

                    
                String json = response.body();

                Conversor test2 = gson.fromJson(json, Conversor.class);
                Conversor test = new Conversor(moeda1, moeda2, valorInicial, test2.getConversion_rates());
                String escrita = gson.toJson(test);

                conversoes.add(escrita);

                System.out.println(test);

        } while (Conversor.repetir());

        FileWriter moedasJson = new FileWriter("Filmes.json");
                    moedasJson.write(gson.toJson(conversoes));
                    moedasJson.close();

        System.out.println("Obrigado por usar o programa Conversor de moedas Java.");
        System.out.println("Tenha um bom dia.");
        System.out.println("Programa encerrado.");

    }
}
