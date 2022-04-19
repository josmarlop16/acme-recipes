package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListTest extends TestHarness{
	

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAdministrator(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String emailAddress) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Chirps", "List Chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, emailAddress);


		


		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestAnonymous(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String emailAddress) {

		super.clickOnMenu("Chirps", "List Chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, emailAddress);

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestInventor(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String emailAddress) {
		super.signIn("inventor03", "inventor03");

		super.clickOnMenu("Chirps", "List Chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, emailAddress);


		


		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
