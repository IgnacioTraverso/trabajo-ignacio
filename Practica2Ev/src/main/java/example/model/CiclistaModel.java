package example.model;

import java.util.List;



public class CiclistaModel {
	private int id;
	private int dorsal;
	private String nombre;
	private int edad;
	private String foto;
	private EquipoModel nomeq;
	private List<EtapaModel> listaE;
	private List<PuertoModel> listaP;
	private List<LlevarModel> listaL;
	
	public CiclistaModel() {}

	public CiclistaModel(int id, int dorsal, String nombre, int edad, String foto, EquipoModel nomeq,
			List<EtapaModel> listaE, List<PuertoModel> listaP, List<LlevarModel> listaL) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.edad = edad;
		this.foto = foto;
		this.nomeq = nomeq;
		this.listaE = listaE;
		this.listaP = listaP;
		this.listaL = listaL;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EquipoModel getNomeq() {
		return nomeq;
	}

	public void setNomeq(EquipoModel nomeq) {
		this.nomeq = nomeq;
	}

	public List<EtapaModel> getListaE() {
		return listaE;
	}

	public void setListaE(List<EtapaModel> listaE) {
		this.listaE = listaE;
	}

	public List<PuertoModel> getListaP() {
		return listaP;
	}

	public void setListaP(List<PuertoModel> listaP) {
		this.listaP = listaP;
	}

	public List<LlevarModel> getListaL() {
		return listaL;
	}

	public void setListaL(List<LlevarModel> listaL) {
		this.listaL = listaL;
	}

	
	

	
}
