package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import domain.PlantillaBO;
import lombok.Getter;
import lombok.Setter;
import servicio.Interfaces.PlantillaService;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class ListBean implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Inject
	private PlantillaService plantillaService;
	
	private List<PlantillaBO> plantillas;

	@PostConstruct
    public void init() {
		plantillas = plantillaService.listarPlantillas();
	}

	public String booleanToString(Boolean valor){
		if(valor)
			return "Sí";
		else
			return "No";
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Guardando cambios", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		plantillaService.modificarPlantilla((PlantillaBO) event.getObject());
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada", "");
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
