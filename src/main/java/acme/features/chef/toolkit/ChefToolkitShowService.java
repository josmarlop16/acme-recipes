package acme.features.chef.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefToolkitShowService implements AbstractShowService<Chef, Toolkit> {
	
		// Internal state ---------------------------------------------------------

		@Autowired
		protected ChefToolkitRepository repository;
		

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
			Collection<Quantity> quantities;
			quantities=this.repository.findQuantityByToolkitId(entity.getId());
			
			final String systemCurrency= this.repository.systemCurrency();
			
			String currency;
			final List<Money> retailPrices = new ArrayList<>();
			
			  double eurAmount=0.0;
			  double usdAmount=0.0;
			  double gbpAmount=0.0;
			  
		
			 
			  
			  for(final Quantity item: quantities){
				  
				  currency=item.getItem().getRetailPrice().getCurrency();
				  
				 if(currency.equals("EUR")) {
					 eurAmount=eurAmount+item.getItem().getRetailPrice().getAmount()*item.getQuantity();
			  		 
				 }else if(currency.equals("USD")) {
					 usdAmount=usdAmount+item.getItem().getRetailPrice().getAmount()*item.getQuantity();
				 }else if(currency.equals("GBP")) {
					 gbpAmount=gbpAmount+item.getItem().getRetailPrice().getAmount()*item.getQuantity();
				 }
			  
			  }
			  if(eurAmount !=0.0) {
				  final Money eurRetailPrice = new Money();
				  eurRetailPrice.setCurrency("EUR");
				  eurRetailPrice.setAmount(eurAmount);
				  retailPrices.add(eurRetailPrice);
				  model.setAttribute("EUR", eurRetailPrice);
					
			  }
			 
			  if(usdAmount!=0.0) {
				  final Money usdRetailPrice = new Money();
				  usdRetailPrice.setCurrency("USD");
				  usdRetailPrice.setAmount(usdAmount);
				  retailPrices.add(usdRetailPrice);
				  model.setAttribute("USD", usdRetailPrice);
			
			  }
			 
			  if(gbpAmount!=0.0) {
				  final Money gbpRetailPrice = new Money();
				  gbpRetailPrice.setCurrency("GBP");
				  gbpRetailPrice.setAmount(gbpAmount);
				  retailPrices.add(gbpRetailPrice);
				  model.setAttribute("GBP", gbpRetailPrice);
			  }
			  
			  
			
			final Money totalComputed=new Money();
			totalComputed.setCurrency(systemCurrency);
			Double amounts=0.0;
			for(final Money retailPrice:retailPrices) {
				if(retailPrice.getCurrency().equals(systemCurrency)) {
					amounts+=retailPrice.getAmount();
				}else {
					amounts+=MoneyExchangePerform.computeMoneyExchange(retailPrice, systemCurrency).getTarget().getAmount();
				}
				
				
				
			}
			
			totalComputed.setAmount(amounts);
			totalComputed.setCurrency(systemCurrency);
						
			model.setAttribute("toolkitId", entity.getId());

			model.setAttribute("computedPrice", totalComputed);
			
			
			

			request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");

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
