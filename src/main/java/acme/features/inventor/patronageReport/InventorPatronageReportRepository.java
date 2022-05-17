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

package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select p from PatronageReport p where p.inventor.id = :inventorId")
	Collection<PatronageReport> findPatronageReportByInventorId(int inventorId);

	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findPatronageReportById(int id);
	
	

	@Query("select i from Inventor i where i.id = :id ")
	Inventor findInventorById(int id);


}
