package example.service;
import java.util.List;

import example.entity.Ciclista;
import example.model.CiclistaModel;

public interface CiclistaService {
	
	public abstract Ciclista model2entity(CiclistaModel ciclistaModel);
	
	public abstract CiclistaModel entity2model(Ciclista ciclista);

	public abstract List<CiclistaModel> listAllCiclista();
	
	public abstract Ciclista addCiclista(CiclistaModel ciclistaModel);
	
	public abstract int removeCiclista(int id);
	
	public abstract Ciclista updateCiclista(CiclistaModel ciclistaModel);
	
	//public abstract Object mostrarCiclistaParaModificar(int id);
}
