package acme.features.inventor.quantity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor,Quantity> {

	@Autowired
	protected InventorQuantityRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Integer quantityId=request.getModel().getInteger("id");
		final Toolkit toolkit= this.repository.findOneToolkitByQuantityId(quantityId);
		final Integer inventorId= request.getPrincipal().getActiveRoleId();
		return (!toolkit.getPublished() && toolkit.getInventor().getId()==inventorId);
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity!= null;
		assert errors != null;
		
		request.bind(entity, errors, "quantity");		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("published", entity.getToolkit().getPublished());
		request.unbind(entity, model, "quantity","item.name","item.code","item.technology","item.retailPrice","item.description","item.type");
		
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
		assert errors != null;
		if (!errors.hasErrors("quantity")) {
			
			errors.state(request, entity.getQuantity() >0, "quantity", "inventor.quantity.form.error.nullquantity");
		}
		
		if(entity.getItem().getType().equals(ItemType.TOOL)) {
			errors.state(request, entity.getQuantity() == 1, "quantity", "inventor.quantity.form.error.one-tool");
		}
	}

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
