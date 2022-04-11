/*
 * AnonymousShoutListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.spam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedSpamListService implements AbstractListService<Authenticated, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedSpamRepository repository;

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Spam> findMany(final Request<Spam> request) {
		assert request != null;

		Collection<Spam> result;

		result = this.repository.findSpams();

		return result;
	}
	
	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamTerm", "isStrong", "threshold");
	}

}
