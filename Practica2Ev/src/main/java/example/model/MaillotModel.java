package example.model;

import java.util.List;


public class MaillotModel {

	private String codigo;
	private String tipo;
	private String color;
	private int premio;
	private List<LlevarModel> listaL;
	
	public MaillotModel() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public List<LlevarModel> getListaL() {
		return listaL;
	}

	public void setListaL(List<LlevarModel> listaL) {
		this.listaL = listaL;
	}
	
}
