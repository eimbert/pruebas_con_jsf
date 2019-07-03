package funcionesWord;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;




public class ToPDF {

	public static void toPdf(String input, String output) throws FileNotFoundException, IOException {
		String inputFile = input;
		String outputFile = output.replace("docx", "pdf");

	    XWPFDocument document = new XWPFDocument(new FileInputStream(inputFile));
	 
	    OutputStream out = new FileOutputStream(new File(outputFile));
	    PdfOptions options = PdfOptions.create();
	    PdfConverter.getInstance().convert(document, out, options);
	    
	    document.close();
	    out.close();

	}
	
	public static void toPdf_d4j(String input, String output, String baseFolder) throws InterruptedException, ExecutionException, IOException {
		String inputFile = input;
		String outputFile = output.replace("docx", "pdf");
		
		
		 ByteArrayOutputStream bo = new ByteArrayOutputStream();

		    InputStream in = new BufferedInputStream(new FileInputStream(inputFile));
		    IConverter converter = (IConverter) LocalConverter.builder()
		            .baseFolder(new File(baseFolder))
		            .workerPool(20, 25, 2, TimeUnit.SECONDS)
		            .processTimeout(5, TimeUnit.SECONDS)
		            .build();

		    Future<Boolean> conversion = converter
		            .convert(in).as(DocumentType.DOCX)
		            .to(bo).as(DocumentType.PDF)
		            .schedule();
		    conversion.get();
		    try (OutputStream outputStream = new FileOutputStream(outputFile)) {
		        bo.writeTo(outputStream);
		    }
		    in.close();
		    bo.close();
		
	}
}
