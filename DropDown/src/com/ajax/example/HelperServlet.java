package com.ajax.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drop.GeneralMethods;
import com.drop.GetterSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelperServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GeneralMethods gm = new GeneralMethods();

		String country = request.getParameter("country");
		String state = request.getParameter("state");
		System.out.println("Inside Helper class:" + country);
	
		try {
			if (country != null && state == null) {

				String statelist = gm.stateNamesAsString(country);

				System.out.println("Result:" + statelist);
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(statelist);

			}

			if (country == null && state != null) {

				String citylist = gm.cityNamesAsString(state);

				System.out.println("Result:" + citylist);
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(citylist);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	
	}

	private void getJsonAjax(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		
		GeneralMethods gm = new GeneralMethods();

		String country = request.getParameter("country");
		String state = request.getParameter("state");
		System.out.println("Inside Helper class:" + country);
		
		
	
		try {
			if (country != null && state == null) {

				ArrayList<GetterSetter> statelist = gm.jsonState(country);
				
				System.out.println("Result:" + statelist);
				
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(statelist);
				System.out.println(json);
				
				
				response.setContentType("application/json");
			    response.getWriter().write(json);	
			    response.setHeader("Cache-Control", "no-cache");
				

			}

			if (country == null && state != null) {

				String citylist = gm.cityNamesAsString(state);

				System.out.println("Result:" + citylist);
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(citylist);

			}
			}finally {
				
			}
			
	

	}

	public void getAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}

}
