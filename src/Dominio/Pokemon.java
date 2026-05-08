package Dominio;

public class Pokemon {
	private String nombre;
	private Habitat habitad;
	private double porcentajeAparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEsoecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private boolean estado;
	private int statsTotales;
	private int indice;
	private static  String[] tiposDePokemons= new String[] {"normal","fuego", "agua", "planta", "electrico", "hielo", "lucha", "veneno", "tierra", "volador", "psiquico", "bicho", "roca", "fantasma", "dragon", "acero", "siniestro", "hada"};
	
	public Pokemon(String nombre, Habitat habitad, double porcentajeAparicion, int vida, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, String tipo, int indice) {
		this.nombre = nombre;
		this.habitad = habitad;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEsoecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
		statsTotales= vida+ataque+defensa+ataqueEspecial+defensaEspecial+velocidad;
		estado=true;
		this.indice=indice;
	}
	public String getNombre() {
		return nombre;
	}
	public Habitat getHabitad() {
		return habitad;
	}
	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}
	public int getVida() {
		return vida;
	}
	public int getAtaque() {
		return ataque;
	}
	public int getDefensa() {
		return defensa;
	}
	public int getAtaqueEsoecial() {
		return ataqueEsoecial;
	}
	public int getDefensaEspecial() {
		return defensaEspecial;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public String getTipo() {
		return tipo;
	}
	
	public void muere() {
		estado=false;
	}
	public void revivir() {
		estado=true;
	}
	public int getStatsTotales() {
		return statsTotales;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public boolean verEstado() {
		return estado;
	}
	
	public int sacarIndiceTipoPokemon(Pokemon miPokemon) {
		for (int i = 0; i < tiposDePokemons.length; i++) {
			if (miPokemon.getTipo().equalsIgnoreCase(tiposDePokemons[i])) {
				return i;
			}
		}
		return 0;
	}
	
}
