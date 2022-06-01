package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String title, final String code, final String description, final String assemblyNotes, final String link) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List my toolkits");

		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", "Test toolkit");
		super.fillInputBoxIn("code", "AKN-910");
		super.fillInputBoxIn("description", "Description test");
		super.fillInputBoxIn("assemblyNotes", "note test");
		super.fillInputBoxIn("link", "https://www.google.es");
		super.clickOnSubmit("Create");

		super.clickOnListingRecord(0);
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String code, final String description, final String assemblyNotes, final String link) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "List my toolkits");
		
		super.clickOnButton("Create"); 
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");

		super.clickOnListingRecord(recordIndex);

		super.checkNotSubmitExists("Delete");

		
		super.signOut();
		
	}

}
