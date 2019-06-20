package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import funcionesWord.Constants;
import funcionesWord.TagWord;

public class WordGetTags {

	Map<String, List<TagWord>> tags = new HashMap<String, List<TagWord>>();;
	private XWPFDocument document;
	private String documentName;
	private String inPath;

	public void searchTags() throws FileNotFoundException, IOException {
		List<String> etiquetas = new ArrayList<String>();

		openDocument();
		SearchTagsInText(etiquetas);
		searchTagsInTables(etiquetas);

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
				tmpTags.add(new TagWord(codigoTag, tipoCampo, txtSolicitud));
				tags.put(apartado, tmpTags);
			} else {
				tags.get(apartado).add(new TagWord(codigoTag, tipoCampo, txtSolicitud));
			}
		}
		closeDocument();
	}

	public List<String> SearchTagsInText(List<String> tags) {
		for (XWPFParagraph p : document.getParagraphs()) {
			String txt = p.getParagraphText();
			if (txt != null && txt.contains(Constants.CODIGO_INICIO)) {
				int inicio = txt.indexOf(Constants.CODIGO_INICIO);
				int fin = txt.indexOf(Constants.CODIGO_FINAL, inicio);
				tags.add(txt.substring(inicio, fin + 2).trim());
			}
		}
		return tags;
	}

	public List<String> searchTagsInTables(List<String> tags) {
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
									tags.add(txt.substring(inicio, fin + 2).trim());
									creandoTag = false;
									subTag = "";
								} else {
									subTag += txt;
									if ((txt.contains(Constants.CODIGO_FINAL) || txt.contains(Constants.CODIGO_CONTROL))
											&& !txt.contains(Constants.CODIGO_INICIO)) {
										creandoTag = false;
										tags.add(subTag.trim());
										subTag = "";
									}
								}
							}
						}
					}
				}
			}
		}
		return tags;
	}

	
	public WordGetTags() {
		
	}

	public void openDocument() throws FileNotFoundException, IOException {
		File filename = new File(inPath + File.separator + documentName);
		InputStream io = new FileInputStream(filename);
		document = new XWPFDocument(io);
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
