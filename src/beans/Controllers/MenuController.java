package beans.Controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import beans.PlantillaBean;
import domain.PlantillaBO;
import lombok.Getter;
import lombok.Setter;
import servicio.Interfaces.PlantillaService;


@Getter
@Setter
@ManagedBean(name = "menuController")
@SessionScoped
public class MenuController {

//	private String nombre;
//	private String modelo;
//	private String version;
//	private String usuario;
//	private String fechaCreacion;

	
	private String document;
	
	@Inject
	private PlantillaService plantillaService;
	
	public String optionPlantilla(String opc) {
		return opc;

	}
	
//	public List<PlantillaBO> getPlantillas(){
//		return plantillaService.listarPlantillasNoEditadass();
//	}
	
	
	
//	public void onDocumentChange() {
//		PlantillaBO registro= plantillaService.encontrarPlantillaId(Integer.parseInt(document));
//		setNombre(registro.getNombre());
//		setModelo(registro.getModelo());
//		setVersion(registro.getVersion()+"");
//		setFechaCreacion(registro.getFechaCreacion().toString());
//		
//	}
	


}
