package bean;

import java.util.List;

public class EnrollmentBean {
	private String cid;
	private List<String> student;
	private int credit;
	
	public EnrollmentBean(String cid, List<String> student, int credit) {
		super();
		this.cid = cid;
		this.student = student;
		this.credit = credit;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public List<String> getStudent() {
		return student;
	}

	public void setStudent(List<String> student) {
		this.student = student;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	
}
