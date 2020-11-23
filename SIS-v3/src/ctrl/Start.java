package ctrl;
import model.SIS;
import java.io.IOException;
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
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	SIS s;
	
	public Start() {
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
	@SuppressWarnings("unlikely-arg-type")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inputError = null;
		if (request.getParameter("report") == null && request.getParameter("gxml") == null && request.getParameter("json") == null && request.getParameter("ajax") == null) {
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

				@SuppressWarnings("rawtypes")
				Iterator iterator = retrieved.entrySet().iterator();
				while (iterator.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry entry = (Map.Entry) iterator.next();
					result += "<tr><td>" + ((StudentBean) entry.getValue()).getSid() + "</td><td>" + ((StudentBean) entry.getValue()).getName() + "</td><td>" + ((StudentBean) entry.getValue()).getCredit_taken() + "</td><td>" + ((StudentBean) entry.getValue()).getCredit_graduate() +"</td></tr>";
				}
				

				this.getServletContext().setAttribute("result", result);

				if(request.getParameter("gxml") != null || request.getParameter("json") != null) {
					String retrieve = "";
					try {
						retrieve = export(namePrefix, mCT);
						System.out.println(retrieve);
						
						if (request.getParameter("json") != null && request.getParameter("ajax") == null) {
							this.getServletContext().setAttribute("json", jsonPage(namePrefix, mCT));
							
							request.setAttribute("gxml", null);
							request.setAttribute("report", null);
							request.setAttribute("json", null);
							
							String target = "/Json.jspx";
							request.getRequestDispatcher(target).forward(request, response);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				request.setAttribute("gxml", null);
				request.setAttribute("report", null);
				request.setAttribute("json", null);
				
				if(request.getParameter("ajax") == null && request.getParameter("json") == null) {
					String target = "/Form.jspx";
					request.getRequestDispatcher(target).forward(request, response);
				}
				System.out.println(request.getParameter("ajax"));
				if (request.getParameter("ajax") != null) {
					response.getWriter().append(s.jsonPage(namePrefix, mCT));
				}
				
			} catch (Exception e) {
				if (e.equals("Invalid name")) {
					inputError = "Invalid name";
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
			s = SIS.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String export(String namePrefix, Integer mCT) throws Exception {
		return s.export(namePrefix, mCT);	
    }
	
    public String jsonPage(String namePrefix, Integer mCT) throws Exception {
    	return s.jsonPage(namePrefix, mCT);	
    }
    
}

