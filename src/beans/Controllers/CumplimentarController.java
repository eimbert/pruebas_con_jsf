package beans.Controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import domain.PlantillaBO;
import domain.TagPlantillaBO;
import servicio.PlantillaService;

@ManagedBean
@SessionScoped
public class CumplimentarController {

	@Inject
	private PlantillaService plantillaService;
	
	PlantillaBO plantilla;
	
	public void findPlantilla(int idPlantilla) {
		plantilla = plantillaService.encontrarPlantillaId(idPlantilla);
	}
	public List<TagPlantillaBO> getTags(int idPlantilla) {
		findPlantilla(idPlantilla);
		return plantilla.getTagPlantilla();
	}
}
