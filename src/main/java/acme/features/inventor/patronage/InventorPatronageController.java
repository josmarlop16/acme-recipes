package acme.features.inventor.patronage;

import org.springframework.stereotype.Controller;
import acme.framework.controllers.AbstractController;
import javax.annotation.PostConstruct;
import acme.roles.Inventor;
import acme.entities.patronages.Patronage;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageListByInventorService listAllService;
	
	@Autowired
	protected InventorPatronageShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
	}
}
