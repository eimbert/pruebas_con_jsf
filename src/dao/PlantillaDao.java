package dao;

import java.util.List;

import domain.PlantillaBO;

public interface PlantillaDao {
	public List<PlantillaBO> findAllPlantillas();

	public PlantillaBO findPlantillaById(int plantilla);

	public PlantillaBO findPlantillaByName(PlantillaBO plantilla);

	public void insertPlantilla(PlantillaBO plantilla);

	public void updatePlantilla(PlantillaBO plantilla);

	public void deletePlantilla(PlantillaBO plantilla);
}
