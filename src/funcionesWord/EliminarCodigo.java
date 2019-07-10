package funcionesWord;

import java.util.ArrayList;
import java.util.List;

import funcionesWord.v2.LecturaDelDocumento;

public class EliminarCodigo{

	List<FragmentoDelDocumento> docFragment = new ArrayList<FragmentoDelDocumento>();
	
	public void anadirParrafo(FragmentoDelDocumento parrafo) {
		if(parrafo.getTextoParrafo()!=null)
			docFragment.add(parrafo);
	}
	
	public void borrarCodigo(String codigo) {
		docFragment.stream().forEach(parrafo -> parrafo.setTextoParrafo(parrafo.getTextoParrafo().replace(codigo, "")));
		borrarCodigosSueltos(codigo);
	}
	
	public void escribirDocumento() {
		docFragment.stream().forEach(parrafo -> parrafo.escribirParrafo());
	}
	
	private void borrarCodigosSueltos(String codigo) {
		String codigoInicio = codigo.substring(0,1);
		String codigoFinal = codigo.substring(codigo.length()-1);
		
		for(int x = 0; x < docFragment.size()-1; x++) {
			if(docFragment.get(x).getUltimoCarcater().equals(codigoInicio) && docFragment.get(x+1).getPrimerCatacter().equals(codigoFinal) && docFragment.get(x).getTextoParrafo().length() > 0) {
				docFragment.get(x).setTextoParrafo(docFragment.get(x).getTextoParrafo().substring(1));
				docFragment.get(x+1).setTextoParrafo(docFragment.get(x+1).getTextoParrafo().substring(0, docFragment.get(x+1).getTextoParrafo().length()-1));
			}
		}
	}
}
