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
    @NamedQuery(name = "PlantillaBase.findAll", query = "SELECT p FROM PlantillaBaseBO p")
   ,@NamedQuery(name = "PlantillaBase.findByNombre", query = "SELECT p FROM PlantillaBO p WHERE p.nombre = :nombre")})

@Table(name = "plantillas_base")
public class PlantillaBaseBO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plantillas_base")
	private int idPlantilla;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "modelo", nullable = false)
	private String modelo;
	
	@Column(name = "version", nullable = false)
	private String version;
	
	@Column(name = "nombre_del_documento", nullable = false)
	private String nombreDelDocumento;
	
	@Column(name = "id_usuario", nullable = false)
	private int idUsuario;
	
	@Column(name = "fecha_de_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	
	@Column(name = "valida")
	private String validada;

	
}
