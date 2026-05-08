package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entrenador {
	private String apodo;
	private int cantPokemones;
	private boolean estado;
	private static  String[] tiposDePokemons= new String[] {"normal","fuego", "agua", "planta", "electrico", "hielo", "lucha", "veneno", "tierra", "volador", "psiquico", "bicho", "roca", "fantasma", "dragon", "acero", "siniestro", "hada"};
	private List<Pokemon> equipoPokemon= new ArrayList<Pokemon>();
	private List<Gimnasio> gimnaciosDerrotados= new ArrayList<Gimnasio>();

	
	public Entrenador(String apodo) {
		this.apodo = apodo;
		cantPokemones=0;
		estado=true;
	}
	public boolean verEstado() {
		return estado;
		
	}
	public void añadirGimnasio(Gimnasio derrotado) {
		gimnaciosDerrotados.add(derrotado);
	}
	public void cambiarEstado() {
		estado= !estado;
	}
	public List<Pokemon> getEquipoPokemon() {
		return equipoPokemon;
	}
	public void setEquipoPokemon(List<Pokemon> equipoPokemon) {
		this.equipoPokemon = equipoPokemon;
	}
	public List<Gimnasio> getGimnaciosDerrotados() {
		return gimnaciosDerrotados;
	}
	public void setGimnaciosDerrotados(List<Gimnasio> gimnaciosDerrotados) {
		this.gimnaciosDerrotados = gimnaciosDerrotados;
	}
	public String getApodo() {
		return apodo;
	}
	public String[] getTiposDePokemons() {
		return tiposDePokemons;
	}
	public boolean ingresarPokemon(Pokemon este) {
		if(equipoPokemon.isEmpty()) {
			equipoPokemon.add(este);
			este.setIndice(++cantPokemones);
			return true;
		}else {
			if(equipoPokemon.contains(este)) {
				System.out.println("Ya haz capturado este pokémon");
				return false;
			}
			equipoPokemon.add(este);
			este.setIndice(++cantPokemones);
		}
		return true;
		
		

	}
	public void revisarEquipo() {
		int i=1;
		for (Pokemon pokemon : equipoPokemon) {
			System.out.println(pokemon.getIndice() + ") "+pokemon.getNombre() + "|" + pokemon.getTipo()+"|Stats totales: "+pokemon.getStatsTotales());
			++i;
			if (i==6) {
				break;
			}
		}
	}
	public void nuevoPokemon() {
		cantPokemones++;
	}

	public int getcantPokemones() {
		return cantPokemones;
	}
	public void mostrarPC(Scanner s) {
		// TODO Auto-generated method stub
		
		for (Pokemon pokemon : equipoPokemon) {
			System.out.println(pokemon.getIndice() + ") "+pokemon.getNombre() + "|" + pokemon.getTipo()+"|Stats totales: "+pokemon.getStatsTotales());
			
	}
		System.out.println("Que deseas hacer?");
		System.out.println("1) Cambiar Pokémon.");
		System.out.println("2) Salir.");
		String opcion = s.nextLine();
		switch (opcion) {
		case "1":
			try {
			System.out.println("¿Qué pokémon deseas cambiar?");
			System.out.print(">");
			String indice = s.nextLine();
			Pokemon elegidoPrimero = elegirPokemon(indice);
			if(elegidoPrimero == null) {
				System.out.println("error, El índice ingresado no corresponde a ningún Pokémon");
				break;
			}
			System.out.println(elegidoPrimero.getNombre());
			System.out.println("¿Por cual pokémon deseas cambiar?");
			System.out.print(">");
			String indiceSegundo = s.nextLine();
			Pokemon elegidoSegundo = elegirPokemon(indiceSegundo);
			if(elegidoSegundo == null) {
				System.out.println("error, El índice ingresado no corresponde a ningún Pokémon");
				break;
			}
			System.out.println(elegidoSegundo.getNombre());
			cambiarEquipo(elegidoPrimero,elegidoSegundo);
			}catch (Exception e) {
				// TODO: handle exception

			}
			break;

		default:
			break;
		}
		
	}
	private void cambiarEquipo(Pokemon elegidoPrimero, Pokemon elegidoSegundo) {
		// TODO Auto-generated method stub
		
		if (elegidoPrimero.getIndice()==elegidoSegundo.getIndice()) {
			System.out.println("No se puede entrenador cambiar de posicion al mismo pokémon...");
		} else {
			Pokemon temporal = elegidoPrimero;
			int indiceUno= elegidoPrimero.getIndice()-1;
			equipoPokemon.set(indiceUno, elegidoSegundo);
			int indiceDos = elegidoSegundo.getIndice()-1;
			equipoPokemon.set(indiceDos, temporal);
			elegidoPrimero.setIndice(indiceDos+1);
			elegidoSegundo.setIndice(indiceUno+1);
			System.out.println("Cambio correcto :)");
		}
		
	}
	private Pokemon elegirPokemon(String indice) {
		// TODO Auto-generated method stub
		try {
		for (Pokemon pokemon : equipoPokemon) {
			if (pokemon.getIndice()==Integer.parseInt(indice)) {
				return pokemon;
			}
		}}catch (Exception e) {
			// TODO: handle exception
			System.out.println("");
		}
		return null;
	}
	public int revisarGimnasio(String opcion) {
	    try {
	
	        return Integer.parseInt(opcion);
	        
	    } catch (NumberFormatException e) {
	        
	        return -1; 
	    }
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
	public Pokemon elejirNuevoPokemon(Scanner s,Pokemon pokMuerto) {
	    while (true) {
	        
	        for (Pokemon pokemon : equipoPokemon) {
	            if (pokemon.verEstado()) {
	                System.out.println(pokemon.getIndice() + ") " + pokemon.getNombre() + " | " + pokemon.getTipo() + " | Stats totales: " + pokemon.getStatsTotales());
	            }
	        }
	        
	        try {
	            System.out.println("¿Qué pokémon deseas cambiar?");
	            System.out.print(">");
	            String indice = s.nextLine();
	            
	            Pokemon elegidoPrimero = elegirPokemon(indice);
	       
	            if (elegidoPrimero == null) {
	                System.out.println("Error: El índice ingresado no corresponde a ningún Pokémon. Intenta de nuevo.\n");
	                continue;
	            }
	            if (!elegidoPrimero.verEstado()) {
	                System.out.println("Error: Ese Pokémon ya está debilitado. Por favor, elige otro.\n");
	                continue; 
	            }
	            cambiarEquipo(pokMuerto, elegidoPrimero);
	            return elegidoPrimero;
	            
	        } catch (Exception e) {
	            
	            System.out.println("Ocurrió un error inesperado de lectura. Intenta de nuevo.");
	        }
	    }
	}
}
