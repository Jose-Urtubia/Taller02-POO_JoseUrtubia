package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Habitat {
	private String nombre;
	private int indice;
	private List<Pokemon> listaPokemons = new ArrayList<Pokemon>();
	public Habitat(String nombre,int indice) {
		this.indice =indice;
		this.nombre = nombre;
	}
	public List<Pokemon> getListaPokemons() {
		return listaPokemons;
	}
	public void setListaPokemons(List<Pokemon> listaPokemons) {
		this.listaPokemons = listaPokemons;
	}
	public String getNombre() {
		return nombre;
	}
	public void ingresarPokemons(Pokemon este) {
		listaPokemons.add(este);
	}
	public int getIndice() {
		return indice;
	}
	public void cambiarIndice(int nuevo) {
		indice = nuevo;
	}
	public Pokemon RandomPokemon() {
		
		Random random = new Random();
		
		double numeroAzar= random.nextDouble();
		
		double sumaProbabilidad=0.0;
		
		for (Pokemon pokemon : listaPokemons) {
			 sumaProbabilidad += pokemon.getPorcentajeAparicion();
			 
			 if (numeroAzar<=sumaProbabilidad) {
				 System.out.println("¡Ha aparecido un "+pokemon.getNombre());
				 return pokemon;
			 }
		}
		return null;
		
	}
}
