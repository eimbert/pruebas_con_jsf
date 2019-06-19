package funcionesWord;

public class Tag {
	private String codigoTag;
	private String tipoCampo;
	private String textoSolicitud;

	public Tag(String codigoTag, String tipoCampo, String textoSolicitud) {
		this.codigoTag = codigoTag;
		this.tipoCampo = tipoCampo;
		this.textoSolicitud = textoSolicitud;
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

}
