package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.Llevar;
import example.model.LlevarModel;
import example.repository.LlevarJpaRepository;
import example.service.LlevarService;


@Service
public class LlevarServiceImpl implements LlevarService {
	
	
	@Autowired
	@Qualifier("llevarJpaRepository")
	private LlevarJpaRepository llevarJpaRepository;
 
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public List<LlevarModel> listAllLlevar() {
		//return llevarJpaRepository.findAll();
		List<LlevarModel> llevarModel=new ArrayList<LlevarModel>();
		for(Llevar llevar: llevarJpaRepository.findAll())
			llevarModel.add(entity2model(llevar));
		return llevarModel;
	}

	@Override
	public Llevar addLlevar(LlevarModel llevarModel) {
		Llevar llevar=model2entity(llevarModel);
		return llevarJpaRepository.save(llevar);
	}

	@Override
	public Llevar model2entity(LlevarModel llevarModel) {
		return config.mapped().map(llevarModel, Llevar.class);
	}

	@Override
	public LlevarModel entity2model(Llevar llevar) {
		return config.mapped().map(llevar, LlevarModel.class);
	}

	@Override
	public boolean check(int idC, int idE, String idM) {
		boolean checked=false;
		List<LlevarModel> listLlevar=listAllLlevar();
		for(LlevarModel llevarmodel: listLlevar) 
		{
			if(llevarmodel.getDorsal().getId()==idC && llevarmodel.getCodigo().getCodigo().equals(idM) && llevarmodel.getNetapa().getNetapa()==idE) 
			{
				checked=true;
			}
		}
		return checked;
	}

}
