package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tags_plantilla")
public class TagPlantillaBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tags_plantilla")
	private int idTagPlantilla;

	@Column(name = "seccion", nullable = false)
	private String seccion;

	@Column(name = "tipo_campo", nullable = false)
	private String tipoDeCampo;

	@Column(name = "texto_pregunta", nullable = false)
	private String textopregunta;

	@Column(name = "respuesta")
	private String respuesta;

	@Column(name = "codigo_etiqueta")
	private String codigoEtiqueta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_plantilla", referencedColumnName = "id_plantilla")
	private PlantillaBO plantilla;

	public TagPlantillaBO() {
	
	}
}
