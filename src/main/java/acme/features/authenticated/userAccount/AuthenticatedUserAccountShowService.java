package acme.features.authenticated.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.features.authenticated.userAccount.AuthenticatedUserAccountRepository;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedUserAccountShowService implements AbstractShowService<Authenticated, UserAccount>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("itemId", entity.getId());

		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");
	}
	
	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);

		return result;
	}
	
}
