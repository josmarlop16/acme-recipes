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

package acme.features.administrator.spam;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {

	@Query("select s from Spam s")
	Collection<Spam> findSpams();
	
	@Query("select s.spamTerm from Spam s where s.isStrong = 0")
	List<String> findWeakSpamsWords();
	
	@Query("select s.spamTerm from Spam s where s.isStrong = 1")
	List<String> findStrongSpamsWords();

}
