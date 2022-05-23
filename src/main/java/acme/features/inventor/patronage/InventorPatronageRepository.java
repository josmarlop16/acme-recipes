package acme.features.inventor.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

public interface InventorPatronageRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.inventor.id = :inventorId and p.published=true")
	Collection<Patronage> findAllPatronagesByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	

	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();

	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);
	
	@Query("select p from Patronage p")
	List<Patronage> findAllPatronage();
	
	
	

}
