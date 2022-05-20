/*
 * AnonymousShoutRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.type = 0 and i.inventor.id = :inventorId")
	Collection<Item> findToolsByInventorId(int inventorId);

	@Query("select i from Item i where i.id = :id and i.type = 0 and i.id = :id")
	Item findToolById(int id);
	
	@Query("select t.item from Toolkit t where t.id = :toolkitId")
	Collection<Item> findItemsByToolkitId(int toolkitId);
	
	@Query("select i from Item i where i.type = 1 and i.inventor.id = :inventorId")
	Collection<Item> findComponentsByInventorId(int inventorId);

	@Query("select i from Item i where i.id = :id and i.type = 1 and i.inventor.id = :inventorId")
	Item findComponentById(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();

	@Query("select c.name from Currency c")
	String[] findAllowedCurrencies();
	
	@Query("select i from Item i where i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select i from Inventor i where i.id = :id ")
	Inventor findInventorById(int id);
	
	@Query("select i from Item i")
	List<Item> findAllItem();


}
