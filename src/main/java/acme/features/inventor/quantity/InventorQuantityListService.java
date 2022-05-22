package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor,Quantity>{

	@Autowired
	protected InventorQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.repository.findOneToolkitById(toolkitId);
		final Integer inventorId=request.getPrincipal().getActiveRoleId();
		
		return toolkit.getInventor().getId()==inventorId;
	}
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		
		return this.repository.findQuantityByToolkitId(toolkitId);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request !=null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		final Boolean showCreate;
		
		final Integer toolkitId=request.getModel().getInteger("toolkitId");
		final Toolkit toolkit=this.repository.findOneToolkitById(toolkitId);
		showCreate=(request.isPrincipal(toolkit.getInventor())) && !toolkit.getPublished();
		
		model.setAttribute("toolkitId", toolkitId);
		model.setAttribute("showCreate", showCreate);
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity,final Model model) {
		assert request !=null;
		assert entity != null;
		assert model!=null;
		
		final String itemName=entity.getItem().getName();
		
		request.unbind(entity, model, "quantity");
		model.setAttribute("item.name", itemName);
		model.setAttribute("item.code", entity.getItem().getCode());
		
		
	}
}
