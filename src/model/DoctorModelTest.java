package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class DoctorModelTest {

	DoctorModel dr = new DoctorModel();
	@Test
	public final void testViewDRProfile() {
		DoctorModel dr = new DoctorModel();
		Doctor doc = dr.viewDRProfile();
		assertNotNull(doc);
	}

	@Test
	public final void testGetSameDeptDRProfile() {
		ArrayList<Doctor> doctorList = dr.getSameDeptDRProfile();
		assertNotNull(doctorList);
	}

	@Test
	public final void testEditDRProfile() {
	}

	@Test
	public final void testGetDID() {
	}

	@Test
	public final void testGetRole() {
		String role = dr.getRole();
		assertNotNull(role);
	}

	@Test
	public final void testGetLevel() {
		String level = dr.getLevel();
		assertNotNull(level);
	}

	@Test
	public final void testGetDept() {
		String doc = dr.getDept();
		assertNotNull(doc);
	}

}
