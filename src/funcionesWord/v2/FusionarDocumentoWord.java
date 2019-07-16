package funcionesWord.v2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class FusionarDocumentoWord extends LeerDocumentoWord{

	private List<DatosEtiqueta> docFragmentado = new ArrayList<DatosEtiqueta>();
	
	private void leerDocumento(String codInicio, String codFinal) throws FileNotFoundException, InvalidFormatException, IOException {
		
		this.openDocument();
		this.leerParrafosEnTexto();
		this.leerParrafosEnTablas();
		
		for(int indice = 0; indice < runs.size(); indice++) {
			DatosEtiqueta parrafo = new DatosEtiqueta(new FragmentoDelDocumento(runs.get(indice)));
			if(parrafo.buscarCompleta(codInicio, codFinal)) {
				parrafo.setParrafoConEtiqueta(true);
				docFragmentado.add(parrafo);
			}else {
				if(!parrafo.buscarInicioEtiqueta(codInicio)) {
					parrafo.setParrafoConEtiqueta(false);
					docFragmentado.add(parrafo);
				}else {
					boolean seEncuentreFinalEtiqueta = false;
					String textoParcialParrafo = "";
					int auxInd = indice;
					docFragmentado.add(parrafo);
					do {
						parrafo = new DatosEtiqueta(new FragmentoDelDocumento(runs.get(++indice)));
						seEncuentreFinalEtiqueta = parrafo.buscarFinalEtiqueta(codFinal);
						parrafo.setEliminarParrafo(true);
						textoParcialParrafo += parrafo.getDocFragment().getTextoParrafo();
						parrafo.getDocFragment().escribirParrafo(""); //borrar el contenido del parrafo
						docFragmentado.add(parrafo);
					}while(seEncuentreFinalEtiqueta==false && indice <  runs.size()-1 && !textoParcialParrafo.contains(codFinal));
					docFragmentado.get(auxInd).juntarTexto(textoParcialParrafo);
					docFragmentado.get(auxInd).setParrafoConEtiqueta(true);
				}
			}
		}
	}
	
	public void fusionarDatos(List<TagWord> valoresCampo, String codInicio, String codFinal) throws FileNotFoundException, InvalidFormatException, IOException {
		leerDocumento(codInicio, codFinal);
		for(DatosEtiqueta parrafo : docFragmentado) {
			for(TagWord tag : valoresCampo)
			if(parrafo.getParrafoConEtiqueta() && parrafo.getEtiqueta(codInicio, codFinal).equals(tag.getCodigoTag())) {
				parrafo.getDocFragment().escribirParrafo(parrafo.getDocFragment().getTextoParrafo().replace(tag.getCodigoTag(), tag.getRespuesta()));
				//parrafo.getDocFragment().escribirParrafo(parrafo.getDocFragment().getTextoParrafo().replace(tag.getCodigoTag(), "[WHITE RABBIT]"));
				break;
			}
			
		}
	}
}
//	protected void fusionar(List<DatosEtiqueta> docFragmentado) {
//		docFragmentado.stream().forEach(parrafo -> {
//			if(parrafo.getEliminarParrafo()==false)
//				if(parrafo.getParrafoConEtiqueta()==true)
//					parrafo.getDocFragment().escribirParrafo("[AQUÍ ETIQUETA]");	//Escribir el contenido de la etiqueta
//				else
//					parrafo.getDocFragment().escribirParrafo();	
//			else 
//				(parrafo).getDocFragment().escribirParrafo("");	
//		});
//	}


