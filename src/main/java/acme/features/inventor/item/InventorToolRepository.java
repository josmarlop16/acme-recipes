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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolRepository extends AbstractRepository {

	@Query("select i from Item i where i.type = 0 and i.inventor.id = :inventorId")
	Collection<Item> findToolsByInventorId(int inventorId);

	@Query("select i from Item i where i.id = :id and i.type = 0 and i.id = :id")
	Item findToolById(int id);



}
