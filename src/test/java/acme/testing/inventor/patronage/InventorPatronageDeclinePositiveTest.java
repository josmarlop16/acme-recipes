package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageDeclinePositiveTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void acceptPositiveTest(final String status, final String code, final String stuff, final String budget, final String periodOfTime, final String optionalLink) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Inventor", "Patronage List");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", "PROPOSED");
		super.clickOnSubmit("Decline");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", "DENIED");
		
		super.signOut();
	}


	
}
