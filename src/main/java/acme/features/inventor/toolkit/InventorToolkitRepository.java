package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findToolkitByInventorId(int inventorId);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsByToolkitId(int id);
	
	@Query("select q from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Quantity> findQuantityByToolkitId(int toolkitId);
	
	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();
	
	@Query("select c.name from Currency c where c.accepted= 1")
	List<String> allCurrencies();

	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findToolkitByCode(String code);

	@Query("select i from Inventor i where i.id = :id ")
	Inventor findInventorById(int id);
	
}