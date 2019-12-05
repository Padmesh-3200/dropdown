package com.drop;

 import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;
import  com.lab.util.DBConnection;

public class GeneralMethods {
	

	
	String sql=null;
	
	public ArrayList<GetterSetter> counnames() throws SQLException
	{
	     sql = "select id,name from country" ;
        
	     System.out.println(sql);
	    
	    ArrayList<GetterSetter> countrylist = new ArrayList<GetterSetter>();
		    Connection con =  DBConnection.getDBconnection();
		   // System.out.println("jaslk");
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);  
		    
		  //  GetterSetter gs = new GetterSetter();
		    
		    GetterSetter gs = null;
		    while(rs.next())
		    {     gs = new GetterSetter();
		          gs.setCountryName(rs.getString("name"));
		          gs.setCountryId(rs.getString("id"));
		          countrylist.add(gs);
		    }
			return countrylist;
			
		
	}
	
	
	public  ArrayList<GetterSetter> statename (String country) throws SQLException 	{

		sql="select stateid,states from states where CountryId ="+country+"";	
		
		System.out.println(sql);
		   ArrayList<GetterSetter> statelist = new ArrayList<GetterSetter>();
		    Connection con =  DBConnection.getDBconnection();
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    GetterSetter gs =null;
		    while(rs.next()) {     
		    	gs= new GetterSetter();
		    	gs.setStateId(rs.getString("stateid"));
		          gs.setStateName(rs.getString("states"));
		          statelist.add(gs);
		    }
		
		
		return statelist;
		
	}
public ArrayList<GetterSetter> Citynames(String state) throws SQLException
	
	{
	sql ="SELECT CityId, Cities FROM cities where StateId="+state;
	System.out.println(sql);
		ArrayList<GetterSetter> citynames = new ArrayList<GetterSetter>();
	    Connection con = DBConnection.getDBconnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		GetterSetter gs =null;
		while(rs.next())
		{
			gs= new GetterSetter();
	    	gs.setCityId(rs.getString("CityId"));
	        gs.setCityName(rs.getString("Cities"));
	        citynames.add(gs);
			
		}
		
		
		return citynames;
		
	}
	
	
	
	
	public  String stateNamesAsString (String country) throws SQLException 	{

		sql="select stateid,states from states where CountryId ="+country+"";
		System.out.println(sql);
		   String statelist = "";
		    Connection con =  DBConnection.getDBconnection();
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {    
		    	statelist=statelist+ rs.getString("stateid")+" # "+rs.getString("states")+" || ";
		    }
		
		
		return statelist;
		
	}
	
	public  String cityNamesAsString (String state) throws SQLException 	{

		sql="select CityId , Cities from cities where StateId = "+state;
		System.out.println(sql);
		   String citylist = "";
		    Connection con =  DBConnection.getDBconnection();
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    while(rs.next()) {    
		    	citylist=citylist+ rs.getString("CityId")+" # "+rs.getString("Cities")+" || ";
		    }
		
		
	return citylist;
		
	}
	
	public ArrayList<GetterSetter> jsonState(String country) throws SQLException 	{
 
	sql="select stateid,states from states where CountryId ="+country+"";	
		
		System.out.println(sql);
		   ArrayList<GetterSetter> statelist = new ArrayList<GetterSetter>();
		    Connection con =  DBConnection.getDBconnection();
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    GetterSetter gs =null;
		    while(rs.next()) {     
		    	gs= new GetterSetter();
		    	gs.setStateId(rs.getString("stateid"));
		          gs.setStateName(rs.getString("states"));
		          statelist.add(gs);
		    }
		return statelist;
		
	}


	public  ArrayList<GetterSetter> jsonCity(String state) throws SQLException {
		
      sql = "select CityId , Cities from cities where StateId = "+state+"";		
      
		System.out.println(sql);
		   ArrayList<GetterSetter> statelist = new ArrayList<GetterSetter>();
		    Connection con =  DBConnection.getDBconnection();
	        Statement stmt =  con.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    GetterSetter gs =null;
		    while(rs.next()) {     
		    	gs= new GetterSetter();
		    	gs.setCityId(rs.getString("CityId"));
		          gs.setCityName(rs.getString("Cities"));
		          statelist.add(gs);
		    }
		return statelist;
		
	}
	
	
	/*
	 * public static void main(String arg[]) {
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * GetterSetter obj = new GetterSetter(); obj.setCityId("c1");
	 * obj.setCityName("abc");
	 * 
	 * 
	 * 
	 * try { String json = objectMapper.writeValueAsString(obj);
	 * System.out.println(json); }catch(Exception e) { e.printStackTrace(); } }
	 */
	/*	ArrayList<String> testList = null; 
		testList=new ArrayList<String>();
		testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");		//testList.add("xyz");
		testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");testList.add("abc");		//testList.add("xyz");
		try {
		System.out.println(getCapacity(testList)+"size:"+testList.size()); 
		}catch(Exception e) {;
			e.printStackTrace();
		}

				System.out.println(testList);
				
				GetterSetter obj = new GetterSetter();
				obj.setCityId("c1");
				obj.setCityName("abc");
				System.out.println(obj.toString());
				
				System.out.println("abc,xyz");
				
				
				String arr[] = "abc # xyz".split (" # ");
				System.out.println("arr:"+ Arrays.toString(arr) + "\narr length:"+arr.length);
				
				//testList = new Vector<String>();
				
				
	}
	
	static int getCapacity(ArrayList<?> l) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(l)).length;
    }
	
	*/

}
