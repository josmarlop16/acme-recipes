package acme.testing.authenticated.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedCurrencyListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/configuration/list-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String name, final String accepted, final String isDefault) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Configuration", "Currency");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 0, "EUR");
		super.checkColumnHasValue(0, 1, "true");
		super.checkColumnHasValue(1, 0, "GBP");
		super.checkColumnHasValue(1, 1, "false");
		super.checkColumnHasValue(2, 0, "USD");
		super.checkColumnHasValue(2, 1, "false");
		

		//super.clickOnListingRecord(0);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------
	
}
