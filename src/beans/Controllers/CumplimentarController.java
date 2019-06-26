package beans.Controllers;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.poi.ss.formula.functions.T;

import domain.PlantillaBO;
import domain.TagPlantillaBO;
import servicio.Interfaces.PlantillaService;

@ManagedBean(name = "tagService")
@SessionScoped
public class CumplimentarController {


	@Inject
	private PlantillaService plantillaService;
	
	PlantillaBO plantilla;
	
	public List<TagPlantillaBO>  getTags(int idPlantilla) {
		plantilla = plantillaService.encontrarPlantillaId(idPlantilla);
		return plantilla.getTagPlantilla();
	}

	public void guardarDatos() {
		plantillaService.modificarPlantilla(plantilla);
	}
	
	public List<String> getSecciones(){
		List<String> secciones = new ArrayList<String>();
		plantilla.getTagPlantilla().forEach((tag) -> secciones.add(tag.getSeccion()));
		return secciones.stream().distinct().collect(Collectors.toList());
	}
	
	public List<PlantillaBO> getplantillasPendientes(){
		return plantillaService.listarPlantillas();
	}
				
}
