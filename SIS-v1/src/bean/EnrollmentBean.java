package bean;

import java.util.ArrayList;

public class EnrollmentBean {
	
	private ArrayList<Integer> cid;
	private ArrayList<String> student;
	private int credit;
	
	
	public EnrollmentBean(ArrayList<Integer> c, ArrayList<String> s, int credit) {
		cid = c;
		student = s;
		this.credit = credit;
	}


	public ArrayList<Integer> getCid() {
		return cid;
	}


	public void setCid(ArrayList<Integer> cid) {
		this.cid = cid;
	}


	public ArrayList<String> getStudent() {
		return student;
	}


	public void setStudent(ArrayList<String> student) {
		this.student = student;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}

	
	
}
