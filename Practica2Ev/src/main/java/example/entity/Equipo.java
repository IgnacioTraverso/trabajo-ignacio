package example.entity;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import example.entity.Ciclista;

@Entity
@Table(name="equipo")
public class Equipo {
	@Id
	@Column(name="nomeq")
	private String nomeq;
	
	@NotNull
	@Column(name="director")
	private String director;
	
	@Column(name="logo")
	private String logo;
	
	@OneToMany(mappedBy="nomeq", cascade=CascadeType.REMOVE)
	private List<Ciclista> listCiclistas;
	
	public Equipo() {}


	public Equipo(String nomeq, @NotNull String director, String logo, List<Ciclista> listCiclistas) 
	{
		super();
		this.nomeq = nomeq;
		this.director = director;
		this.logo = logo;
		this.listCiclistas = listCiclistas;
	}

	//Getters and setters
	
	public List<Ciclista> getListaC() 
	{
		return listCiclistas;
	}

	public void setListaC(List<Ciclista> listCiclistas) 
	{
		this.listCiclistas = listCiclistas;
	}

	public String getNomeq() 
	{
		return nomeq;
	}

	public void setNomeq(String nomeq) 
	{
		this.nomeq = nomeq;
	}

	public String getDirector() 
	{
		return director;
	}

	public void setDirector(String director) 
	{
		this.director = director;
	}

	public String getLogo() 
	{
		return logo;
	}

	public void setLogo(String logo) 
	{
		this.logo = logo;
	}

}
