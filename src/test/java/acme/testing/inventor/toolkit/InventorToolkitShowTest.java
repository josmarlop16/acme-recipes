package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitShowTest extends TestHarness {
	// Lifecycle management ---------------------------------------------------

			// Test cases -------------------------------------------------------------

			@ParameterizedTest
			@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
			@Order(10)
			public void positiveTest(final int recordIndex,final String title, final String code, final String description, final String assemblyNotes, final String link) {
				super.signIn("administrator", "administrator");

				super.clickOnMenu("Inventor", "List my toolkits");
				super.checkListingExists();
				super.sortListing(0, "asc");
				
				super.checkColumnHasValue(recordIndex, 0, title);

				super.clickOnListingRecord(recordIndex);
				super.checkFormExists();
				super.checkInputBoxHasValue("title", title);
				super.checkInputBoxHasValue("code", code);
				super.checkInputBoxHasValue("description", description);
				super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
				super.checkInputBoxHasValue("link", link);
				

				super.signOut();
			}

			// Ancillary methods ------------------------------------------------------
			
}
