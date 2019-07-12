package funcionesWord.v2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagWord {
	private String codigoTag;
	private String tipologia;
	private String tipoCampo;
	private String textoSolicitud;
	private String respuesta;
	private String textoAyuda;
	

	public TagWord() {
		
	}
	
	public TagWord(String codigoTag, String tipoCampo, String textoSolicitud, String ayuda) {
		this.codigoTag = codigoTag;
		this.tipoCampo = tipoCampo;
		this.textoSolicitud = textoSolicitud;
		this.respuesta = "";
		this.textoAyuda = ayuda;
	}
	
	public TagWord(String codigoTag, String tipoCampo, String textoSolicitud) {
		this.codigoTag = codigoTag;
		this.tipoCampo = tipoCampo;
		this.textoSolicitud = textoSolicitud;
		this.respuesta = "";
		this.textoAyuda = "";
	}
}
