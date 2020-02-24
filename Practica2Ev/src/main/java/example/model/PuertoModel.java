package example.model;



public class PuertoModel {

	private String nompuerto;
	private int altura;
	private String categoria;
	private int pendiente;
	private EtapaModel netapa;
	private CiclistaModel dorsal;
	
	public PuertoModel() {}


	public String getNompuerto() {
		return nompuerto;
	}

	public void setNompuerto(String nompuerto) {
		this.nompuerto = nompuerto;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getPendiente() {
		return pendiente;
	}

	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}

	public EtapaModel getNetapa() {
		return netapa;
	}

	public void setNetapa(EtapaModel netapa) {
		this.netapa = netapa;
	}

	public CiclistaModel getDorsal() {
		return dorsal;
	}

	public void setDorsal(CiclistaModel dorsal) {
		this.dorsal = dorsal;
	}


	
	
}
