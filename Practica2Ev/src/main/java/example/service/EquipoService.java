package example.service;
import java.util.List;

import example.entity.Equipo;
import example.model.EquipoModel;

public interface EquipoService {
	public abstract Equipo model2entity(EquipoModel equipoModel);
	public abstract EquipoModel entity2model(Equipo equipo);

	public abstract List<EquipoModel> listAllEquipo();
	public abstract Equipo addEquipo(EquipoModel equipoModel);
	public abstract int removeEquipo(String nomeq);
	
	public abstract Equipo updateEquipo(EquipoModel equipoModel);
	
	//public abstract Object mostrarEquipoParaModificar(String dorsal);
}
