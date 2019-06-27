package beans.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import funcionesWord.Constants;
import domain.PlantillaBO;
import servicio.Interfaces.PlantillaService;
import servicio.Interfaces.WordReplaceService;

@ManagedBean
@SessionScoped
public class GenerarWordController {

	@Inject
	private WordReplaceService wordReplace; 
	
	@Inject
	private PlantillaService plantillaService;
	
	public String newWord(String document) throws FileNotFoundException, InvalidFormatException, IOException {
		PlantillaBO registro= plantillaService.encontrarPlantillaId(Integer.parseInt(document));
		wordReplace.replaceTags(Constants.IN_PATH, Constants.OUT_PATH, registro.getNombreDelDocumento(), registro.getTagPlantilla());
		
		return "index";
	}
}
