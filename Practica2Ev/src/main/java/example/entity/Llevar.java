package example.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="llevar")
public class Llevar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="dorsal")
	private Ciclista dorsal;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="netapa")
	private Etapa netapa;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@JoinColumn(name="codigo")
	private Maillot codigo;
	
	public Llevar() {}

	public Llevar(int id, Ciclista dorsal, Etapa netapa, Maillot codigo) 
	{
		super();
		this.id =id;
		this.dorsal = dorsal;
		this.netapa = netapa;
		this.codigo = codigo;
	}

	//Getters and setters
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Ciclista getDorsal() 
	{
		return dorsal;
	}

	public void setDorsal(Ciclista dorsal) 
	{
		this.dorsal = dorsal;
	}

	public Etapa getNetapa() 
	{
		return netapa;
	}

	public void setNetapa(Etapa netapa) 
	{
		this.netapa = netapa;
	}

	public Maillot getCodigo() 
	{
		return codigo;
	}

	public void setCodigo(Maillot codigo) 
	{
		this.codigo = codigo;
	}
	
}
