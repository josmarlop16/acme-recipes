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

import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	Integer totalNumberOfProposedPatronages();

	Integer totalNumberOfAcceptedPatronages();

	Integer totalNumberOfDeniedPatronages();

	Double averageNumberOfPatronages();

	Long deviationOfPatronages();

	Double minimumBudgetOfProposedPatronages();

	Double minimumBudgetOfAcceptedPatronages();

	Double minimumBudgetOfDeniedPatronages();

	Double maximumBudgetOfProposedPatronages();

	Double maximumBudgetOfAcceptedPatronages();

	Double maximumBudgetOfDeniedPatronages();
	
	
	
	
	
	
	

//	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
//	Double averageNumberOfJobsPerEmployer();
//
//	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
//	Double averageNumberOfApplicationsPerWorker();
//
//	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
//	Double averageNumberOfApplicationsPerEmployer();
//
//	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.PENDING")
//	Double ratioOfPendingApplications();
//
//	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
//	Double ratioOfAcceptedApplications();
//
//	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
//	Double ratioOfRejectedApplications();

}
