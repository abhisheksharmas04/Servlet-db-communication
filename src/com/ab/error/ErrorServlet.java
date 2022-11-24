package com.ab.error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/errorServlet")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get printwriter
		PrintWriter pw = response.getWriter();

		// set response content type
		response.setContentType("text/html");

		// Place non-techincal guiding message for the end user
		pw.println("<h1 style='color:red;text-align:center'> Internal Problme Try Again </h1>");
		pw.println("<br><a href='input.html'> Home </a>");

		pw.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
