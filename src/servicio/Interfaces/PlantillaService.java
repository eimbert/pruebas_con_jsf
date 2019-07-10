package servicio.Interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.PlantillaBO;


public interface PlantillaService {
	
    public List<PlantillaBO> listarPlantillas();
    
    public List<PlantillaBO> listarPlantillasNoEditadass();
    
    public List<PlantillaBO> listarPlantillasEditadas();

    public PlantillaBO encontrarPlantillaId(int plantilla);

    public PlantillaBO encontrarPlantillaPorEmail(PlantillaBO plantilla);

    public void registrarPlantilla(PlantillaBO plantilla);

    public void modificarPlantilla(PlantillaBO plantilla);

    public void eliminarPlantilla(PlantillaBO plantilla);

}
