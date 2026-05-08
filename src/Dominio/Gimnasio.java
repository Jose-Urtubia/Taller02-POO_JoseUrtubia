package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
	private int indice;
	private String nombre;
	private boolean estado;
	private List<Pokemon> equipoPokemon= new ArrayList<Pokemon>();
	public Gimnasio(int indice, String nombre, String estadoEnTXT) {

		this.indice = indice;
		this.nombre = nombre;
		if (estadoEnTXT.equalsIgnoreCase("Sin derrotar")) {
			this.estado = true;
	} else {
		this.estado=false;
	}
	
}
	public String getNombre() {
		return nombre;
	}
	public void ingresarPokemon(Pokemon pokemon) {
		equipoPokemon.add(pokemon);
	}
}
