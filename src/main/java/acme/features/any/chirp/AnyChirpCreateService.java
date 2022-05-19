package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.chirp.Chirp;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;


	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Any.class);
		return result;
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;


		Chirp result;
		
		result = new Chirp();
		final Date fecha = new Date();
		result.setCreationMoment(fecha);
		
		return result;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "title", "author", "body", "emailAddress");
	}
	
	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("title")) {
            errors.state(request, SpamModule.spamValidator(entity.getTitle(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "title", "form.error.spam");
        }
		
		if (!errors.hasErrors("author")) {
            errors.state(request, SpamModule.spamValidator(entity.getAuthor(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "author", "form.error.spam");
        }
		
		if (!errors.hasErrors("body")) {
            errors.state(request, SpamModule.spamValidator(entity.getBody(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "body", "form.error.spam");
        }
		
		
		if (!errors.hasErrors("emailAddress")) {
            errors.state(request, SpamModule.spamValidator(entity.getEmailAddress(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "emailAddress", "form.error.spam");
        }
		
		
		boolean isConfirmed;
		
		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "any.chirp.form.error.must-confirm");
		
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "author", "body", "emailAddress");
	}


	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	

	
}