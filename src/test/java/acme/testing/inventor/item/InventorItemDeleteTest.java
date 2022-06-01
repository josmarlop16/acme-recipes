package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemDeleteTest extends TestHarness {
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String name, final String code, final String technology, 
							 final String description, final String retailPrice, final String link, final String itemType) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Inventor", "Tool list");
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "Tool Test Delete");
		super.fillInputBoxIn("code", "TOO-897-L");
		super.fillInputBoxIn("technology", "Technology-01");
		super.fillInputBoxIn("description", "Description");
		super.fillInputBoxIn("retailPrice", "EUR 5.00");
		super.fillInputBoxIn("type", "TOOL");
		super.fillInputBoxIn("link", "https://www.google.es");
		super.clickOnSubmit("Create");

		
		super.clickOnMenu("Inventor", "Tool list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Inventor", "Components list");
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", "Component Test Delete");
		super.fillInputBoxIn("code", "TRS-897-M");
		super.fillInputBoxIn("technology", "Technology-01");
		super.fillInputBoxIn("description", "Description");
		super.fillInputBoxIn("retailPrice", "EUR 5.00");
		super.fillInputBoxIn("type", "COMPONENT");
		super.fillInputBoxIn("link", "https://www.google.es");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "Components list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();

		
		
		

		
	}
	
		// En este caso, segun esta implementado, no tendria sentido hacer un caso negativo, ya que un patron siempre puede borrar los chimpums
		// creados por el, siempre le aparecera la opcion de borrar.



}
