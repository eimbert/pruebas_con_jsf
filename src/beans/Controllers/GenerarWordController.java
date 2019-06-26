package beans.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import domain.PlantillaBO;
import domain.TagPlantillaBO;
import servicio.Interfaces.PlantillaService;
import servicio.Interfaces.TagsSearchFunctions;
import servicio.Interfaces.WordReplaceService;

@ManagedBean
@SessionScoped
public class GenerarWordController {

	private List<TagPlantillaBO> tags;
	
	@Inject
	private WordReplaceService wordReplace; 
	
	@Inject
	private PlantillaService plantillaService;
	
		
	public String newWord(String document) throws FileNotFoundException, InvalidFormatException, IOException {
		PlantillaBO registro= plantillaService.encontrarPlantillaId(Integer.parseInt(document));
		wordReplace.replaceTags("c:\\uploads", "c:\\uploads\\generados", registro.getNombreDelDocumento(), registro.getTagPlantilla());
		
		return "index";
	}
}
