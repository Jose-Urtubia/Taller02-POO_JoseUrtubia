package Logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
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
				for (int i = 0; i < 6; i++) {
					aniadirPokemonAltoMando(otroTipo,partes[2+i]);
				}
				AltoMando.add(otroTipo);
				
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
			cargar(s);
			jugar(s);
			return"3";
			
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
	private static void cargar(Scanner s) {
	    File archivo = new File("Registros.txt");
	    if (!archivo.exists()) {
	        System.out.println("No hay ninguna partida guardada en este equipo.");
	        return;
	    }
	    try {
	    	Scanner lector = new Scanner(archivo);
	       
	        if (lector.hasNextLine()) {
	            String primeraLinea = lector.nextLine();
	            String[] partes = primeraLinea.split(";");
	            
	          
	            jugador = new Entrenador(partes[0]);
	            
	            for (int i = 1; i < partes.length; i++) {
	                String nombreGym = partes[i];
	                for (Gimnasio g : gimnaciosDisponibles) {
	                    if (g.getNombre().equalsIgnoreCase(nombreGym)) {
	                        g.cambiarEstado();
	                        jugador.añadirGimnasio(g);
	                    }
	                }
	            }
	        }

	        while (lector.hasNextLine()) {
	            String linea = lector.nextLine();
	            if (linea.isEmpty()) continue;
	            
	            String[] partesPok = linea.split(";");
	            String nombrePok = partesPok[0];
	            String estadoPok = partesPok[1];

	            for (Pokemon pBase : pokedex) {
	                if (pBase.getNombre().equalsIgnoreCase(nombrePok)) {
	                    if (estadoPok.equalsIgnoreCase("Muerto")) {
	                        pBase.muere();
	                    }
	                    jugador.ingresarPokemon(pBase);
	                    break;
	                }
	            }
	        }
	        System.out.println("¡Partida cargada! Bienvenido de vuelta, " + jugador.getApodo());

	    } catch (FileNotFoundException e) {
	        System.out.println("Error al acceder al archivo de registros.");
	    }
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
			retarAltoMando(s);
			break;
		case "6":
			curarTodosLosPokemons();
			break;
		case "7":
			guardar(s);
			break;
		case "8":
			guardar(s);
			return "8";
		default:
			System.out.println("Ingresaste mal los valores");
			break;
		}
		return "0";
	}


private static void curarTodosLosPokemons() {
		// TODO Auto-generated method stub
	if (jugador.getEquipoPokemon().isEmpty()) {
        System.out.println("No tienes Pokémon en tu equipo para curar.");
        return;
    }

    System.out.println("Enfermera Joy: ¡Bienvenidos al Centro Pokémon!");
    System.out.println("Sanando a tu equipo...");

    for (Pokemon p : jugador.getEquipoPokemon()) {
        p.revivir();
    }
    jugador.revivir();
    System.out.println("Enfermera Joy: ¡Tus Pokémon están en perfecta forma!");
    System.out.println("¡Vuelve pronto!");
}
	


private static void retarAltoMando(Scanner s) {
    if (jugador.getGimnaciosDerrotados().size() < 8) {
        System.out.println("Guardia: ¡Alto ahí! Solo los campeones con 8 medallas pueden pasar.");
        return; 
    }

    System.out.println("¡Has ingresado a la Liga Pokémon!");
    System.out.println("Afirmate papito...");

    for (int i = 0; i < AltoMando.size(); i++) {
        Dominio.AltoMando contrincanteActual = AltoMando.get(i);
        
        System.out.println("--- ENFRENTAMIENTO " + (i + 1) + " DE " + AltoMando.size() + " ---");
        
        pelear(contrincanteActual, s);
        
        if (!jugador.verEstado()) {
            System.out.println("Has sido derrotado por el Alto Mando " + contrincanteActual.getNombre() + ".");
            System.out.println("Debes volver al Centro Pokémon y empezar el desafío desde cero.");
            
            break; 
        } else {
            if (i < AltoMando.size() - 1) {
                System.out.println("¡Has vencido a " + contrincanteActual.getNombre() + "!");
                System.out.println("Avanzas a la siguiente sala...");
            } else {
             
                System.out.println("¡INCREÍBLE! ¡HAS DERROTADO A TODOS Y TE HAS CORONADO COMO EL NUEVO CAMPEÓN!");

            }
        }
    }
}


private static void guardar(Scanner s) {
		// TODO Auto-generated method stub
		try{
			BufferedWriter writen = new BufferedWriter(new FileWriter("Registros.txt"));
			String texto="";
			
			writen.write(jugador.getApodo());
			for (int i = 0; i < jugador.getGimnaciosDerrotados().size(); i++) {
			    Gimnasio gym = jugador.getGimnaciosDerrotados().get(i);
			    

			    writen.write(";"+gym.getNombre());
			    
			
			}
			writen.newLine();
			for (int i = 0; i < jugador.getEquipoPokemon().size(); i++) {
				Pokemon pok = jugador.getEquipoPokemon().get(i);
				String estado="";
				if(pok.verEstado()) {
					estado="Vivo";
				} else {
						estado = "Muerto";}
				 writen.write(pok.getNombre() + ";"+ estado);
				writen.newLine();
				
			}
			writen.close();
		    System.out.println("Partida guardada exitosamente.");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al intentar guardar la partida");
		}
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
        
        if (opcion.equals("9")) {
            System.out.println("Volviendo al menú...");
            return; 
        }

        int numeroIngresado = jugador.revisarGimnasio(opcion);
        int gimnasiosDerrotados = jugador.getGimnaciosDerrotados().size();
        
      
        if (jugador.getGimnaciosDerrotados().isEmpty()) {
        
            if (numeroIngresado == 1) {
                Gimnasio contrincante = gimnaciosDisponibles.get(0);
                pelear(contrincante, s);
                return; 
            } else {
                System.out.println("Debes derrotar al primer líder antes de avanzar.");
                return;
            }
        }

        int indiceReal = numeroIngresado - 1; 
        
        if (indiceReal > gimnasiosDerrotados) {
            System.out.println("¡Alto ahí! Aún no puedes retar a este gimnasio.");
            System.out.println("Debes derrotar al líder anterior primero.");
            return; 
        }
        if (indiceReal < gimnasiosDerrotados) {
            System.out.println("Ya derrotaste a este líder. ¡Busca un nuevo desafío!");
            return; 
        }
        
     
        Gimnasio contrincante = gimnaciosDisponibles.get(indiceReal);
        
        pelear(contrincante, s);
        
    } catch (Exception e) {
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
	                	int baseMio= miPokemon.getStatsTotales();
	                	int baseEnemigo =delOtro.getStatsTotales();
	                	System.out.println("\n" + miPokemon.getNombre() + " -> " + baseMio + " puntos");
	                    System.out.println(delOtro.getNombre() + " -> " + baseEnemigo + " puntos\n");
	                    int iContrincante = delOtro.sacarIndiceTipoPokemon(delOtro);
	                    int iMio = miPokemon.sacarIndiceTipoPokemon(miPokemon);
	                    
	                    float efecEnemiga = tablaTipos.sacarEfectividad(iContrincante, iMio);
	                    float efecMia = tablaTipos.sacarEfectividad(iMio, iContrincante);
	                    
	                    if (efecMia > 1.0) {
	                        System.out.println(miPokemon.getNombre() + " es súper efectivo contra " + delOtro.getNombre() + "!");
	                    } else if (efecMia < 1.0 && efecMia > 0.0f) {
	                        System.out.println(miPokemon.getNombre() + " no es efectivo contra " + delOtro.getNombre() + "!");
	                    } else if (efecMia == 0.0) {
	                        System.out.println("El ataque de " + miPokemon.getNombre() + " no afecta a " + delOtro.getNombre() + "!");
	                    }

	                    if (efecEnemiga > 1.0f) {
	                        System.out.println(delOtro.getNombre() + " es súper efectivo contra " + miPokemon.getNombre() + "!");
	                    } else if (efecEnemiga < 1.0 && efecEnemiga > 0.0f) {
	                        System.out.println(delOtro.getNombre() + " no es efectivo contra " + miPokemon.getNombre() + "!");
	                    } else if (efecEnemiga == 0.0) {
	                        System.out.println("El ataque de " + delOtro.getNombre() + " no afecta a " + miPokemon.getNombre() + "!");
	                    }
	                    
	                    float sEnemigo = delOtro.getStatsTotales() * efecEnemiga;
	                    float sMio = miPokemon.getStatsTotales() * efecMia;
	                    
	                    System.out.println("\nNuevo puntaje:");
	                    System.out.println(miPokemon.getNombre() + " -> " + sMio + " puntos");
	                    System.out.println(delOtro.getNombre() + " -> " + sEnemigo + " puntos\n");
	           

	                    if (sEnemigo > sMio) {
	                        miPokemon.muere();
	                        System.out.println("Ha ganado " + delOtro.getNombre() + "! " + miPokemon.getNombre() + " ha sido derrotado...");
	                        if (!jugador.tienePokemonVivos()) {
	                            System.out.println("¡Ya no te quedan Pokémon para luchar!");
	                            jugador.cambiarEstado();
	                            
	                        } else {
	                    
	                            miPokemon = jugador.elejirNuevoPokemon(s, miPokemon);
	                        }
	                    } else if (sMio >= sEnemigo) { 	                        delOtro.muere();
	                        System.out.println("¡Has derrotado a " + delOtro.getNombre() + "!");
	                        
	                        indiceEnemigo++;

	                        if (indiceEnemigo < contrincante.getCantidadPokemones()) {
	                            delOtro = contrincante.entregarPokemon(indiceEnemigo);
	                            System.out.println("\n" + contrincante.getNombre() + " envía a su siguiente Pokémon: " + delOtro.getNombre());

	                        } else {
	                            System.out.println("¡Has derrotado a todos los Pokémon de " + contrincante.getNombre() + "!");
	                            contrincante.cambiarEstado();
	                        }
	                    
	                    }
	                    break;

	                case "2":
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
	    if (!contrincante.verEstado()) {
	        System.out.println("¡Felicidades! Ganaste la medalla del gimnasio.");
	        jugador.añadirGimnasio(contrincante);
	        
	    }
	}
	

private static void pelear(AltoMando contrincante, Scanner s) {
    int indiceEnemigo = 0;
    System.out.println("\n¡Te enfrentas al Alto Mando " + contrincante.getNombre() + "!");
    
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
                    int baseMio = miPokemon.getStatsTotales();
                    int baseEnemigo = delOtro.getStatsTotales();
                    
                    System.out.println("\n" + miPokemon.getNombre() + " -> " + baseMio + " puntos");
                    System.out.println(delOtro.getNombre() + " -> " + baseEnemigo + " puntos\n");

                    int iContrincante = delOtro.sacarIndiceTipoPokemon(delOtro);
                    int iMio = miPokemon.sacarIndiceTipoPokemon(miPokemon);
                    
                    float efecEnemiga = tablaTipos.sacarEfectividad(iContrincante, iMio);
                    float efecMia = tablaTipos.sacarEfectividad(iMio, iContrincante);

                    // Mensajes de efectividad
                    if (efecMia > 1.0f) { System.out.println(miPokemon.getNombre() + " es súper efectivo contra " + delOtro.getNombre() + "!"); } 
                    else if (efecMia < 1.0f && efecMia > 0.0f) { System.out.println(miPokemon.getNombre() + " no es efectivo contra " + delOtro.getNombre() + "!"); } 

                    if (efecEnemiga > 1.0f) { System.out.println(delOtro.getNombre() + " es súper efectivo contra " + miPokemon.getNombre() + "!"); } 
                    else if (efecEnemiga < 1.0f && efecEnemiga > 0.0f) { System.out.println(delOtro.getNombre() + " no es efectivo contra " + miPokemon.getNombre() + "!"); }

                    float sEnemigo = baseEnemigo * efecEnemiga;
                    float sMio = baseMio * efecMia;

                    System.out.println("\nNuevo puntaje:");
                    System.out.println(miPokemon.getNombre() + " -> " + sMio + " puntos");
                    System.out.println(delOtro.getNombre() + " -> " + sEnemigo + " puntos\n");

                    // Lógica de victoria/derrota
                    if (sEnemigo > sMio) {
                        miPokemon.muere();
                        System.out.println("Ha ganado " + delOtro.getNombre() + "! " + miPokemon.getNombre() + " ha sido derrotado...");
                        
                        // REGLA DE SUPERVIVENCIA:
                        if (!jugador.tienePokemonVivos()) {
                            System.out.println("¡Ya no te quedan Pokémon para luchar! Has perdido el desafío de la Liga.");
                            jugador.cambiarEstado(); // Esto rompe el while al volver el estado false
                        } else {
                            miPokemon = jugador.elejirNuevoPokemon(s, miPokemon);
                        }
                        
                    } else if (sMio >= sEnemigo) {
                        delOtro.muere();
                        System.out.println("¡Has derrotado a " + delOtro.getNombre() + "!");
                        
                        indiceEnemigo++;
                   
                        if (indiceEnemigo < 6) { // Ajustado a tus 6 Pokémon estrictos
                            delOtro = contrincante.entregarPokemon(indiceEnemigo);
                            System.out.println("\n" + contrincante.getNombre() + " envía a su siguiente Pokémon: " + delOtro.getNombre());
                        } else {
                            System.out.println("\n¡Has derrotado a todos los Pokémon de " + contrincante.getNombre() + "!");
                            contrincante.cambiarEstado();
                        }
                    }
                    break;

                case "2":
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
    
    if (!contrincante.verEstado()) {
        System.out.println("¡Felicidades! Has superado esta etapa de la Liga Pokémon.");
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
