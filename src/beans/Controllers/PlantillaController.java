package beans.Controllers;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import funcionesWord.v2.Constants;
import funcionesWord.v2.TagWord;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import beans.PlantillaBean;
import domain.PlantillaBO;
import domain.PlantillaBaseBO;
import domain.TagPlantillaBO;
import servicio.TagsSearchFunctionsImpl;
import servicio.Interfaces.PlantillaBaseService;
import servicio.Interfaces.PlantillaService;
import servicio.Interfaces.TagsSearchFunctions;

import static java.nio.file.StandardCopyOption.*;

@ManagedBean
@ViewScoped
public class PlantillaController {

	@ManagedProperty(value = "#{plantillaBean}")
	private PlantillaBean plantilla;

	@Inject
	private PlantillaBaseService plantillaBaseService;

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
		File copied = new File(Constants.IN_PATH + File.separator + nombreDelFichero);

		String tmp = fichero.substring((fichero.indexOf("StoreLocation") + 14));
		int hasta = tmp.indexOf(",");
		tmp = tmp.substring(0, hasta);
		InputStream in = new BufferedInputStream(new FileInputStream(tmp));

		Files.copy(in, copied.toPath(), REPLACE_EXISTING);
		
		saveData();
		
		return "index";
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
		PlantillaBaseBO datosPlantilla = new PlantillaBaseBO();
		datosPlantilla.setNombre(plantilla.getNombre());
		datosPlantilla.setNombreDelDocumento(nombreDelFichero);
		datosPlantilla.setModelo(plantilla.getModelo());
		datosPlantilla.setIdUsuario(plantilla.getIdUsuario());
		datosPlantilla.setVersion(plantilla.getVersion());
		datosPlantilla.setValidada("true");
		
		
		datosPlantilla.setFechaCreacion(new Date());
						
		plantillaBaseService.registrarPlantilla(datosPlantilla);

	}

}
