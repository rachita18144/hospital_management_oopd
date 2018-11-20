package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GetDoctorDetailsModelTest {

	@Test
	public final void testGetAllDoctorDetails() {
		ArrayList<Doctor> doctorList = GetDoctorDetailsModel.getAllDoctorDetails();
		assertNotNull(doctorList);
	}

	@Test
	public final void testGetAllDoctorDetailsObjectName() {
		ArrayList<Doctor> doctorList = GetDoctorDetailsModel.getAllDoctorDetails();
		assertEquals("rachi",doctorList.get(0).getFirstName());
	}
	
	@Test
	public final void testGetAllDoctorDetailsObjectEmail() {
		ArrayList<Doctor> doctorList = GetDoctorDetailsModel.getAllDoctorDetails();
		assertEquals("delhi",doctorList.get(0).getAddress());
	}


	@Test
	public final void testGetAllDoctorDetailsCategory() {
		GetDoctorDetailsModel doc = new GetDoctorDetailsModel();
		ArrayList<Doctor> doctorList = doc.getAllDoctorDetailsForCategory("orthopedics");
		assertEquals("orthopedics",doctorList.get(0).getSpecialization());
	}
}
