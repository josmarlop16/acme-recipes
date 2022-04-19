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
	public void positiveTest(final String creationMoment, final String title, final String author, final String body, final String emailAddress) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Chirps", "List Chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(0, 0, "2022/04/09 11:00");
		super.checkColumnHasValue(0, 1, "Chirp-04");
		super.checkColumnHasValue(0, 2, "Mike Doe");
		super.checkColumnHasValue(0, 3, "This is the body");
		super.checkColumnHasValue(0, 4, "mikedoe@gmail.com");
		super.checkColumnHasValue(1, 0, "2022/04/10 11:00");
		super.checkColumnHasValue(1, 1, "Chirp-03");
		super.checkColumnHasValue(1, 2, "Mike Doe");
		super.checkColumnHasValue(1, 3, "This is the body");
		super.checkColumnHasValue(1, 4, "mikedoe@gmail.com");

		

		//super.clickOnListingRecord(0);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
