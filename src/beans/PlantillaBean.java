package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
public class PlantillaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String modelo;
	private String version;
	private String usuario;
	private String fechaCreacion;
	private String fechaValidez;

	private Part uploadedFile;

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

}
