package br.com.CONVERSOR.MODELOS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiExchange {
    private String base_code;
    private String target_code;
    private double amount;

    public ApiExchange(Moedas valores){
        this.base_code = valores.moedaEscolhida();
        this.target_code = valores.moedaConverter();
        this.amount = valores.cotacao();
    }

    public String buscaMoeda(){
        String pKey = "5a02b34a9a306513aa9e235e";
        String url = "https://v6.exchangerate-api.com/v6/"+pKey+"/pair/"+base_code+"/"+target_code+"/"+amount+"/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Erro ao buscar dados da API: " + e.getMessage();
        }

        if (response == null) {
            return "Erro: resposta da API é nula.";
        }

        var json = response.body();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Moedas moedas = gson.fromJson(json, Moedas.class);
        String mensagemUsuario = "A conversão de: " + moedas.moedaEscolhida() + " para: " + moedas.moedaConverter() + " é: " + moedas.cotacao();
        return mensagemUsuario;
    }
}