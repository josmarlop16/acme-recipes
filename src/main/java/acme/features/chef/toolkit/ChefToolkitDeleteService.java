package acme.features.chef.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefToolkitDeleteService implements AbstractDeleteService<Chef,Toolkit> {
	
	@Autowired
	protected ChefToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
		Chef chef;

		masterId=request.getModel().getInteger("id");
		toolkit=this.repository.findToolkitById(masterId);
		chef=toolkit.getChef();
		result=!toolkit.getPublished() && request.isPrincipal(chef);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link", "published");

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id=request.getModel().getInteger("id");
		result=this.repository.findToolkitById(id);

		return result;
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		final Collection<Quantity> quantities = this.repository.findQuantityByToolkitId(entity.getId());
		if(!quantities.isEmpty()) {
			for (final Quantity q : quantities) {
				this.repository.delete(q);
			}
		}
		

		this.repository.delete(entity);
	}
	
}
