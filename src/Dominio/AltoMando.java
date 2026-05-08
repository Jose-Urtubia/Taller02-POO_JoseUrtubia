package Dominio;

import java.util.ArrayList;
import java.util.List;

public class AltoMando {
	private int indice;
	private String nombre;
	private List<Pokemon> equipoPokemon = new ArrayList<Pokemon>();
	private boolean estado; 

	public AltoMando(int indice, String nombre) {
		this.indice = indice;
		this.nombre = nombre;
		this.estado = true;
	}
	
	public void ingresarPokemon(Pokemon este) {
		equipoPokemon.add(este);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Pokemon entregarPokemon(int indice) {
		return equipoPokemon.get(indice);
	}
	
	public int getCantidadPokemones() {
		return equipoPokemon.size();
	}
	
	public boolean verEstado() {
		return estado;
	}
	
	public void cambiarEstado() {
		this.estado = false;
	}

	@Override
	public String toString() {
		String txtEstado = estado ? "Sin derrotar" : "Derrotado";
		return indice + ") Alto Mando " + nombre + " - Estado: " + txtEstado;
	}
}