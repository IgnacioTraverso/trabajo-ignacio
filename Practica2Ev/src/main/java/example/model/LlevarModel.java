package example.model;


public class LlevarModel {

	private int id;
	
	private CiclistaModel dorsal;
	
	private EtapaModel netapa;
	
	private MaillotModel codigo;
	
	
	public LlevarModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CiclistaModel getDorsal() {
		return dorsal;
	}

	public void setDorsal(CiclistaModel dorsal) {
		this.dorsal = dorsal;
	}

	public EtapaModel getNetapa() {
		return netapa;
	}

	public void setNetapa(EtapaModel netapa) {
		this.netapa = netapa;
	}

	public MaillotModel getCodigo() {
		return codigo;
	}

	public void setCodigo(MaillotModel codigo) {
		this.codigo = codigo;
	}

	

	
}
