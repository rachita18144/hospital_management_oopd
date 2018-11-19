package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GetPatientDetailsModelTest {

	@Test
	public final void testGetPatientDataFromID() {
		Patient patient = GetPatientDetailsModel.getPatientDataFromID("7");
		assertEquals(99999, patient.getContact());
	}

}
