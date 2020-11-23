package model;

import java.util.Map;

import bean.EnrollmentBean;
import bean.StudentBean;

public class SIS {

	StudentDAO sDAO;
	EnrollmentDAO	eDAO;
	
	public SIS() throws Exception {
		sDAO = new StudentDAO();
		eDAO = new EnrollmentDAO();
	}

	public Map<String, StudentBean> retriveStudent(String namePrefix, Integer mCT) throws Exception {
		try {
			return sDAO.retrieve(namePrefix, mCT);
		} catch (Exception e) {
			throw new Exception("Special Charater in namePrefix or credit_taken is not integer");
		}
	}
	
	public Map<String, EnrollmentBean> retriveEnrollment() throws Exception {
		return null;
		
	}
	
}
