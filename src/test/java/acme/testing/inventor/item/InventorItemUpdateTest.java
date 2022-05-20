package acme.testing.inventor.item;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------
	@BeforeAll
	public void createItems() {
		super.signIn("inventor2", "inventor2");

		navigateItemListingByType("TOOL");
			
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "Tool-03");
		super.fillInputBoxIn("code", "TOO-435-E");
		super.fillInputBoxIn("technology", "Technology-02");
		super.fillInputBoxIn("description", "This is a description");
		super.fillInputBoxIn("retailPrice", "EUR 13.00");
		super.fillInputBoxIn("type", "TOOL");
		super.fillInputBoxIn("link", "http://www.tool-03.org");
		super.clickOnSubmit("Create");
		
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
	
//	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String itemType) {
		super.signIn("inventor2", "inventor2");

		navigateItemListingByType(itemType);
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("type", itemType);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(0, 0, name);
		super.checkColumnHasValue(0, 1, code);
		super.checkColumnHasValue(0, 2, technology);
		super.checkColumnHasValue(0, 3, description);
		super.checkColumnHasValue(0, 4, retailPrice);
		super.checkColumnHasValue(0, 6, link);
		super.clickOnListingRecord(0);

		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String itemType) {

		super.signIn("inventor2", "inventor2");

		navigateItemListingByType(itemType);
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

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
