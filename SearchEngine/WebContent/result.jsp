<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.LinkedHashMap"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
<link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="page-header" id="banner">
<h3><font color="#003D4C">Results for <%=(String)request.getAttribute("query") %>:</font></h3>
</div>
<div>
<table class="table table-striped table-hover " width=400>
	<tbody>
	<%
	LinkedHashMap<String,Double> result= (LinkedHashMap)request.getAttribute("result");
	if(result.size()==0){
		%><p>Oops...Your search returned no results.</p><%
	}
	else{
		%><p>Your search returned <%= result.size()%> results.</p><%
	for(String str: result.keySet()){%>
		<tr><td><a href="/SearchEngine/FileExtractor?fileName=<%= str%>"/><%= str%></td></tr>
	<%}}%>
	</tbody>
</table>
</div>
</div>
</body>
</html>