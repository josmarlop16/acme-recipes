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
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	Double averageBudgetOfProposedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	Double averageBudgetOfAcceptedPatronages();
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	Double averageBudgetOfDeniedPatronages();

	
//	Deviation of budgets by patronage statuses
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	Long deviationBudgetOfProposedPatronages();
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	Long deviationBudgetOfAcceptedPatronages();
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	Long deviationBudgetOfDeniedPatronages();
	
	
//	Minimum budget of patronages by statuses
	@Query("select min(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	Double minimumBudgetOfProposedPatronages();

	@Query("select min(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	Double minimumBudgetOfAcceptedPatronages();

	@Query("select min(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	Double minimumBudgetOfDeniedPatronages();

//	Maximum budget of patronages by statuses
	@Query("select max(p.budget.amount) from Patronage p where p.status = 0 group by p.budget.currency")
	Double maximumBudgetOfProposedPatronages();

	@Query("select max(p.budget.amount) from Patronage p where p.status = 1 group by p.budget.currency")
	Double maximumBudgetOfAcceptedPatronages();

	@Query("select max(p.budget.amount) from Patronage p where p.status = 2 group by p.budget.currency")
	Double maximumBudgetOfDeniedPatronages();
 
}
