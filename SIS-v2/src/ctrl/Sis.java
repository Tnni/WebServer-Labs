package ctrl;
import model.SIS;
import model.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentBean;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Sis")
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	SIS s;
	
	public Sis() {
		super();
		try {
			this.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputError = null;
		if (request.getParameter("report") == null) {
			String target = "/Form.jspx";
			request.getRequestDispatcher(target).forward(request, response);
		}else {
			try {

				String namePrefix = request.getParameter("name");
				Map<String, StudentBean> retrieved = new HashMap<String, StudentBean>();
				Integer mCT;
				if (request.getParameter("mct").isEmpty()) {
					mCT = Integer.parseInt(this.getServletContext().getInitParameter("mct"));
				} else {
					mCT = Integer.parseInt(request.getParameter("mct"));
				}
				retrieved = s.retriveStudent(namePrefix, mCT);

				String result = "<table><tr><th>Id</th><th>Name</th><th>Credits Taken</th><th>Credits to Graduate</th></tr>";

				Iterator iterator = retrieved.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					result += "<tr><td>" + ((StudentBean) entry.getValue()).getSid() + "</td><td>" + ((StudentBean) entry.getValue()).getName() + "</td><td>" + ((StudentBean) entry.getValue()).getCredit_taken() + "</td><td>" + ((StudentBean) entry.getValue()).getCredit_graduate() +"</td></tr>";
				}
				
				System.out.println(result);
				this.getServletContext().setAttribute("result", result);

				String target = "/Form.jspx";
				request.getRequestDispatcher(target).forward(request, response);
			} catch (Exception e) {
				if (e.equals("Special Charater in namePrefix or credit_taken is not integer")) {
					inputError = "Special Charater in namePrefix or credit_taken is not integer";
					this.getServletContext().setAttribute("inputError", inputError);
					
					String target = "/Form.jspx";
					request.getRequestDispatcher(target).forward(request, response);
				}
			}
			
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		try {
			s = new SIS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

