package acme.features.epicure.patronageReport;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicurePatronageReportController extends AbstractController<Epicure, PatronageReport>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected EpicurePatronageReportListMineService listService;
	
	@Autowired
	protected EpicurePatronageReportShowMineService showService;

	@PostConstruct
	protected void initialise() {	
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}
