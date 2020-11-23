package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.SIS;

@Path("student") // this is the path of the resource
public class Student {
	
	//curl -X POST 'http://localhost:8080/SIS-v3/rest/student/create?sid=003&givenName=John&surName=Wayne&creditTaken=3&creditGraduate=1'
	//curl -X GET 'http://localhost:8080/SIS-v3/rest/student/read?studentName=Way'
	//curl -X DELETE 'http://localhost:8080/SIS-v3/rest/student/delete?sid=003'
	
	@GET
	@Path("/read/")
	@Produces("text/plain")
	public String getStudent(@QueryParam("studentName") String name) throws Exception {
		// add the body of the method here… you can try in simple steps, for example,
		// just return the query parameter so you can see it works..
		// you should call a method from the model, SIS.getInstance().getAsXML(name) for
		// example or you might already have a method from previous labs
		SIS s = SIS.getInstance();
		try {
			return s.export(name, 0);
		}catch(Exception e){
			return "Not Found";
		}
	}

	// do not copy and paste, type it so you will better remember the //patterns
	@POST
	@Path("/create/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String createStudent(@QueryParam("sid") String sid, @QueryParam("givenName") String givenname,
			@QueryParam("surName") String surname, @QueryParam("creditTaken") String credittaken,
			@QueryParam("creditGraduate") String creditgraduate) throws Exception {
		// add the body of the method here… you can try in simple steps, for example,
		// just return the query parameters so you can see //it works..
		// later, you should call a method from the model,
		// //SIS.getInstance().create(….)
		int ct = Integer.parseInt(credittaken);
		int cg = Integer.parseInt(creditgraduate);
		SIS s = SIS.getInstance();
		try {
			return "InsertedRow:" + s.insert(sid, givenname, surname, ct, cg);
		}catch(Exception e){
			return "InsertedRow: 0";
		}
	}
// curl -X POST 'http://localhost:8080/SIS-v3/rest/student/create?sid=003&givenName=John&surName=Wayne&creditTaken=3&creditGraduate=1'
	@DELETE
	@Path("/delete/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public String delete(@QueryParam("sid") String sid) throws Exception {
		// add the body of the method here..
		// you should call a method from the model, //SIS.getInstance().delete(….)
		SIS s = SIS.getInstance();
		return "DeletedRow:" + s.delete(sid);
	}
}