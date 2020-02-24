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
import org.springframework.security.access.prepost.PreAuthorize;
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
import example.model.EquipoModel;
import example.service.EquipoService;
import example.constantes.Constantes;
import example.validator.EquipoValidator;

@Controller
@RequestMapping("/practica")
public class EquipoController {
	
	@Autowired
	@Qualifier("equipoServiceImpl")
	private EquipoService equipoService;
	
	private EquipoValidator validator=new EquipoValidator();
	
	private static final Log LOGGER=LogFactory.getLog(EquipoController.class);
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EQUIPO')")
	@GetMapping("/equipos")
	public ModelAndView listEquipos() {
		ModelAndView mav=new ModelAndView(Constantes.EQUIPO_VIEW);
		mav.addObject("equipos",equipoService.listAllEquipo());
		LOGGER.info("Lista equipos");
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EQUIPO')")
	@GetMapping("/Equipoform")
	public String formularioEquipo(@RequestParam(name="nomeq") String nomeq, Model model) {
		EquipoModel equipoModel=new EquipoModel();
		if(nomeq!="") {
			List<EquipoModel> list=equipoService.listAllEquipo();
			for(EquipoModel emodel:list) {
				if(emodel.getNomeq().equals(nomeq)) {
					equipoModel=emodel;
				}
			}
		}
		model.addAttribute("equipo",equipoModel);	
		LOGGER.info("formulario");
		return Constantes.EQUIPO;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EQUIPO')")
	@PostMapping("/addEquipo")
	public ModelAndView addEquipo(@Valid @ModelAttribute("equipo") EquipoModel equipoModel,@RequestParam("imagen") MultipartFile foto, BindingResult result, RedirectAttributes flash, Model model) {
		ModelAndView mav=new ModelAndView();
		this.validator.validate(equipoModel, result);
		if(result.hasErrors()) 
		{
			mav.setViewName(Constantes.EQUIPO);	
		}else 
		{
			if(!foto.isEmpty()) {
				Path sourceimg=Paths.get("/src//main//resources//static/imagenes");
				String rootPath=sourceimg.toFile().getAbsolutePath();
				try {
					byte[] bytes=foto.getBytes();
					Path rutaCompleta=Paths.get(rootPath+"//"+equipoModel.getNomeq()+".png");
					Files.write(rutaCompleta,bytes);
					equipoModel.setLogo("/imagenes/"+equipoModel.getNomeq()+".png");
				}catch(IOException e) {
					e.printStackTrace();
				}
			}else {
				equipoModel.setLogo("/imagenes/equipo.jpg");
			}
			
		}
		equipoService.addEquipo(equipoModel);
		mav.setViewName("redirect:/practica/equipos");
		flash.addFlashAttribute("success","Añadido");
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EQUIPO')")
	@PostMapping("/removeEquipo")
	public String removeEquipo(@RequestParam(name="nomeq") String nomeq, RedirectAttributes flash) {
		@SuppressWarnings("unused")
		EquipoModel equipoModel=new EquipoModel();
		if(nomeq!="") {
			List<EquipoModel> list=equipoService.listAllEquipo();
			for(EquipoModel emodel:list) {
				if(emodel.getNomeq().equals(nomeq)) {
					equipoModel=emodel;
				}
			}
		}
		equipoService.removeEquipo(nomeq);
		flash.addFlashAttribute("success","Eliminado");
		return "redirect:/practica/equipos";
	}
	
}