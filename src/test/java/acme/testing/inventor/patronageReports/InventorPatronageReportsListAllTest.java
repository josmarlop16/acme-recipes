package acme.testing.inventor.patronageReports;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import acme.testing.TestHarness;

public class InventorPatronageReportsListAllTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String seqNumber, final String creation, final String memorandum, final String optionalLink) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Inventor", "Patronage Reports list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 0, seqNumber);
		super.checkColumnHasValue(0, 1, creation);
		super.checkColumnHasValue(0, 2, memorandum);
		super.checkColumnHasValue(0, 3, optionalLink);

		super.signOut();
		
	}

	// Ancillary methods ------------------------------------------------------
	
}
