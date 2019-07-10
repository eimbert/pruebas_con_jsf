package funcionesWord.v2;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import funcionesWord.Constants;
import funcionesWord.FragmentoDelDocumento;
import funcionesWord.TagWord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosEtiqueta {

	private FragmentoDelDocumento docFragment;
	
	private TagWord codigoEtiqueta;
	private Boolean parrafoConEtiqueta;
	private Boolean eliminarParrafo;
		
	public DatosEtiqueta(FragmentoDelDocumento docFragment) {
		this.docFragment = docFragment;
		this.parrafoConEtiqueta = false;
		this.eliminarParrafo = false;
	}
	
	public Boolean buscarCompleta(String codInicio, String codFinal) {
		if(docFragment.getTextoParrafo()!=null) {
			if(docFragment.getTextoParrafo().contains(codInicio) && docFragment.getTextoParrafo().contains(codFinal)) {
				return true; //busqueda completa 
			}
		}
		return false; //se deberá hacer una busqueda parcial
	}
	
	public Boolean buscarInicioEtiqueta(String codInicio) {
		if(docFragment.getTextoParrafo()!=null) {
			if(docFragment.getTextoParrafo().contains(codInicio)) {
				return true; //contiene inicio
			}
		}
		return false; //parrafo sin etiqueta
	}
	
	public Boolean buscarFinalEtiqueta(String codFinal) {
		if(docFragment.getTextoParrafo()!=null) {
			if(docFragment.getTextoParrafo().contains(codFinal)) {
				return true; //contiene final
			}
		}
		return false; //parrafo sin etiqueta
	}
	
	public void juntarTexto(String texto) {
		docFragment.addText(texto);
	}	
}
