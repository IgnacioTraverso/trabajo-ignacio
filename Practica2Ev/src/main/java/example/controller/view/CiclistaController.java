package example.controller.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import example.validator.CiclistaValidator;
import example.constantes.Constantes;
import example.service.*;
import example.model.*;

@Controller
@RequestMapping("/practica")
public class CiclistaController {

	private static final Log LOGGER = LogFactory.getLog(CiclistaController.class);
	
	@Autowired
	@Qualifier("ciclistaServiceImpl")
	private CiclistaService ciclistaService;
	
	@Autowired
	@Qualifier("equipoServiceImpl")
	private EquipoService equipoService;
	
	@Autowired
	@Qualifier("llevarServiceImpl")
	private LlevarService llevarService;
	
	private CiclistaValidator validator=new CiclistaValidator();
	
	
	@GetMapping("/ciclistas")
	public ModelAndView listCiclista() {
		ModelAndView mav=new ModelAndView(Constantes.CICLISTAS_VIEW);
		mav.addObject("ciclistas",ciclistaService.listAllCiclista());
		LOGGER.info("Lista ciclistas");
		return mav;
	}
	
	@GetMapping("/Ciclistaform")
	public String FormularioCiclistas(@RequestParam(name="id") int id, Model model) {
		CiclistaModel ciclistaModel=new CiclistaModel();
		if(id!=-1) {
			List<CiclistaModel> list=ciclistaService.listAllCiclista();
			for(CiclistaModel cmodel:list) {
				if(cmodel.getId()==id) {
					ciclistaModel=cmodel;
				}
			}
		}
		model.addAttribute("ciclista",ciclistaModel);
		model.addAttribute("equipos", equipoService.listAllEquipo());
		LOGGER.info("Vista del formulario");
	
		return Constantes.CICLISTA;
	}
	
	@PostMapping("/addCiclista")
	public ModelAndView addCiclista(@RequestParam(name="nombre") String nombre,@RequestParam("imagen") MultipartFile foto, @Valid @ModelAttribute("ciclista") CiclistaModel ciclistaModel, BindingResult result, RedirectAttributes flash, Model model) {
		
		ModelAndView mav=new ModelAndView();
		EquipoModel equipoModel=new EquipoModel();
		List<EquipoModel> list=equipoService.listAllEquipo();
		for(EquipoModel emodel:list) {
			if(emodel.getNomeq().equals(nombre)) {
				equipoModel=emodel;
			}
		}
		this.validator.validate(ciclistaModel, result);
		if(result.hasErrors()) {
			mav.setViewName(Constantes.CICLISTA);
			model.addAttribute("equipos", equipoService.listAllEquipo());
			LOGGER.info("error"+result.getFieldError());
		}else {
			LOGGER.info("Agregando");
			if(!foto.isEmpty()) {
				Path sourceimg=Paths.get("/src//main//resources//static/imagenes");
				String rootPath=sourceimg.toFile().getAbsolutePath();
				try {
					byte[] bytes=foto.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+ciclistaModel.getNombre()+".png");
					Files.write(rutaCompleta,bytes);
					ciclistaModel.setFoto("/imagenes/"+ciclistaModel.getNombre()+".png");
				}catch(IOException e) {
					e.printStackTrace();
				}
			}else {
				ciclistaModel.setFoto("/imagenes/ciclista.png");
			}
		}
		ciclistaModel.setNomeq(equipoModel);
		ciclistaService.addCiclista(ciclistaModel);
		mav.setViewName("redirect:/practica/ciclistas");
		flash.addFlashAttribute("success","Añadido");
		return mav;
	}
	
	@PostMapping("/removeCiclista")
	public String removeCiclista(@RequestParam(name="id") int id, RedirectAttributes flash) {
		ciclistaService.removeCiclista(id);
		flash.addFlashAttribute("success","Eliminado");
		return "redirect:/practica/ciclistas";
	}

}


	