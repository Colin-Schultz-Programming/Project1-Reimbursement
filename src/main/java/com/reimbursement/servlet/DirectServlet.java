package com.reimbursement.servlet;

import java.io.IOException;

/**
 * 
 * servlet lifecycle
 * 
 * 		init				called once
 * 		service				called everytime a request is processed
 * 		destroy				called once
 * 
 * 
 * 
 * REST
 * 		REpresentational State Transfer
 * 	six rest constraints
 * 		1. stateless
 * 			it does not keep track of the client and does not care
 * 				if it is the same or different client during 
 * 				subsequent requests.
 * 		2. client server separation
 * 			the client and the server are kept separate and the
 * 				updating of one does not mean the other has to.
 * 		3. layered system
 * 			the client is unaware of the different layers of the 
 * 				restful application.
 * 		4. code on demand (optional)
 * 			when making a request, we can get any type of format back 
 * 				in the response. RESTful applications can send back
 * 				any format including html, xml, javascript, java, css, etc...
 * 		5. cacheable
 * 			the information coming back from the restful application
 * 				should be cacheable.
 * 		6. uniform interface
 * 			we use the same endpoint with different http verbs to interact
 * 				with the same objects. This provides a uiform interface
 * 				to work with our api's.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "direct", urlPatterns = { "/direct" }, /*
			 * load on startup will determine how the servlet is
			 * initialize. >=0 will be eager, <0 lazy
			 */ loadOnStartup = 0)
public class DirectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<h1 style=\"color:red\">this is a direct response</h1>");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("it is being initialized");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("it is in service");
		super.service(req, resp);
	}

	@Override
	public void destroy() {
		System.out.println("it is being destroyed");
		super.destroy();
	}
}
