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

package acme.features.chef.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.type = 0 and i.chef.id = :chefId")
	Collection<Item> findToolsByChefId(int chefId);

	@Query("select i from Item i where i.id = :id and i.type = 0 and i.id = :id")
	Item findToolById(int id);
	
	@Query("select i from Item i where i.type = 1 and i.chef.id = :chefId")
	Collection<Item> findComponentsByChefId(int chefId);

	@Query("select i from Item i where i.id = :id and i.type = 1 and i.chef.id = :chefId")
	Item findComponentById(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select c.name from Currency c where c.isDefault=true")
	String systemCurrency();

	@Query("select c.name from Currency c")
	String[] findAllowedCurrencies();
	
	@Query("select i from Item i where i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select i from Chef i where i.id = :id ")
	Chef findChefById(int id);
	
	@Query("select i from Item i")
	List<Item> findAllItem();

}
