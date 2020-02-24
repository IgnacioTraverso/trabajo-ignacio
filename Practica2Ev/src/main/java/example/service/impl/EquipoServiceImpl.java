package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Equipo;
import example.model.EquipoModel;
import example.repository.EquipoJpaRepository;
import example.service.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {
	
	
	@Autowired
	@Qualifier("equipoJpaRepository")
	private EquipoJpaRepository equipoJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<EquipoModel> listAllEquipo() {
		//return equipoJpaRepository.findAll();
		List<EquipoModel> equipoModel=new ArrayList<EquipoModel>();
		for(Equipo equipo: equipoJpaRepository.findAll())
			equipoModel.add(entity2model(equipo));
		return equipoModel;
	}

	@Override
	public Equipo addEquipo(EquipoModel equipoModel) {
		Equipo equipo=model2entity(equipoModel);
		return equipoJpaRepository.save(equipo);
	}

	@Override
	public int removeEquipo(String nomeq) {
		equipoJpaRepository.deleteById(nomeq);
		return 0;
	}


	@Override
	public Equipo updateEquipo(EquipoModel equipoModel) {
		Equipo equipo=model2entity(equipoModel);
		return equipoJpaRepository.save(equipo);
	}

	@Override
	public Equipo model2entity(EquipoModel equipoModel) {
		return config.mapped().map(equipoModel, Equipo.class);
	}

	@Override
	public EquipoModel entity2model(Equipo equipo) {
		return config.mapped().map(equipo, EquipoModel.class);
	}



}