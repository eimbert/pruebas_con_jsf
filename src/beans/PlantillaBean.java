package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import beans.Controllers.CumplimentarController;
import beans.Controllers.MenuController;
import domain.PlantillaBO;
import domain.TagPlantillaBO;
import lombok.Getter;
import lombok.Setter;
import servicio.Interfaces.PlantillaService;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class PlantillaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlantillaService plantillaService;
	
	private String nombre;
	private String modelo;
	private String version;
	private String usuario;
	private Date fechaCreacion;
	private Date fechaValidez;
	private int validada;
	private List<PlantillaBO> plantillas;

	private Part uploadedFile;

	@PostConstruct
    public void init() {
		plantillas = plantillaService.listarPlantillas();
	}
	
	public Boolean getValidacion(int estaValidada) {
		if(estaValidada==0)
			return false;
		else return true;
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Campo editado");//, ((PlantillaBO) event.getObject()).getNombre()());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		//service.guardarDatos();
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada");// , ((PlantillaBO)
																	// event.getObject()).getIdPlantilla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
