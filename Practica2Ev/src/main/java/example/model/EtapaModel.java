package example.model;

import java.util.List;


public class EtapaModel {
	
	private int netapa;
	private int km;
	private String salida;
	private String llegada;
	private CiclistaModel dorsal;
	private List<PuertoModel> listaP;
	private List<LlevarModel> listaL;
	
	
	public EtapaModel() {}


	public int getNetapa() {
		return netapa;
	}

	public void setNetapa(int netapa) {
		this.netapa = netapa;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getLlegada() {
		return llegada;
	}

	public void setLlegada(String llegada) {
		this.llegada = llegada;
	}


	public CiclistaModel getDorsal() {
		return dorsal;
	}


	public void setDorsal(CiclistaModel dorsal) {
		this.dorsal = dorsal;
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
