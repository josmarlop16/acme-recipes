package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {
	
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
	
	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void negativeTest() {

		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkNotButtonExists("Publish");

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
