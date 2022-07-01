package acme.features.chef.quantity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefQuantityRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id= :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("select i from Item i where i.name= :itemName")
	Item findItemByName(String itemName);
	
	@Query("select i from Item i where i.published = true")
	List<Item> findItems();
	
	@Query("select q from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Quantity> findQuantityByToolkitId(int toolkitId);
	
	@Query("select q.item.retailPrice from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Money> findQuantityPriceByToolkitId(int toolkitId);

	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();
	
	@Query("select q from Quantity q where q.id = :quantityId")
	Quantity findQuantityById(Integer quantityId);

	@Query("select q.toolkit from Quantity q where q.id = :quantityId")
	Toolkit findOneToolkitByQuantityId(Integer quantityId);

	@Query("select q.item from Quantity q where q.item.id = :itemId")
	List<Item> findItemByQuantityId(int itemId);

	@Query("select i from Item i where i.published=1")
	List<Item> findItemPublished();
	
	@Query("select q.item from Quantity q where q.toolkit.id= :toolkitid")
	List<Item> findItemsByToolkidId(int toolkitid);
	
	
	
}
