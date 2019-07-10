package beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import beans.Controllers.MenuController;
import domain.PlantillaBO;
import domain.PlantillaBaseBO;
import domain.TagPlantillaBO;
import funcionesWord.Constants;
import lombok.Getter;
import lombok.Setter;
import servicio.Interfaces.PlantillaBaseService;
import servicio.Interfaces.PlantillaService;
import servicio.Interfaces.TagsSearchFunctions;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class ListBaseBean implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlantillaBaseService plantillaBaseService;
	
	@Inject
	private PlantillaService plantillaService;
	
	@Inject
	private TagsSearchFunctions buscarTags; 
	
	@ManagedProperty("#{menuController}")
	private MenuController menuService;
	
	private List<PlantillaBaseBO> plantillas;
	private List<String> tipologias;
	private String nombreDocumento;
	private String tipologiaSelected;
	private int IdBasePlantillaSeleccionada;
 
	@PostConstruct
    public void init() {
		plantillas = plantillaBaseService.listarPlantillas();
		tipologias = new ArrayList<String>(Arrays.asList("Tipologia 1", "Tipologia 2", "Tipologia 3"));
	}

	public String booleanToString(Boolean valor){
		if(valor)
			return "Sí";
		else
			return "No";
	}
	
	public void setIdBasePlantilla(int id) {
		this.IdBasePlantillaSeleccionada = id;
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Guardando cambios", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		plantillaBaseService.modificarPlantilla((PlantillaBaseBO) event.getObject());
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}	
	
	public void nuevoDocumento() throws FileNotFoundException, InvalidFormatException, IOException {
		PlantillaBO documento = new PlantillaBO();
		PlantillaBaseBO plantillaBase = plantillaBaseService.encontrarPlantillaId(IdBasePlantillaSeleccionada);
		documento.setNombre(nombreDocumento);
		documento.setFechaCreacion(new Date());

		documento.setIdTipologia(0);
		documento.setIdPlantilla(plantillaBase.getIdPlantilla());
		documento.setModelo(plantillaBase.getModelo());
		
		
		buscarTags.searchTags(Constants.IN_PATH, plantillaBase.getNombreDelDocumento());
		buscarTags.getTags().keySet().forEach((key) -> buscarTags.getTags().get(key).forEach((tag) -> {
			TagPlantillaBO  tagPlantilla = new TagPlantillaBO();
			tagPlantilla.setSeccion(key);
			tagPlantilla.setTipoDeCampo(tag.getTipoCampo());
			tagPlantilla.setTextopregunta(tag.getTextoSolicitud());
			tagPlantilla.setCodigoEtiqueta(tag.getCodigoTag());
			tagPlantilla.setTextoAyuda(tag.getTextoAyuda());
			tagPlantilla.setPlantilla(documento);
			documento.addTagplantilla(tagPlantilla);
		}));
		
		plantillaService.registrarPlantilla(documento);
	}
}
