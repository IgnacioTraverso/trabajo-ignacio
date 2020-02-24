package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Puerto;
import example.model.PuertoModel;
import example.repository.PuertoJpaRepository;
import example.service.PuertoService;

@Service
public class PuertoServiceImpl implements PuertoService {
	
	
	@Autowired
	@Qualifier("puertoJpaRepository")
	private PuertoJpaRepository puertoJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<PuertoModel> listAllPuerto() {
		//return puertoJpaRepository.findAll();
		List<PuertoModel> puertoModel=new ArrayList<PuertoModel>();
		for(Puerto puerto: puertoJpaRepository.findAll())
			puertoModel.add(entity2model(puerto));
		return puertoModel;
	}

	@Override
	public Puerto addPuerto(PuertoModel puertoModel) {
		Puerto puerto=model2entity(puertoModel);
		return puertoJpaRepository.save(puerto);
	}

	@Override
	public int removePuerto(String nompuerto) {
		puertoJpaRepository.deleteById(nompuerto);
		return 0;
	}


	@Override
	public Puerto updatePuerto(PuertoModel puertoModel) {
		Puerto puerto=model2entity(puertoModel);
		return puertoJpaRepository.save(puerto);
	}

	@Override
	public Puerto model2entity(PuertoModel puertoModel) {
		return config.mapped().map(puertoModel, Puerto.class);
	}

	@Override
	public PuertoModel entity2model(Puerto puerto) {
		return config.mapped().map(puerto, PuertoModel.class);
	}

	

}