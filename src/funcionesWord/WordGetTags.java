package funcionesWord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


public class WordGetTags {

	Map<String, List<TagWord>> tags = new HashMap<String, List<TagWord>>();
	List<String> etiquetas = new ArrayList<String>();
	List<String> ayudasTag = new ArrayList<String>();
	private XWPFDocument document;
	private String documentName;
	private String inPath;

	public void searchTags() throws FileNotFoundException, IOException, InvalidFormatException {
		

		openDocument();
		SearchTagsInText();
		searchTagsInTables();
		
		int indice = 0;
		for (String str : etiquetas) {
			List<TagWord> tmpTags = new ArrayList<TagWord>();
			String codigoTag = str;
			String apartado = str.substring(2, str.indexOf(Constants.CODIGO_SEPARADOR, 2));
			String tipoCampo = str.substring(str.indexOf(Constants.CODIGO_SEPARADOR) + 1,
					str.indexOf(Constants.CODIGO_SEPARADOR, str.indexOf(Constants.CODIGO_SEPARADOR) + 1));
			String txtSolicitud = str.substring(
					str.indexOf(Constants.CODIGO_SEPARADOR, str.indexOf(Constants.CODIGO_SEPARADOR) + 1) + 1,
					str.indexOf(Constants.CODIGO_FINAL));
			if (tags.get(apartado) == null) {
				tmpTags.add(new TagWord(codigoTag, tipoCampo, txtSolicitud, limpiarTextoAyuda(ayudasTag.get(indice), codigoTag)));
				tags.put(apartado, tmpTags);
			} else {
				tags.get(apartado).add(new TagWord(codigoTag, tipoCampo, txtSolicitud, limpiarTextoAyuda(ayudasTag.get(indice), codigoTag)));
			}
			indice++;
		}
		closeDocument();
	}

	public void SearchTagsInText() {
		String parrafo = "";
		Boolean guardarTextoParrafo = false;
		for (XWPFParagraph p : document.getParagraphs()) {
			String txt = p.getParagraphText();
			if ((txt != null && txt.contains(Constants.CODIGO_INICIO_PARRAFO)) || guardarTextoParrafo ) {
				guardarTextoParrafo = true;
				parrafo += txt;
			}
			if (txt != null && txt.contains(Constants.CODIGO_FIN_PARRAFO) && guardarTextoParrafo) {
				guardarTextoParrafo = false;
//				ayudasTag.add(parrafo);
//				parrafo = ""; 
			}
			if (txt != null && txt.contains(Constants.CODIGO_INICIO)) {
				int inicio = txt.indexOf(Constants.CODIGO_INICIO);
				int fin = txt.indexOf(Constants.CODIGO_FINAL, inicio);
				etiquetas.add(txt.substring(inicio, fin + 2).trim());
				if(parrafo.trim().length()==0 ) 
					ayudasTag.add("Sin ayuda");
				else
					ayudasTag.add(parrafo);
				parrafo = ""; 
			}
		}
	}

	public void searchTagsInTables() {
		Boolean creandoTag = false;
		String subTag = "";
		for (XWPFTable tbl : document.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String txt = r.getText(0);
							if (txt != null && txt.contains(Constants.CODIGO_INICIO) || creandoTag) {
								creandoTag = true;
								int inicio = txt.indexOf(Constants.CODIGO_INICIO);
								int fin = txt.indexOf(Constants.CODIGO_FINAL, inicio);
								if (fin > inicio && fin > 0 && inicio >= 0) {
									etiquetas.add(txt.substring(inicio, fin + 2).trim());
									ayudasTag.add("sin ayuda");
									creandoTag = false;
									subTag = "";
								} else {
									subTag += txt;
									if ((txt.contains(Constants.CODIGO_FINAL) || txt.contains(Constants.CODIGO_CONTROL))
											&& !txt.contains(Constants.CODIGO_INICIO)) {
										creandoTag = false;
										ayudasTag.add("sin ayuda");
										etiquetas.add(subTag.trim());
										subTag = "";
									}
								}
							}
						}
					}
				}
			}
		}
	}

	
	private String limpiarTextoAyuda(String texto, String codigoTag) {
		texto = texto.replace(Constants.CODIGO_INICIO_PARRAFO, "");
		texto = texto.replace(Constants.CODIGO_FIN_PARRAFO, "");
		texto = texto.replace(codigoTag, " [ETIQUETA] ");
		return texto;
	}

	public void openDocument() throws FileNotFoundException, IOException, InvalidFormatException {
		File filename = new File(inPath + File.separator + documentName);
		InputStream is = new FileInputStream(filename);
		OPCPackage oPackage = OPCPackage.open(is);
		document = new XWPFDocument(oPackage);
		
	}

	public void closeDocument() throws IOException {
		document.close();
	}

	public Map<String, List<TagWord>> getTags() {
		return tags;
	}

	public void setTags(Map<String, List<TagWord>> tags) {
		this.tags = tags;
	}

	public XWPFDocument getDocument() {
		return document;
	}

	public void setDocument(XWPFDocument document) {
		this.document = document;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}
}
