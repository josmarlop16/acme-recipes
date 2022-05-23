package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSpamRepository repository;

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Administrator.class);
		return result;
	}
	
	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "spamTerm", "isStrong", "threshold");
	}

	
	
	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(request.getModel().getAttribute("isStrong").equals("true")) {
			entity.setThreshold(10);
			entity.setIsStrong(true);
		}else{
			entity.setThreshold(25);
			entity.setIsStrong(false);
		}
		
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamTerm", "isStrong", "threshold");
	}



	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;
		
		Spam result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findSpamWordById(id);
		
		return result;
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}