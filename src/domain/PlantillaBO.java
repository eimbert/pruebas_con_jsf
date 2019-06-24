package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries({
    @NamedQuery(name = "Plantilla.findAll", query = "SELECT p FROM PlantillaBO p")
   ,@NamedQuery(name = "Plantilla.findByNombre", query = "SELECT p FROM PlantillaBO p WHERE p.nombre = :nombre")})
@Table(name = "plantillas")
public class PlantillaBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plantillas")
	private int idPlantilla;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "version", nullable = false)
	private int version;
	
	@Column(name = "nombre_del_documento", nullable = false)
	private String nombreDelDocumento;
	
	@Column(name = "usuario", nullable = false)
	private String usuario;
	
	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Column(name = "fecha_validez", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaValidez;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_tags_plantilla")
	private List<TagPlantillaBO> tagPlantilla  = new ArrayList<TagPlantillaBO>();


	public PlantillaBO() {
		super();
		
	}

	public PlantillaBO(String nombre, String modelo, int version, String usuario, Date fechaCreacion, Date fechaValidez) {
		super();
		this.nombre = nombre;
		this.modelo = modelo;
		this.version = version;
		this.usuario = usuario;
		this.fechaCreacion = fechaCreacion;
		this.fechaValidez = fechaValidez;
	}
	
	

	public PlantillaBO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdPlantilla() {
		return idPlantilla;
	}

	public void setIdPlantilla(int idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreDelDocumento() {
		return nombreDelDocumento;
	}

	public void setNombreDelDocumento(String nombreDelDocumento) {
		this.nombreDelDocumento = nombreDelDocumento;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaValidez() {
		return fechaValidez;
	}

	public void setFechaValidez(Date fechaValidez) {
		this.fechaValidez = fechaValidez;
	}
	
	public void addTagplantilla(TagPlantillaBO tag) {
		this.tagPlantilla.add(tag);
	}
	
	public List<TagPlantillaBO> getTagPlantilla() {
		return tagPlantilla;
	}

	public void setTagPlantilla(List<TagPlantillaBO> tagPlantilla) {
		this.tagPlantilla = tagPlantilla;
	}

}
