package acme.features.chef.quantity;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefQuantityListService implements AbstractListService<Chef,Quantity>{

	@Autowired
	protected ChefQuantityRepository repository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.repository.findOneToolkitById(toolkitId);
		final Integer chefId=request.getPrincipal().getActiveRoleId();
		
		return toolkit.getChef().getId()==chefId;
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
		
		final List<Item> itemsToolkit=this.repository.findItemsByToolkidId(toolkitId);
		final List<Item> items=this.repository.findItemPublished();
		
		items.removeAll(itemsToolkit);
		
		showCreate=(request.isPrincipal(toolkit.getChef())) && !toolkit.getPublished() && !items.isEmpty();
		
		
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
