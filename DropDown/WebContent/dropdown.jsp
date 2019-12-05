<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
    <%@page import="com.drop.GetterSetter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<style>

</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>
<script language="javascript">

function State(){
	var country = document.getElementById("country").value;
	//alert(country);
	if(country=="-1"){
		//alert("test");
		document.getElementById("state").options.length=0;
		document.getElementById("city").options.length=0;
	}
	else{
		location.href= '<%=request.getContextPath()%>/dropdown?country='+country;
		}
	
}
function City(){

	var country = document.getElementById("country").value;
    var state = document.getElementById("state").value;
    if(state=="-1"){
    document.getElementById("city").options.length=0;
	}
	else{
	location.href= '<%=request.getContextPath()%>/dropdown?country='+country+'&&state='+state;
}
}

 
function stateAjaxCall()  
{
	var country = document.getElementById("countryAjax").value;
	//alert(country);
	var ajaxUrl = "<%=request.getContextPath()%>/helper?country="+country+"&&cmd=get1";
	//alert(ajaxUrl);            
    var Result = getAjaxResult(ajaxUrl); //no prob
   // alert("result  "+Result);
	var ResultArray = Result.split(" || ");
	//alert(ResultArray);
	//alert(ResultArray.length);
	stateupdateComponent(ResultArray,'stateAjax','cityAjax');
}
function cityAjaxCall()   
{
	var state = document.getElementById("stateAjax").value;
	//alert(country);
	var ajaxUrl = "<%=request.getContextPath()%>/helper?state="+state+"&&cmd=get1";
	//alert(ajaxUrl);            
    var Result = getAjaxResult(ajaxUrl); //no prob
   // alert("result  "+Result);
	var ResultArray = Result.split(" || ");
	//alert(ResultArray);
	//alert(ResultArray.length);
	cityupdateComponent(ResultArray,'cityAjax');
}

function getAjaxResult(url) { 
	//alert(url);
	var oXMLHTTP = getHTTPObject();
	oXMLHTTP.open("GET", url, false);
//	oXMLHTTP.setRequestHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//	oXMLHTTP.setR equestHeader("Cache-Control", "post-check=0, pre-check=0");
//	oXMLHTTP.setRequestHeader("Pragma", "no-cache");
	oXMLHTTP.send("Firefox");
//	alert("1: "+ oXMLHTTP.responseText);
	return oXMLHTTP.responseText;
}

function getHTTPObject() {
	var xmlhttp;
	//alert("getHTTPObject");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		//isIE = "true";
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	return xmlhttp;
}

function stateupdateComponent(IdDescArray,componentName,componentNameTwo)
{

	//alert(IdDescArray+"  "+componentName);
	var returnArray = [];
	document.getElementById(componentNameTwo).options.length=0;  //clear the city list
	document.getElementById(componentName).options.length=0;     //clear the state list and updating below in loop
	var optionlist=document.createElement("option");
	//alert(IdDescArray.length-1);
	document.getElementById(componentName).options.add(optionlist);
	optionlist.value="-1";
	optionlist.innerHTML="Select Any";

	if(IdDescArray.length > 0)
	{
		for(var i=0;i<IdDescArray.length-1;i++)
		{
		if(IdDescArray[i].indexOf(" # ") != -1)
		{
			returnArray = IdDescArray[i].split(" # ");
		//	alert( returnArray[0]+" - "+returnArray[1]);
			optionlist=document.createElement("option");   //<option value="indaia">india</option>
			document.getElementById(componentName).options.add(optionlist);
			optionlist.value=returnArray[0];
			optionlist.innerHTML=returnArray[1];
			//optionlist.setAttribute("title", returnArray[0]+" - "+returnArray[1] );
		}// end if
		} // end for

	} //end if
}

function cityupdateComponent(IdDescArray,componentName)
{

	//alert(IdDescArray+"  "+componentName);
	var returnArray = [];
	//document.getElementById(componentNameTwo).options.length=0;
	document.getElementById(componentName).options.length=0;  //clear the city list and updating in the loop below
	var optionlist=document.createElement("option");
	//alert(IdDescArray.length-1);
	document.getElementById(componentName).options.add(optionlist);
	optionlist.value="-1";
	optionlist.innerHTML="Select Any";

	if(IdDescArray.length > 0)
	{
		for(var i=0;i<IdDescArray.length-1;i++)
		{
		if(IdDescArray[i].indexOf(" # ") != -1)
		{
			returnArray = IdDescArray[i].split(" # ");
		//	alert( returnArray[0]+" - "+returnArray[1]);
			optionlist=document.createElement("option");   //<option value="indaia">india</option>
			document.getElementById(componentName).options.add(optionlist);
			optionlist.value=returnArray[0];
			optionlist.innerHTML=returnArray[1];
			//optionlist.setAttribute("title", returnArray[0]+" - "+returnArray[1] );
		}// end if
		} // end for

	} //end if
}

function jsonState(){
	var country = document.getElementById("countryJson").value;
	
	//alert(country);
	var url = "<%=request.getContextPath()%>/jsonhelper?country="+country+"&cmd=get2";
	//alert(url);
	var result= getAjaxResult(url); 
	
	//alert("Value:"+result);
	var obj = JSON.parse(result);
	//alert("check");
	jsonStatePopulate(obj);
}

function jsonStatePopulate(obj){
	//alert(obj);
	document.getElementById("stateJson").options.length=0;
	document.getElementById("cityJson").options.length=0;
	
	var optionlist=document.createElement("option");
	document.getElementById("stateJson").options.add(optionlist);
	optionlist.value="-1";
	optionlist.innerHTML="Select Any";
	var text="";
	   
	if(obj.length > 0){
	for (var i = 0; i < obj.length; i++) {
		 var innerobj = obj[i];
		 optionlist=document.createElement("option");
		 document.getElementById("stateJson").options.add(optionlist);
			optionlist.value=innerobj.stateId;
			optionlist.innerHTML=innerobj.stateName 
		 
	  }
}
}

function jsonCity(){
//	alert("ttt");
	var state = document.getElementById("stateJson").value;
	var url = "<%=request.getContextPath()%>/jsonhelper?state="+state+"&cmd=get2";
	
	var result= getAjaxResult(url); 
	alert(result);
	
	//alert("check");
	var obj = JSON.parse(result);
	//alert(obj);
	jsonCityPopulate(obj);
}

function jsonCityPopulate(obj){
	//alert(obj);
	
	document.getElementById("cityJson").options.length=0; 
	var optionlist=document.createElement("option");
	document.getElementById("cityJson").options.add(optionlist);
	optionlist.value="-1";
	optionlist.innerHTML="Select Any";
	var text="";
	   
	if(obj.length > 0){
	for (var i = 0; i < obj.length; i++) {
		 var innerobj = obj[i];
		// alert(innerobj.)
		 optionlist=document.createElement("option");
		 document.getElementById("cityJson").options.add(optionlist);
			optionlist.value=innerobj.cityId;
			optionlist.innerHTML=innerobj.cityName 
		 
	  }
}
	
}

</script>
<body>

<%ArrayList<GetterSetter> countrylist = (ArrayList<GetterSetter>)request.getAttribute("countrylist"); %>
<%ArrayList<GetterSetter> statelist = (ArrayList<GetterSetter>)request.getAttribute("statelist"); %>
<%ArrayList<GetterSetter> citylist = (ArrayList<GetterSetter>)request.getAttribute("citylist"); %>

<%String selcountry =(String)request.getAttribute("selcountry"); %>
<%String selstate =(String)request.getAttribute("selstate"); %>
<h1>Dropdown   </h1>


<form name="country" method= "get" >
<table border=1  position="absoute">
<tr><td colspan="3">
<h2>NORMAL SERVLET RELOADING method</h2>
</td></tr>
<tr> 
<td>

Country : 
<select id = "country" onchange ="State()">
<option value = "-1"> Select Any</option>
<%
if(countrylist!=null){ 
	String flag = "selected"; 
	for(GetterSetter gs : countrylist){  	
		if(gs.getCountryId()!=null && gs.getCountryId().equalsIgnoreCase(selcountry)){
			flag="selected";
		}else{
			flag ="";
		}
%>
<option value = "<%=gs.getCountryId() %>" <%=flag%> > <%=gs.getCountryName()%></option>
 	
<%}} %>  
</select></td>


<td>
State :<select id = "state" onchange="City()">
<option value="-1" > Select Any</option>
<%
if(statelist!=null){
	String flag="selected";
for(GetterSetter gs : statelist ){
	if(gs.getStateId()!=null && gs.getStateId().equalsIgnoreCase(selstate)){
		flag="selected";
	}else{
		flag ="";
	}
%>

<option value = <%=gs.getStateId()%> <%=flag %> > <%=gs.getStateName() %></option>
 	
<%} }%>  
</select></td>

<td>
City :<select id = "city" >
<option value="-1"> Select Any</option>
<%
if(citylist!=null){
for(GetterSetter gs : citylist ){%>

<option value = <%=gs.getCityId() %> > <%=gs.getCityName() %></option>
 	
<%} }%>  
</select></td>



</tr>


<!-- ==============================================================   -->



















<tr><td colspan="3">
 <h2>AJAX method:</h2>  
 </td></tr>
 
<tr><td>
Country : 
<select id = "countryAjax" onchange =  "stateAjaxCall()">
<option value = "-1"> Select Any</option>
<%
if(countrylist!=null){ 
         String flag ="selected";
	for(GetterSetter gs : countrylist){  %>
	
<option value = "<%=gs.getCountryId() %>" > <%=gs.getCountryName()%></option>
 	
<%}} %> 
</select></td>
<td>
State :
<select id = "stateAjax" onchange="cityAjaxCall()"></select>

</td>


<td>
City :
<select id = "cityAjax"></select>


</td></tr>








<!-- ==============================================================   -->



ss

<tr ><td colspan=3>
<h2>SENDING DATA IN JSON FORMAT in AJAX
</h2>
</td></tr>

<tr> 
<td>

Country : 
<select id = "countryJson" onchange ="jsonState()">
<option value="-1"> Select Any</option>
<%
if(countrylist!=null){ 
	String flag = "selected"; 
	for(GetterSetter gs : countrylist){  	
		
%>
<option value = "<%=gs.getCountryId() %>" > <%=gs.getCountryName()%></option>
 	
<%}} %>  
</select></td>


<td>
State :<select id = "stateJson" onchange="jsonCity()">
 s</select></td>

<td>
City :<select id = "cityJson" >  
</select></td>



</tr>


</table>
</form>
<p id="demo"></p>
</body>
</html>