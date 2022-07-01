package acme.features.chef.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef,Quantity> {
	
	@Autowired
	protected ChefQuantityRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request !=null;
		
		final Integer quantityId = request.getModel().getInteger("id");
		final Toolkit toolkit=this.repository.findOneToolkitByQuantityId(quantityId);
		final Integer chefId=request.getPrincipal().getActiveRoleId();
		return toolkit.getChef().getId()==chefId;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request !=null;
		
		final Integer quantityId=request.getModel().getInteger("id");
		
		return this.repository.findQuantityById(quantityId);
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		final List<Item> itemsToolkit=this.repository.findItemsByToolkidId(entity.getToolkit().getId());
		final List<Item> items=this.repository.findItemPublished();
		
		itemsToolkit.removeAll(items);
		
		model.setAttribute("items", itemsToolkit);
		model.setAttribute("item.name", entity.getItem().getName());
		model.setAttribute("item.code", entity.getItem().getCode());
		model.setAttribute("item.technology", entity.getItem().getTechnology());
		model.setAttribute("item.description", entity.getItem().getDescription());
		model.setAttribute("item.type", entity.getItem().getType());
		model.setAttribute("item.link", entity.getItem().getLink());
		model.setAttribute("published", entity.getToolkit().getPublished());
		model.setAttribute("item.price", entity.getItem().getRetailPrice());
		
		final String systemCurrency = this.repository.systemCurrency();
		
		if(entity.getItem().getRetailPrice().getCurrency().equals(systemCurrency)) {
			final Money computedPrice = new Money();
			computedPrice.setCurrency(entity.getItem().getRetailPrice().getCurrency());
			computedPrice.setAmount(entity.getItem().getRetailPrice().getAmount()*entity.getQuantity());
			model.setAttribute("computedPrice", computedPrice);
			model.setAttribute("item.price", computedPrice);
			
		}else {
			final Money retailPrice=MoneyExchangePerform.computeMoneyExchange(entity.getItem().getRetailPrice(),systemCurrency).getTarget();
			retailPrice.setAmount(retailPrice.getAmount()*entity.getQuantity());
			model.setAttribute("computedPrice", retailPrice);
			model.setAttribute("item.price", retailPrice);
		}
		
		
		request.unbind(entity, model, "quantity");
	}
	
	

}
