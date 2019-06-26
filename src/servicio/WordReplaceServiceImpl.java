package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import domain.TagPlantillaBO;
import domain.WordReplaceTags;
import servicio.Interfaces.WordReplaceService;

@Stateless
public class WordReplaceServiceImpl implements WordReplaceService {
	
	WordReplaceTags wordReplace = new WordReplaceTags();

	@Override
	public int replaceTags(String inPath, String outPath, String name, List<TagPlantillaBO> tags) throws FileNotFoundException, IOException, InvalidFormatException {
		wordReplace.setInPath(inPath);
		wordReplace.setOutPath(outPath);
		wordReplace.setDocumentName(name);
		wordReplace.replaceTags(tags);
		
		return 0;
	}

}
