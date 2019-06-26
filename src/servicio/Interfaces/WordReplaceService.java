package servicio.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import domain.TagPlantillaBO;

@Local
public interface WordReplaceService {

	public int replaceTags(String inPath, String outPath, String name, List<TagPlantillaBO> tags) throws FileNotFoundException, IOException, InvalidFormatException;
}
