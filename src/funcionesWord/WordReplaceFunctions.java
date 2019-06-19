package funcionesWord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import com.mc_mutual.utils.Constants;

public class WordReplaceFunctions {

	private XWPFDocument document;
	private String inPath, outPath;
	private String documentName;

	public WordReplaceFunctions(String inPath, String outPath, String documentName)
			throws FileNotFoundException, IOException {
		this.inPath = inPath;
		this.outPath = outPath;
		this.documentName = documentName;
	}

	public void openDocument() throws FileNotFoundException, IOException {
		document = new XWPFDocument(new FileInputStream(inPath + "/" + documentName));
	}

	public void saveDocument() throws FileNotFoundException, IOException {
		document.write(new FileOutputStream(outPath + "/" + documentName));

	}

	public void closeDocument() throws IOException {
		document.close();
	}

	public void replaceTagsFormat(Tag tag, String replace) throws Exception {
		String subTag = "";

		for (XWPFParagraph p : document.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String txt = r.getText(0);
					subTag = concatenandoRuns(txt, tag, subTag, r, replace);
				}
			}
		}
	}

	public void replaceTextInTables(Tag tag, String replace) throws FileNotFoundException, IOException {
		String subTag = "";
		
		for (XWPFTable tbl : document.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String txt = r.getText(0);
							subTag = concatenandoRuns(txt, tag, subTag, r, replace);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param txt      -> Párrafo de texto que se ha de comprbra el contenido
	 * @param tag 	   -> Objeto tag
	 * @param subTag   -> Cadena que se va componiendo concatenando párrafos
	 * @param r        -> Objeto XWPFRun que hace referencia al parrafo actual leido
	 * @param replace  -> Cadena de tenxto con la que reemplazar la etiqueta
	 * @return Devuelve la cadena vacia si ya ha recorrido toda una etiqueta, o la cadena de texto que se va creando con la etiqueta.
	 */
	private String concatenandoRuns(String txt, Tag tag, String subTag, XWPFRun r, String replace) {
		if (txt != null && txt.contains(Constants.CODIGO_INICIO) || !subTag.equals("")) {
			int inicio = txt.indexOf(Constants.CODIGO_INICIO);
			int fin = txt.indexOf(Constants.CODIGO_FINAL, inicio);
			if (fin > inicio && fin > 0 && inicio >= 0) {
				if (tag.getCodigoTag().equals(txt)) {
					r.setText("", 0); // Elimino el texto con el código de la etiqueta del documento.
					typeFieldResponse(replace,r, tag.getTipoCampo());
					return "";
				}
			} else {
				subTag += txt;
				if ((txt.contains(Constants.CODIGO_FINAL) || txt.contains(Constants.CODIGO_CONTROL)) && 
								  !txt.contains(Constants.CODIGO_INICIO)) {
					r.setText("", 0); //Borro el último caracter 
					if (tag.getCodigoTag().trim().equals(subTag.trim())) {
						typeFieldResponse(replace,r, tag.getTipoCampo());
					}else {
						r.setText(subTag, 0); //Pongo la etiqueta original porque no es la que buscaba
					}
					return "";
				} else {
					r.setText("", 0); // Elimino el texto con el código de la etiqueta del documento.
				}
			}
		}
		return subTag;
	}

	private void typeFieldResponse(String str, XWPFRun r, String typeField) {
		r.setFontSize(11);
		switch(typeField) {
			case "text":
				r.setText(str);
				return;
			case "only_check":
				r.setText("[  ]");
				return;
			case "y_n":
				r.setText("Si [  ]  No [  ]");
				return;
		}
		//r.addCarriageReturn();
	}

	public void replaceTags(String tag, String newText) {

		for (XWPFParagraph p : document.getParagraphs()) {

			String txt = p.getParagraphText();

			if (txt != null && txt.contains(tag)) {
				txt = txt.replace(tag, newText);
				removeAllRuns(p);
				XWPFRun newRun = p.createRun();
				newRun.setText(txt);
				newRun.setColor("00CC44");
			}
		}
	}

	public void getBookMarkAndInsertText(String marca, String texto) {

		List<XWPFParagraph> paragraphs = document.getParagraphs();
		for (XWPFParagraph paragraph : paragraphs) {

			CTP ctp = paragraph.getCTP();

			List<CTBookmark> bookmarks = ctp.getBookmarkStartList();

			for (CTBookmark bookmark : bookmarks) {
				if (bookmark.getName().equals(marca)) {
					System.out.println(bookmark.getName());
					XWPFRun run = paragraph.createRun();
					run.setText(texto);
					ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), bookmark.getDomNode());
				}
			}
		}
	}

	private void removeAllRuns(XWPFParagraph paragraph) {
		int size = paragraph.getRuns().size();
		for (int i = 0; i < size; i++) {
			paragraph.removeRun(0);
		}
	}

}
