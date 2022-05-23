package acme.features.any.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.framework.roles.Any;
import org.springframework.stereotype.Service;

@Service
public class AnyItemListByToolkitService implements AbstractListService<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		int toolkitId;
		
		toolkitId=request.getModel().getInteger("toolkitId");
		
		
		result = this.repository.findItemsByToolkitId(toolkitId);


		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type","published");
	}
	
}