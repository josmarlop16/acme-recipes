package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController extends AbstractController<Inventor, Patronage>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageListByInventorService listAllService;
	
	@Autowired
	protected InventorPatronageShowService showService;
	
	@Autowired
	protected InventorPatronageAcceptService acceptService;
	
	@Autowired
	protected InventorPatronageDeniedService deniedService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
		super.addCommand("accept", "update", this.acceptService);
		super.addCommand("denied", "update", this.deniedService);
	}
}
