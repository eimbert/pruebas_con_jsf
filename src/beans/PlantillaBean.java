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
public class PlantillaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String modelo;
	private String version;
	private String usuario;
	private Date fechaCreacion;
	private Date fechaValidez;
	
	private Part uploadedFile;

	
}
