package com.ab.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employeeSearch")
public class EmployeeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String GET_EMP_DETAILS_BY_ENO = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";

       
	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get access to servlet config object:
		ServletContext sc = getServletContext();
		
		// read context values
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String username = sc.getInitParameter("dbuser");
		String password = sc.getInitParameter("password");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException cne) {
			System.out.println("Oracle driver class not found");
		}
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		try {
			String em = request.getParameter("eno");
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/errorServlet");
			rd.forward(request, response);
		}

		int empNo = 101Integer.parseInt(request.getParameter("eno"));
		
		try(Connection con = DriverManager.getConnection(url, username, password)) {
			try(PreparedStatement ps = con.prepareStatement(GET_EMP_DETAILS_BY_ENO)){
				ps.setInt(1, empNo);
				
				try(ResultSet rs = ps.executeQuery()){
					
					if(rs.next()) {
						pw.println(
								"<h1 style='color:blue;text-align:center'>" + empNo + " Employee details are: </h1>");
						pw.println(
								"<h2 style='color:blue;text-align:center'> Employee No" + rs.getInt(1) + " <br> </h2>");
						pw.println("<h2 style='color:blue;text-align:center'> Employee Name" + rs.getString(2)
								+ " <br> </h2>");
					}else {
						pw.println("<h1 style='color:blue;text-align:center'> No records found for " + empNo + "</h1>");
					}
					
				}
				
			}
			pw.println("<a href='input.html'>home</a>");
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/errorServlet");
			rd.forward(request, response);
			
			// Creating Request Dispatcher using Servlet Context:
			ServletContext scc = getServletContext();
			RequestDispatcher rd = scc.getRequestDispatcher("/errorServlet");
			// in sc.getRequestDispatcher("/errorServlet") "/" is mendatory to place. 
			rd.forward(request, response);
			
			// Configuring JSP file as Error Page
			RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
			rd.forward(request, response);
			// Like this we can take html file as error file.
		}
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// code to rs.include();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = getServletContext();

		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String username = sc.getInitParameter("dbuser");
		String password = sc.getInitParameter("password");

		try {
			Class.forName(driver);
			PrintWriter pw = response.getWriter();
			response.setContentType("text/html");

			// include header
			RequestDispatcher headerRequest = request.getRequestDispatcher("/headerServlet");
			headerRequest.include(request, response);

			int empNo = 101/* Integer.parseInt(request.getParameter("eno")) */;

			try (Connection con = DriverManager.getConnection(url, username, password)) {
				try (PreparedStatement ps = con.prepareStatement(GET_EMP_DETAILS_BY_ENO)) {
					ps.setInt(1, empNo);

					try (ResultSet rs = ps.executeQuery()) {
						if (rs.next()) {
							pw.println("<h1 style='color:blue;text-align:center'>" + empNo
									+ " Employee details are: </h1>");
							pw.println("<h2 style='color:blue;text-align:center'> Employee No" + rs.getInt(1)
									+ " <br> </h2>");
							pw.println("<h2 style='color:blue;text-align:center'> Employee Name" + rs.getString(2)
									+ " <br> </h2>");
						} else {
							pw.println("<h1 style='color:blue;text-align:center'> No records found for " + empNo
									+ "</h1>");
						}

					}
				}
			}
			pw.println("<a href='input.html'>home</a>");

			// include footer
			RequestDispatcher fotter = request.getRequestDispatcher("/footer.html");
			fotter.include(request, response);

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/errorServlet");
			rd.forward(request, response);
		}
	}

}
