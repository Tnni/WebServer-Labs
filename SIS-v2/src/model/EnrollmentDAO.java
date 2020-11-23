package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import bean.EnrollmentBean;

public class EnrollmentDAO {

	private DataSource ds;

	public EnrollmentDAO() throws ClassNotFoundException{
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			} catch (NamingException e) {
				e.printStackTrace();
				}
		}
	
	public Map<String, EnrollmentBean> retrieve(String cid, int credit) throws SQLException{
		String query = "select * from enrollment where cid like '%" + cid +"%'and credit = " + credit;
		Map<String, EnrollmentBean> rv = new HashMap<String, EnrollmentBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		List<String> student = new ArrayList<String>();
		
		while (r.next()){
			String sid = r.getString("SID");
			student.add(sid);
		}	
		EnrollmentBean eb = new EnrollmentBean(cid, student, credit);
		rv.put(cid, eb);
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
}
