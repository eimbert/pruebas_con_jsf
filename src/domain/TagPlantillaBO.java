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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_plantilla", referencedColumnName = "id_plantilla")
	private PlantillaBO plantilla;


	public TagPlantillaBO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdTagPlantilla() {
		return idTagPlantilla;
	}

	public PlantillaBO getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(PlantillaBO plantilla) {
		this.plantilla = plantilla;
	}

	public void setIdTagPlantilla(int idTagPlantilla) {
		this.idTagPlantilla = idTagPlantilla;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getTipoDeCampo() {
		return tipoDeCampo;
	}

	public void setTipoDeCampo(String tipoDeCampo) {
		this.tipoDeCampo = tipoDeCampo;
	}

	public String getTextopregunta() {
		return textopregunta;
	}

	public void setTextopregunta(String textopregunta) {
		this.textopregunta = textopregunta;
	}



}
