package acme.features.anonymous.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousUserAccountShowService implements AbstractShowService<Anonymous, UserAccount>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousUserAccountRepository2 repository;


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

		request.unbind(entity, model, "username");
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
