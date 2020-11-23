package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bean.StudentBean;

@XmlRootElement(name = "sisReport")
public class ListWrapper {
	@XmlAttribute
	private String namePrefix;
	
	@XmlAttribute(name = "creditTaken")
	private int credit_taken;
	
	@XmlElement(name = "studentList")
	private List<StudentBean> list;
	
	public ListWrapper(){
		
	}
	
	public ListWrapper(String namePrefix, int credit_taken, List<StudentBean> list) {
		super();
		this.namePrefix = namePrefix;
		this.credit_taken = credit_taken;
		this.list = list;
	}
	
}