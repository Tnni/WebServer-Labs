package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class StudentBean {
	@XmlElement
	private String sid;
	@XmlElement
	private String name;
	@XmlElement
	private int credit_taken;
	@XmlElement
	private int credit_graduate;
	
	public StudentBean(String sid, String name, int credit_taken, int credit_graduate) {
		super();
		this.sid = sid;
		this.name = name;
		this.credit_taken = credit_taken;
		this.credit_graduate = credit_graduate;
	}
	
	@XmlTransient
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlTransient
	public int getCredit_taken() {
		return credit_taken;
	}
	public void setCredit_taken(int credit_taken) {
		this.credit_taken = credit_taken;
	}
	
	@XmlTransient
	public int getCredit_graduate() {
		return credit_graduate;
	}
	public void setCredit_graduate(int credit_graduate) {
		this.credit_graduate = credit_graduate;
	}

}
