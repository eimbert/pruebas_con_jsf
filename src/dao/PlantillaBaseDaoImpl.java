package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.PlantillaBO;
import domain.PlantillaBaseBO;

@Stateless
public class PlantillaBaseDaoImpl implements PlantillaBaseDao {

	@PersistenceContext(unitName = "PlantillasPU")
    EntityManager em;
	
	@Override
	public List<PlantillaBaseBO> findAllPlantillas() {
		 return em.createNamedQuery("PlantillaBase.findAll").getResultList();
	}
	
	@Override
	public PlantillaBaseBO findPlantillaById(int plantilla) {
		 return em.find(PlantillaBaseBO.class, plantilla);
	}

	@Override
	public PlantillaBaseBO findPlantillaByName(PlantillaBaseBO plantilla) {
        Query query = em.createQuery("from PlantillaBaseBO p where p.nombre =: name");
        query.setParameter("name", plantilla.getNombre());
        return (PlantillaBaseBO) query.getSingleResult();
	}

	@Override
	public void insertPlantilla(PlantillaBaseBO plantilla) {
		em.persist(plantilla);
	}

	@Override
	public void updatePlantilla(PlantillaBaseBO plantilla) {
		 em.merge(plantilla);

	}

	@Override
	public void deletePlantilla(PlantillaBaseBO plantilla) {
        em.merge(plantilla);
        em.remove(plantilla);

	}

}
