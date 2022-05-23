package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor,Quantity> {
	@Autowired
	protected InventorQuantityRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.repository.findOneToolkitById(toolkitId);
		final Integer inventorId= request.getPrincipal().getActiveRoleId();
		
		return (!toolkit.getPublished() && toolkit.getInventor().getId()==inventorId);
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String itemName= request.getModel().getAttribute("item.name").toString();
		final Item item=this.repository.findItemByName(itemName);
		
		entity.setItem(item);
		request.bind(entity, errors, "quantity","item.name");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		
		final List<Item> itemsToolkit=this.repository.findItemsByToolkidId(entity.getToolkit().getId());
		final List<Item> items=this.repository.findItemPublished();
		
		items.removeAll(itemsToolkit);
		model.setAttribute("items", items);
		model.setAttribute("toolkitId", request.getModel().getAttribute("toolkitId"));
		
		request.unbind(entity, model, "quantity","item.name");
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		result=new Quantity();
		
		final Integer toolkitId=request.getModel().getInteger("toolkitId");
		final Toolkit toolkit= this.repository.findOneToolkitById(toolkitId);
		
		result.setToolkit(toolkit);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("quantity")) {
			errors.state(request, entity.getQuantity() >0, "quantity", "inventor.quantity.form.error.nullquantity");
		}
		if(entity.getItem().getType().equals(ItemType.TOOL)) {
			errors.state(request, entity.getQuantity() == 1, "quantity", "inventor.quantity.form.error.one-tool");
		}
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		final String itemName= request.getModel().getAttribute("item.name").toString();
		final Item item=this.repository.findItemByName(itemName);
		entity.setItem(item);
		this.repository.save(entity);
	}
}
