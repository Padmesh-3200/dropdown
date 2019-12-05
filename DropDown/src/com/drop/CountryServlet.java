package com.drop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GeneralMethods gm = new GeneralMethods();

		try {

			String country = request.getParameter("country");
			String state = request.getParameter("state");

			System.out.println(country);
			if (country == null && state == null) {
				ArrayList<GetterSetter> countrylist = gm.counnames();

				request.setAttribute("countrylist", countrylist);

				RequestDispatcher rd = request.getRequestDispatcher("/dropdown.jsp");
				rd.forward(request, response);
			} else if (country != null && state == null) {
				ArrayList<GetterSetter> countrylist = gm.counnames();

				ArrayList<GetterSetter> statelist = gm.statename(country);

				request.setAttribute("countrylist", countrylist);
				request.setAttribute("statelist", statelist);
				request.setAttribute("selcountry", country);

				RequestDispatcher rd = request.getRequestDispatcher("/dropdown.jsp");
				rd.forward(request, response);
			} else {
				ArrayList<GetterSetter> countrylist = gm.counnames();

				ArrayList<GetterSetter> statelist = gm.statename(country);

				ArrayList<GetterSetter> citylist = gm.Citynames(state);
				for (int i = 0; i < citylist.size(); i++) {
					System.out.println(citylist.get(i).getCityId()+"  "+citylist.get(i).getCityName());
				}
               
				request.setAttribute("countrylist", countrylist);
				request.setAttribute("statelist", statelist);
				request.setAttribute("citylist", citylist);
				request.setAttribute("selcountry", country);
				request.setAttribute("selstate", state);

				RequestDispatcher rd = request.getRequestDispatcher("/dropdown.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
