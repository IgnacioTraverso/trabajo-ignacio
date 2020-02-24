package example.model;

import java.util.List;


public class EquipoModel {
	
	private String nomeq;
	private String director;
	private String logo;
	private List<CiclistaModel> listaC;
	
	public EquipoModel() {}

	public String getNomeq() {
		return nomeq;
	}

	public void setNomeq(String nomeq) {
		this.nomeq = nomeq;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<CiclistaModel> getListaC() {
		return listaC;
	}

	public void setListaC(List<CiclistaModel> listaC) {
		this.listaC = listaC;
	}


	
}