package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserSignUpTest {

	@Test
	public final void testStoreSignUpDataForUser() {
	}

	@Test
	public final void testCheckUserAuthenticationDetails() {
		UserSignUp user = new UserSignUp();
		String check = user.checkUserAuthenticationDetails("abcd", "abcd");
		assertEquals("doctor",check);
	}

	@Test
	public final void TestCheckPatientAuthenticationDetails() {
		UserSignUp user = new UserSignUp();
		String check = user.checkUserAuthenticationDetails("qwerty", "qwerty");
		assertEquals("patient",check);
	}

	@Test
	public final void TestCheckAdminAuthenticationDetails() {
		UserSignUp user = new UserSignUp();
		String check = user.checkUserAuthenticationDetails("admin", "admin");
		assertEquals("admin",check);
	}

	@Test
	public final void TestCheckInvalidAuthenticationDetails() {
		UserSignUp user = new UserSignUp();
		String check = user.checkUserAuthenticationDetails("bla", "abcd");
		assertEquals("invalid",check);
	}

}
