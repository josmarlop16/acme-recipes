package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpCreateTest extends TestHarness {
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title,final String author, final String body,final String emailAddress) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Chirps", "Create Chirps");
		

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("emailAddress", emailAddress);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Chirps", "List Chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, title);
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("emailAddress", emailAddress);
		

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String title,final String author, final String body,final String emailAddress) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Chirps", "Create Chirps");
	
		super.fillInputBoxIn("title","");
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("emailAddress", emailAddress);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
	
}
