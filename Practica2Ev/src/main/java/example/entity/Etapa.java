package example.entity;

import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import example.entity.Llevar;
import example.entity.Puerto;

@Entity
@Table(name="etapa")
public class Etapa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="netapa")
	private int netapa;
	
	@Column(name="km")
	private int km;
	
	@Column(name="salida")
	private String salida;
	
	@Column(name="llegada")
	private String llegada;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="dorsal")
	private Ciclista dorsal;
	
	@OneToMany(mappedBy="netapa", cascade=CascadeType.REMOVE)
	private List<Puerto> listPuertos;
	
	@OneToMany(mappedBy="netapa", cascade=CascadeType.REMOVE)
	private List<Llevar> listLlevar;
	
	public Etapa() {}

	public Etapa(int netapa, int km, String salida, String llegada, Ciclista dorsal, List<Puerto> listPuertos, List<Llevar> listLlevar) {
		super();
		this.netapa = netapa;
		this.km = km;
		this.salida = salida;
		this.llegada = llegada;
		this.dorsal = dorsal;
		this.listPuertos = listPuertos;
		this.listLlevar = listLlevar;
	}

	public List<Puerto> getListaP() 
	{
		return listPuertos;
	}

	public void setListaP(List<Puerto> listPuertos) 
	{
		this.listPuertos = listPuertos;
	}

	public List<Llevar> getListaL() 
	{
		return listLlevar;
	}

	public void setListaL(List<Llevar> listLlevar) 
	{
		this.listLlevar = listLlevar;
	}

	public int getNetapa() 
	{
		return netapa;
	}

	public void setNetapa(int netapa) 
	{
		this.netapa = netapa;
	}

	public int getKm() 
	{
		return km;
	}

	public void setKm(int km) 
	{
		this.km = km;
	}

	public String getSalida() 
	{
		return salida;
	}

	public void setSalida(String salida) 
	{
		this.salida = salida;
	}

	public String getLlegada() 
	{
		return llegada;
	}

	public void setLlegada(String llegada) 
	{
		this.llegada = llegada;
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
