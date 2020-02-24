package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Ciclista;
import example.model.CiclistaModel;
import example.repository.CiclistaJpaRepository;
import example.service.CiclistaService;

@Service
public class CiclistaServiceImpl implements CiclistaService {
	
	
	@Autowired
	@Qualifier("ciclistaJpaRepository")
	private CiclistaJpaRepository ciclistaJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<CiclistaModel> listAllCiclista() {
		//return ciclistaJpaRepository.findAll();
		List<CiclistaModel> ciclistaModel=new ArrayList<CiclistaModel>();
		for(Ciclista ciclista: ciclistaJpaRepository.findAll())
			ciclistaModel.add(entity2model(ciclista));
		return ciclistaModel;
	}

	@Override
	public Ciclista addCiclista(CiclistaModel ciclistaModel) {
		Ciclista ciclista=model2entity(ciclistaModel);
		return ciclistaJpaRepository.save(ciclista);
	}

	@Override
	public int removeCiclista(int id) {
		ciclistaJpaRepository.deleteById(id);
		return 0;
	}


	@Override
	public Ciclista updateCiclista(CiclistaModel ciclistaModel) {
		Ciclista ciclista=model2entity(ciclistaModel);
		return ciclistaJpaRepository.save(ciclista);
	}

	@Override
	public Ciclista model2entity(CiclistaModel ciclistaModel) {
		return config.mapped().map(ciclistaModel, Ciclista.class);
	}

	@Override
	public CiclistaModel entity2model(Ciclista ciclista) {
		return config.mapped().map(ciclista, CiclistaModel.class);
	}

	
	

}
