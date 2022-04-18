package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementShowTest extends TestHarness {
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String creation, final String title,final String body ,final String critical, final String optionalLink) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Announcements", "Recent Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, creation);
		super.checkColumnHasValue(recordIndex, 1, title);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creation", creation);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("optionalLink", optionalLink);

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------
}
