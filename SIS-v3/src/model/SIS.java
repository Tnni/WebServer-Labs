package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import bean.EnrollmentBean;
import bean.StudentBean;

import org.json.JSONObject;

public class SIS {

	private static SIS s;
	public StudentDAO sDAO;
	public EnrollmentDAO eDAO;

	private SIS() throws Exception {
		sDAO = new StudentDAO();
		eDAO = new EnrollmentDAO();
	}

	public static SIS getInstance() throws Exception {
		if (s == null) {
			s = new SIS();
		}
		return s;
	}

	public Map<String, StudentBean> retriveStudent(String namePrefix, Integer mCT) throws Exception {
		if (namePrefix != null && mCT >= 0 && namePrefix.getClass().getName() == "java.lang.String"
				&& mCT.getClass().getName() == "java.lang.Integer") {
			if (containsSqlInjection(namePrefix)) {
				throw new Exception("Invalid name");
			} else {
				return sDAO.retrieve(namePrefix, mCT);
			}
		}
		return null;
	}

	public Map<String, EnrollmentBean> retriveEnrollment(String cid, Integer credit) throws Exception {
		if (cid.getClass().getName() == "java.lang.String" && credit.getClass().getName() == "java.lang.Integer"
				&& cid != null && credit != 0) {
			return eDAO.retrieve(cid, credit);
		}
		return null;
	}

	public String export(String namePrefix, Integer mCT) throws Exception {
		Map<String, StudentBean> r = new HashMap<String, StudentBean>();
		r = retriveStudent(namePrefix, mCT);
		List<StudentBean> sb = new ArrayList<StudentBean>();

		for (Map.Entry<String, StudentBean> entry : r.entrySet()) {
			sb.add(new StudentBean(entry.getValue().getSid(), entry.getValue().getName(),
					entry.getValue().getCredit_taken(), entry.getValue().getCredit_graduate()));
		}

		ListWrapper lw = new ListWrapper(namePrefix, mCT, sb);
		JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema sm = sf.newSchema(new File("D:\\Program Files\\EECS\\WORKPLACE\\SIS-v2\\WebContent\\SIS.xsd"));
		marshaller.setSchema(sm);

		StringWriter sw = new StringWriter();
		sw.write("\n");
		marshaller.marshal(lw, new StreamResult(sw));

		System.out.println(sw.toString()); // for debugging
		// return XML

		FileWriter fw = new FileWriter("D:\\Program Files\\EECS\\WORKPLACE\\SIS-v2\\WebContent\\SIS_Generated.xml");
		fw.write(sw.toString());
		fw.close();

		return sw.toString();
	}

	public int insert(String sid, String givenname, String surname, int credittake, int creditgraduate)
			throws SQLException, NamingException {
		if (givenname != null && surname != null && credittake >= 0 && creditgraduate >= 0
				&& givenname.getClass().getName() == "java.lang.String"
				&& surname.getClass().getName() == "java.lang.String"
				&& sid.matches("[0-9]+")) {
			if (containsSqlInjection(givenname) || containsSqlInjection(surname)) {
				return 0;
			} else {
				return sDAO.insert(sid, givenname, surname, credittake, creditgraduate);
			}
		}
		return 0;
	}

	public int delete(String sid) throws SQLException, NamingException {
		if (sid.matches("[0-9]+") && sid.length() > 2) {
			return sDAO.delete(sid);
		}
		return sDAO.delete(sid);
	}

	public static boolean containsSqlInjection(Object obj) {
		Pattern pattern = Pattern
				.compile("\\b(and|exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|"
						+ "truncate|char|declare|or)\\b|(\\*|;|\\+|'|%)");
		Matcher matcher = pattern.matcher(obj.toString());
		return matcher.find();
	}

	public String jsonPage(String namePrefix, Integer mCT) throws Exception {
		Map<String, StudentBean> r = new HashMap<String, StudentBean>();
		r = retriveStudent(namePrefix, mCT);
		List<StudentBean> sb = new ArrayList<StudentBean>();

		for (Map.Entry<String, StudentBean> entry : r.entrySet()) {
			sb.add(new StudentBean(entry.getValue().getSid(), entry.getValue().getName(),
					entry.getValue().getCredit_taken(), entry.getValue().getCredit_graduate()));
		}
		JSONObject jo = new JSONObject();
		jo.put(namePrefix, sb);
		return jo.toString();
	}

}
