package br.com.alura.ScreenMatch.principal;

import br.com.alura.ScreenMatch.Service.ConsumoAPI;
import br.com.alura.ScreenMatch.Service.ConverteDados;
import br.com.alura.ScreenMatch.model.DadosEpisodios;
import br.com.alura.ScreenMatch.model.DadosSerie;
import br.com.alura.ScreenMatch.model.DadosTemporadas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=df04059f";

    public void exibeMenu(){
        System.out.println("Digite o nome da série desejada: ");
        var nomeSerie = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporadas> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
			temporadas.add(dadosTemporadas);
		}
		temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodios> episodiosTemporadas = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporadas.size(); j++);
//            System.out.println(episodiosTemporadas.get(i).titulo());
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodios> dadosEpisodios  = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 Episodios da série escolhida: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avalicao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodios::avalicao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

}
