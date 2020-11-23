package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.StudentBean;

public class StudentDAO {

	private DataSource ds;

	public StudentDAO() throws Exception {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, StudentBean> retrieve(String namePrefix, int credit_taken) throws Exception {
        	String query = "select * from students where surname like '%" + namePrefix + "%'and credit_taken >= " + credit_taken;
    		Map<String, StudentBean> rv = new HashMap<String, StudentBean>();
    		Connection con = this.ds.getConnection();
    		PreparedStatement p = con.prepareStatement(query);
    		ResultSet r = p.executeQuery();
    		System.out.println(namePrefix + "||" + credit_taken);
    		while (r.next()) {
    			String name = r.getString("GIVENNAME") + ", " + r.getString("SURNAME");
    			String em = r.getString("SID");
    			String ct = r.getString("CREDIT_TAKEN");
    			String cg = r.getString("CREDIT_GRADUATE");
    			StudentBean zz = new StudentBean(em, name, Integer.parseInt(ct), Integer.parseInt(cg));
    			rv.put(name, zz);
    		}
    		r.close();
    		p.close();
    		con.close();
    		return rv;
	}
	
}
