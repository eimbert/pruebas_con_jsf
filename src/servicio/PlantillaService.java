package servicio;

import java.util.List;

import javax.ejb.Local;

import domain.PlantillaBO;

@Local
public interface PlantillaService {
	
    public List<PlantillaBO> listarPlantillas();

    public PlantillaBO encontrarPlantillaId(PlantillaBO plantilla);

    public PlantillaBO encontrarPlantillaPorEmail(PlantillaBO plantilla);

    public void registrarPlantilla(PlantillaBO plantilla);

    public void modificarPlantilla(PlantillaBO plantilla);

    public void eliminarPlantilla(PlantillaBO plantilla);

}
