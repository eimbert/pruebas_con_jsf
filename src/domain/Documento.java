package domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT p FROM Documento p")})
@Table(name = "documentos_generados")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento")
	private int idDocumento;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Column(name = "validado_por", nullable = false)
	private String validadoPor;

	@Column(name = "validado")
	private String validado;

	@Column(name = "id_plantilla_origen")
	private int idPlantilla;
	
}
