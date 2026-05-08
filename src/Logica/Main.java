package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.AltoMando;
import Dominio.Entrenador;
import Dominio.Gimnasio;
import Dominio.Habitat;
import Dominio.Pokemon;
import Dominio.TablaTipos;

public class Main {
	static Entrenador jugador= null;
	private static TablaTipos tablaTipos = new TablaTipos();
	private static List<Pokemon> pokedex = new ArrayList<Pokemon>();
	private static List<Habitat> habitatsDispo= new ArrayList<Habitat>();
	private static List<Gimnasio> gimnaciosDisponibles= new ArrayList<Gimnasio>();
	private static List<AltoMando> AltoMando= new ArrayList<AltoMando>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		abrirHabitats(s);
		abrirPokedex(s);
		abrirGimnasio(s);
		abrirAltoMando(s);
		menuInicial(s); 
		
			
		};
	

	private static void abrirPokedex(Scanner s) {
		// TODO Auto-generated method stub
		int i =0;
		File arch = new File("Pokedex.txt");
		try {
			Scanner linea = new Scanner(arch);
			while (linea.hasNextLine()) {
				String contenido = linea.nextLine();
				String[] partes= contenido.split(";");
				String nombre=partes[0];
				String nombreHabitad=partes[1];
				double porcentajeAparicion= Double.parseDouble(partes[2]);
				int vida= Integer.parseInt(partes[3]);
				int ataque= Integer.parseInt(partes[4]);
				int defensa=Integer.parseInt(partes[5]);
				int ataqueEsoecial=Integer.parseInt(partes[6]);
				int defensaEspecia=Integer.parseInt(partes[7]);
				int velocidad=Integer.parseInt(partes[8]);
				String tipo= partes[9];
				int indice=i;
				Habitat buscar = encontrarSuHabitad(nombreHabitad);
				if (buscar ==null) {
					pokedex.add(new Pokemon(nombre, null, porcentajeAparicion, vida, ataqueEsoecial, defensa, ataque, defensaEspecia, velocidad, tipo, indice));
				}else {
				buscar.ingresarPokemons(new Pokemon(nombre, buscar, porcentajeAparicion, vida, ataqueEsoecial, defensa, ataque, defensaEspecia, velocidad, tipo, indice));
				pokedex.add(new Pokemon(nombre, buscar, porcentajeAparicion, vida, ataqueEsoecial, defensa, ataque, defensaEspecia, velocidad, tipo, indice));
			}}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no está");
		}
	}


	private static Habitat encontrarSuHabitad(String nombreHabitad) {
		// TODO Auto-generated method stub
		for (Habitat habitat : habitatsDispo) {
			if (habitat.getNombre().equalsIgnoreCase(nombreHabitad)) {
				return habitat;
			}
		}
		return null;
	}


	private static void abrirHabitats(Scanner s) {
		// TODO Auto-generated method stub
		File arch = new File("Habitats.txt");
		int indice=0;
		try {
			Scanner linea = new Scanner(arch);
			while (linea.hasNextLine()) {
				String contenido = linea.nextLine();
				String[] partes= contenido.split(";");
				String nombre= partes[0];
				habitatsDispo.add(new Habitat(nombre, indice++));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no está");
		}
	}


	private static void abrirAltoMando(Scanner s) {
		// TODO Auto-generated method stub
		File arch = new File("Alto Mando.txt");
		
		try {
			Scanner linea = new Scanner(arch);
			while (linea.hasNextLine()) {
				String contenido = linea.nextLine();
				String[] partes= contenido.split(";");
				int indice= Integer.parseInt(partes[0]);
				String nombre= partes[1];
				String estadoText=partes[2];
				AltoMando otroTipo = new AltoMando(indice, nombre);
				for (int i = 0; i < 5; i++) {
					aniadirPokemonAltoMando(otroTipo,partes[2+i]);
				}
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no está");
		}
	}


	private static void abrirGimnasio(Scanner s) {
		// TODO Auto-generated method stub
File arch = new File("Gimnasios.txt");
		
		try {
			Scanner linea = new Scanner(arch);
			while (linea.hasNextLine()) {
				String contenido = linea.nextLine();
				String[] partes= contenido.split(";");
				int indice= Integer.parseInt(partes[0]);
				String nombre= partes[1];
				String estadoText=partes[2];
				int cantPokemons=Integer.parseInt(partes[3]);
				Gimnasio contrincante = new Gimnasio(indice, nombre, estadoText);
				for (int i = 0; i < cantPokemons; i++) {
					aniadirPokemon(contrincante,partes[4+i]);
				}
		
				gimnaciosDisponibles.add(contrincante);
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no está");
		}
	}


	private static void aniadirPokemon(Gimnasio contrincante, String nombre) {
		// TODO Auto-generated method stub
		for (Pokemon pokemon : pokedex) {
			if(pokemon.getNombre().equalsIgnoreCase(nombre)) {
				contrincante.ingresarPokemon(pokemon);
				return;
			}
		}
	}
	private static void aniadirPokemonAltoMando(AltoMando contrincante, String nombre) {
		// TODO Auto-generated method stub
		for (Pokemon pokemon : pokedex) {
			if(pokemon.getNombre().equalsIgnoreCase(nombre)) {
				contrincante.ingresarPokemon(pokemon);
				return;
			}
		}
	}


	public static void menuInicial(Scanner s) {
		// TODO Auto-generated method stub
		String opcion ="";
		do {
		System.out.println("1) Continuar.");
		System.out.println("2) Nueva Partida.");
		System.out.println("3) Salir.");
		System.out.print(">");
		boolean existPartida=false;
		
		opcion = seleccion(s);
		}while (opcion!="3");
	}


	public static void crearEntrenador(Scanner s) {
		// TODO Auto-generated method stub
		System.out.print("Ingrese apodo: ");
		String nombre = s.nextLine();
		System.out.println("");
		Entrenador cualquiera = new Entrenador(nombre);
		jugador=cualquiera;
	}

	public static String seleccion(Scanner s) {
		String opcion= (s.nextLine());
		switch (opcion) {
		case "1":
			
			jugar(s);
			break;
		case "2":
			crearEntrenador(s);
			System.out.println("Bienvenido "+jugador.getApodo()+"!!");
			jugar(s);
			System.out.println("Hata luego "+ jugador.getApodo()+" ...");
			return "3";
		case "3":
			System.out.println("Salio con exito");
			return "3";
		default:
			System.out.println("Seleccionaste mal");
			
			break;
			
		}
		return "0";

	}

	public static void jugar(Scanner s) {
		// TODO Auto-generated method stub
		String opcion="0";
		do {
		System.out.println(jugador.getApodo()+", que deseas hacer?");
		System.out.println("");
		System.out.println("1) Revisar equipo ");
		System.out.println("2) salir a capturar");
		System.out.println("3) Acceso al PC");
		System.out.println("4) Retar un gimnasio");
		System.out.println("5) Desafío al Alto Mando");
		System.out.println("6) Curar Pokémon");
		System.out.println("7) Guardar");
		System.out.println("8) Guardar y Salir");
		
		System.out.println("");
		System.out.print("Ingrese Opcion: ");
		
		opcion = submenu(s);
		}while(opcion !="8");
	}


	private static String submenu(Scanner s) {
		// TODO Auto-generated method stub
		String opcion= ((s.nextLine()));
		switch (opcion) {
		case "1":
			if(jugador.getEquipoPokemon().isEmpty()) {
				System.out.println("El equipo está vacío, sale a capturar Pokémones");
			}
			else {
			revisarEquipo();}
			break;
		case "2":
			salirACapturar(s);		
			break;
		case "3":
			accesoAlPc(s);
			break;
		case "4":
			retarGimnacio(s);
			break;
		case "5":
			
			break;
		case "6":
			
			break;
		case "7":
			
			break;
		case "8":
			return "8";
		default:
			System.out.println("Ingresaste mal los valores");
			break;
		}
		return "0";
	}


private static void retarGimnacio(Scanner s) {
    System.out.println("A cual Lider deseas retar??\n");
    
    for (Gimnasio gim : gimnaciosDisponibles) {
        System.out.println(gim.toString());
    }
    System.out.println("9) Volver al menu.");
    System.out.print("Ingrese Opcion: ");
    
    try {
        String opcion = s.nextLine();
        
        // Interceptamos la opción de salir antes de hacer cualquier cálculo
        if (opcion.equals("9")) {
            System.out.println("Volviendo al menú...");
            return; // Termina la ejecución de este método inmediatamente
        }
        
        int indiceContrincante = jugador.revisarGimnasio(opcion);
        Gimnasio contrincante = gimnaciosDisponibles.get(indiceContrincante - 1);
        
        pelear(contrincante, s);
        
    } catch (Exception e) {
        // Evitamos que el juego se rompa en silencio si ingresan una letra o un número fuera de rango
        System.out.println("Error: Ingreso inválido. Volviendo al menú principal...");
        e.printStackTrace();
    }
}


	private static void pelear(Gimnasio contrincante, Scanner s) {
	    int indiceEnemigo = 0;
	    System.out.println("Desafiando a " + contrincante.getNombre());
	    
	    Pokemon delOtro = contrincante.entregarPokemon(indiceEnemigo);
	    System.out.println(contrincante.getNombre() + " saca a " + delOtro.getNombre() + "!");
	    
	    Pokemon miPokemon = jugador.entregarPokemon(0);
	    System.out.println(jugador.getApodo() + " saca a " + miPokemon.getNombre() + "!");

	    String opcion = "";
	    
	  
	    while (jugador.verEstado() && contrincante.verEstado() && !opcion.equals("3")) {
	        System.out.println("\n--- MENÚ DE COMBATE ---");
	        System.out.println("1) Atacar\n2) Cambiar de pokemon\n3) Rendirse");
	        System.out.print(">");
	        
	        try {
	            opcion = s.nextLine();
	            switch (opcion) {
	                case "1":
	                   
	                    int iContrincante = delOtro.sacarIndiceTipoPokemon(delOtro);
	                    int iMio = miPokemon.sacarIndiceTipoPokemon(miPokemon);
	                    
	                    float efecEnemiga = tablaTipos.sacarEfectividad(iContrincante, iMio);
	                    float efecMia = tablaTipos.sacarEfectividad(iMio, iContrincante);
	                    
	                    float sEnemigo = delOtro.getStatsTotales() * efecEnemiga;
	                    float sMio = miPokemon.getStatsTotales() * efecMia;

	                    if (sEnemigo > sMio) {
	                        miPokemon.muere();
	                        System.out.println(miPokemon.getNombre() + " ha sido derrotado.");
	                        if (jugador.verEstado()) {
	                            miPokemon = jugador.elejirNuevoPokemon(s, miPokemon);
	                        }
	                    } else if (sMio > sEnemigo) {
	                        delOtro.muere();
	                        System.out.println("¡Has derrotado a " + delOtro.getNombre() + "!");
	                        
	                        
	                        indiceEnemigo++;
	                        // Verificamos si al gimnasio le quedan pokemones (asumiendo que tienes este dato)
	                        if (indiceEnemigo < contrincante.getCantidadPokemones()) {
	                            delOtro = contrincante.entregarPokemon(indiceEnemigo);
	                            System.out.println("\n" + contrincante.getNombre() + " envía a su siguiente Pokémon: " + delOtro.getNombre());
	                            // El flujo termina aquí y el 'while' vuelve a mostrar el menú
	                        } else {
	                            System.out.println("¡Has derrotado a todos los Pokémon de " + contrincante.getNombre() + "!");
	                            contrincante.cambiarEstado();
	                        }
	                    }
	                    break;

	                case "2":
	                    // Regla de cambio voluntario
	                    Pokemon cambio = jugador.elejirNuevoPokemon(s, miPokemon);
	                    if (cambio != null) {
	                        miPokemon = cambio;
	                    }
	                    break;

	                case "3":
	                    System.out.println("Has huido del combate...");
	                    break;
	            }
	        } catch (Exception e) {
	            System.out.println("Error en la entrada de datos.");
	        }
	    }
	    
	    // Al salir del while, verificas quién ganó
	    if (!contrincante.verEstado()) {
	        System.out.println("¡Felicidades! Ganaste la medalla del gimnasio.");
	        jugador.añadirGimnasio(contrincante);
	        
	    }
	}




	

	private static void accesoAlPc(Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("...");
		System.out.println("Entrando al pc");
		System.out.println("Pokemon disponible:");
		jugador.mostrarPC(s);
	}


	private static void revisarEquipo() {
		// TODO Auto-generated method stub
		jugador.revisarEquipo();
	}


	private static void salirACapturar(Scanner s) {
		// TODO Auto-generated method stub
		
		System.out.println("Donde deseas ir a explorar?");
		System.out.println();
		System.out.println("Zonas disponibles:");
		zonasDeCaptura();
		System.out.print("Ingrese opcion: ");
		try {
		String opcion= (s.nextLine());
		Habitat zona= revisarHabitad(opcion);
		if (zona==null) {
			System.out.println("Mal ingresado");
		} else {
			Pokemon aleatorio = zona.RandomPokemon();
			menuAtrapar(aleatorio,s);
		}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Ingresaste mal los valores");
		}
	}




	private static void menuAtrapar(Pokemon aleatorio, Scanner s) {
		// TODO Auto-generated method stub
		System.out.println("Qué deseas hacer?");
		System.out.println();
		System.out.println("1) Capturar");
		System.out.println("2) Huir");
		System.out.println("Ingrese opcion: ");
		try {
			int opcion = Integer.parseInt(s.nextLine());
			switch (opcion) {
			case 1:
				boolean capturado = jugador.ingresarPokemon(aleatorio);
				if (capturado) {
				System.out.println(aleatorio.getNombre()+" ha sido sido atrapado con exito!!");
				if (jugador.getcantPokemones()<6) {
					System.out.println(aleatorio.getNombre()+" ha sido agregado a tu equipo!!");
				}else {
					System.out.println(aleatorio.getNombre()+" ha ido a la pc!!");
				}} 
				
				break;
				
			case 2:
				System.out.println("Huiste sin problemas");
				break;
			default:
				System.out.println("Ingresaste mal el valor");
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	private static Habitat revisarHabitad(String opcion) {
		// TODO Auto-generated method stub
		
		for (Habitat habitad : habitatsDispo) {
			if (habitad.getIndice()==Integer.parseInt(opcion)-1) {
				return habitad;
			}
		}
		return null;
	}

	public static void zonasDeCaptura() {
		Scanner s= new Scanner(System.in);
		for (Habitat habitad : habitatsDispo) {
			System.out.println(habitad.getIndice()+1 + ") " + habitad.getNombre());
		}

	}
	
	
}
