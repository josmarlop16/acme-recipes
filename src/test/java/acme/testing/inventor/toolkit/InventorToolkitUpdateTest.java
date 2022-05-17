package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness {
	
	@BeforeAll
	public void createToolkits() {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", "toolkit-01");
		super.fillInputBoxIn("code", "ART-765-Y");
		super.fillInputBoxIn("description", "This is a description");
		super.fillInputBoxIn("assemblyNotes", "Nothing");
		super.fillInputBoxIn("link", "http://www.toolkit-01.org");
		super.clickOnSubmit("Create");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTest(final int recordIndex, final String title, final String code, final String description, final String assemblyNotes, final String link) {

		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();

		super.clickOnListingRecord(0);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);

		super.signOut();
	}
	

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/toolkit/create");
		super.checkPanicExists();
	}

	
}
