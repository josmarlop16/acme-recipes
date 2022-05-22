package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness {
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String creation,final String title,final String body, final String critical,final String optionalLink) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Announcements", "Create Announcements");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical", critical);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Announcements", "Recent Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, critical);
		
		
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("critical", critical);
		super.checkInputBoxHasValue("optionalLink", optionalLink);
		

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String creation,final String title,final String body, final String critical,final String optionalLink) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Announcements", "Create Announcements");
	
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);
		super.fillInputBoxIn("critical",critical);
		super.fillInputBoxIn("optionalLink", optionalLink);
		super.fillInputBoxIn("confirm", "true");
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
	}

	// Ancillary methods ------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
	
}
