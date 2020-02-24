package example.entity;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="puerto")
public class Puerto {

	@Id
	@Column(name="nompuerto")
	private String nompuerto;
	
	@Column(name="altura")
	private int altura;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="pendiente")
	private int pendiente;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="netapa")
	private Etapa netapa;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="dorsal")
	private Ciclista dorsal;
	
	public Puerto() {}
	
	public Puerto(String nompuerto, int altura, String categoria, int pendiente, Etapa netapa, Ciclista dorsal) 
	{
		super();
		this.nompuerto = nompuerto;
		this.altura = altura;
		this.categoria = categoria;
		this.pendiente = pendiente;
		this.netapa = netapa;
		this.dorsal = dorsal;
	}

	//Getters and Setters
	
	public String getNompuerto() 
	{
		return nompuerto;
	}

	public void setNompuerto(String nompuerto) 
	{
		this.nompuerto = nompuerto;
	}

	public int getAltura() 
	{
		return altura;
	}

	public void setAltura(int altura) 
	{
		this.altura = altura;
	}

	public String getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(String categoria) 
	{
		this.categoria = categoria;
	}

	public int getPendiente() 
	{
		return pendiente;
	}

	public void setPendiente(int pendiente) 
	{
		this.pendiente = pendiente;
	}

	public Etapa getNetapa() 
	{
		return netapa;
	}

	public void setNetapa(Etapa netapa) 
	{
		this.netapa = netapa;
	}

	public Ciclista getDorsal() 
	{
		return dorsal;
	}

	public void setDorsal(Ciclista dorsal) 
	{
		this.dorsal = dorsal;
	}
	
}
