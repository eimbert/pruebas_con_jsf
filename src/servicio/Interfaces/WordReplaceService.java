package servicio.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import domain.TagPlantillaBO;
import funcionesWord.v2.TagWord;

@Local
public interface WordReplaceService {

	public int replaceTags(String inPath, String outPath, String name, List<TagWord> tags) throws FileNotFoundException, IOException, InvalidFormatException;
}
