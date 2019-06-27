package funcionesWord;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagWord {
	private String codigoTag;
	private String tipoCampo;
	private String textoSolicitud;
	private String respuesta;

	public TagWord(String codigoTag, String tipoCampo, String textoSolicitud) {
		this.codigoTag = codigoTag;
		this.tipoCampo = tipoCampo;
		this.textoSolicitud = textoSolicitud;
		this.respuesta = "";
	}
}
