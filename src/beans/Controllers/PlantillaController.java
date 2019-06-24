package beans.Controllers;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import beans.PlantillaBean;
import domain.PlantillaBO;
import domain.TagPlantillaBO;
import servicio.PlantillaService;
import servicio.TagsSearchFunctions;
import servicio.TagsSearchFunctionsImpl;

import static java.nio.file.StandardCopyOption.*;

@ManagedBean
@ViewScoped
public class PlantillaController {

	@ManagedProperty(value = "#{plantillaBean}")
	private PlantillaBean plantilla;

	@Inject
	private PlantillaService plantillaService;

	//@Inject
	private TagsSearchFunctions buscarTags = new TagsSearchFunctionsImpl();

	private String folder = "c:\\uploads";
	private String nombreDelFichero;

	public PlantillaBean getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaBean plantilla) {
		this.plantilla = plantilla;
	}

	public String upload() throws InvalidFormatException, FileNotFoundException, IOException {
		String nombreDelficheroCompleto = getFileName(plantilla.getUploadedFile());
		String fichero = plantilla.getUploadedFile().toString();
		nombreDelFichero = nombreDelficheroCompleto.substring(nombreDelficheroCompleto.lastIndexOf("\\") + 1, nombreDelficheroCompleto.length());
		File copied = new File(folder + File.separator + nombreDelFichero);

		String tmp = fichero.substring((fichero.indexOf("StoreLocation") + 14));
		int hasta = tmp.indexOf(",");
		tmp = tmp.substring(0, hasta);
		InputStream in = new BufferedInputStream(new FileInputStream(tmp));

		Files.copy(in, copied.toPath(), REPLACE_EXISTING);
		

		buscarTags.searchTags(folder, nombreDelFichero);
		
		saveData();
		
		return "ok";
	}

	/**
	 * Utility method to get file name from HTTP header content-disposition
	 */
	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		System.out.println("content-disposition header= " + contentDisp);
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}

	private void saveData() {
		PlantillaBO datosPlantilla = new PlantillaBO();
		datosPlantilla.setNombre(plantilla.getNombre());
		datosPlantilla.setNombreDelDocumento(nombreDelFichero);
		datosPlantilla.setFechaCreacion(new Date());
			
		buscarTags.getTags().keySet().forEach((key) -> buscarTags.getTags().get(key).forEach((tag) -> {
			TagPlantillaBO  tagPlantilla = new TagPlantillaBO();
			tagPlantilla.setSeccion(key);
			tagPlantilla.setTipoDeCampo(tag.getTipoCampo());
			tagPlantilla.setTextopregunta(tag.getTextoSolicitud());
			datosPlantilla.addTagplantilla(tagPlantilla);
		}));
		
		buscarTags.getTags().keySet().forEach((key) -> buscarTags.getTags().get(key).forEach((tag) -> System.out.println("Key: "+ key +" "+ tag.getTipoCampo()+" "+tag.getTextoSolicitud())));
				
		plantillaService.registrarPlantilla(datosPlantilla);
	}

}
