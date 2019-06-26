package funcionesWord;

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

	public String getCodigoTag() {
		return codigoTag;
	}

	public void setCodigoTag(String codigoTag) {
		this.codigoTag = codigoTag;
	}

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public String getTextoSolicitud() {
		return textoSolicitud;
	}

	public void setTextoSolicitud(String textoSolicitud) {
		this.textoSolicitud = textoSolicitud;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

}
