package acme.features.chef.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPatronageController extends AbstractController<Chef, Patronage>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPatronageListByChefService listAllService;
	
	@Autowired
	protected ChefPatronageShowService showService;
	
	@Autowired
	protected ChefPatronageAcceptService acceptService;
	
	@Autowired
	protected ChefPatronageDeniedService deniedService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
		super.addCommand("accept", "update", this.acceptService);
		super.addCommand("denied", "update", this.deniedService);
	}
}
