package beans.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import domain.Documento;
import domain.PlantillaBO;
import funcionesWord.v2.Constants;
import servicio.Interfaces.DocumentoService;
import servicio.Interfaces.PlantillaService;
import servicio.Interfaces.WordReplaceService;

@ManagedBean
@ViewScoped
public class GenerarWordController {

	@Inject
	private WordReplaceService wordReplace; 
	
	@Inject
	private PlantillaService plantillaService;
	
	@Inject
	private DocumentoService documentoService;
	
	
	public String newWord(String document) throws FileNotFoundException, InvalidFormatException, IOException {
		PlantillaBO registro= plantillaService.encontrarPlantillaId(Integer.parseInt(document));
		wordReplace.replaceTags(Constants.IN_PATH, Constants.OUT_PATH, registro.getNombreDelDocumento(), registro.getTagPlantilla());
		
		saveNewDocument(registro);
		
		return "index";
	}
	
	private void saveNewDocument(PlantillaBO plantilla) {
		Documento documento = new Documento();
		documento.setNombre(plantilla.getNombreDelDocumento());
		documento.setFechaCreacion(new Date());
		documento.setValidado("false");
		documento.setIdPlantilla(plantilla.getIdPlantilla());
		
		documentoService.guardarDocumento(documento);
	}
}
