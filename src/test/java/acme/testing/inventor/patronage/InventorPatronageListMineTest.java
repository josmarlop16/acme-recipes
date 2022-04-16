package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import acme.testing.TestHarness;

public class InventorPatronageListMineTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String status, final String code, final String stuff, final String budget, final String periodOfTime, final String optionalLink) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Inventor", "Patronage List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 0, status);

		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("stuff", stuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("periodOfTime", periodOfTime);
		super.checkInputBoxHasValue("optionalLink", optionalLink);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------
	
}
