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

package acme.features.chef.patronageReport;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefPatronageReportRepository extends AbstractRepository {

	@Query("select p from PatronageReport p where p.chef.id = :chefId")
	Collection<PatronageReport> findPatronageReportByChefId(int chefId);

	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findPatronageReportById(int id);

	@Query("select i from Chef i where i.id = :id ")
	Chef findChefById(int id);

}