package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor,Quantity> {
	
	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request!=null;
		
		final Integer quantityId = request.getModel().getInteger("id");
		final Integer inventorId= request.getPrincipal().getActiveRoleId();
		final Toolkit toolkit=this.repository.findOneToolkitByQuantityId(quantityId);
		return (!toolkit.getPublished() && toolkit.getInventor().getId()==inventorId);
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity!= null;
		assert errors != null;
		
		request.bind(entity, errors, "quantity","item");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity!= null;
		assert model != null;
		
		request.unbind(entity, model, "quantity","item");
		model.setAttribute("status", entity.getToolkit().getPublished());
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		final Integer quantityId=request.getModel().getInteger("id");
		
		return this.repository.findQuantityById(quantityId);
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
	}
	

}
