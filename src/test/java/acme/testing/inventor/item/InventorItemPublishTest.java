package acme.testing.inventor.item;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorItemPublishTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	@BeforeAll
	public void createItems() {
		super.signIn("inventor2", "inventor2");
		
		navigateItemListingByType("");
		
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "Component-03");
		super.fillInputBoxIn("code", "COM-435-E");
		super.fillInputBoxIn("technology", "Technology-02");
		super.fillInputBoxIn("description", "This is a description");
		super.fillInputBoxIn("retailPrice", "EUR 13.00");
		super.fillInputBoxIn("type", "COMPONENT");
		super.fillInputBoxIn("link", "http://www.component-03.org");
		super.clickOnSubmit("Create");
	}
	
	// Test cases -------------------------------------------------------------
	
	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor2", "inventor2");

		navigateItemListingByType("COMPONENT");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {

		super.signIn("inventor2", "inventor2");

		navigateItemListingByType("COMPONENT");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkNotButtonExists("Publish");

		super.signOut();
	}

	public void hackingTest() {
//		Actualmente el framework no tiene las herramientas necesarias para simular este
//		hackeo, asi que se indican los pasos a seguir a continuacion:
//			1. Crear un item con inventor2
//			2. Iniciar sesion con inventor3, e intentar acceder a la url /inventor/item/show?id=XX,
//			siendo XX el id del item creado previamente
	}

	// Ancillary methods ------------------------------------------------------
	
	public void navigateItemListingByType(String itemType) {
		if(itemType.equals("TOOL")) {
			super.clickOnMenu("Inventor", "Tool list");
		}
		else {
			super.clickOnMenu("Inventor", "Components list");
		}
	}
	
}
