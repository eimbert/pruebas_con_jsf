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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQueries({
    @NamedQuery(name = "Plantilla.findAll", query = "SELECT p FROM PlantillaBO p")
   ,@NamedQuery(name = "Plantilla.findUnEdited", query = "SELECT p FROM PlantillaBO p WHERE p.editada = 0 ORDER BY p.idPlantilla DESC") 
   ,@NamedQuery(name = "Plantilla.findEdited", query = "SELECT p FROM PlantillaBO p WHERE p.editada = 1 ORDER BY p.idPlantilla DESC")
   ,@NamedQuery(name = "Plantilla.findByNombre", query = "SELECT p FROM PlantillaBO p WHERE p.nombre = :nombre")})
@Table(name = "plantillas")
public class PlantillaBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plantilla")
	private int idPlantilla;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "version", nullable = false)
	private String version;
	
	@Column(name = "nombre_del_documento", nullable = false)
	private String nombreDelDocumento;
	
	@Column(name = "usuario", nullable = false)
	private String usuario;
	
	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Column(name = "id_tipologia", nullable = false)
	private int idTipologia;
	
	@Column(name = "id_grupo_usuarios", nullable = false)
	private int idGrupoUsuarios;
	
	@Column(name = "valida")
	private String validada;
	
	@Column(name = "editada")
	private int editada;
	
	@OneToMany(mappedBy="plantilla", cascade = CascadeType.ALL)
	private List<TagPlantillaBO> tagPlantilla  = new ArrayList<TagPlantillaBO>();


	
	public void addTagplantilla(TagPlantillaBO tag) {
		this.tagPlantilla.add(tag);
	}
}
