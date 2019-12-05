package com.ajax.json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.drop.GeneralMethods;
import com.drop.GetterSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		
		GeneralMethods gm = new GeneralMethods();

		String country = request.getParameter("country");
		String state = request.getParameter("state");
		System.out.println("Inside Helper class:" + country);
		System.out.println("get in jsonhelper");
		
	
		try {
			if (country != null && state == null) {

				ArrayList<GetterSetter> statelist = gm.jsonState(country);
				
				System.out.println("Result:" + statelist);
				
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(statelist);
				System.out.println(json); 
				
				
				response.setContentType("text/xml");
			    //response.setCharacterEncoding("UTF-8");
			    response.setHeader("Cache-Control", "no-cache");
			    response.getWriter().write(json);	
			    
				

			}

			if (country == null && state != null) {

				ArrayList<GetterSetter> citylist = gm.jsonCity(state);

				System.out.println("Result:" + citylist);
				

				
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(citylist);
				System.out.println(json); 
				
				
			
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(json);

			}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
			}
				
		}
			
	
	
	


	public static void main(String arg[]) {
		try {
		GeneralMethods gm = new GeneralMethods();
		ArrayList<GetterSetter> statelist = gm.jsonState("102");
		
		System.out.println("Result:" + statelist);
		
		ObjectMapper obj = new ObjectMapper();
		String json = obj.writeValueAsString(statelist);
		System.out.println(json); 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}





}

