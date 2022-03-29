package acme.features.authenticated.chirp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirp.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChirpShowService implements AbstractShowService<Authenticated, Chirp> {


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedChirpRepository repository;


	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "author", "body", "emailAddress");
	}
	
	@Override
	public Chirp findOne(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneChirpById(id);

		return result;
	}
	
}
