package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.peep.Peep;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep> {

	@Autowired
	protected AnyPeepRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;


	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Any.class);
		return result;
	}
	
	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request != null;


		Peep result;
		
		result = new Peep();
		final Date fecha = new Date();
		result.setInstantiationMoment(fecha);
		
		return result;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "InstantiationMoment", "heading", "writer", "text", "emailAddress");
	}
	
	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("heading")) {
            errors.state(request, SpamModule.spamValidator(entity.getHeading(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "head", "form.error.spam");
        }
		
		if (!errors.hasErrors("writer")) {
            errors.state(request, SpamModule.spamValidator(entity.getWriter(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "writer", "form.error.spam");
        }
		
		if (!errors.hasErrors("text")) {
            errors.state(request, SpamModule.spamValidator(entity.getText(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "text", "form.error.spam");
        }
		
		
		if (!errors.hasErrors("emailAddress")) {
            errors.state(request, SpamModule.spamValidator(entity.getEmailAddress(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "emailAddress", "form.error.spam");
        }
		
		
		boolean isConfirmed;
		
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "any.peep.form.error.must-confirm");
		
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "InstantiationMoment", "heading", "writer", "text", "emailAddress");
	}


	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	

	
}