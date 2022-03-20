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

//	Number of patronages by statuses
	@Query("select count(p) from Patronage p where p.status = 0")
	Integer totalNumberOfProposedPatronages();

	@Query("select count(p) from Patronage p where p.status = 1")
	Integer totalNumberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = 2")
	Integer totalNumberOfDeniedPatronages();

//	Average budget from patronages by statuses
	@Query("select avg(p.budget) from Patronage p where p.status = 0")
	Double averageBudgetOfProposedPatronages();
	
	@Query("select avg(p.budget) from Patronage p where p.status = 1")
	Double averageBudgetOfAcceptedPatronages();
	
	@Query("select avg(p.budget) from Patronage p where p.status = 2")
	Double averageBudgetOfDeniedPatronages();

	
//	Deviation of budgets by patronage statuses
	@Query("select stddev(p.budget) from Patronage p where p.status = 0")
	Long deviationBudgetOfProposedPatronages();
	
	@Query("select stddev(p.budget) from Patronage p where p.status = 1")
	Long deviationBudgetOfAcceptedPatronages();
	
	@Query("select stddev(p.budget) from Patronage p where p.status = 2")
	Long deviationBudgetOfDeniedPatronages();
	
	
//	Minimum budget of patronages by statuses
	@Query("select min(p.budget) from Patronage p where p.status = 0")
	Double minimumBudgetOfProposedPatronages();

	@Query("select min(p.budget) from Patronage p where p.status = 1")
	Double minimumBudgetOfAcceptedPatronages();

	@Query("select min(p.budget) from Patronage p where p.status = 2")
	Double minimumBudgetOfDeniedPatronages();

//	Maximum budget of patronages by statuses
	@Query("select max(p.budget) from Patronage p where p.status = 0")
	Double maximumBudgetOfProposedPatronages();

	@Query("select max(p.budget) from Patronage p where p.status = 1")
	Double maximumBudgetOfAcceptedPatronages();

	@Query("select max(p.budget) from Patronage p where p.status = 2")
	Double maximumBudgetOfDeniedPatronages();
 
}
