package example.entity;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import example.entity.Etapa;
import example.entity.Llevar;
import example.entity.Puerto;

@Entity
@Table(name="ciclista")
public class Ciclista {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="dorsal")
	private int dorsal;
	
	@Column(name="nombre", unique=true)
	private String nombre;
	
	@Column(name="edad")
	private int edad;
	
	@Column(name="foto")
	private String foto;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="nomeq")
	private Equipo nomeq;
	
	@OneToMany(mappedBy="dorsal", cascade=CascadeType.REMOVE)
	private List<Etapa> listEtapas;
	
	@OneToMany(mappedBy="dorsal", cascade=CascadeType.REMOVE)
	private List<Puerto> listPuertos;
	
	@OneToMany(mappedBy="dorsal", cascade=CascadeType.REMOVE)
	private List<Llevar> listLlevar;
	
	public Ciclista() {}

	public Ciclista(@NotNull int id, int dorsal, String nombre, int edad, String foto, Equipo nomeq, List<Etapa> listEtapas,List<Puerto> listPuertos, List<Llevar> listLlevar) 
	{
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.edad = edad;
		this.foto = foto;
		this.nomeq = nomeq;
		this.listEtapas = listEtapas;
		this.listPuertos = listPuertos;
		this.listLlevar = listLlevar;
	}

	//Getters and Setters

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public List<Etapa> getListEtapas() 
	{
		return listEtapas;
	}

	public void setListEtapas(List<Etapa> listEtapas) 
	{
		this.listEtapas = listEtapas;
	}

	public List<Puerto> getListPuertos() 
	{
		return listPuertos;
	}

	public void setListPuertos(List<Puerto> listPuertos) 
	{
		this.listPuertos = listPuertos;
	}
	
	public List<Llevar> getListLlevar() 
	{
		return listLlevar;
	}

	public void setListLlevar(List<Llevar> listLlevar) 
	{
		this.listLlevar = listLlevar;
	}

	public int getDorsal() 
	{
		return dorsal;
	}

	public void setDorsal(int dorsal) 
	{
		this.dorsal = dorsal;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public int getEdad() 
	{
		return edad;
	}

	public void setEdad(int edad) 
	{
		this.edad = edad;
	}

	public String getFoto() 
	{
		return foto;
	}

	public void setFoto(String foto) 
	{
		this.foto = foto;
	}

	public Equipo getNomeq() 
	{
		return nomeq;
	}

	public void setNomeq(Equipo nomeq) 
	{
		this.nomeq = nomeq;
	}

}
