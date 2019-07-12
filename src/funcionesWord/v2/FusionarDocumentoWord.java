package funcionesWord.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import funcionesWord.Constants;

public class FusionarDocumentoWord extends LeerDocumentoWord{

	private List<DatosEtiqueta> docFragmentado = new ArrayList<DatosEtiqueta>();
	
	public void tratarDocumento(String codInicio, String codFinal, String tipoEtiqueta) {
		for(int indice = 0; indice < docFragmentado.size(); indice++) {
			if(docFragmentado.get(indice).buscarCompleta(codInicio, codFinal)) {
				docFragmentado.get(indice).setParrafoConEtiqueta(true);
			}else {
				if(!docFragmentado.get(indice).buscarInicioEtiqueta(codInicio)) {
					docFragmentado.get(indice).setParrafoConEtiqueta(false);
				}else {
					boolean seEncuentreFinalEtiqueta = false;
					String textoParcialParrafo = "";
					int auxInd = indice;
					do {
						seEncuentreFinalEtiqueta = docFragmentado.get(++indice).buscarFinalEtiqueta(codFinal);
						docFragmentado.get(indice).setEliminarParrafo(true);
						textoParcialParrafo += docFragmentado.get(indice).getDocFragment().getTextoParrafo();
					}while(seEncuentreFinalEtiqueta==false && indice <  docFragmentado.size() && !textoParcialParrafo.contains(codFinal));
					docFragmentado.get(auxInd).juntarTexto(textoParcialParrafo);
					docFragmentado.get(auxInd).setParrafoConEtiqueta(true);
				}
			}
		}
	}
	


}
