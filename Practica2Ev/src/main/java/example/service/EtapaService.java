package example.service;
import java.util.List;

import example.entity.Etapa;
import example.model.EtapaModel;

public interface EtapaService {
	public abstract Etapa model2entity(EtapaModel etapaModel);
	public abstract EtapaModel entity2model(Etapa etapa);

	public abstract List<EtapaModel> listAllEtapa();
	public abstract Etapa addEtapa(EtapaModel etapaModel);
	
	public abstract int removeEtapa(int id);
	
	public abstract Etapa updateEtapa(EtapaModel etapaModel);
	
	//public abstract Object mostrarEtapaParaModificar(int netapa);
}
