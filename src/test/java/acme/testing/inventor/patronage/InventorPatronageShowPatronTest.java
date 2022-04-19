package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageShowPatronTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/show-patron.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String company, final String statement, final String optionalLink) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Inventor", "Patronage List");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("patron.company", company);
		super.checkInputBoxHasValue("patron.statement", statement);
		super.checkInputBoxHasValue("patron.optionalLink", optionalLink);

		super.signOut();
		
	}

	// Ancillary methods ------------------------------------------------------
	
}
