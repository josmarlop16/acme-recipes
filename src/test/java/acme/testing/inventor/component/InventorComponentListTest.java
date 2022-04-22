package acme.testing.inventor.component;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorComponentListTest extends TestHarness {
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/component/component-list.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String name, final String code, final String technology, final String description,final String retailPrice,final String link) {
			
			super.signIn("inventor2", "inventor2");
			super.clickOnMenu("Items", "Components list");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.checkColumnHasValue(recordIndex, 0, name);
			super.checkColumnHasValue(recordIndex, 1, code);
			super.checkColumnHasValue(recordIndex, 2, technology);
			super.checkColumnHasValue(recordIndex, 3, description);
			super.checkColumnHasValue(recordIndex, 4, retailPrice);
			super.checkColumnHasValue(recordIndex, 5, link);
			
			super.signOut();

		}

		// Ancillary methods ------------------------------------------------------
		
}
