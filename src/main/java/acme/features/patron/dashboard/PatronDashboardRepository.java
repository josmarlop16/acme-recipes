/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select count(t) from Patron t")
	Integer totalNumberOfProposedPatronages();

	@Query("select count(t) from Patron t")
	Integer totalNumberOfAcceptedPatronages();

	@Query("select count(t) from Patron t")
	Integer totalNumberOfDeniedPatronages();

	@Query("select count(t) from Patron t")
	Double averageNumberOfPatronages();

	@Query("select count(t) from Patron t")
	Long deviationOfPatronages();

	@Query("select count(t) from Patron t")
	Double minimumBudgetOfProposedPatronages();

	@Query("select count(t) from Patron t")
	Double minimumBudgetOfAcceptedPatronages();

	@Query("select count(t) from Patron t")
	Double minimumBudgetOfDeniedPatronages();

	@Query("select count(t) from Patron t")
	Double maximumBudgetOfProposedPatronages();

	@Query("select count(t) from Patron t")
	Double maximumBudgetOfAcceptedPatronages();

	@Query("select count(t) from Patron t")
	Double maximumBudgetOfDeniedPatronages();
 
}
