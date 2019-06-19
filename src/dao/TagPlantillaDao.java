package dao;

import java.util.List;

import domain.TagPlantillaBO;


public interface TagPlantillaDao {
	public List<TagPlantillaBO> findAllPlantillas();

	public TagPlantillaBO findPlantillaById(TagPlantillaBO tagPlantilla);

	public TagPlantillaBO findPlantillaByName(TagPlantillaBO tagPlantilla);

	public void insertPlantilla(TagPlantillaBO tagPlantilla);

	public void updatePlantilla(TagPlantillaBO tagPlantilla);

	public void deletePlantilla(TagPlantillaBO tagPlantilla);
}
