package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import funcionesWord.v2.Constants;
import funcionesWord.v2.FusionarDocumentoWord;
import funcionesWord.v2.TagWord;
import servicio.Interfaces.WordReplaceService;

@Stateless
public class WordReplaceServiceImpl implements WordReplaceService {
	
	FusionarDocumentoWord docFusionado = new FusionarDocumentoWord();
	
	@Override
	public int replaceTags(String inPath, String outPath, String name, List<TagWord> tags) throws FileNotFoundException, IOException, InvalidFormatException {
		
		docFusionado.fusionarDatos(tags, Constants.CODIGO_INICIO, Constants.CODIGO_FINAL);
		docFusionado.saveDocument();
		return 0;
	}

}
