package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Maillot;
import example.model.MaillotModel;
import example.repository.MaillotJpaRepository;
import example.service.MaillotService;

@Service
public class MaillotServiceImpl implements MaillotService {
	
	
	@Autowired
	@Qualifier("maillotJpaRepository")
	private MaillotJpaRepository maillotJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<MaillotModel> listAllMaillot() {
		//return maillotJpaRepository.findAll();
		List<MaillotModel> maillotModel=new ArrayList<MaillotModel>();
		for(Maillot maillot: maillotJpaRepository.findAll())
			maillotModel.add(entity2model(maillot));
		return maillotModel;
	}

	@Override
	public Maillot addMaillot(MaillotModel maillotModel) {
		Maillot maillot=model2entity(maillotModel);
		return maillotJpaRepository.save(maillot);
	}

	@Override
	public int removeMaillot(String codigo) {
		maillotJpaRepository.deleteById(codigo);
		return 0;
	}


	@Override
	public Maillot updateMaillot(MaillotModel maillotModel) {
		Maillot maillot=model2entity(maillotModel);
		return maillotJpaRepository.save(maillot);
	}

	@Override
	public Maillot model2entity(MaillotModel maillotModel) {
		return config.mapped().map(maillotModel, Maillot.class);
	}

	@Override
	public MaillotModel entity2model(Maillot maillot) {
		return config.mapped().map(maillot, MaillotModel.class);
	}

	
	

}
