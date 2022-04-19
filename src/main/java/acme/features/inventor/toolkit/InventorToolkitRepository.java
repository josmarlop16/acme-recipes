package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("select distinct q.toolkit from Quantity q where q.item.inventor.id = :inventorId")
	Collection<Toolkit> findToolkitByInventorId(int inventorId);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	
	
	
}
