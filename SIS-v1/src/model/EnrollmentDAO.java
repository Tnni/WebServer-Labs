package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.EnrollmentBean;

public class EnrollmentDAO {
	private DataSource ds;

	public EnrollmentDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, EnrollmentBean> retrieve(String namePrefix, int credit_taken) throws SQLException {
		String query = "select * from students where surname like '%" + namePrefix + "%'and credit_taken >= "
				+ credit_taken;
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String cid = r.getString("CID") ;
			String sid = r.getString("SID");
			String c = r.getString("CREDIT");
			//rv.put(name, );
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

}
