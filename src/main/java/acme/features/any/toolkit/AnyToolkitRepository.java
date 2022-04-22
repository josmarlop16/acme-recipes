package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();
	
	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();
	
	@Query("select q.item from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
}
