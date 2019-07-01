package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;


public class ToPDF {

	public static void toPdf(String input, String output) throws FileNotFoundException, IOException {
		String inputFile = input;
		String outputFile = output.replace("docx", "pdf");

		System.out.println("inputFile:" + inputFile + ", outputFile:" + outputFile);
	    

	    // get the file
	    XWPFDocument document = new XWPFDocument(new FileInputStream(inputFile));
	    // final file
	    OutputStream out = new FileOutputStream(new File(outputFile));
	    PdfOptions options = PdfOptions.create();
	    PdfConverter.getInstance().convert(document, out, options);

	    System.out.println(LocalTime.now().toString());
	}
}
