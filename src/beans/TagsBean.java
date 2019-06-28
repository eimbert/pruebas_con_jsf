package beans;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.tangosol.coherence.component.util.Collections;

import beans.Controllers.CumplimentarController;
import beans.Controllers.MenuController;
import domain.TagPlantillaBO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@ViewScoped
public class TagsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TagPlantillaBO> etiquetas;
	private String seccion;
	private int idPlantilla;

	@ManagedProperty("#{tagService}")
	private CumplimentarController service;

	@ManagedProperty("#{menuController}")
	private MenuController menuService;

	@PostConstruct
	public void init() {
		idPlantilla = Integer.parseInt(menuService.getDocument());
		etiquetas = service.getTags(idPlantilla);
	}

	public List<String> getSecciones() {
		return service.getSecciones();
	}

	public List<TagPlantillaBO> getEtiquetas() {
		return etiquetas;
	}

	public void onSeccionChange() {
		etiquetas = service.getTags(idPlantilla);
		if (seccion!=null && !seccion.equals("Mostrar todas las secciones"))
			etiquetas = etiquetas.stream().filter(tag -> tag.getSeccion().contentEquals(seccion)).collect(Collectors.toList());
	}
	
	public void setService(CumplimentarController service) {
		this.service = service;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Campo editado", ((TagPlantillaBO) event.getObject()).getTipoDeCampo());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		service.guardarDatos();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada");
																	
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setMenuService(MenuController menuService) {
		this.menuService = menuService;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
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
