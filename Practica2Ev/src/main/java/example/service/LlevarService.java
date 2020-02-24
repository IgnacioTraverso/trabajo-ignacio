package example.service;
import java.util.List;

import example.entity.Llevar;
import example.model.LlevarModel;

public interface LlevarService {
	public abstract Llevar model2entity(LlevarModel llevarModel);
	public abstract LlevarModel entity2model(Llevar llevar);

	public abstract List<LlevarModel> listAllLlevar();
	public abstract Llevar addLlevar(LlevarModel llevarModel);
	
	public abstract boolean check(int idC, int idE, String idM);
	
	//public abstract Object mostrarLlevarParaModificar(int id);
}
