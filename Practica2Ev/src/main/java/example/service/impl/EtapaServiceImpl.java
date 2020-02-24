package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Etapa;
import example.model.EtapaModel;
import example.repository.EtapaJpaRepository;
import example.service.EtapaService;

@Service
public class EtapaServiceImpl implements EtapaService {
	
	
	@Autowired
	@Qualifier("etapaJpaRepository")
	private EtapaJpaRepository etapaJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<EtapaModel> listAllEtapa() {
		//return etapaJpaRepository.findAll();
		List<EtapaModel> etapaModel=new ArrayList<EtapaModel>();
		for(Etapa etapa: etapaJpaRepository.findAll())
			etapaModel.add(entity2model(etapa));
		return etapaModel;
	}

	@Override
	public Etapa addEtapa(EtapaModel etapaModel) {
		Etapa etapa=model2entity(etapaModel);
		return etapaJpaRepository.save(etapa);
	}

	@Override
	public int removeEtapa(int id) {
		etapaJpaRepository.deleteById(id);
		return 0;
	}


	@Override
	public Etapa updateEtapa(EtapaModel etapaModel) {
		Etapa etapa=model2entity(etapaModel);
		return etapaJpaRepository.save(etapa);
	}

	@Override
	public Etapa model2entity(EtapaModel etapaModel) {
		return config.mapped().map(etapaModel, Etapa.class);
	}

	@Override
	public EtapaModel entity2model(Etapa etapa) {
		return config.mapped().map(etapa, EtapaModel.class);
	}

	
	

}
