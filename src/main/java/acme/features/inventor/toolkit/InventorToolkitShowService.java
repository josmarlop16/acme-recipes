package acme.features.inventor.toolkit;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorToolkitRepository repository;


		@Override
		public boolean authorise(final Request<Toolkit> request) {
			assert request != null;
			return true;
		}

		@Override
		public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			List<Item> items;
			items=this.repository.findItemsByToolkitId(entity.getId()).stream().collect(Collectors.toList());
			
			
			final Money eurRetailPrice = new Money();
			final Money usdRetailPrice = new Money();
			final Money gbpRetailPrice = new Money();
			
			String currency;
			double eurAmount=0.0;
		    double usdAmount=0.0;
			double gbpAmount=0.0;
			
			for(final Item item: items){
				currency=item.getRetailPrice().getCurrency();
				
				switch(currency) {
					case "EUR":
						eurAmount=+item.getRetailPrice().getAmount();
						break;
					case "USD":
						usdAmount=+item.getRetailPrice().getAmount();
						break;
					case "GBP":
						gbpAmount=+item.getRetailPrice().getAmount();
						break;
					default:
						break;
						
				}
			}
			eurRetailPrice.setCurrency("EUR");
			eurRetailPrice.setAmount(eurAmount);
			
			usdRetailPrice.setCurrency("USD");
			usdRetailPrice.setAmount(usdAmount);
			
			gbpRetailPrice.setCurrency("GBP");
			gbpRetailPrice.setAmount(gbpAmount);
			
			model.setAttribute("toolkitId", entity.getId());
			model.setAttribute("EUR", eurRetailPrice);
			model.setAttribute("USD", usdRetailPrice);
			model.setAttribute("GBP", gbpRetailPrice);
			
			request.unbind(entity, model, "title", "code", "description", "assemblyNotes","link");
		}

		@Override
		public Toolkit findOne(final Request<Toolkit> request) {
			assert request != null;

			Toolkit result;
			int id;
			
			id = request.getModel().getInteger("id");
			result = this.repository.findToolkitById(id);

			return result;
		}
}