package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyComponentListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void anonymousListComponents(final int recordIndex, final String name, final String code, final String technology, final String description, final String link) {
	
		super.clickOnMenu("Items", "Components list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 5, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/components.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void administratorListComponents(final int recordIndex, final String name, final String code, final String technology, final String description, final String link) {

		super.signIn("administrator", "administrator");
	
		super.clickOnMenu("Items", "Components list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, description);
		super.checkColumnHasValue(recordIndex, 5, link);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);

	
		super.signOut();
		
	}
}
