package beans.Controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import beans.PlantillaBean;
import domain.PlantillaBO;
import servicio.PlantillaService;



@ManagedBean
@SessionScoped
public class MenuController {

	private String nombre;
	private String modelo;
	private String version;
	private String usuario;
	private String fechaCreacion;
	private String fechaValidez;	
	
	private String document;
	
	@Inject
	private PlantillaService plantillaService;
	
	public String optionPlantilla(String opc) {
		if(opc.equals("new"))
			return "new";
		else if(opc.equals("list"))
			return "list";
		else if(opc.equals("completar"))
			return "completar";
		else if(opc.equals("rellenar"))
			return "rellenar";
		else if(opc.equals("logout"))
			return "index";
		else return "index";

	}
	
	public List<PlantillaBO> getPlantillas(){
		return plantillaService.listarPlantillas();
	}
	
	public void onDocumentChange() {
		PlantillaBO registro= plantillaService.encontrarPlantillaId(Integer.parseInt(document));
		setNombre(registro.getNombre());
		setModelo(registro.getModelo());
		setVersion(registro.getVersion()+"");
		setFechaCreacion(registro.getFechaCreacion().toString());
		setFechaValidez(registro.getFechaValidez().toString());
		
	}
	
	public void modificarPlantilla() {
		
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaValidez() {
		return fechaValidez;
	}

	public void setFechaValidez(String fechaValidez) {
		this.fechaValidez = fechaValidez;
	}

	public PlantillaService getPlantillaService() {
		return plantillaService;
	}

	public void setPlantillaService(PlantillaService plantillaService) {
		this.plantillaService = plantillaService;
	}
	

}
