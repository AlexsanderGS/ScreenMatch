package br.com.alura.ScreenMatch.principal;

import br.com.alura.ScreenMatch.Service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);

    private ConsumoAPI consumo = new ConsumoAPI();

    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=df04059f";

    public void exibeMenu(){
        System.out.println("Digite o nome da série desejada: ");
        var nomeSerie = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
    }
}
