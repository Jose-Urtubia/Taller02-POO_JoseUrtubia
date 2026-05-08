package Dominio;

import java.util.ArrayList;
import java.util.List;

public class AltoMando {
	private int indice;
	private String nombre;
	private List<Pokemon> equipoPokemon= new ArrayList<Pokemon>();
	public AltoMando(int indice, String nombre) {

		this.indice = indice;
		this.nombre = nombre;
	}
	public void ingresarPokemon(Pokemon este) {
		equipoPokemon.add(este);
	}
	public String getNombre() {
		return nombre;
	}
	
}
