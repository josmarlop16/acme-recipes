package acme.testing.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAdministratorConfigurationListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/configuration/list-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveCurrencyAuthenticatedTest(final int recordIndex, final String name, final String isDefault) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Configuration", "Currency");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, isDefault);
		
		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/configuration/list-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveCurrencyAdministratorTest(final int recordIndex, final String name, final String isDefault) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Configuration", "Currency");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, isDefault);

		super.signOut();
	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "/configuration/list-spam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveSpamWordsAuthenticatedTest(final int recordIndex, final String spamTerm, final String isStrong, final String threshold) {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Configuration", "Spam words");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, spamTerm);
		super.checkColumnHasValue(recordIndex, 1, isStrong);
		super.checkColumnHasValue(recordIndex, 2, threshold);

		super.signOut();
	}


	
	@ParameterizedTest
	@CsvFileSource(resources = "/configuration/list-spam.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void positiveSpamWordsAdministratorTest(final int recordIndex, final String spamTerm, final String isStrong, final String threshold) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Configuration", "Spam words");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, spamTerm);
		super.checkColumnHasValue(recordIndex, 1, isStrong);
		super.checkColumnHasValue(recordIndex, 2, threshold);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------
	
}
