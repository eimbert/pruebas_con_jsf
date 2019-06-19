package funcionesWord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordTagsFunctions {

	private XWPFDocument document;
	private String documentName;
	private String inPath;

	public WordTagsFunctions(String inPath, String documentName) {
		this.documentName = documentName;
		this.inPath = inPath;
	}

	public void openDocument() throws FileNotFoundException, IOException {
		document = new XWPFDocument(new FileInputStream(inPath + "/" + documentName));
	}

	public void closeDocument() throws IOException {
		document.close();
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
									tags.add(txt.substring(inicio, fin+2).trim());
									creandoTag=false;
									subTag = "";
								} else {
									subTag += txt;
									if ((txt.contains(Constants.CODIGO_FINAL) || txt.contains(Constants.CODIGO_CONTROL)) && 
										!txt.contains(Constants.CODIGO_INICIO)) {
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
}
