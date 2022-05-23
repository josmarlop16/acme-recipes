/*
 * AnonymousShoutController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolListService		listService;
	
	@Autowired
	protected InventorComponentListService		listComponentService;

	@Autowired
	protected InventorItemShowService		showService;
	
	
	@Autowired
	protected InventorItemCreateService createService;
	
	@Autowired
	protected InventorItemPublishService publishService;
	
	@Autowired
	protected InventorItemDeleteService deleteService;
	
	@Autowired
	protected InventorItemUpdateService updateService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish","update" ,this.publishService);
		super.addCommand("delete", this.deleteService);
		}

}
