package example.service;
import java.util.List;

import example.entity.Puerto;
import example.model.PuertoModel;

public interface PuertoService {
	public abstract Puerto model2entity(PuertoModel puertoModel);
	public abstract PuertoModel entity2model(Puerto puerto);

	public abstract List<PuertoModel> listAllPuerto();
	public abstract Puerto addPuerto(PuertoModel puertoModel);
	public abstract int removePuerto(String nompuerto);
	
	public abstract Puerto updatePuerto(PuertoModel puertoModel);
	
	//public abstract Object mostrarPuertoParaModificar(String nompuerto);
}
