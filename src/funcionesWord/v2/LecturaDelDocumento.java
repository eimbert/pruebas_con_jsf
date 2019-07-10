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
import funcionesWord.FragmentoDelDocumento;
import funcionesWord.Constants;

public class LecturaDelDocumento {

	private List<DatosEtiqueta> docFragmentado = new ArrayList<DatosEtiqueta>();
	
	private XWPFDocument document;
	
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
	
	public void leerParrafosEnTexto() {
		for (XWPFParagraph p : document.getParagraphs()) 
			for (XWPFRun r : p.getRuns()) 
				if(r!=null && r.getText(0)!=null)
					docFragmentado.add(new DatosEtiqueta(new FragmentoDelDocumento(r)));
	}
	
	
	public void leerParrafosEnTablas() {
		for (XWPFTable tbl : document.getTables())
			for (XWPFTableRow row : tbl.getRows())
				for (XWPFTableCell cell : row.getTableCells())
					for (XWPFParagraph p : cell.getParagraphs())
						for (XWPFRun r : p.getRuns())
							if(r!=null && r.getText(0)!=null)
								docFragmentado.add(new DatosEtiqueta(new FragmentoDelDocumento(r)));
							
	}
	
	public void escribirDocumento() {
		docFragmentado.stream().forEach(parrafo -> {
			if(parrafo.getEliminarParrafo()==false)
				if(parrafo.getParrafoConEtiqueta()==true)
					parrafo.getDocFragment().escribirParrafo("[AQUÍ ETIQUETA]");	//Escribir el contenido de la etiqueta
				else
					parrafo.getDocFragment().escribirParrafo();	
			else 
				parrafo.getDocFragment().escribirParrafo("");	
		});
	}
	
	public void openDocument() throws FileNotFoundException, IOException, InvalidFormatException {
		File filename = new File(Constants.IN_PATH + File.separator + Constants.TEST_DOCUMENT_NAME);
		InputStream is = new FileInputStream(filename);
		OPCPackage oPackage = OPCPackage.open(is);
		document = new XWPFDocument(oPackage);
		
	}
	
	public void saveDocument() throws FileNotFoundException, IOException {
		FileOutputStream fileOutput = new FileOutputStream (Constants.OUT_PATH + File.separator + Constants.TEST_DOCUMENT_NAME);
		document.write(fileOutput);
		fileOutput.close();
		document.close();
	}

}
