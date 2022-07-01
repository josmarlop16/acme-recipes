package acme.features.epicure.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

public interface EpicurePatronageRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.epicure.id = :epicureId")
	Collection<Patronage> findAllPatronagesByEpicureId(int epicureId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from Epicure p where p.id = :id ")
	Epicure findEpicureById(int id);
	
	@Query("select i from Chef i")
	List<Chef> findAllChefs();
	
	@Query("select p from Chef p where p.id = :id ")
	Chef findChefById(int id);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);
	
	
}
