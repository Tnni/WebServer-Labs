package bean;

public class StudentBean {

	private String sid;
	private String name;
	private int credit_taken;
	private int credit_graduate;
	
	public  StudentBean(String s, String n, int ct, int cg) {
		sid = s;
		name = n;
		credit_taken = ct;
		credit_graduate = cg;
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredit_taken() {
		return credit_taken;
	}

	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}

	public int getCredit_graduate() {
		return credit_graduate;
	}

	public void setCredit_graduate(int credit_graduate) {
		this.credit_graduate = credit_graduate;
	}
	
}
