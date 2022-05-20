package acme.testing.inventor.item;

import acme.testing.TestHarness;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class InventorItemCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String itemType) {
		super.signIn("inventor2", "inventor2");

		navigateItemListingByType(itemType);
			
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("type", itemType);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		navigateItemListingByType(itemType);
		super.checkListingExists();
		super.sortListing(0, "asc");
		if(itemType.equals("TOOL")) {
			super.checkColumnHasValue(recordIndex-3, 0, name);
			super.checkColumnHasValue(recordIndex-3, 1, code);
			super.checkColumnHasValue(recordIndex-3, 2, technology);
			super.checkColumnHasValue(recordIndex-3, 3, description);
			super.checkColumnHasValue(recordIndex-3, 4, retailPrice);
			super.checkColumnHasValue(recordIndex-3, 6, link);
			super.clickOnListingRecord(recordIndex-3);
		}
		else {
			super.checkColumnHasValue(recordIndex, 0, name);
			super.checkColumnHasValue(recordIndex, 1, code);
			super.checkColumnHasValue(recordIndex, 2, technology);
			super.checkColumnHasValue(recordIndex, 3, description);
			super.checkColumnHasValue(recordIndex, 4, retailPrice);
			super.checkColumnHasValue(recordIndex, 6, link);
			super.clickOnListingRecord(recordIndex);
		}

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

		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/item/create");
		super.checkPanicExists();
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
