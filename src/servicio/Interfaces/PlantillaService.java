package servicio.Interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.PlantillaBO;


public interface PlantillaService {
	
    public List<PlantillaBO> listarPlantillas();

    public PlantillaBO encontrarPlantillaId(int plantilla);

    public PlantillaBO encontrarPlantillaPorEmail(PlantillaBO plantilla);

    public void registrarPlantilla(PlantillaBO plantilla);

    public void modificarPlantilla(PlantillaBO plantilla);

    public void eliminarPlantilla(PlantillaBO plantilla);

}
