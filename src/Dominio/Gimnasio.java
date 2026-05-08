package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Gimnasio {
	private int indice;
	private String nombre;
	private boolean estado;
	private static  String[] tiposDePokemons= new String[] {"normal","fuego", "agua", "planta", "electrico", "hielo", "lucha", "veneno", "tierra", "volador", "psiquico", "bicho", "roca", "fantasma", "dragon", "acero", "siniestro", "hada"};
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
	public boolean verEstado() {
		return estado;
	}
	public void cambiarEstado() {
		estado=!estado;
	}

	public String getNombre() {
		return nombre;
	}
	public void ingresarPokemon(Pokemon pokemon) {
		equipoPokemon.add(pokemon);
	}
	@Override
	public String toString() {
	    String textEstado = "Sin derrotar";
	    
	
	    if (this.estado == false) { 
	        textEstado = "Derrotado";
	    }
	    
	    return indice + ") " + nombre + " - Estado: " + textEstado;
	}
	public int getIndice() {
		return indice;
	}
	public Pokemon entregarPokemon(int indice) {
		return equipoPokemon.get(indice);
	}
	public int sacarIndiceTipoPokemon(Pokemon miPokemon) {
		for (int i = 0; i < tiposDePokemons.length; i++) {
			if (miPokemon.getTipo().equalsIgnoreCase(tiposDePokemons[i])) {
				return i;
			}
		}
		return 0;
	}
	public int getCantidadPokemones() {
		// TODO Auto-generated method stub
		return equipoPokemon.size();
	}
}
