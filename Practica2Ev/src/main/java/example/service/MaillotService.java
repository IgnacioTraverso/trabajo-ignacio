package example.service;
import java.util.List;

import example.entity.Maillot;
import example.model.MaillotModel;

public interface MaillotService {
	public abstract Maillot model2entity(MaillotModel maillotModel);
	public abstract MaillotModel entity2model(Maillot maillot);

	public abstract List<MaillotModel> listAllMaillot();
	public abstract Maillot addMaillot(MaillotModel maillotModel);
	public abstract int removeMaillot(String codigo);
	
	public abstract Maillot updateMaillot(MaillotModel maillotModel);
	
	//public abstract Object mostrarMaillotParaModificar(String codigo);
}
