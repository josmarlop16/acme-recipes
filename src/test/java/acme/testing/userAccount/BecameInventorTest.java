package acme.testing.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class BecameInventorTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becameInventor(final String username, final String password, final String name, final String surname, final String email, final String phone) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become inventor");
		super.fillInputBoxIn("company", "Bulet.io");
		super.fillInputBoxIn("statement", "lorem");
		
		super.clickOnSubmit("Register");
		
		super.checkNotErrorsExist();
	}
}
