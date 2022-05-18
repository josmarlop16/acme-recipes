package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

public interface PatronPatronageRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findAllPatronagesByPatronId(int patronId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from Patron p where p.id = :id ")
	Patron findPatronById(int id);
	
	@Query("select i from Inventor i")
	List<Inventor> findAllInventors();
	
	@Query("select p from Inventor p where p.id = :id ")
	Inventor findInventorById(int id);
	
}
